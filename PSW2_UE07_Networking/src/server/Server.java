package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static utils.Constants.*;

public class Server {

    public volatile boolean terminate = false;
    private final ServerSocket server;

    public Server() throws IOException {
        server = new ServerSocket(PORT);
    }

    public void start() throws IOException {
        System.out.println("Server started on port " + PORT);

        while (!terminate) {
            try {
                System.out.println("Server waiting to accept client");
                Socket clientSocket = server.accept();
                System.out.println("Server accepted client");
                new Thread(new ClientHandler(clientSocket)).start();
            } catch (SocketException e) {
                System.out.println("Server closed with " + server);
            }
        }
    }

    public void terminate() throws IOException {
        terminate = true;
        server.close();
    }

    private class ClientHandler implements Runnable {

        private final Socket socket;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (PrintWriter out = new PrintWriter(socket.getOutputStream());
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                ArrayList<String> lines = new ArrayList<>();
                BufferedWriter writer;

                // #1 send hello to client
                send(out, HELO_FROM + server);

                // #2 receive LOGIN data from client
                String msg = receive(in);
                if (!msg.startsWith(LOGIN)) {
                    System.out.println(LOGIN + "expected but received " + msg);
                    return;
                }

                // #3 send LOGIN acception to client
                String clientName = msg.substring(6);
                send(out, OK_LOGIN + clientName);

                // #4 receive START_SYNC
                msg = receive(in);
                if(!msg.startsWith(START_SYNC)) {
                    System.out.println(START_SYNC + "expected but received " + msg);
                }

                // #5 send OK_START_SYNC
                send(out, OK_START_SYNC);

                // #6 receive lines of file
                msg = receive(in);

                // check if current sync run does have any new commands
                if (!msg.startsWith(NO_COMMANDS)) {
                    while (!msg.startsWith(DONE_COMMANDS)) {
                        // add read lines into a List, skip EOF
                        if (!msg.startsWith(EOF)) lines.add(msg);

                        // receive the next line
                        msg = receive(in);
                        // found a full file to write to server now
                        if (msg.startsWith(EOF)) {
                            Path filePath = Path.of(lines.get(0));

                            // Handle Modify, Create if file content != DELETE flag
                            if (!lines.get(1).equals(DELETE)) {
                                Files.deleteIfExists(filePath);
                                Path f = Files.createFile(filePath);
                                writer = new BufferedWriter(new FileWriter(String.valueOf(f), true));

                                // write read file content from client into new file on server
                                for (String line : lines) {
                                    if (!line.equals(lines.get(0))) {
                                        line = line + LF;
                                        writer.append(line);
                                    }
                                }
                                writer.close();
                                lines.clear();
                            } else {
                                // Handle Delete event
                                Files.deleteIfExists(filePath);
                                lines.clear();
                            }
                        }
                    }
                }

                // DONE syncing, start goodbye dialogue
                send(out, OK_DONE_COMMANDS);

                msg = receive(in);
                if (!msg.startsWith(END_SYNC)) {
                    System.out.println(END_SYNC + " expected but received " + msg);
                    return;
                }

                send(out, OK_END_SYNC);

                msg = receive(in);
                if (!msg.startsWith(DONE)) {
                    System.out.println(DONE + " expected but received " + msg);
                    return;
                }
                send(out, BYE);

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import static utils.Constants.*;

public class Client {

    public static Client create(String name) {
        return new Client(name);
    }

    private final String name;

    private Client(String name) {
        super();
        this.name = name;
    }

    public void start(Map<String, List<String>> serverCommands) throws IOException {
        communicate(serverCommands);
    }

    private void communicate(Map<String, List<String>> serverCommands) {
        try (Socket socket = new Socket(SERVER, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream())) {

            // #1 receive hello from server
            String reply = receive(in);
            if (!reply.startsWith(HELO_FROM)) {
                System.out.println(HELO_FROM +" expected but received " + reply);
                return;
            }

            // #2 send Login data to server
            send(out, LOGIN + name);

            // #3 receive LOGIN accepted from server
            reply = receive(in);
            if (!reply.startsWith(OK_LOGIN)) {
                System.out.println(OK_LOGIN + " expected but received " + reply);
                return;
            }

            // #4 send out START_SYNC
            send(out, START_SYNC);

            // #5 receive OK_START_SYNC
            reply = receive(in);
            if (!reply.startsWith(OK_START_SYNC)) {
                System.out.println(OK_START_SYNC + " expected but received " + reply);
                return;
            }

            // #6 send out file lines
            if (!serverCommands.isEmpty()) {
                serverCommands.forEach((x, y) -> {
                    try {
                        send(out, x);
                        for (String l : y) {
                            send(out, l);
                        }
                        send(out, EOF);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                send(out, DONE_COMMANDS);
            } else {
                send(out, NO_COMMANDS);
            }

            // #7 send OK_DONE_COMMANDS
            reply = receive(in);
            if (!reply.startsWith(OK_DONE_COMMANDS)) {
                System.out.println(OK_DONE_COMMANDS + " expected but received " + reply);
                return;
            }

            // DONE syncing, start goodbye dialogue
            send(out, END_SYNC);

            reply = receive(in);
            if (!reply.startsWith(OK_END_SYNC)) {
                System.out.println(OK_END_SYNC + " expected but received " + reply);
                return;
            }

            send(out, DONE);

            reply = receive(in);
            if (!reply.startsWith(BYE)) {
                System.out.println(BYE + " expected but received " + reply);
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

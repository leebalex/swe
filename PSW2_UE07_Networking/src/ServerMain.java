import inout.In;
import server.Server;

import java.io.IOException;

public class ServerMain {

    public static void main(String[] args) throws IOException {
        System.out.println("Server starting");

        Server server = new Server();
        new Thread(() -> {
            try {
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("Server started, terminate with enter.");
        In.readLine();

        server.terminate();
        System.out.println("Server terminated");

    }
}

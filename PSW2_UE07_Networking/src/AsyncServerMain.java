import inout.In;
import server.AsyncServer;

import java.io.IOException;

public class AsyncServerMain {

    public static void main(String[] args) throws IOException {

        System.out.println("AsyncServer starting");
        AsyncServer server = new AsyncServer();
        new Thread(() -> {
            try {
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("AsyncServer started");

        In.readLine();

        server.terminate();
        System.out.println("AsyncServer terminated");

    }
}

import client.Client;
import client.FileSafeService;
import inout.In;

import java.io.IOException;


public class ClientMain {

    private final static String SRC_DIR = "source";
    private final static String DEST_DIR = "dest";

    public static void main(String[] args) throws IOException, InterruptedException {

        FileSafeService service = null;
        System.out.println("");
        try {
            service = FileSafeService.start(SRC_DIR, DEST_DIR);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (;;) {
            System.out.println("Press x to quit, press any other char to check for planned events");
            if (service != null) {
                char c = In.readChar();
                if (c == 'x') {
                    System.out.println("TERMINATING");
                    service.stopFileWatcher();
                    break;
                } else {
                    service.getInfoStore().readMapState();
                }
            }
        }
    }
}

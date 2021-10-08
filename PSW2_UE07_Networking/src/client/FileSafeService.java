package client;

import utils.Constants;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.file.StandardWatchEventKinds.*;

public class FileSafeService {

    private static final int SAVE_START = 2;
    private static final int SAVE_INTERVAL = 10;

    private static final Logger logger = Logger.getLogger("fileSafe");
    private static final String FILES_GLOB = "glob:**.{java,xml,txt}";
    private static WatchService watchService;

    private final Path dest;
    private final SaveInfoRegistry infoStore;
    private final PathMatcher matcher;
    private final Thread fileWatcherThread;
    private volatile boolean fileWatcherStopped = false;
    private final FileSaverRunnable saver;
    private final ScheduledExecutorService saverExecutor;

    private final static Map<String, List<String>> serverCommands = new HashMap<>();

    public static FileSafeService start(String src, String dest) throws IOException {
        return new FileSafeService(Paths.get(src), Paths.get(dest));
    }

    public FileSafeService(Path src, Path dest) throws IOException {
        super();
        this.dest = dest;
        if (!Files.exists(src)) Files.createDirectory(src);
        if (!Files.exists(dest)) Files.createDirectory(dest);

        this.infoStore = new SaveInfoRegistry();
        this.matcher = FileSystems.getDefault().getPathMatcher(FILES_GLOB);
        this.saver = new FileSaverRunnable();
        this.saverExecutor = Executors.newScheduledThreadPool(1);

        setupWatchService(src);
        this.fileWatcherThread = new Thread(() -> {
            WatchKey key = null;
            while (!fileWatcherStopped) {
                try {
                    key = watchService.take();
                    for (WatchEvent<?> evt : key.pollEvents()) {
                        WatchEvent<Path> pathEvt = (WatchEvent<Path>) evt;
                        Path relPath = pathEvt.context();
                        Path dirPath = (Path) key.watchable();
                        Path absPath = dirPath.resolve(relPath);

                        if (matcher.matches(relPath)) { // only handle defined filetypes
                            infoStore.putSaveInfo(absPath, pathEvt);
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    if (key != null)
                        key.reset(); // reset key after handling event, otherwise no more events are signaled
                }
            }
        });
        fileWatcherThread.start();
        this.saverExecutor.scheduleAtFixedRate(saver, SAVE_START, SAVE_INTERVAL, TimeUnit.SECONDS);
    }

    private static void setupWatchService(Path src) throws IOException {
        watchService = FileSystems.getDefault().newWatchService();
        if (Files.isDirectory(src)) {
            try {
                src.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
            } catch (Exception e) {
                throw new IOException("Failed registering watchService");
            }
        }
    }

    // interrupts fileWatcherThread, this thread will then run into ThreadInterruptedException and stop itself
    // also shuts down the Scheduled Executor thread
    public void stopFileWatcher() {
        fileWatcherStopped = true;
        fileWatcherThread.interrupt();
        saverExecutor.shutdown();
    }

    public Path getDest() {
        return dest;
    }

    public SaveInfoRegistry getInfoStore() {
        return infoStore;
    }

    private class FileSaverRunnable implements Runnable {

        @Override
        public void run() {
            logger.log(Level.INFO, "Running sync...");
            SaveInfo[] infos = infoStore.getAllInfos();
            //TODO: interpret these infos as suitable messages for server
            boolean modifyHandled = false;
            List<String> fileList;

            // iterates over all saveInfos and handling their events
            for (SaveInfo info : infos) {
                for (WatchEvent<Path> event : info.getEvents()) {
                    Path dest = getDest().resolve(info.getSrcPath().getFileName());
                    if (event.kind() == ENTRY_CREATE) {
                        logger.log(Level.INFO, "File was created, now copying SRC " + info.getSrcPath() + " to DEST " + dest);
                        try {
                            // read in lines from source file and write them into a list for serverCommands
                            fileList = Files.readAllLines(info.getSrcPath());
                            serverCommands.put(dest.toString(),  fileList);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // ignore upcoming modify events when a new file was created
                        modifyHandled = true;
                    }
                    else if (event.kind() == ENTRY_MODIFY && !modifyHandled) {
                        logger.log(Level.INFO, "File was modified, now copying SRC " + info.getSrcPath() + " to DEST " + dest);
                        try {
                            // read in lines from source file and write them into a list for serverCommands
                            fileList = Files.readAllLines(info.getSrcPath());
                            serverCommands.put(dest.toString(), fileList);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // multiple MODIFY Events should be ignored, only handle first one
                        modifyHandled = true;
                    }
                    else if (event.kind() == ENTRY_DELETE) {
                        logger.log(Level.INFO, "File was deleted, now deleting DEST " + dest);
                        serverCommands.put(dest.toString(), List.of(Constants.DELETE));

                    }
                }
            }

            //### Starting a client for every sync run
            Client client = Client.create("Client");
            Thread clientThread = new Thread(() -> {
                try {
                    client.start(serverCommands);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            clientThread.start();
            try {
                clientThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // clear commands for the next sync run
            serverCommands.clear();
        }
    }
}

package client;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.concurrent.CopyOnWriteArrayList;

public class SaveInfo {

    private final Path srcPath;
    private final CopyOnWriteArrayList<WatchEvent<Path>> events;

    public SaveInfo(Path srcPath) {
        this.srcPath = srcPath;
        this.events = new CopyOnWriteArrayList<>();
    }

    public void addEvent(WatchEvent<Path> event) {
        events.add(event);
    }

    public Path getSrcPath() { return srcPath; }

    public CopyOnWriteArrayList<WatchEvent<Path>> getEvents() { return events; }

    @Override
    public String toString() {
        return "client.SaveInfo{" +
                "srcPath=" + srcPath +
                ", events=" + events +
                '}';
    }
}

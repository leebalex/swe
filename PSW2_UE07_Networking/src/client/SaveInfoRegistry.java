package client;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;

public class SaveInfoRegistry {

    private final Map<Path, SaveInfo> map = new HashMap<>();
    private final Lock lock = new ReentrantLock();

    public void putSaveInfo(Path path, WatchEvent<Path> event) {
        lock.lock();
        try {
            if (map.containsKey(path)) {
                // ENTRY DELETE event, all planed events are deleted
                if (event.kind() == ENTRY_DELETE) {
                    map.replace(path, new SaveInfo(path));
                }
                map.get(path).addEvent(event);
            } else {
                SaveInfo info = new SaveInfo(path);
                info.addEvent(event);
                map.put(path, info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public SaveInfo[] getAllInfos() {
        lock.lock();
        try {
            SaveInfo[] infos = map.values().toArray(new SaveInfo[map.size()]);
            map.clear();
            return infos;
        } catch (Exception e) {
            return new SaveInfo[0];
        } finally {
            lock.unlock();
        }
    }

    public void readMapState() {
        lock.lock();
        try {
            SaveInfo[] infos = map.values().toArray(new SaveInfo[map.size()]);
            for (SaveInfo info : infos) {
                System.out.println(info.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

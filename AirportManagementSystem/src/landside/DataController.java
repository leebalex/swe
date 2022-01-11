package landside;

import java.util.ArrayList;
import java.util.List;

public class DataController {
    private LandsideDataStorage dataStorage = new LandsideDataStorage();

    public List getLandsideData(){
        return dataStorage.getLandsideData();
    }
}

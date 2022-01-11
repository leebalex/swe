/*
 * @author Florian Engertsberger
 */

package landside;

import java.util.ArrayList;
import java.util.List;

public class DataController {
    private LandsideDataStorage dataStorage;

    public DataController(LandsideDataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    public List getLandsideData(){
        return dataStorage.getLandsideData();
    }
}

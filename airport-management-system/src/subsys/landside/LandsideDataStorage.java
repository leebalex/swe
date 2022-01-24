/*
 * @author Florian Engertsberger
 */

package subsys.landside;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LandsideDataStorage {
    private final List<String> landsideData;

    public LandsideDataStorage() {
        this.landsideData = new ArrayList<>();
    }

    public List<String> getLandsideData() {
        return Collections.unmodifiableList(landsideData);
    }

    public void setLandsideData(String data){
        this.landsideData.add(data);
    }
}

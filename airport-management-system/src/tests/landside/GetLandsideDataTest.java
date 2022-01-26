/*
 * @author Florian Engertsberger
 */

package landside;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subsys.landside.DataController;
import subsys.landside.LandsideDataStorage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetLandsideDataTest {
    LandsideDataStorage dataStorage;
    DataController controller;

    @BeforeEach
    void setUp() {
        this.dataStorage = new LandsideDataStorage();
        this.dataStorage.setLandsideData("TestData");
        this.dataStorage.setLandsideData("TestData1");
        this.dataStorage.setLandsideData("TestData2");
        this.dataStorage.setLandsideData("TestData3");
        this.controller = new DataController(dataStorage);
    }

    @Test
    @DisplayName("Should return Landside Data")
    void testGetLandsideData() {
        assertEquals("TestData", controller.getLandsideData().get(0),
                "The result should be the same");
        assertEquals("TestData1", controller.getLandsideData().get(0),
                "The result should be the same");
        assertEquals("TestData2", controller.getLandsideData().get(0),
                "The result should be the same");
        assertEquals("TestData3", controller.getLandsideData().get(0),
                "The result should be the same");
    }





}
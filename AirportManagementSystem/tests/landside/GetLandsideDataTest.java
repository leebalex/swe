/*
 * @author Florian Engertsberger
 */

package landside;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetLandsideDataTest {

    LandsideDataStorage dataStorage;
    DataController controller;
    List<String > testArraylist = new ArrayList<>();


    @BeforeEach
    void setUp() {
        this.controller = new DataController();
        this.dataStorage = new LandsideDataStorage();
        this.dataStorage.setLandsideData("TestData");
        this.testArraylist.add("TestData");
    }

    @Test
    @DisplayName("Should return Landside Data")
    void testGetLandsideData() {

        assertEquals(testArraylist, controller.getLandsideData(),
                "These two list should be the same");
    }





}
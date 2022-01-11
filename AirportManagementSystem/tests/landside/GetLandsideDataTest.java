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


    @BeforeEach
    void setUp() {
        this.dataStorage = new LandsideDataStorage();
        this.dataStorage.setLandsideData("TestData");
        this.controller = new DataController(dataStorage);
    }

    @Test
    @DisplayName("Should return Landside Data")
    void testGetLandsideData() {

        assertEquals("TestData", controller.getLandsideData().get(0),
                "These two list should be the same");
    }





}
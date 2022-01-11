/*
 * @author Florian Engertsberger
 */

package landside;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuelAirplaneTest {

    FuelTransport fuelTruck;
    FuelController controller;


    @BeforeEach
    void setUp() {
        this.controller = new FuelController();
        this.fuelTruck = new FuelTransport(new Location(1,1), new VehicleDriver(1, "John", "Doe", true, "Transporter"), 1, true, 200);
    }

    @Test
    @DisplayName("Should move the fuelTruck to the right location")
    void fuelAirplane() {

        assertEquals(true, controller.sendFuelTruck(new Location(2,2)),
                "Should be successful and return true");
    }
}

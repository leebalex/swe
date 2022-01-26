/*
 * @author Florian Engertsberger
 */

package landside;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subsys.landside.FuelController;
import subsys.landside.FuelTransport;
import subsys.landside.Location;
import subsys.landside.VehicleDriver;

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
    @DisplayName("Move Fuel Truck to the right Location")
    void fuelAirplane() {
        assertEquals(true, controller.sendFuelTruck(new Location(2,2)),
                "Should be successful and return true");
        assertEquals(true, controller.sendFuelTruck(new Location(3,4)),
                "Should be successful and return true");
        assertEquals(true, controller.sendFuelTruck(new Location(790,4.678)),
                "Should be successful and return true");
        assertEquals(true, controller.sendFuelTruck(new Location(3.67,4990)),
                "Should be successful and return true");
    }
}

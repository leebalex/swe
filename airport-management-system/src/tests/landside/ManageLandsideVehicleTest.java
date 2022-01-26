package landside;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import subsys.landside.Location;
import subsys.landside.Vehicle;
import subsys.landside.VehicleController;
import subsys.landside.VehicleDriver;

import java.util.ArrayList;
import java.util.List;

public class ManageLandsideVehicleTest {
    @BeforeEach
    void setUp() {
        List<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle1 = new Vehicle(new Location(1,1), new VehicleDriver(1, "John", "Doe", true, "Transporter"), 1, true);
        Vehicle vehicle2 = new Vehicle(new Location(2,2), new VehicleDriver(1, "Johnny", "Doe", true, "Staircase Transporter"), 1, true);
        Vehicle vehicle3 = new Vehicle(new Location(3,3), new VehicleDriver(1, "Jon", "Doe", true, "Transporter"), 1, true);
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
        VehicleController vehicleController = new VehicleController(vehicles);
    }

    @Test
    @DisplayName("Move Vehicle")
    void sendVehicleTest() {
        //assertEquals("TestData", controller.getLandsideData().get(0),"The result should be the same");
    }

    @Test
    @DisplayName("Get vehicle location")
    void testGetLandsideData() {
        //assertEquals("TestData", controller.getLandsideData().get(0),"The result should be the same");
    }
}

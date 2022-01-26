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

import static org.junit.jupiter.api.Assertions.*;

public class ManageLandsideVehicleTest {
    List<Vehicle> vehicles;
    Vehicle vehicle1;
    Vehicle vehicle2;
    Vehicle vehicle3;
    Vehicle vehicle4;

    VehicleController vehicleController;

    @BeforeEach
    void setUp() {
        vehicles = new ArrayList<>();
        vehicle1 = new Vehicle(new Location(1,1), new VehicleDriver(1, "John", "Doe", true, "Transporter"), 1, true);
        vehicle2 = new Vehicle(new Location(2,2), new VehicleDriver(2, "Johnny", "Doe", true, "Staircase Transporter"), 1, true);
        vehicle3 = new Vehicle(new Location(3,3), new VehicleDriver(3, "Jon", "Doe", true, "Transporter"), 1, true);
        vehicle4 = new Vehicle(new Location(4,4), new VehicleDriver(3, "Jane", "Doe", true, "Transporter"), 1, true);
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
        vehicleController = new VehicleController(vehicles);
    }

    @Test
    @DisplayName("Retrieve Activity Status")
    void setActivityStatus() {
        assertTrue(vehicleController.getActivityStatus(1));
        assertTrue(vehicleController.getActivityStatus(2));
        assertTrue(vehicleController.getActivityStatus(3));
        assertFalse(vehicleController.getActivityStatus(4));
    }

    @Test
    @DisplayName("Move Vehicle")
    void sendVehicleTest() {
        if(vehicleController.getVehicleById(1).isActive()) {
            assertEquals(new Location(1, 1), vehicleController.sendVehicle(1, 1, 1));
        }
        if(vehicleController.getVehicleById(2).isActive()) {
            assertEquals(new Location(2, 2), vehicleController.sendVehicle(2, 2, 2));
        }
        if(vehicleController.getVehicleById(3).isActive()) {
            assertEquals(new Location(3, 3), vehicleController.sendVehicle(3, 3, 3));
        }
        if(vehicleController.getVehicleById(4).isActive()) {
            assertEquals(new Location(4, 4), vehicleController.sendVehicle(4, 3, 3));
        }
    }

}

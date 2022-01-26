/*
 * @author Florian Engertsberger
 */

package subsys.landside;
import java.util.ArrayList;
import java.util.List;

public class VehicleController {

    private final List<Vehicle> vehicles;
    private final List<ParkingSpot> parkingSpots;


    public VehicleController(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        this.parkingSpots = new ArrayList<>();
    }


    private Vehicle getVehicleById(int id){
        return vehicles.stream().filter(vec -> vec.getId() == id).findAny().orElse(null);
    };

    public Location sendVehicle(int id, int lon, int lat){
        Vehicle vehicle = getVehicleById(id);
        Location newLocation = new Location(lon, lat);
        vehicle.setLocation(newLocation);
        return newLocation;
    }

    public boolean setActiveStatus(int id){
        Vehicle vehicle = getVehicleById(id);
        return vehicle.isActive();
    }
}

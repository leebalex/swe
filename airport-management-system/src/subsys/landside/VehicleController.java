/*
 * @author Florian Engertsberger
 */

package subsys.landside;
import java.util.List;

public class VehicleController {

    private final List<Vehicle> vehicles;


    public VehicleController(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }


    public Vehicle getVehicleById(int id){
        return vehicles.stream().filter(vec -> vec.getId() == id).findAny().orElse(null);
    };

    public Location sendVehicle(int id, int lon, int lat){
        Vehicle vehicle = getVehicleById(id);
        vehicle.moveToPosition(lat, lon);
        return vehicle.getLocation();
    }

    public boolean getActivityStatus(int id){
        Vehicle vehicle = getVehicleById(id);
        return vehicle.isActive();
    }

    public boolean setActivityStatus(int id, boolean status){
        Vehicle vehicle = getVehicleById(id);
        vehicle.setActive(status);
        return vehicle.isActive();
    }
}

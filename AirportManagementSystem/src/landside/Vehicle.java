/*
 * @author Florian Engertsberger
 */

package landside;

public class Vehicle {
    private Location location;
    private VehicleDriver vehicleDriver;
    private final int id;
    private boolean isActive;

    public Vehicle(Location location, VehicleDriver vehicleDriver ,int id, boolean isActive ) {
        this.location = location;
        this.vehicleDriver = vehicleDriver;
        this.id = id;
        this.isActive = isActive;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public VehicleDriver getVehicleDriver() {
        return vehicleDriver;
    }

    public void setVehicleDriver(VehicleDriver vehicleDriver) {
        this.vehicleDriver = vehicleDriver;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean moveToPosition(double latitude, double longitude){
        setLocation(new Location(latitude, longitude));
        return true;
    }
}

/*
 * @author Florian Engertsberger
 */

package subsys.landside;


public class CargoTransport extends Vehicle {
    private final int capacity;
    private int availableCapacity;

    public CargoTransport(Location location, VehicleDriver vehicleDriver, int id, boolean isActive, int capacity) {
        super(location, vehicleDriver, id, isActive);
        this.capacity = capacity;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }
}

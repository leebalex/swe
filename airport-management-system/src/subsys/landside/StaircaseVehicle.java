/*
 * @author Florian Engertsberger
 */

package subsys.landside;


public class StaircaseVehicle extends Vehicle {
    private final int maxHeight; //cm
    private final int minHeight; //cm
    public StaircaseVehicle(Location location, VehicleDriver vehicleDriver, int id, boolean isActive, int maxHeight, int minHeight) {
        super(location, vehicleDriver, id, isActive);
        this.maxHeight = maxHeight;
        this.minHeight = minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getMinHeight() {
        return minHeight;
    }
}

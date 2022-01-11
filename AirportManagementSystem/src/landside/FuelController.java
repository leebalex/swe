/*
 * @author Florian Engertsberger
 */

package landside;

public class FuelController {
    private FuelTransport fuelTruck;

    public FuelController() {
        this.fuelTruck = new FuelTransport(new Location(1,1), new VehicleDriver(1, "John", "Doe", true, "Transporter"), 1, true, 200);

    }

    public boolean sendFuelTruck(Location location){
        this.fuelTruck.moveToPosition(location.getLatitude(), location.getLongitude());
        return true;
    }
}

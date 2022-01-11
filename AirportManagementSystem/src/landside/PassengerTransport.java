/*
 * @author Florian Engertsberger
 */

package landside;

public class PassengerTransport extends Vehicle{
    private final int maxSeats;
    private int availableSeats;

    public PassengerTransport(Location location, VehicleDriver vehicleDriver, int id, boolean isActive, int maxSeats) {
        super(location, vehicleDriver, id, isActive);
        this.maxSeats = maxSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
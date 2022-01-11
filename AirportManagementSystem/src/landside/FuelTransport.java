package landside;

public class FuelTransport extends Vehicle{
    private final int maxFuelCapacity;
    private int availableFuel;

    public FuelTransport(Location location, VehicleDriver vehicleDriver, int id, boolean isActive, int maxFuelCapacity) {
        super(location, vehicleDriver, id, isActive);
        this.maxFuelCapacity = maxFuelCapacity;
    }

    public int getMaxFuelCapacity() {
        return maxFuelCapacity;
    }

    public int getAvailableFuel() {
        return availableFuel;
    }

    private void setAvailableFuel(int availableFuel) {
        this.availableFuel = availableFuel;
    }
}

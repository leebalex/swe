package landside;

public class ParkingSpot {
    public final Location location;
    public final int size;
    public final int id;
    private boolean available;

    public ParkingSpot(Location location, int id, int size, boolean available) {
        this.location = location;
        this.size = size;
        this.id = id;
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

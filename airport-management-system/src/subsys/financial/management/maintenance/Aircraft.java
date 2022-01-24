package subsys.financial.management.maintenance;

/**
 * @author Alexander Leeb, k11702617
 */
public class Aircraft {
	
	private int id;
	private boolean inUse;
	private String category;
	private int numSeats;
	
	public Aircraft(int id, boolean inUse, String category, int numSeats) {
		this.id = id;
		this.inUse = inUse;
		this.category = category;
		this.numSeats = numSeats;
	}

	public int getId() {
		return id;
	}

	public boolean isInUse() {
		return inUse;
	}

	public String getCategory() {
		return category;
	}

	public int getNumSeats() {
		return numSeats;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}

	@Override
	public String toString() {
		return "Aircraft [id=" + id + ", inUse=" + inUse + ", category=" + category + ", numSeats=" + numSeats + "]";
	}
}

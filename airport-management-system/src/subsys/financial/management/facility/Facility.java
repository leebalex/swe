package subsys.financial.management.facility;

/**
 * @author Alexander Leeb, k11702617
 */
public class Facility {
	
	private int id;
	private String address;
	private float rent;
	private String description;
	
	public Facility(int id, String address, float rent, String description) {
		this.id = id;
		this.address = address;
		this.rent = rent;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public float getRent() {
		return rent;
	}

	public String getDescription() {
		return description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setRent(float rent) {
		this.rent = rent;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

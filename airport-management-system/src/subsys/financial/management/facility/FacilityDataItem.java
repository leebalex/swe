package subsys.financial.management.facility;

import subsys.financial.utils.DataItem;

/**
 * @author Alexander Leeb, k11702617
 */
public class FacilityDataItem extends DataItem {
	
	private int id;
	private String address;
	private double rent;
	private String description;
	
	public FacilityDataItem(int id, String address, double rent, String description) {
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

	public double getRent() {
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

	public void setRent(double rent) {
		this.rent = rent;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "FacilityDataItem [id=" + id + ", address=" + address + ", rent=" + rent + ", description=" + description
				+ "]";
	}

	@Override
	public String toCSVString() {
		return id + ";" + address + ";" + rent + ";" + description + ";\n";
	}
}

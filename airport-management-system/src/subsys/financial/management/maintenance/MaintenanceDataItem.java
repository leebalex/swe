package subsys.financial.management.maintenance;

import java.util.Date;

import subsys.financial.utils.DataItem;

/**
 * @author Alexander Leeb, k11702617
 */
public class MaintenanceDataItem extends DataItem {
	
	private Aircraft aircraft;
	private String description;
	private Date due;
	
	public MaintenanceDataItem(Aircraft aircraft, String description, Date due) {
		this.aircraft = aircraft;
		this.description = description;
		this.due = due;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public String getDescription() {
		return description;
	}

	public Date getDue() {
		return due;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDue(Date due) {
		this.due = due;
	}
	
	@Override
	public String toString() {
		return "MaintenanceDataItem [aircraft=" + aircraft + ", description=" + description + ", due=" + due + "]";
	}

	@Override
	public String toCSVString() {
		return aircraft.getId() + ";" + aircraft.isInUse() + ";" + aircraft.getCategory() + ";" + aircraft.getNumSeats() + ";" + description + ";" + due + ";\n";
	}
}

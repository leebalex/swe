package subsys.financial.management.ticket;

import java.util.List;

import subsys.financial.utils.DataItem;

/**
 * @author Alexander Leeb, k11702617
 */
public class Flight extends DataItem {
	
	private int id;
	private String departureTime;
	private String arrivalTime;
	private int destinationAirportId;
	private String destinationAirportName;
	
	public Flight(int id, String departureTime, String arrivalTime, int destinationAirportId, String destinationAirportName) {
		this.id = id;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.destinationAirportId = destinationAirportId;
		this.destinationAirportName = destinationAirportName;
	}

	public int getId() {
		return id;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public int getDestinationAirportId() {
		return destinationAirportId;
	}

	public String getDestinationAirportName() {
		return destinationAirportName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setDestinationAirportId(int destinationAirportId) {
		this.destinationAirportId = destinationAirportId;
	}

	public void setDestinationAirportName(String destinationAirportName) {
		this.destinationAirportName = destinationAirportName;
	}
	
	@Override
	public String toString() {
		return "Flight [id=" + id + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime
				+ ", destinationAirportId=" + destinationAirportId + ", destinationAirportName="
				+ destinationAirportName + "]";
	}

	@Override
	public String toCSVString() {
		return id + ";" + departureTime + ";" + arrivalTime + ";" + destinationAirportId + ";" + destinationAirportName + ";\n";
	}
	
}

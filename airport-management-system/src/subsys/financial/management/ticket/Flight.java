package subsys.financial.management.ticket;

import java.util.List;

/**
 * @author Alexander Leeb, k11702617
 */
public class Flight {
	
	private int id;
	private String departureTime;
	private String arrivalTime;
	private int destinationAirportId;
	private String destinationAirportName;
	private List<subsys.financial.management.ticket.Ticket> registeredTickets;
	
	public Flight(int id, String departureTime, String arrivalTime, int destinationAirportId, String destinationAirportName, List<subsys.financial.management.ticket.Ticket> registeredTickets) {
		this.id = id;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.destinationAirportId = destinationAirportId;
		this.destinationAirportName = destinationAirportName;
		this.registeredTickets = registeredTickets;
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

	public List<subsys.financial.management.ticket.Ticket> getRegisteredTickets() {
		return registeredTickets;
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

	public void setRegisteredTickets(List<subsys.financial.management.ticket.Ticket> registeredTickets) {
		this.registeredTickets = registeredTickets;
	}
}

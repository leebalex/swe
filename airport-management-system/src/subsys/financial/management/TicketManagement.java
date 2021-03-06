package subsys.financial.management;

import java.io.IOException;
import java.util.List;

import subsys.financial.management.ticket.Flight;

/**
 * @author Alexander Leeb, k11702617
 */
public interface TicketManagement {
	
	/**
	 * reads out all registered flights
	 * @return List of Flight
	 */
	public List<Flight> readFlightData();
	
	/**
	 * Registers a flight by writing the data to the designated file
	 * The file flightdata.csv stores data about all registered/planned flights
	 * @param flight The flight to register
	 * @return true for successful registration
	 * @throws IOException 
	 */
	public boolean registerFlight(Flight flight) throws IOException;
	
	/**
	 * Unregisters a flight by deleting the data from designated file
	 * @param flightId The flightId to delete
	 * @return true for successful deletion
	 */
	public boolean unregisterFlight(int flightId);
}

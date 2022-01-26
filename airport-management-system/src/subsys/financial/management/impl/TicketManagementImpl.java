package subsys.financial.management.impl;

import java.io.IOException;
import java.util.List;

import subsys.financial.department.Department;
import subsys.financial.management.TicketManagement;
import subsys.financial.management.humanressources.Employee;
import subsys.financial.management.ticket.Flight;
import subsys.financial.utils.DataType;
import subsys.financial.utils.FileManager;

/**
 * @author Alexander Leeb, k11702617
 */
public class TicketManagementImpl extends Department implements TicketManagement {

	private final FileManager fm;
	
	public TicketManagementImpl(int id, String name, List<Employee> employees) {
		super(id, name, employees);
		fm = new FileManager();
	}

	@SuppressWarnings("unchecked")
	public List<Flight> readFlightData() {
		return (List<Flight>) fm.readFinancialData(DataType.FLIGHT);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean registerFlight(Flight flight) throws IOException {
		if (flight == null) throw new IllegalArgumentException("flight must not be null");
		List<Flight> flights = (List<Flight>) fm.readFinancialData(DataType.FLIGHT);
		if (flights.contains(flight)) {
			return false;
		}
		return fm.writeFlightData(flight);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean unregisterFlight(int flightId) {
		List<Flight> flights = (List<Flight>) fm.readFinancialData(DataType.FLIGHT);
		if (flights == null || !(flights.size() > 0)) throw new IllegalArgumentException("flight does not exist");
		flights.remove(flightId);
		try {
			FileManager.resetData(DataType.FLIGHT);
			for (Flight f : flights) {
				registerFlight(f);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
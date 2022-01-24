package subsys.financial.management.impl;

import java.util.List;

import subsys.financial.department.Department;
import subsys.financial.management.TicketManagement;
import subsys.financial.management.humanressources.Employee;
import subsys.financial.management.ticket.Flight;

/**
 * @author Alexander Leeb, k11702617
 */
public class TicketManagementImpl extends Department implements TicketManagement {

	public TicketManagementImpl(int id, String name, List<Employee> employees) {
		super(id, name, employees);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean registerFlight(Flight flight) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unregisterFlight(int flightId) {
		// TODO Auto-generated method stub
		return false;
	}

}
package subsys.financial.management.impl;

import java.util.List;

import subsys.financial.department.Department;
import subsys.financial.management.FacilityManagement;
import subsys.financial.management.facility.Facility;
import subsys.financial.management.humanressources.Employee;

/**
 * @author Alexander Leeb, k11702617
 */
public class FacilityManagementImpl extends Department implements FacilityManagement {

	public FacilityManagementImpl(int id, String name, List<Employee> employees) {
		super(id, name, employees);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean registerFacility(Facility facility) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unregisterFacility(int facilityId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float calculateFacilityCost(int facilityId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

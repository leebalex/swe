package subsys.financial.management.impl;

import java.io.IOException;
import java.util.List;

import subsys.financial.department.Department;
import subsys.financial.management.FacilityManagement;
import subsys.financial.management.facility.FacilityDataItem;
import subsys.financial.management.humanressources.Employee;
import subsys.financial.utils.DataType;
import subsys.financial.utils.FileManager;

/**
 * @author Alexander Leeb, k11702617
 */
public class FacilityManagementImpl extends Department implements FacilityManagement {
	
	private final FileManager fm;
	
	public FacilityManagementImpl(int id, String name, List<Employee> employees) {
		super(id, name, employees);
		fm = new FileManager();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FacilityDataItem> readFacilityData() {
		return (List<FacilityDataItem>) fm.readFinancialData(DataType.FACILITY);
	}

	@Override
	public boolean registerFacility(FacilityDataItem facility) throws IllegalArgumentException, IOException {
		if (facility == null) throw new IllegalArgumentException("facility must not be null");
		return fm.writeFacilityData(facility);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean unregisterFacility(int facilityId) throws IllegalArgumentException {
		List<FacilityDataItem> facilities = (List<FacilityDataItem>) fm.readFinancialData(DataType.FACILITY); 
		if (facilities == null || !(facilities.size() > 0)) throw new IllegalArgumentException("facilityId does not exist");
		facilities.remove(facilityId);
		try {
			fm.resetData(DataType.EMPLOYEES);
			for (FacilityDataItem f : facilities) {
				registerFacility(f);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public double calculateFacilityCost(int facilityId) throws IllegalArgumentException {
		List<FacilityDataItem> facilities = (List<FacilityDataItem>) fm.readFinancialData(DataType.FACILITY); 
		if (facilities == null || !(facilities.size() > 0)) throw new IllegalArgumentException("facilityId does not exist");
		FacilityDataItem f = facilities.get(facilityId);
		return f != null ? f.getRent() : 0.0;
	}
}

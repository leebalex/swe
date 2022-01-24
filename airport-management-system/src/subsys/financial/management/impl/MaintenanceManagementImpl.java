package subsys.financial.management.impl;

import java.io.IOException;
import java.util.List;

import subsys.financial.department.Department;
import subsys.financial.management.MaintenanceManagement;
import subsys.financial.management.humanressources.Employee;
import subsys.financial.management.humanressources.Employee;
import subsys.financial.management.maintenance.MaintenanceDataItem;
import subsys.financial.management.maintenance.MaterialDataItem;
import subsys.financial.utils.FileManager;

/**
 * @author Alexander Leeb, k11702617
 */
public class MaintenanceManagementImpl extends Department implements MaintenanceManagement {

	private FileManager fm;
	
	public MaintenanceManagementImpl(int id, String name, List<Employee> employees) {
		super(id, name, employees);
		this.fm = new FileManager();
	}

	@Override
	public List<MaintenanceDataItem> reportMaintenanceDemand() {
		return fm.readMaintenanceData();
	}

	@Override
	public List<MaterialDataItem> reportMaterialDemand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MaintenanceDataItem> reportEquipmentDemand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean writeMaintenanceData(List<MaintenanceDataItem> maintenanceDataItems) throws IllegalArgumentException, IOException {
		if (maintenanceDataItems == null) throw new IllegalArgumentException("maintenanceDataItems must not be null");
		return fm.writeMaintenanceDemand(maintenanceDataItems);
	}

}
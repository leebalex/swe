package subsys.financial.management.impl;

import java.io.IOException;
import java.util.List;

import subsys.financial.department.Department;
import subsys.financial.management.MaintenanceManagement;
import subsys.financial.management.humanressources.Employee;
import subsys.financial.management.maintenance.MaintenanceDataItem;
import subsys.financial.management.maintenance.MaterialDataItem;
import subsys.financial.utils.DataType;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MaintenanceDataItem> readMaintenanceData() {
		return (List<MaintenanceDataItem>) fm.readFinancialData(DataType.MAINTENANCE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialDataItem> readMaterialData() {
		return (List<MaterialDataItem>) fm.readFinancialData(DataType.MATERIAL);
	}

	@Override
	public boolean writeMaintenanceData(List<MaintenanceDataItem> maintenanceDataItems) throws IllegalArgumentException, IOException {
		if (maintenanceDataItems == null) throw new IllegalArgumentException("maintenanceDataItems must not be null");
		return fm.writeFinancialData(DataType.MAINTENANCE, maintenanceDataItems);
	}

	@Override
	public boolean writeMaterialData(List<MaterialDataItem> materialDataItems) throws IllegalArgumentException, IOException {
		if (materialDataItems == null) throw new IllegalArgumentException("materialDataItems must not be null");
		return fm.writeFinancialData(DataType.MATERIAL, materialDataItems);
	}

}
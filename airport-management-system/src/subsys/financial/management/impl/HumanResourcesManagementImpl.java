package subsys.financial.management.impl;

import java.io.IOException;
import java.util.List;

import subsys.financial.department.Department;
import subsys.financial.management.HumanResourcesManagement;
import subsys.financial.management.humanressources.Employee;
import subsys.financial.utils.FileManager;

/**
 * @author Alexander Leeb, k11702617
 */
public class HumanResourcesManagementImpl extends Department implements HumanResourcesManagement {

	private final FileManager fm;
	
	public HumanResourcesManagementImpl(int id, String name, List<Employee> employees) {
		super(id, name, employees);
		fm = new FileManager();
	}

	@Override
	public boolean registerEmployee(Employee employee) throws IllegalArgumentException, IOException {
		if (employee == null) throw new IllegalArgumentException("employee must not be null");
		return fm.writeEmployeeData(employee);
	}

	@Override
	public boolean unregisterEmployee(int employeeId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float calculateSalary(int employeeId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}
}

package subsys.financial.management.impl;

import java.io.IOException;
import java.util.List;

import subsys.financial.department.Department;
import subsys.financial.management.HumanResourcesManagement;
import subsys.financial.management.humanressources.Employee;
import subsys.financial.utils.DataType;
import subsys.financial.utils.FileManager;

/**
 * @author Alexander Leeb, k11702617
 */
public class HumanResourcesManagementImpl extends Department implements HumanResourcesManagement {

	private static double RANK1_BASE_SALARY = 1764.55;
	private static double RANK2_BASE_SALARY = 2155.31;
	private static double RANK3_BASE_SALARY = 2899.64;
	
	private final FileManager fm;

	public HumanResourcesManagementImpl(int id, String name, List<Employee> employees) {
		super(id, name, employees);
		fm = new FileManager();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> readEmployeeData() {
		return (List<Employee>) fm.readFinancialData(DataType.EMPLOYEES);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean registerEmployee(Employee employee) throws IllegalArgumentException, IOException {
		if (employee == null) throw new IllegalArgumentException("employee must not be null");
		List<Employee> employees = (List<Employee>) fm.readFinancialData(DataType.EMPLOYEES);
		if (employees.contains(employee)) {
			return false;
		}
		return fm.writeEmployeeData(employee);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean unregisterEmployee(int employeeId) throws IllegalArgumentException {
		List<Employee> employees = (List<Employee>) fm.readFinancialData(DataType.EMPLOYEES);
		if (employees == null || !(employees.size() > 0)) throw new IllegalArgumentException("employeeId does not exist");
		employees.remove(employeeId);
		try {
			FileManager.resetData(DataType.EMPLOYEES);
			for (Employee e : employees) {
				registerEmployee(e);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public double calculateSalary(int employeeId) throws IllegalArgumentException {
		List<Employee> employees = (List<Employee>) fm.readFinancialData(DataType.EMPLOYEES);
		if (employees == null || !(employees.size() > 0)) throw new IllegalArgumentException("employeeId does not exist");
		Employee e = employees.get(employeeId);
		if (e.getRank() == 1) {
			return RANK1_BASE_SALARY;
		} else if (e.getRank() == 2) {
			return RANK2_BASE_SALARY;
		} else {
			return RANK3_BASE_SALARY;
		}
	}
}

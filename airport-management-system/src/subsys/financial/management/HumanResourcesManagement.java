package subsys.financial.management;

import java.io.IOException;

import subsys.financial.management.humanressources.Employee;

/**
 * @author Alexander Leeb, k11702617
 */
public interface HumanResourcesManagement {
	
	/**
	 * Registers a new employee for the terminal by writing employee data to designated file
	 * The file employeedata.csv stores data for employees like name, department, rank, etc.
	 * @param employee The employee to register
	 * @returns true for successful registration
	 * @throws IllegalArgumentException if parameter is null
	 */
	public boolean registerEmployee(Employee employee) throws IllegalArgumentException, IOException;
	
	/**
	 * Unregisters a existing employee by deleting employee data from designated file
	 * @param employeeId The employeeId of the employee to delete
	 * @returns true for successful deletion
	 * @throws IllegalArgumentException if employeeId is not registered
	 */
	public boolean unregisterEmployee(int employeeId) throws IllegalArgumentException;
	
	/**
	 * Calculates the salary of a specific employee be looking at department and rank
	 * @param employeeId The employeeId of the employee to calculate the salary for
	 * @returns float salary
	 * @throws IllegalArgumentException if employeeId is not registered
	 */
	public float calculateSalary(int employeeId) throws IllegalArgumentException;
}

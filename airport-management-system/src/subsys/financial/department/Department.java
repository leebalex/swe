package subsys.financial.department;

import subsys.financial.management.humanressources.Employee;

import java.util.List;

/**
 * @author Alexander Leeb, k11702617
 */
public abstract class Department {
	private int id;
	private String name;
	private List<Employee> employees;
	
	public Department(int id, String name, List<Employee> employees) {
		this.id = id;
		this.name = name;
		this.employees = employees;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}

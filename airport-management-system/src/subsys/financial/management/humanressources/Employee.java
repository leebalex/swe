package subsys.financial.management.humanressources;

import subsys.financial.utils.DataItem;

/**
 * @author Alexander Leeb, k11702617
 */
public class Employee extends DataItem {
	private int id;
	private boolean active;
	private String name;
	private String surname;
	private String departmentId;
	private int rank;
	
	public Employee(int id, boolean active, String name, String surname, String departmentId, int rank) {
		this.id = id;
		this.active = active;
		this.name = name;
		this.surname = surname;
		this.departmentId = departmentId;
		this.rank = rank;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getDepartmentId() {
		return departmentId;
	}
	
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", active=" + active + ", name=" + name + ", surname=" + surname
				+ ", departmentId=" + departmentId + ", rank=" + rank + "]";
	}

	@Override
	public String toCSVString() {
		return this.id + ";" + this.active + ";" + this.name + ";" + this.surname + ";" + this.departmentId + ";" + this.rank + ";\n";
	}
	
}

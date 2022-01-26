package subsys.financial.utils;

public enum DataType {
	EMPLOYEES("resources/employeedata.csv"),
	MAINTENANCE("resources/maintenancedata.csv"),
	MATERIAL("resources/materialdata.csv"),
	FACILITY("resources/facilitydata.csv"),
	FLIGHT("resources/flightdata.csv");
	
	private final String path;
	
	private DataType(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
}
package subsys.financial.utils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import subsys.financial.management.humanressources.Employee;
import subsys.financial.management.maintenance.Aircraft;
import subsys.financial.management.maintenance.MaintenanceDataItem;
import subsys.financial.management.maintenance.MaintenanceDataItem;

/**
 * @author Alexander Leeb, k11702617
 */
public class FileManager {
	
	public boolean writeMaintenanceDemand(List<MaintenanceDataItem> maintenanceDataItems) throws IOException {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("resources/maintenancedata.csv");
			
			for (MaintenanceDataItem di : maintenanceDataItems) {
				fileWriter.write(di.toCSVString());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fileWriter.close();
		}
		return true;
	}
	
	public List<MaintenanceDataItem> readMaintenanceData() {
		List<MaintenanceDataItem> maintenanceDataItems = new ArrayList<>();
		Path pathToFile = Paths.get("resources/maintenancedata.csv");
		
		try (BufferedReader br = Files.newBufferedReader(pathToFile)) {
			String line = br.readLine();
			while (line != null) {
				String[] values = line.split(";");
				Aircraft ac = new Aircraft(Integer.parseInt(values[0]), Boolean.getBoolean(values[1]), values[2], Integer.parseInt(values[3]));
				maintenanceDataItems.add(new MaintenanceDataItem(ac, values[4], Date.valueOf(values[5])));
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return maintenanceDataItems;
	}
	
	public boolean writeEmployeeData(Employee employee) throws IOException {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("resources/employeedata.csv");
			fileWriter.write(employee.toCSVString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fileWriter.close();
		}
		return true;
	}
}
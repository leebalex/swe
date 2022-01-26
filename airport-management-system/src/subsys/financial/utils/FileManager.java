package subsys.financial.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import subsys.financial.management.facility.FacilityDataItem;
import subsys.financial.management.humanressources.Employee;
import subsys.financial.management.maintenance.Aircraft;
import subsys.financial.management.maintenance.MaintenanceDataItem;
import subsys.financial.management.maintenance.Material;
import subsys.financial.management.maintenance.MaterialDataItem;
import subsys.financial.management.ticket.Flight;

/**
 * @author Alexander Leeb, k11702617
 */
public class FileManager {
	
	public List<? extends DataItem> readFinancialData(DataType dataType) {
		List<DataItem> dataItems = new ArrayList<>();
		Path pathToFile = Paths.get(dataType.getPath());
		
		try (BufferedReader br = Files.newBufferedReader(pathToFile)) {
			String line = br.readLine();
			while (line != null) {
				if (line.length() > 0) {
					String[] values = line.split(";");
					dataItems.add(parseDataItem(dataType, values));
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataItems;
	}
	
	public boolean writeFinancialData(DataType dataType, List<? extends DataItem> dataItems) throws IOException {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(dataType.getPath());
			for (DataItem di : dataItems) {
				fileWriter.write(di.toCSVString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			fileWriter.close();
		}
		return true;
	}
		
	public boolean writeFacilityData(FacilityDataItem facility) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(DataType.FACILITY.getPath(), true));
		try {
			bw.write(facility.toCSVString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bw.close();
		}
		return true;
	}
	
	
	
	public boolean writeFlightData(Flight flight) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(DataType.FLIGHT.getPath(), true));
		try {
			bw.write(flight.toCSVString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bw.close();
		}
		return true;
	}
	
	public boolean writeEmployeeData(Employee employee) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(DataType.EMPLOYEES.getPath(), true));
		try {
			bw.write(employee.toCSVString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bw.close();
		}
		return true;
	}
	
	public static boolean resetData(DataType dataType) throws IOException {
		try {
			Files.delete(Path.of(dataType.getPath()));
			File file = new File(dataType.getPath());
			return file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private DataItem parseDataItem(DataType type, String[] values) {
		if (type.equals(DataType.MAINTENANCE)) {
			Aircraft ac = new Aircraft(Integer.parseInt(values[0]), Boolean.getBoolean(values[1]), values[2], Integer.parseInt(values[3]));
			return new MaintenanceDataItem(ac, values[4], Date.valueOf(values[5]));
		}
		else if (type.equals(DataType.MATERIAL)) {
			Material material = new Material(Integer.parseInt(values[0]), values[1], Double.parseDouble(values[2]), Double.parseDouble(values[3]));
			return new MaterialDataItem(material, Double.parseDouble(values[4]), values[5]);
		}
		else if (type.equals(DataType.FACILITY)) {
			return new FacilityDataItem(Integer.parseInt(values[0]), values[1], Double.parseDouble(values[2]), values[3]);
		}
		else if (type.equals(DataType.EMPLOYEES)) {
			return new Employee(Integer.parseInt(values[0]), Boolean.parseBoolean(values[1]), values[2], values[3], values[4], Integer.parseInt(values[5]));
		}
		else if (type.equals(DataType.FLIGHT)) {
			return new Flight(Integer.parseInt(values[0]), values[1], values[2], Integer.parseInt(values[3]), values[4]);
		}
		return null;
	}
}
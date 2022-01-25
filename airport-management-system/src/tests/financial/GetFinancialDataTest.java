package financial;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import subsys.financial.management.facility.FacilityDataItem;
import subsys.financial.management.humanressources.Employee;
import subsys.financial.management.impl.FacilityManagementImpl;
import subsys.financial.management.impl.HumanResourcesManagementImpl;
import subsys.financial.management.impl.MaintenanceManagementImpl;
import subsys.financial.management.maintenance.Aircraft;
import subsys.financial.management.maintenance.MaintenanceDataItem;
import subsys.financial.management.maintenance.Material;
import subsys.financial.management.maintenance.MaterialDataItem;
import subsys.financial.utils.DataType;
import subsys.financial.utils.FileManager;

/**
 * @author Alexander Leeb, k1172617
 */
class GetFinancialDataTest {
	
	private FacilityManagementImpl fm;
	private MaintenanceManagementImpl mm;
	private HumanResourcesManagementImpl hr;

	@BeforeAll
	public static void setup() {
		try {
			FileManager.resetData(DataType.FACILITY);
			FileManager.resetData(DataType.MAINTENANCE);
			FileManager.resetData(DataType.MATERIAL);
			FileManager.resetData(DataType.EMPLOYEES);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * reads all types of financial data from the respective management department
	 * @throws IOException
	 */
	@Test
	public void testGetFinancialData() throws IOException {		
		fm = new FacilityManagementImpl(1, "FacilityManagement", null);
		mm = new MaintenanceManagementImpl(2, "Maintenance Management", null);
		hr = new HumanResourcesManagementImpl(3, "Human Resources", null);
		
		testRegisterFacility();
		testWriteMaintenanceData();
		testRegisterEmployee();
		
		List<FacilityDataItem> facilities = fm.readFacilityData();
		assertNotNull(facilities);
		facilities.forEach(x -> System.out.println(x));
		
		List<MaintenanceDataItem> di = mm.readMaintenanceData();
		assertNotNull(di);
		di.forEach(y -> System.out.println(y));
		
		List<MaterialDataItem> mdi = mm.readMaterialData();
		assertNotNull(mdi);
		mdi.forEach(z -> System.out.println(z));
		
		List<Employee> e = hr.readEmployeeData();
		assertNotNull(e);
		e.forEach(a -> System.out.println(a));
	}
		
	/*
	 * FACILITY MANAGEMENT
	 */
	@Test
	public void testRegisterFacility() {
		fm = new FacilityManagementImpl(1, "FacilityManagement", null);
		List<FacilityDataItem> facilities = new ArrayList<FacilityDataItem>();
		facilities.add(new FacilityDataItem(0, "5 South Bank Ave Zionsville, CA 92831", 8762.83, "Maintenance Management Storage"));
		facilities.add(new FacilityDataItem(1, "7024 Glen Creek St. Fullerton, CA 92831", 8762.83, "Maintenance Management Garage"));
		facilities.add(new FacilityDataItem(2, "154 Andover Ave. Hartsville, CA 92831", 8762.83, "HumanResources Management Office"));
		facilities.add(new FacilityDataItem(3, "12 Grove Street, CA 92831", 8762.83, "Facility Management Office"));
		
		for (FacilityDataItem f : facilities) {
			try {
				fm.registerFacility(f);
			} catch (IllegalArgumentException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * MAINTENANCE MANAGEMENT
	 */
	@Test
	public void testWriteMaintenanceData() {
		MaintenanceManagementImpl mm = new MaintenanceManagementImpl(3, "Maintenance Management", null);
		List<MaterialDataItem> materials = new ArrayList<MaterialDataItem>();
		MaterialDataItem mdi1 = new MaterialDataItem(new Material(1, "Car Tires", 52.5, 100.5), 50, "5 South Bank Ave Zionsville, CA 92831");
		MaterialDataItem mdi2 = new MaterialDataItem(new Material(2, "Turbine", 102.10, 15.29), 100, "7024 Glen Creek St. Fullerton, CA 92831");
		MaterialDataItem mdi3 = new MaterialDataItem(new Material(3, "Metals", 15.75, 223.75), 150, "154 Andover Ave. Hartsville, CA 92831");
		MaterialDataItem mdi4 = new MaterialDataItem(new Material(4, "Cockpit Electronics", 29, 155.10), 90, "154 Andover Ave. Hartsville, CA 92831");
		MaterialDataItem mdi5 = new MaterialDataItem(new Material(5, "Glass", 455, 19.13), 150, "5 South Bank Ave Zionsville, CA 92831");
		MaterialDataItem mdi6 = new MaterialDataItem(new Material(6, "Metals", 188, 100.5), 50, "5 South Bank Ave Zionsville, CA 92831");
		MaterialDataItem mdi7 = new MaterialDataItem(new Material(7, "Paper", 166.11, 30.29), 100, "7024 Glen Creek St. Fullerton, CA 92831");
		MaterialDataItem mdi8 = new MaterialDataItem(new Material(8, "Metals", 29.85, 223.75), 150, "154 Andover Ave. Hartsville, CA 92831");
		MaterialDataItem mdi9 = new MaterialDataItem(new Material(9, "Leather", 29.78, 179.10), 90, "154 Andover Ave. Hartsville, CA 92831");
		MaterialDataItem mdi10 = new MaterialDataItem(new Material(10, "Glass", 888, 19.13), 150, "5 South Bank Ave Zionsville, CA 92831");
		
		materials.add(mdi1);
		materials.add(mdi2);
		materials.add(mdi3);
		materials.add(mdi4);
		materials.add(mdi5);
		materials.add(mdi6);
		materials.add(mdi7);
		materials.add(mdi8);
		materials.add(mdi9);
		materials.add(mdi10);
		
		List<MaintenanceDataItem> maintenances = new ArrayList<MaintenanceDataItem>();
		MaintenanceDataItem di1 = new MaintenanceDataItem(new Aircraft(1, true, "A", 250), "Big Plane and some description of issues", Date.valueOf("2022-12-12"));
		MaintenanceDataItem di2 = new MaintenanceDataItem(new Aircraft(2, true, "A", 300), "Very Big Plane and some description of issues", Date.valueOf("2022-03-17"));
		MaintenanceDataItem di3 = new MaintenanceDataItem(new Aircraft(3, false, "B", 150), "Smaller Plane and some description of issues", Date.valueOf("2022-08-13"));
		MaintenanceDataItem di4 = new MaintenanceDataItem(new Aircraft(4, true, "C", 100), "Pretty small Plane and some description of issues", Date.valueOf("2022-10-19"));
		MaintenanceDataItem di5 = new MaintenanceDataItem(new Aircraft(5, true, "D", 100), "Private Jet Plane and some description of issues", Date.valueOf("2022-05-20"));
		maintenances.add(di1);
		maintenances.add(di2);
		maintenances.add(di3);
		maintenances.add(di4);
		maintenances.add(di5);
		
		try {
			assertTrue(mm.writeMaterialData(materials));
			assertTrue(mm.writeMaintenanceData(maintenances));
		} catch (IllegalArgumentException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * HUMAN RESOURCES
	 */
	@Test
	public void testRegisterEmployee() throws IOException {
		FileManager.resetData(DataType.EMPLOYEES);
		hr = new HumanResourcesManagementImpl(0, "Human Resources", null);
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee(0, true, "Dwayne", "Johnson", "Facility Management", 1));
		employees.add(new Employee(1, true, "Harry", "Potter", "Facility Management", 2));
		employees.add(new Employee(2, true, "Micky", "Mouse", "Human Resources Management", 3));
		try {
			for (Employee e : employees) {
				assertTrue(hr.registerEmployee(e));
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		employees = hr.readEmployeeData();
		assertEquals(3, employees.size());
	}	
}

package financial;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import subsys.financial.management.impl.MaintenanceManagementImpl;
import subsys.financial.management.maintenance.Aircraft;
import subsys.financial.management.maintenance.MaintenanceDataItem;

/**
 * @author Alexander Leeb, k11702617
 */
class MaintenanceManagementTest {

	@Test
	void testWriteMaintenanceData() {
		MaintenanceManagementImpl mm = new MaintenanceManagementImpl(3, "Maintenance Management", null);
		List<MaintenanceDataItem> dis = new ArrayList<MaintenanceDataItem>();
		MaintenanceDataItem di1 = new MaintenanceDataItem(new Aircraft(1, true, "A", 250), "Big Plane and some description of issues", Date.valueOf("2022-12-12"));
		MaintenanceDataItem di2 = new MaintenanceDataItem(new Aircraft(2, true, "A", 300), "Very Big Plane and some description of issues", Date.valueOf("2022-03-17"));
		MaintenanceDataItem di3 = new MaintenanceDataItem(new Aircraft(3, false, "B", 150), "Smaller Plane and some description of issues", Date.valueOf("2022-08-13"));
		MaintenanceDataItem di4 = new MaintenanceDataItem(new Aircraft(4, true, "C", 100), "Pretty small Plane and some description of issues", Date.valueOf("2022-10-19"));
		MaintenanceDataItem di5 = new MaintenanceDataItem(new Aircraft(4, true, "D", 100), "Private Jet Plane and some description of issues", Date.valueOf("2022-05-20"));
		dis.add(di1);
		dis.add(di2);
		dis.add(di3);
		dis.add(di4);
		dis.add(di5);
		
		try {
			assertTrue(mm.writeMaintenanceData(dis));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void testReportMaintenanceDemand() {
		MaintenanceManagementImpl mm = new MaintenanceManagementImpl(3, "Maintenance Management", null);
		assertTrue(mm.reportMaintenanceDemand().size() > 0);
	}
}

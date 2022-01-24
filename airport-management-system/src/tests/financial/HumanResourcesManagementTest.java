package financial;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import subsys.financial.management.humanressources.Employee;
import subsys.financial.management.impl.HumanResourcesManagementImpl;

/**
 * @author Alexander Leeb, k11702617
 */
class HumanResourcesManagementTest {
	private HumanResourcesManagementImpl hr;
	private Employee employee;
		
	@Test
	void testRegisterEmployee() {
		hr = new HumanResourcesManagementImpl(0, "Human Resources", null);
		employee = new Employee(2, true, "Dwayne", "Johnson", "Facility Management", 1);
		try {
			assertTrue(hr.registerEmployee(employee));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


package control;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import subsys.control.Usecase1_CheckSecurityInformation.*;
import subsys.control.Usecase2_CheckMaintenance.Maintenance;
import subsys.control.Usecase2_CheckMaintenance.MaintenancePrognosis;
import subsys.control.Usecase1_CheckSecurityInformation.ControlWorker;
import subsys.control.Usecase1_CheckSecurityInformation.SecurityWorker;
import subsys.control.Usecase1_CheckSecurityInformation.SubsystemCategory;
import subsys.control.Usecase3_GetHealthStatus.HealthStatus;
import subsys.control.Usecase3_GetHealthStatus.HealthStatusCategory;

import java.nio.file.AccessDeniedException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static subsys.control.Usecase1_CheckSecurityInformation.RiskLevel.CRITICAL;

/**
 * @author Wolfgang Schedl, k00846738
 */

public class GetHealthStatusTest {

    ControlWorker worker1;
    HealthStatus healthStatus;

    @BeforeEach
    void setUp() throws IOException {
        worker1 = new ControlWorker("Hans", "Maier", "Officer", "All", "all", "Maintenance");
        healthStatus = new HealthStatus(SubsystemCategory.CONTROLSYSTEM, worker1);
    }

    @Test
    public void testWorkerWitNoAccess() throws IOException {
        DummyWorker worker =new DummyWorker("A", "B", "C", "D");
        Assertions.assertThrows(AccessDeniedException.class, () -> new SecurityInformation(SubsystemCategory.CONTROLSYSTEM, worker));
    }

    @Test
    public void testIfAccessDeniedForSecurityWorker() throws AccessDeniedException {
        SecurityWorker worker2 = new SecurityWorker("Hans", "Maier", "Officer", "All", "all");
        Assertions.assertThrows(AccessDeniedException.class, ()-> new HealthStatus(SubsystemCategory.CONTROLSYSTEM,worker2));
    }

    @Test
    public void testIfAlarmIsOff() {
        HealthStatusCategory status = healthStatus.getCategory();
        if (status == HealthStatusCategory.ERROR || status ==HealthStatusCategory.DEGRADED) {
            assertTrue(healthStatus.getAlarmStatus());
        }
        else
        assertFalse(healthStatus.getAlarmStatus());
    }

    @Test
    public void testIfAlarmIsOn() throws IOException {
        healthStatus.setAlarmStatusOn(worker1);
        assertTrue(healthStatus.getAlarmStatus());
    }



}


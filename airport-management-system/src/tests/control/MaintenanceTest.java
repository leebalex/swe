package control;

import org.junit.jupiter.api.Test;
import subsys.control.Usecase1_CheckSecurityInformation.*;
import subsys.control.Usecase2_CheckMaintenance.Maintenance;
import subsys.control.Usecase2_CheckMaintenance.MaintenancePrognosis;
import subsys.control.Usecase1_CheckSecurityInformation.ControlWorker;
import subsys.control.Usecase1_CheckSecurityInformation.SecurityWorker;
import subsys.control.Usecase1_CheckSecurityInformation.SubsystemCategory;

import java.nio.file.AccessDeniedException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class MaintenanceTest {
    ControlWorker worker1 = new ControlWorker("Hans", "Maier", "Officer", "All", "all", "Maintenance");
    Maintenance maintenance1 = new Maintenance(SubsystemCategory.CONTROLSYSTEM, worker1);

    //@Rule
    //public final ExpectedException exception = ExpectedException.none();

    public MaintenanceTest() throws AccessDeniedException {
    }

    @Test
    public void getMaintenanceIds() throws AccessDeniedException {
        int numb1 = maintenance1.getMaintenanceId();
        ControlWorker worker2 = new ControlWorker("Hans", "Maier", "Officer", "All", "all", "Maintenance");
        Maintenance maintenance2 = new Maintenance(SubsystemCategory.CONTROLSYSTEM, worker1);
        assertEquals(numb1 + 1, maintenance2.getMaintenanceId());
    }

    @Test
    public void testIfAccessDeniedForSecurityWorker() throws AccessDeniedException {
        SecurityWorker worker1 = new SecurityWorker("Hans", "Maier", "Officer", "All", "all");
        //exception.expect(AccessDeniedException.class);
        Maintenance maintenance1 = new Maintenance(SubsystemCategory.CONTROLSYSTEM, worker1);
    }

    @Test
    public void testGetMaintenanceInformation() throws AccessDeniedException {
        assertNotNull(maintenance1);
        assertTrue(maintenance1.getMaintenanceTitle().contains(worker1.getName()));
        assertTrue(maintenance1.getMaintenanceTitle().contains(String.valueOf(worker1.getWorkerId())));
        assertTrue(maintenance1.getMaintenanceTitle().contains(String.valueOf(worker1.getWorkerId())));
        assertTrue(maintenance1.getMaintenanceInformation().contains("Lorem ipsum dolor "));

        assertEquals(SubsystemCategory.CONTROLSYSTEM, maintenance1.getSubsystemCategory());
    }

    @Test
    public void testWorkerWitNoAccess() throws IOException {
        DummyWorker dummyWorker = new DummyWorker("A", "B", "C", "D");
        //exception.expect(AccessDeniedException.class);
        SecurityInformation secInfo = new SecurityInformation(SubsystemCategory.CONTROLSYSTEM, dummyWorker);
    }

    @Test
    public void testWrongSubcategory() throws IOException {
        SecurityWorker worker1 = new SecurityWorker("Hans", "Maier", "Officer", "All", "all");
        //exception.expect(AccessDeniedException.class);
        SecurityInformation secInfo = new SecurityInformation(SubsystemCategory.NONE, worker1);
    }


    @Test
    public void testMaintenancePrognosis() throws IOException {
        MaintenancePrognosis prognosis = new MaintenancePrognosis(SubsystemCategory.CONTROLSYSTEM, worker1);
        assertTrue(prognosis.prognosisReport.containsKey(0));
        assertEquals("Everything will be fine", prognosis.prognosisReport.get(0));

    }


}

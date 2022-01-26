package control;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import subsys.control.Usecase1_CheckSecurityInformation.*;
import subsys.landside.FuelController;
import subsys.landside.FuelTransport;
import subsys.landside.Location;
import subsys.landside.VehicleDriver;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

import static org.junit.jupiter.api.Assertions.*;
import static subsys.control.Usecase1_CheckSecurityInformation.RiskLevel.CRITICAL;
/**
 * @author Wolfgang Schedl, k00846738
 */

public class CheckSecurityInformationTest {
    SecurityInformation secInfo;
    SecurityWorker worker1;

    @BeforeEach
    void setUp() throws IOException {
       worker1 = new SecurityWorker("Hans", "Maier", "Officer", "All", "all");
       secInfo = new SecurityInformation(SubsystemCategory.CONTROLSYSTEM, worker1);
    }


    @Test
    public void getInformationIDs() throws IOException {
        int numb1 = secInfo.getInformationId();
        SecurityWorker worker1 = new SecurityWorker("Hans", "Maier", "Officer", "All", "all");
        SecurityInformation secInfo = new SecurityInformation(SubsystemCategory.CONTROLSYSTEM, worker1);
        int numb2 = secInfo.getInformationId();
        assertEquals(numb1 + 1, numb2);
    }

    @Test
    public void testGetSecurityInformation(){
        assertNotNull(secInfo);
        assertTrue(secInfo.getTitleSecurity().contains(worker1.getName()));
        assertTrue(secInfo.getTitleSecurity().contains(String.valueOf(Worker.getWorkerId())));
        assertTrue(secInfo.getDescriptionSecurity().contains("Lorem ipsum dolor sit amet"));
        assertTrue(SecurityInformation.getSecurityProtocol().containsKey(secInfo.getInformationId()));
        assertTrue(SecurityInformation.getSecurityProtocol().get(secInfo.getInformationId()).contains(secInfo.getRiskLevel().toString()));
        assertTrue(SecurityInformation.getSecurityProtocol().get(secInfo.getInformationId()).contains(secInfo.getTitleSecurity()));
        assertNotNull(secInfo.getTimestamp());
        assertEquals("securityProtocolFile.txt", secInfo.getSecurityProtocolFile());
    }

    @Test
    public void testIfAlarmIsOn() {
        secInfo.setRiskLevel(CRITICAL);
        assertTrue(SecurityInformation.isAlarmOn());
    }

    @Test
    public void testWorkerWitNoAccess() {
        DummyWorker worker =new DummyWorker("A", "B", "C", "D");
        Assertions.assertThrows(AccessDeniedException.class, () -> new SecurityInformation(SubsystemCategory.CONTROLSYSTEM, worker));
    }
    @Test
    public void testWrongSubcategory()  {
        SecurityWorker securityWorker =new SecurityWorker("Hans", "Maier", "Officer", "All", "all");
        Assertions.assertThrows(AccessDeniedException.class, () -> new SecurityInformation(SubsystemCategory.NONE,securityWorker));
    }

    @Test
    public void testIfAlarmIsOff() {
        if (secInfo.getRiskLevel() == CRITICAL) {
            secInfo.setRiskLevel(RiskLevel.NORMAL);
        }
        assertFalse(SecurityInformation.isAlarmOn());
    }

    @Test
    public void testIfAlarmIsOn2() throws IOException {
        secInfo.setAlarmOn();
        assertTrue(SecurityInformation.isAlarmOn());
    }





    @Test
    public void testlogToSecurityProtocol() throws IOException {
        SecurityWorker worker1 = new SecurityWorker("Hans", "Maier", "Officer", "All", "all");
        secInfo.logToSecurityProtocol(1);
        Assertions.assertThrows(IOException.class, ()-> secInfo = new SecurityInformation(SubsystemCategory.NONE, worker1));
    }

}

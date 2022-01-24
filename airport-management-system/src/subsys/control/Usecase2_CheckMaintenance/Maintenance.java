package subsys.control.Usecase2_CheckMaintenance;

import subsys.control.Usecase1_CheckSecurityInformation.*;

import java.nio.file.AccessDeniedException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Maintenance {
    private static int maintenanceId=0;
    private String maintenanceInformation;
    private String maintenanceTitle;
    private int maintenanceLevel;
    private Map<Integer, String> maintenanceHistory;
    private SubsystemCategory subsystemCategory;

    public Maintenance(SubsystemCategory subsystemCategory, Worker worker) throws AccessDeniedException {
        this.subsystemCategory = subsystemCategory;
        this.maintenanceId++;
        getMaintenanceInformation(subsystemCategory, worker, this);
    }


    public Maintenance getMaintenanceInformation(SubsystemCategory subsystemCategory, Worker worker, Maintenance maintenance) throws AccessDeniedException {
        if (!(worker instanceof ControlWorker) || subsystemCategory == SubsystemCategory.NONE) {
            throw new AccessDeniedException("You have not the rights to access the security information!");
        }

        maintenance.subsystemCategory = subsystemCategory;

        maintenance.maintenanceTitle = "Maintenance information with the ID " + maintenance
                + "\nfor " + worker.getName() + " with the workerID: " + worker.getWorkerId()
                + "\nregarding the subsystem category " + subsystemCategory;
        maintenance.maintenanceInformation = "\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ";
        maintenance.maintenanceLevel=getMaintenanceLevel();
        maintenance.maintenanceHistory=new HashMap<>();
        maintenanceHistory.put(maintenanceId, maintenanceTitle +"\n"+ maintenanceInformation +"\n"+createTimestamp());

        if (maintenanceLevel<10) {
            throw new IllegalStateException("\"ATTENTION! \\nThe current maintenanceLevel is very low\"");
        }
        return maintenance;

    }
    public static int getMaintenanceLevel () {
        Random r = new Random();
        return 10+r.nextInt(101);
    }


    public  int getMaintenanceId() {
        return maintenanceId;
    }

    public String getMaintenanceInformation() {
        return maintenanceInformation;
    }

    public String getMaintenanceTitle() {
        return maintenanceTitle;
    }

    public Map<Integer, String> getMaintenanceHistory() {
        return maintenanceHistory;
    }

    public SubsystemCategory getSubsystemCategory() {
        return subsystemCategory;
    }

    public Timestamp createTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}

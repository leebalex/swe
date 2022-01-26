package subsys.control.Usecase3_GetHealthStatus;

import java.nio.file.AccessDeniedException;
import java.sql.Timestamp;
import java.util.Random;

import subsys.control.Usecase1_CheckSecurityInformation.*;

public class HealthStatus {
    private String generalHealthStatus;
    private String detailedHealthStatus;
    private static boolean alarmStatusOn = false;
    private Timestamp timestamp;
    HealthStatusCategory category;

    public HealthStatus(SubsystemCategory subsystemCategory, Worker worker) throws AccessDeniedException {
        getHealthStatus(worker, subsystemCategory, this);
    }

    public HealthStatus getHealthStatus(Worker worker, SubsystemCategory subsystemCategory, HealthStatus healthStatus) throws AccessDeniedException {
        if (worker instanceof ControlWorker && subsystemCategory != SubsystemCategory.NONE) {
            healthStatus.category = createHealthStatus();
            if (healthStatus.category == HealthStatusCategory.UNREACHABLE || healthStatus.category == HealthStatusCategory.ERROR) {
                alarmStatusOn = true;
            } else {
                alarmStatusOn = false;
            }
            healthStatus.timestamp = createTimestamp();
            healthStatus.generalHealthStatus = "Health Status of Subsystem: " + subsystemCategory.toString()
                    + "the general health status was assessed to: " + healthStatus.category+
            "Timestamp"+ healthStatus.timestamp;
            healthStatus.detailedHealthStatus = "";
            return healthStatus;
        } else {
            throw new AccessDeniedException("Only control worker can access the health status");
        }
    }

    private HealthStatusCategory createHealthStatus() {
        HealthStatusCategory category;
        Random random = new Random();
        int num = random.nextInt(4);
        if (num == 0)
            category = HealthStatusCategory.OK;
        else if (num == 1)
            category = HealthStatusCategory.OK_WITH_SUPRESSED;
        else if (num == 2)
            category = HealthStatusCategory.DEGRADED;
        else
            category = HealthStatusCategory.ERROR;
        return category;
    }

    public HealthStatus getDetailedHealthStatus(Worker worker, HealthStatus healthStatus) throws AccessDeniedException {
        if (worker instanceof ControlWorker && healthStatus != null) {
            healthStatus.detailedHealthStatus = "Your detailed Health Status of Subsystem: " + healthStatus.category.toString() +
                    "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. ";
            return healthStatus;
        } else {
            throw new AccessDeniedException("Only control worker can access the health status");
        }
    }

    public Timestamp createTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public void setAlarmStatusOn(Worker worker) {
        if (worker instanceof ControlWorker){
            alarmStatusOn = true;
        }
    }

    public void setHealthStatus(Worker worker, HealthStatus healthStatus, HealthStatusCategory category) {
        if (worker instanceof ControlWorker && healthStatus != null) {
            healthStatus.category = category;
        }
    }

    public String getGeneralHealthStatus() {
        return generalHealthStatus;
    }

    public String getDetailedHealthStatus() {
        return detailedHealthStatus;
    }

    public static boolean isAlarmStatusOn() {
        return alarmStatusOn;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public HealthStatusCategory getCategory() {
        return category;
    }
    public boolean getAlarmStatus() {
        return alarmStatusOn;
    }
}

package subsys.control.Usecase2_CheckMaintenance;

import subsys.control.Usecase1_CheckSecurityInformation.SubsystemCategory;
import subsys.control.Usecase1_CheckSecurityInformation.Worker;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

public class MaintenancePrognosis extends Maintenance {

    private static int prognosisReportID=0;
    public  Map<Integer, String> prognosisReport=new HashMap<>();
    private boolean renovationRequired;

    public MaintenancePrognosis(SubsystemCategory subsystemCategory, Worker worker) throws AccessDeniedException {
        super(subsystemCategory, worker);
        requestedPrognosis();
        prognosisReportID++;
    }


    public  MaintenancePrognosis requestedPrognosis() throws AccessDeniedException {
        return MaintenancePrognosisDB.returnPrognosisData(this);
    }

    public int getPrognosisReportID() {
        return prognosisReportID;
    }

}

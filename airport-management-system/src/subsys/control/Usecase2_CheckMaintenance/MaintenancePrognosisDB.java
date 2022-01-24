package subsys.control.Usecase2_CheckMaintenance;


import java.nio.file.AccessDeniedException;

public class MaintenancePrognosisDB {


    private MaintenancePrognosis maintenancePrognosis;


    public static MaintenancePrognosis returnPrognosisData(MaintenancePrognosis maintenancePrognosis) throws AccessDeniedException {
        System.out.println("Get statistical data from DB....");
        maintenancePrognosis.prognosisReport.put(maintenancePrognosis.getPrognosisReportID(), "Everything will be fine");
        return maintenancePrognosis;
    }
}

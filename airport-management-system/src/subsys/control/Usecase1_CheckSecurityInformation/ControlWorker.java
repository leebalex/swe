package subsys.control.Usecase1_CheckSecurityInformation;

public class ControlWorker extends Worker {
    private String securityPermission;
    private String securityTaskArea;

    public ControlWorker( String name, String surname, String rank, String permissions, String securityPermission, String securityTaskArea) {
        super(name, surname, rank, permissions);
        this.securityPermission = securityPermission;
        this.securityTaskArea = securityTaskArea;
    }

}

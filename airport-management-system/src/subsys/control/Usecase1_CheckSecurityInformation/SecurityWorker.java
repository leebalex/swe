package subsys.control.Usecase1_CheckSecurityInformation;

public class SecurityWorker extends Worker {
    private String controlPermissions;

    public SecurityWorker(String name, String surname, String rank, String permissions, String controlPermissions) {
        super(name, surname, rank, permissions);
        this.controlPermissions = controlPermissions;
    }
}

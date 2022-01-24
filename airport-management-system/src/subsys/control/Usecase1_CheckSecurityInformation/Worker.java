package subsys.control.Usecase1_CheckSecurityInformation;

public abstract class Worker {
    protected static int workerId=0;
    protected String name;
    protected String surname;
    protected String rank;
    protected String permissions;

    public Worker(String name, String surname, String rank, String permissions) {
        this.name = name;
        this.surname = surname;
        this.rank = rank;
        this.permissions = permissions;
        workerId++;
    }

    public static int getWorkerId() {
        return workerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}

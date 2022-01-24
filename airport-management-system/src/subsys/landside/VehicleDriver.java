/*
 * @author Florian Engertsberger
 */

package subsys.landside;

public class VehicleDriver extends Worker{
    private String license;


    public VehicleDriver(int id, String name, String surname, boolean isActive, String license) {
        super(id, name, surname, isActive);
        this.license = license;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}

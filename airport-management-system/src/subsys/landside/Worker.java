/*
 * @author Florian Engertsberger
 */

package subsys.landside;

public class Worker {
    private final int id;
    private String name;
    private String surname;
    private boolean isActive;

    public Worker(int id,String name, String surname, boolean isActive ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

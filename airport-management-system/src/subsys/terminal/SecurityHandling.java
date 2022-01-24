

/**
 * @author Michael Janisch, k11904994
 */

package subsys.terminal;
import java.util.ArrayList;


public class SecurityHandling {
    ArrayList<Passenger> passengers = new ArrayList();
    Security security;


    public boolean checkPassengerData(Passenger p) {

        passengers.add(p);
        if(p.name != null && p.passportId != null) {
            System.out.println("Security "+security.name+" : 'Everything is alright, have a nice Journey!'");
            return true;
        }
        System.out.println("Security "+security.name+" : 'Your Data is invalid. I'm afraid you have to be further investigated!'");
        return false;
    }
}

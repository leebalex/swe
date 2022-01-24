/**
 * @author Michael Janisch, k11904994
 */

package terminal;
import subsys.terminal.Baggage;
import subsys.terminal.Passenger;
import subsys.terminal.SecurityHandling;

import static org.junit.jupiter.api.Assertions.*;


class SecurityHandlingTest {

    @org.junit.jupiter.api.Test
    void checkPassengerData() {
        Passenger Peter = new Passenger("Peter",0123,new Baggage(12));
        try {
            Passenger unknown = new Passenger(null, null, null);
            SecurityHandling sh = new SecurityHandling();
            assertEquals(true, sh.checkPassengerData(Peter));
            assertEquals(false, sh.checkPassengerData(unknown));
        } catch (NullPointerException e) {}
    }
}
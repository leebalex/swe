/**
 * @author Michael Janisch, k11904994
 */

package terminal;
import org.junit.jupiter.api.Test;
import subsys.terminal.Baggage;
import subsys.terminal.Check_In;
import subsys.terminal.Passenger;

import static org.junit.jupiter.api.Assertions.*;

class Check_InTest {

    @Test
    void checkPassportId() {
        try {
            Passenger Gustav = new Passenger("Gustav", 0143, new Baggage(10));
            Passenger Paul = new Passenger("Paul", null, new Baggage(9));
            Check_In checkIn = new Check_In();
            assertEquals(true, checkIn.checkPassportId(Gustav.passportId));
            assertEquals(false, checkIn.checkPassportId(Paul.passportId));
        } catch (NullPointerException e) {}
    }
}
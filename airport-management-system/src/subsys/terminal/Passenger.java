/**
 * @author Michael Janisch, k11904994
 */

package subsys.terminal;


public class Passenger {

    public final String name;
    public final Integer passportId;
    public Baggage baggage;
    public String ticket;

    public Passenger(String name, Integer passportId, Baggage baggage) {
        this.name = name;
        this.passportId = passportId;
        this.baggage = baggage;
    }
}


/**
 * @author Michael Janisch, k11904994
 */

package subsys.terminal;


public class Check_In {
    Passenger passenger;
    TerminalWorker worker;

    public boolean checkPassportId(Integer id) {
        if(id != null) {
            printTicket();
            return true;
        }
        System.out.println("Terminal Worker "+worker.name+" : 'I'm sorry your Passport is not valid!'");
        return false;
    }

    public void printTicket() {
        passenger.ticket = "Flight Ticket: \n Passenger: "+passenger.name;
        System.out.println(passenger.ticket);
    }
}

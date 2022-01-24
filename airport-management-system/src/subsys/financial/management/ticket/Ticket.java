package subsys.financial.management.ticket;

/**
 * @author Alexander Leeb, k11702617
 */
public class Ticket {

	private int id;
	private int flightId;
	private String passengerLastName;
	private String passengerFirstName;
	private String passportId;
	private float price;
	private String seatNumber;
	
	public Ticket(int id, int flightId, String passengerLastName, String passengerFirstName, String passportId, float price, String seatNumber) {
		this.id = id;
		this.flightId = flightId;
		this.passengerLastName = passengerLastName;
		this.passengerFirstName = passengerFirstName;
		this.passportId = passportId;
		this.price = price;
		this.seatNumber = seatNumber;
	}

	public int getId() {
		return id;
	}

	public int getFlightId() {
		return flightId;
	}

	public String getPassengerLastName() {
		return passengerLastName;
	}

	public String getPassengerFirstName() {
		return passengerFirstName;
	}

	public String getPassportId() {
		return passportId;
	}

	public float getPrice() {
		return price;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public void setPassengerLastName(String passengerLastName) {
		this.passengerLastName = passengerLastName;
	}

	public void setPassengerFirstName(String passengerFirstName) {
		this.passengerFirstName = passengerFirstName;
	}

	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
}

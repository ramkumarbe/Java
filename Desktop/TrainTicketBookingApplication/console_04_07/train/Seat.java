package train;

import passenger.Passenger;

public class Seat {
	private Passenger passenger;
	private String seatNumber;
	private boolean isBooked;
	private String compartmentName;
	private String passengerId;
	
	public Seat(String compartmentName, String seatNumber) {
		this.setCompartmentName(compartmentName);
		this.seatNumber = seatNumber;
	}

    public String getSeatNumber() {
    	return seatNumber;
    }
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	public boolean getIsBooked() {
		return isBooked;
	}
	public void setIsBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	public String getCompartmentName() {
		return compartmentName;
	}

	public void setCompartmentName(String compartmentName) {
		this.compartmentName = compartmentName;
	}

	public String getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}
}

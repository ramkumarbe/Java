package train;

import java.util.List;

public class Coach {
    private String coachName;
    protected double ticketPrice;
	private List<Seat> seats;
	private String compartmentName;
	private List<Seat> RACSeats;

	
    public Coach(String coachName, String compartmentName) {
    	this.coachName = coachName;
    	this.compartmentName = compartmentName;
    }

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
	public List<Seat> getSeats() {
		return seats;
	}

	protected String getCompartmentName() {
		return compartmentName;
	}

}

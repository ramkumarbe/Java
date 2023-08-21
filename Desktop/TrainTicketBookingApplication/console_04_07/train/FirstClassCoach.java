package train;

import java.util.ArrayList;
import java.util.List;

public class FirstClassCoach extends Coach {

	public FirstClassCoach(String coachName, String compartmentName) {	
		super(coachName, compartmentName);
		this.ticketPrice = 2400;
	}

	private List<Seat> seats = new ArrayList<Seat>();
	
	public List<Seat> getSeats() {
		return seats;
	}
}

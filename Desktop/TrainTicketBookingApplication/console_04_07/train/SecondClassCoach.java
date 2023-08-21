package train;

import java.util.ArrayList;
import java.util.List;

public class SecondClassCoach extends Coach {

	public SecondClassCoach(String coachName, String compartmentName) {	
		super(coachName, compartmentName);
		this.ticketPrice = 1200;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	private List<Seat> seats = new ArrayList<Seat>();
	
}

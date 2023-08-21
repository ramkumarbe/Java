package train;

import java.util.ArrayList;
import java.util.List;


public class SittingCoach extends Coach {
	
	public SittingCoach (String coachName, String compartmentName) {	
		super(coachName, compartmentName);
		super.ticketPrice=300;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	private List<Seat> seats = new ArrayList<Seat>();
	
}

package train;

import java.util.ArrayList;
import java.util.List;

public class SleeperCoach extends Coach{
	
	public SleeperCoach(String coachName, String compartmentName) {	
		super(coachName, compartmentName);
		this.ticketPrice = 600;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	private List<Seat> seats = new ArrayList<Seat>();
	
}

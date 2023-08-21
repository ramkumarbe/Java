package train;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import passenger.Passenger;
import passenger.User;
import passenger.UserUtil;

public class Train {
	private int trainNumber;
	private String trainName;
	private String time;
	private TrainDate trainDate;
	private Station station;

	public Train(Station station, TrainDate trainDate, int trainNumber, String trainName, String time) {
		this.station = station;
		this.trainDate = trainDate;
		this.trainNumber = trainNumber;
		this.trainName = trainName;
		this.time = time;
	}
    
	private List<Ticket> tickets = new ArrayList<Ticket>();
	private List<Seat> RACSeats = new ArrayList<Seat>();

	public final static String FIRST_CLASS = "First Class";
	public final static String SECOND_CLASS = "Second Class";
	public final static String SLEEPER = "Sleeper";
	public final static String SITTING = "Sitting";

	private static final int firstClassCompartmentsCount = Util.getInstance().getFirstClassCompartmentCount();
	private static final int secondClassCompartmentsCount = Util.getInstance().getSecondClassCompartmentCount();
	private static final int sleeperCompartmentsCount = Util.getInstance().getSleeperCompartmentCount();
	private static final int sittingCompartmentsCount = Util.getInstance().getSittingCompartmentCount();

	private FirstClassCoach[] firstClassCoaches = new FirstClassCoach[firstClassCompartmentsCount];
	private SecondClassCoach[] secondClassCoaches = new SecondClassCoach[secondClassCompartmentsCount];
	private SleeperCoach[] sleeperCoaches = new SleeperCoach[sleeperCompartmentsCount];
	private SittingCoach[] sittingCoaches = new SittingCoach[sittingCompartmentsCount];

	private List<Seat> firstClassWaitingListSeats = new ArrayList<Seat>();
	private List<Seat> secondClassWaitingListSeats = new ArrayList<Seat>();
	private List<Seat> sleeperWaitingListSeats = new ArrayList<Seat>();
	private List<Seat> sittingWaitingListSeats = new ArrayList<Seat>();

	public void removeTickets() {
		for(int i=0; i<tickets.size(); i++) {
			Ticket ticket = this.tickets.get(i);
			for(User user:UserUtil.getInstance().userDetails)
				user.bookedTickets.remove(ticket);
			this.tickets.remove(i);
		}
	}

	public void changeTime() {
		System.out.print("Enter new Time:");
		String newTime = null;
		do {
			newTime = Util.getInstance().getNewTime();
			if(Util.getInstance().isValidTime(newTime))
				System.out.println("Enter valid Time.");
		}while(Util.getInstance().isValidTime(newTime));
		
		this.setTime(newTime);
		System.out.println();
		System.out.println("Train Time has been changed.");
	}

	public String getCoachName(String compartmentName) {
		for (Coach coach : firstClassCoaches)
			if (compartmentName.equals(coach.getCompartmentName()))
				return coach.getCoachName();

		for (Coach coach : secondClassCoaches)
			if (compartmentName.equals(coach.getCompartmentName()))
				return coach.getCoachName();

		for (Coach coach : sleeperCoaches)
			if (compartmentName.equals(coach.getCompartmentName()))
				return coach.getCoachName();

		for (Coach coach : sittingCoaches)
			if (compartmentName.equals(coach.getCompartmentName()))
				return coach.getCoachName();
		return null;
	}
	
	public Coach getCoach(String coachName) {
		if (coachName.equals(FIRST_CLASS))
			for (Coach coach : firstClassCoaches)
				return coach;

		if (coachName.equals(SECOND_CLASS))
			for (Coach coach : secondClassCoaches)
				return coach;

		if (coachName.equals(SLEEPER))
			for (Coach coach : sleeperCoaches)
				return coach;

		if (coachName.equals(SITTING))
			for (Coach coach : sittingCoaches)
				return coach;
		return null;
	}
    private static final String availableKeyWord = "AVL ";
	private static final String RACKeyWord = "RAC ";
	public void viewAvailableSeats() {
		System.out.println("\t\t\t ----------------------------------------------------------------------");
	    System.out.println("\t\t\t\t\t First Class : "+ availableSeats(FIRST_CLASS));

		System.out.println("\t\t\t\t\t Second Class : "+ availableSeats(SECOND_CLASS));  

		System.out.println("\t\t\t\t\t Sleeper : "+ availableSeats(SLEEPER));

		System.out.println("\t\t\t\t\t Sitting : "+ availableSeats(SITTING));
		System.out.println("\t\t\t ----------------------------------------------------------------------");
	}

	public String availableSeats(String coachName) {
		
		if (coachName.equals(FIRST_CLASS)) {
			if(this.availableSeats(this.firstClassCoaches)>=0&&this.firstClassWaitingListSeats.size()==0)
				return availableKeyWord+this.availableSeats(this.firstClassCoaches);
			else 
				return this.firstClassWaitingListSeats.get(this.firstClassWaitingListSeats.size()-1).getSeatNumber();
		}
			
		else if (coachName.equals(SECOND_CLASS)) {
			if(this.availableSeats(this.secondClassCoaches)>=0&&this.secondClassWaitingListSeats.size()==0)
				return availableKeyWord + this.availableSeats(this.secondClassCoaches);
			else 
				return this.secondClassWaitingListSeats.get(this.secondClassWaitingListSeats.size()-1).getSeatNumber();
		}
            
		else if (coachName.equals(SLEEPER)) {
			if(this.availableSeats(this.sleeperCoaches)>0)
				return availableKeyWord + this.availableSeats(this.sleeperCoaches);
			else if(this.availableRACSeats()>=0&&this.sleeperWaitingListSeats.size()==0)
				return RACKeyWord + this.availableRACSeats();
			else 
		        return this.sleeperWaitingListSeats.get(this.sleeperWaitingListSeats.size()-1).getSeatNumber();
		}
		
		else if (coachName.equals(SITTING)) {
			if(this.availableSeats(this.sittingCoaches)>=0&&this.sittingWaitingListSeats.size()==0)
				return availableKeyWord + this.availableSeats(this.sittingCoaches);
			else 
				return this.sittingWaitingListSeats.get(this.sittingWaitingListSeats.size()-1).getSeatNumber();
		}
		return null;
	}

	private short availableRACSeats() {
		short availableSeats = 0;
		for (Seat seat : getRACSeats())
			if (!seat.getIsBooked())
				availableSeats++;

		return availableSeats;
	}
	private short availableSeats(Coach[] coaches) {
		short availableSeats = 0;
		for (Coach coach : coaches)
			for (Seat seat : coach.getSeats())
				if (!seat.getIsBooked())
					availableSeats++;

		return availableSeats;
	}

	private Seat getPreferredBerth(Coach[] coaches, char berthPreference) {
		if (berthPreference == RANDOM_BERTH)
			for (Coach coach : coaches)
				for (Seat seat : coach.getSeats())
					if (!seat.getIsBooked())
						return seat;

		for (Coach coach : coaches)
			for (Seat seat : coach.getSeats())
				if (!seat.getIsBooked())
					if (seat.getSeatNumber().charAt(seat.getSeatNumber().length() - 1) == berthPreference)
						return seat;

		for (Coach coach : coaches)
			for (Seat seat : coach.getSeats())
				if (!seat.getIsBooked())
					return seat;

		if(coaches[0].getCoachName().equals(SLEEPER))
			for (Seat seat : getRACSeats())
				if (!seat.getIsBooked())
					return seat;
		return null;
	}

	public Seat getSeat(Coach coach, char berthPreference, Passenger passenger, String passengerId) {
		Seat seat = null;

		if (coach.getCoachName().equals(FIRST_CLASS))
			seat = getPreferredBerth(firstClassCoaches, berthPreference);
		else if (coach.getCoachName().equals(SECOND_CLASS))
			seat = getPreferredBerth(secondClassCoaches, berthPreference);
		else if (coach.getCoachName().equals(SLEEPER))
			seat = getPreferredBerth(sleeperCoaches, berthPreference);
		else if (coach.getCoachName().equals(SITTING))
			seat = getPreferredBerth(sittingCoaches, berthPreference);
		if (seat == null) {
			seat = new Seat(null, getWaitingListNumber(coach));
			this.getWaitingListSeats(coach).add(seat);
		}
		seat.setPassengerId(passengerId);
		seat.setPassenger(passenger);
		seat.setIsBooked(true);
		return seat;
	}

	private static final byte UPPER = 1;
	private static final byte LOWER = 2;
	private static final byte MIDDLE = 3;
	private static final byte WINDOW = 1;

	public static final char UPPER_BERTH = 'U';
	public static final char LOWER_BERTH = 'L';
	public static final char MIDDLE_BERTH = 'M';
	public static final char WINDOW_SEAT = 'W';
	public static final char RANDOM_BERTH = ' ';

	public char berthPreference(Coach coach) {
		if(availableSeats(coach.getCoachName()).contains(waitingListKeyWord))
			return RANDOM_BERTH;
		boolean validInput = true;
		byte berth = 0;
		do {
			validInput = true;
			switch (coach.getCoachName()) {
			case FIRST_CLASS:
				break;
			case SECOND_CLASS:
				System.out.println("Berth Preference:");
				System.out.println("1.Upper    2.Lower");
				System.out.println("Press other number to select any.");
				berth = Util.getInstance().getByteInput();
				if (berth == UPPER)
					return UPPER_BERTH;
				else if (berth == LOWER)
					return LOWER_BERTH;
				else
					return RANDOM_BERTH;
			case SLEEPER:
				System.out.println("Berth Preference:");
				System.out.println("1.Upper    2.Lower    3.Middle");
				System.out.println("Press other number to select any.");

				berth = Util.getInstance().getByteInput();
				if (berth == UPPER)
					return UPPER_BERTH;
				else if (berth == LOWER)
					return LOWER_BERTH;
				else if (berth == MIDDLE)
					return MIDDLE_BERTH;
				else
					return RANDOM_BERTH;
			case SITTING:
				System.out.println("Press '1' for window preference.");
				System.out.println("Press other number to select any.");
				berth = Util.getInstance().getByteInput();
				if (berth == WINDOW)
					return WINDOW_SEAT;
				else
					return RANDOM_BERTH;
			default:
				System.out.println("Invalid Input.");
				validInput = false;
			}
		} while (!validInput);
		return RANDOM_BERTH;
	}

	public int getTrainNumber() {
		return trainNumber;
	}

	public String getTrainName() {
		return trainName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	public TrainDate getTrainDate() {
		return trainDate;
	}

	public Station getStation() {
		return station;
	}

	public FirstClassCoach[] getFirstClassCoaches() {
		return firstClassCoaches;
	}

	public SecondClassCoach[] getSecondClassCoaches() {
		return secondClassCoaches;
	}

	public SleeperCoach[] getSleeperCoaches() {
		return sleeperCoaches;
	}

	public SittingCoach[] getSittingCoaches() {
		return sittingCoaches;
	}
	
	public List<Ticket> getTickets() {
		return tickets;
	}

	private int firstClassWaitingListNumber = 1;
	private int getFirstClassWaitingListNumber() {
		return this.firstClassWaitingListNumber++;
	}
	
	private int secondClassWaitingListNumber = 1;
	private int getSecondClassWaitingListNumber() {
		return this.secondClassWaitingListNumber++;
	}
	
	private int sleeperWaitingListNumber = 1;
	private int getSleeperWaitingListNumber() {
		return this.sleeperWaitingListNumber++;
	}
	
	private int sittingWaitingListNumber = 1;
	private int getSittingWaitingListNumber() {
		return this.sittingWaitingListNumber++;
	}
	public void reduceWaitingListSeatCount(Coach coach) {
		if(coach.getCoachName().equals(FIRST_CLASS))
		    this.firstClassWaitingListNumber--;
		if(coach.getCoachName().equals(SECOND_CLASS))
		    this.secondClassWaitingListNumber--;
		if(coach.getCoachName().equals(SLEEPER))
		    this.sleeperWaitingListNumber--;
		if(coach.getCoachName().equals(SITTING))
		    this.sittingWaitingListNumber--;
	}
	public static final String waitingListKeyWord = "WL ";
	public String getWaitingListNumber(Coach coach) {
		if(coach.getCoachName().equals(FIRST_CLASS))
			return waitingListKeyWord+getFirstClassWaitingListNumber();
		if(coach.getCoachName().equals(SECOND_CLASS))
			return waitingListKeyWord+getSecondClassWaitingListNumber();
		if(coach.getCoachName().equals(SLEEPER))
			return waitingListKeyWord+getSleeperWaitingListNumber();
		if(coach.getCoachName().equals(SITTING))
			return waitingListKeyWord+getSittingWaitingListNumber();
		return null;
	}

	public List<Seat> getWaitingListSeats(Coach coach) {
		if(coach.getCoachName().equals(FIRST_CLASS))
			return firstClassWaitingListSeats;
		else if(coach.getCoachName().equals(SECOND_CLASS))
			return secondClassWaitingListSeats;
		else if(coach.getCoachName().equals(SLEEPER))
			return sleeperWaitingListSeats;
		else if(coach.getCoachName().equals(SITTING))
			return sittingWaitingListSeats;
		else 
			return null;
	}

	public List<Seat> getRACSeats() {
		return RACSeats;
	}
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyHH:mm");
//    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("");
	public boolean canBookNow() {
		Date trainTime = null;
		try {
			trainTime = dateFormat.parse(this.getTrainDate().getDate()+this.time);
//			date = timeFormat.parse(this.time);
		} catch (ParseException e) {
			System.out.println("Date Parsing Exception.");
		}
   
		Date currentTime = new Date();
		long differenceInTime = trainTime.getTime() - currentTime.getTime();
		if(differenceInTime > 1)  //43200000
			return true;
		
		return false;
	}
}
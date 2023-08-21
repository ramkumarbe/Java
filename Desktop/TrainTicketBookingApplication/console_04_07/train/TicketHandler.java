package train;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bank.Bank;
import bank.BankAccount;
import passenger.Passenger;
import passenger.User;
import passenger.UserUtil;


public class TicketHandler {
	
	private static TicketHandler obj = null;
	private TicketHandler() { }
	public static TicketHandler getInstance() {
		if(obj == null)
			obj = new TicketHandler();
		return obj;
	}
	
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yy  HH:mm:ss");
    private final static byte FIRST_CLASS_BOOKING = 1;
	private final static byte SECOND_CLASS_BOOKING = 2;
	private final static byte SLEEPER_BOOKING = 3;
	private final static byte SITTING_BOOKING = 4;
	private final static byte EXIT = 5;
	private final static byte GENDER_FEMALE = 1;
	private final static byte GENDER_MALE = 2;
	private final static byte GENDER_OTHERS = 3;
	private final static String FEMALE = "Female";
	private final static String MALE = "Male";
	private final static String OTHERS = "Others";
	private final static int NOT_A_NUMBER = -1;
	private final static int NEGATIVE_INTEGER = -2;
	
	public Ticket ticketBooking(User user) {
		Train train = Util.getInstance().getTrain();
		if(!train.canBookNow()) {
			System.out.println("Too late. You can't book a ticket now.");
			return null;
		}
		Ticket ticket = null;
		boolean validInput = false;
		Coach coach = null;
		List<Passenger> passengerList = new ArrayList<Passenger>();
		byte numberOfTickets = 0;
		do {
			validInput = true;
			System.out.println("1.First Class - "+train.availableSeats(Train.FIRST_CLASS));
			System.out.println("2.Second Class - "+train.availableSeats(Train.SECOND_CLASS));
			System.out.println("3.Sleeper - "+train.availableSeats(Train.SLEEPER));
			System.out.println("4.Sitting - "+train.availableSeats(Train.SITTING));
			System.out.println("5.Exit");
			
			byte selection = Util.getInstance().getByteInput();
			switch(selection) {
			case FIRST_CLASS_BOOKING: coach = train.getCoach(Train.FIRST_CLASS); break;
			case SECOND_CLASS_BOOKING: coach = train.getCoach(Train.SECOND_CLASS); break;
			case SLEEPER_BOOKING: coach = train.getCoach(Train.SLEEPER); break;
			case SITTING_BOOKING: coach = train.getCoach(Train.SITTING); break;
			case EXIT: return null;
			default: System.out.println("Invalid input."); validInput = false;
			}
		}while(!validInput);
		
		validInput = false;
		do {
			System.out.print("Enter the number of tickets(upto 6):");
			numberOfTickets = Util.getInstance().getByteInput();
			if(numberOfTickets>0 && numberOfTickets<=12)
				validInput = true;
			else if(numberOfTickets == NOT_A_NUMBER)
				System.out.println("Input must be a number");
			else if(numberOfTickets == NEGATIVE_INTEGER)
				System.out.println("Input must be a positive number");
			else if(numberOfTickets>6)
				System.out.println("Maximum 6 numbers of tickets can be booked at a time");
			else
				System.out.println("Enter Valid Input.");
		}while(!validInput);

		String passengerName = null;
		byte passengerAge = 0;
		String gender = null;
		byte genderChoice = 0;
		boolean genderCheck = false;
		char[] berthPreferences = new char[numberOfTickets];

		for(byte i=0; i<numberOfTickets; i++) {
			do {
				System.out.print("Enter the Name:");
				passengerName = Util.getInstance().getPassengerName();
				if(!UserUtil.getInstance().isValidPassengerName(passengerName))
					System.out.println("Invalid Passenger Name.");
			}while(!UserUtil.getInstance().isValidPassengerName(passengerName));

			do {
				validInput = false;
				System.out.print("Enter the Age:");
				passengerAge = Util.getInstance().getByteInput();
				if(passengerAge>0 && passengerAge<=125)
					validInput = true;
				else if(passengerAge == NOT_A_NUMBER)
					System.out.println("Enter valid Age.");
				else if(passengerAge == NEGATIVE_INTEGER)
					System.out.println("Input must be a positive number");
			}while(!validInput);

			do {
				genderCheck = true;
				System.out.print("1.Female  ");
				System.out.print("2.Male  ");
				System.out.println("3.Others");
				do {
					validInput = false;
					System.out.print("Select the Gender: ");
					genderChoice = Util.getInstance().getByteInput();
					if(genderChoice == NEGATIVE_INTEGER)
						System.out.println("Choice cannot be a negative value.");
					else if(genderChoice == NOT_A_NUMBER) 
						System.out.println("Only Numbers are allowed.");
					else
						validInput = true;
				}while(!validInput);
				switch (genderChoice) {
				case GENDER_FEMALE:
					gender = FEMALE;
					break;
				case GENDER_MALE:
					gender = MALE;
					break;
				case GENDER_OTHERS:
					gender = OTHERS;
					break;
				default:
					System.out.println("Invalid input");
					genderCheck = false;
				}
			} while (!genderCheck);
			passengerList.add(new Passenger(passengerName,passengerAge,gender));
			berthPreferences[i] = train.berthPreference(coach);
			System.out.println();
		}
 
		ticket = getTicket(user, train, coach);
		BankAccount bankAccount = Bank.getInstance().payment(coach.getTicketPrice()*numberOfTickets);
		if(bankAccount!=null) {
			ticket = bookTicket(ticket,bankAccount,passengerList,berthPreferences);
		    user.bookedTickets.add(ticket);
		    train.getTickets().add(ticket);
        	return ticket;
		}
        else 
            System.out.println("Payment transaction failed.");
        return null;
	}
	
	private Ticket bookTicket(Ticket ticket, BankAccount bankAccount, List<Passenger> passengerList, char[] berthPreferences) {
		ticket.setBankAccount(bankAccount);
		ticket.setBookedDate(dateFormat.format(new Date()));
		System.out.println("\t\tTicketNumber: "+ticket.getTicketNumber());
		System.out.println("-----------------------------------------------------------");
		byte berthCount = 0;
		for(Passenger passenger:passengerList) {
			String passengerId = UserUtil.getPassengerId();
			Seat seat = ticket.getTrain().getSeat(ticket.getCoach(),berthPreferences[berthCount++],passenger,passengerId);

			ticket.getSeats().add(seat);
			System.out.println("\t\tPassenger Name: "+passenger.getPassengerName());
			System.out.println("\t\tPassenger Id  : "+passengerId);
			if(seat.getCompartmentName() != null)
				System.out.println("\t\tSeat Number   : "+seat.getCompartmentName()+" - "+seat.getSeatNumber());
			else
				System.out.println("\t\tSeat Number   : "+seat.getSeatNumber());
			System.out.println("-------------------------------------------------------");
		}
		System.out.println("Ticket Booking was successful.");
		System.out.println("Press Enter to get back.");
		Util.getInstance().getBack();
		ticket.getCoach().getSeats().stream().forEach(s->System.out.println(s.getSeatNumber()));
		return ticket;
	}

    private Ticket getTicket(User user, Train train, Coach coach) {
    	return new Ticket(Util.getTicketNumber(),user,train,coach);
    }
    
}

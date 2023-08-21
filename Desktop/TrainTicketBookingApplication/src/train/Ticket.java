package train;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import bank.BankAccount;
import passenger.Passenger;
import passenger.User;

public class Ticket {
	private Train train;
	private String coach;
	private String userName;
	private String seatNumber;
	private String bookedDate;
	private final static byte CASE_FIRST_CLASS = 1;
	private final static byte CASE_SECOND_CLASS = 2;
	private final static byte CASE_SLEEPER = 3;
	private final static byte CASE_SITTING = 4;
	private final static byte CASE_EXIT = 5;
	private final static byte CASE_FEMALE = 1;
	private final static byte CASE_MALE = 2;
	private final static byte CASE_OTHERS = 3;
	private final static String FEMALE = "Female";
	private final static String MALE = "Male";
	private final static String OTHERS = "Others";
	private final static String FIRST_CLASS = "First Class";
	private final static String SECOND_CLASS = "Second Class";
	private final static String SLEEPER = "Sleeper";
	private final static String SITTING = "Sitting";
	private final static String firstClassWaitinglistFirstSeatNumber = "FCWL1";
	private final static String secondClassWaitinglistFirstSeatNumber = "SCWL1";
	private final static String sleeperWaitinglistFirstSeatNumber = "SLWL1";
	private final static String sittingWaitinglistFirstSeatNumber = "SWL1";
	private final static String RACFirstSeatNumber = "RAC1";
	private final static String RACLastSeatNumber = "RAC10";
	private final static String firstClassWaitinglistSeat = "FCWL";
	private final static String secondClassWaitinglistSeat = "SCWL";
	private final static String sleeperWaitinglistSeat = "SLWL";
	private final static String sittingWaitinglistSeat = "SIWL";
	private final static String RACSeat = "RAC";
	private static int passengerId = 1;
	

    Util util = Util.getInstance();
	public Ticket() { }
	public Ticket(String userName, Train train, String bookedDate, String coach, String seatNumber) {
		this.train = train;
		this.setCoach(coach);
		this.setUserName(userName);
		this.setSeatNumber(seatNumber);
		this.setBookedDate(bookedDate);
	}
	public Ticket bookTicket(String userName) {
		System.out.println("Select Destination: ");
		String destination = new Train().selectDestination();
		System.out.println("Select Date: ");
		String date = TrainDate.getInstance().getDate();
		System.out.println("Select Train: ");
		Train train = new Train().getTrain(destination, date);
		Ticket ticket = null;
		boolean validInput = false;
		do {
			validInput = true;
			System.out.println("1.First Class");
			System.out.println("2.Second Class");
			System.out.println("3.Sleeper");
			System.out.println("4.Sitting");
			System.out.println("5.Exit");
			int selection = util.getByteInput();

			switch (selection) {

			case CASE_FIRST_CLASS:
				ticket = bookTicket(train, FIRST_CLASS, userName, train.firstClassSeats, Train.firstClassPrice);
				break;

			case CASE_SECOND_CLASS:
				ticket = bookTicket(train, SECOND_CLASS, userName, train.secondClassSeats, Train.secondClassPrice);
				break;

			case CASE_SLEEPER:
				ticket = bookTicket(train, SLEEPER, userName, train.sleeperSeats, Train.sleeperPrice);
				break;

			case CASE_SITTING:
				ticket = bookTicket(train, SITTING, userName, train.sittingSeats, Train.sittingPrice);
				break;

			case CASE_EXIT:
				break;

			default:
				System.out.println("Enter valid input.");
				validInput = false;
			}
		} while (!validInput);
		return ticket;
	}

	private Ticket bookTicket(Train train, String coach, String userName, List<String> seats, int ticketPrice) {
		SimpleDateFormat dateformat = new SimpleDateFormat("dd MMMM yyyy 'at' HH:mm:ss");
		List<Passenger> pass = new LinkedList<Passenger>();
		System.out.print("Enter the Number of Tickets: ");
		int ticketNeeded = util.getByteInput();
		String passengerName;
		int age;
		boolean genderCheck;
		boolean validAge;
		String gender = null;
		Ticket ticket = null;
		int totalAmount = 0;
		for (int i = 0; i < ticketNeeded; i++) {
			do {
				System.out.print("Enter the Passenger Name: ");
				passengerName = util.getPassengerName();
				if (!util.isValidPassengerName(passengerName))
					System.err.println("Invalid Username.");
			} while (!util.isValidPassengerName(passengerName));

			do {
				validAge = true;
				System.out.print("Enter the Age: ");
			    age = util.getByteInput();
			    if(age<1 && age>100) {
			    	System.out.println("Enter valid Age.");
			    	validAge = false;
			    }
			}while(!validAge);
			do {
				genderCheck = true;
				System.out.print("1.Female  ");
				System.out.print("2.Male  ");
				System.out.println("3.Others");
				System.out.print("Select the Gender: ");
				int choice = util.getByteInput();
				switch (choice) {
				case CASE_FEMALE:
					gender = FEMALE;
					break;
				case CASE_MALE:
					gender = MALE;
					break;
				case CASE_OTHERS:
					gender = OTHERS;
					break;
				default:
					System.out.println("Invalid input");
					genderCheck = false;
				}
			} while (!genderCheck);

			System.out.println();
			String seatNumber = train.getSeats(coach);
			Date date = new Date();
			String bookedDate = dateformat.format(date);
			ticket = new Ticket(userName,train,bookedDate,coach,seatNumber);
			Passenger passenger = new Passenger(passengerId, passengerName, age, gender, ticket);
			pass.add(passenger);
			passengerId++;
			totalAmount += ticketPrice;
		}
		BankAccount bankAccount = new BankAccount();
		bankAccount = bankAccount.payment(totalAmount);
		if (bankAccount != null) {
			confirmTicket(userName, ticket, pass, bankAccount);
			System.out.println("Press Enter to get back");
			util.getBack();
		} else {
			totalAmount = 0;
			passengerId -= ticketNeeded;
			System.err.println("Payment transaction failed.");
			for (int i = pass.size() - 1; i >= 0; i--) {
				Passenger passengerr = pass.get(i);
				seats.add(0, passengerr.getTicket().getSeatNumber());
				train.availableSeats.replace(passengerr.getTicket().getSeatNumber(), true);
			}
			System.out.println("Press Enter to get back");
			util.getBack();
		}
		return ticket;
	}

	private void confirmTicket(String userName,Ticket ticket, List<Passenger> pass, BankAccount bankAccount	) {
		User user = TrainMain.getUser(userName);
		for (Passenger passenger : pass) {
			ticket.getTrain().addPassenger(passenger);
			user.passengersList.add(passenger);
			System.out.println("\t\t\t ----------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t Passenger id : " + passenger.getPassengerId());
			System.out.println("\t\t\t\t\t Seat Number  : " + passenger.getTicket().getSeatNumber());
			System.out.println("\t\t\t ----------------------------------------------------------------------");
		}
		System.out.println("Ticket booking was successful.");
	}
	
	public void viewBookedTickets(User user) {
		for (Passenger passenger: user.passengersList) {
			if (passenger.getTicket().getUserName().equals(userName)) {
				System.out.println("\t\t\t ----------------------------------------------------------------------");
				System.out.println("\t\t\t\t\t Passenger ID  : " + passenger.getPassengerId());
				System.out.println("\t\t\t\t\t Passenger Name: " + passenger.getPassengerName());
				System.out.println("\t\t\t\t\t Passenger Age : " + passenger.getAge());
				System.out.println("\t\t\t\t\t Gender        : " + passenger.getGender());
				System.out.println("\t\t\t\t\t Destination   : " + passenger.getTicket().train.getDestination());
				System.out.println("\t\t\t\t\t Train Name    : " + passenger.getTicket().train.getTrainName());
				System.out.println("\t\t\t\t\t Coach         : " + passenger.getTicket().getCoach());
				System.out.println("\t\t\t\t\t Seat Number   : " + passenger.getTicket().getSeatNumber());
				System.out.println("\t\t\t\t\t Booked User   : " + passenger.getTicket().getUserName());
				System.out.println("\t\t\t\t\t Booked Time   : " + passenger.getTicket().getBookedDate());
				System.out.println("\t\t\t\t\t Departure Date: " + passenger.getTicket().train.getDate() + " at "
						+ passenger.getTicket().train.getTime());
				System.out.println("\t\t\t ----------------------------------------------------------------------");
			}
		}
	}

	private void userBookedTickets(User user) {
		for (Passenger passenger : user.passengersList) {
			System.out.println("\t\t\t ----------------------------------------------------------------------");
			System.out.println("\t\t\t\t\t Passenger Name :" + passenger.getPassengerName());
			System.out.println("\t\t\t\t\t Passenger Id   :" + passenger.getPassengerId());
			System.out.println("\t\t\t ----------------------------------------------------------------------");
		}
	}

	public void cancelTicket(User currentUser) {
		String password;
		userBookedTickets(currentUser);
			System.out.print("Enter the ID: ");
			int idGiven = util.getNumber();
			boolean idMatch = false;
			for (Passenger passenger : currentUser.passengersList)
				if (passenger.getPassengerId() == idGiven)
					if (passenger.getTicket().getUserName().equals(userName))
						idMatch = true;

			if (idMatch) {
				loopPassenger: for (Passenger cancelPassenger : currentUser.passengersList) {
					if (idGiven == cancelPassenger.getPassengerId()) {
						System.out.println("Passenger name :" + cancelPassenger.getPassengerName());
						System.out.print("Enter your password to cancel ticket: ");
						password = util.getPassword();
						if (currentUser.getPassword().equals(password)) {
							if (cancelPassenger.getTicket().getCoach() == FIRST_CLASS) {
								if (cancelPassenger.getTicket().train.firstClassWaitingList.size() > 0) {
									addWaitingListPassenger(cancelPassenger,firstClassWaitinglistSeat,firstClassWaitinglistFirstSeatNumber,cancelPassenger.getTicket().getSeatNumber());
									cancelPassenger.getTicket().train.firstClassWaitingList.remove();
								} else {
									cancelPassenger.getTicket().train.firstClassSeats
									.add(cancelPassenger.getTicket().getSeatNumber());
									cancelPassenger.getTicket().train.availableSeats
									.replace(cancelPassenger.getTicket().getSeatNumber(), true);
								}
							}

							else if (cancelPassenger.getTicket().getCoach() == SECOND_CLASS) {
								if (cancelPassenger.getTicket().train.secondClassWaitingList.size() > 0) {
									addWaitingListPassenger(cancelPassenger,secondClassWaitinglistSeat,secondClassWaitinglistFirstSeatNumber,cancelPassenger.getTicket().getSeatNumber());
									cancelPassenger.getTicket().train.secondClassWaitingList.remove();
								} else {
									cancelPassenger.getTicket().train.secondClassSeats
									.add(cancelPassenger.getTicket().getSeatNumber());
									cancelPassenger.getTicket().train.availableSeats
									.replace(cancelPassenger.getTicket().getSeatNumber(), true);
								}
							}

							else if (cancelPassenger.getTicket().getCoach() == SLEEPER) {
								if (cancelPassenger.getTicket().train.RACSeats.size() < 10) {
									addWaitingListPassenger(cancelPassenger,RACSeat,RACFirstSeatNumber,cancelPassenger.getTicket().getSeatNumber());
									if (cancelPassenger.getTicket().train.sleeperWaitingList.size() > 0) {
										addWaitingListPassenger(cancelPassenger,sleeperWaitinglistSeat,sleeperWaitinglistFirstSeatNumber,RACLastSeatNumber);
										cancelPassenger.getTicket().train.sleeperWaitingList.remove();
									}
								} else {
									cancelPassenger.getTicket().train.sleeperSeats
									.add(cancelPassenger.getTicket().getSeatNumber());
									cancelPassenger.getTicket().train.availableSeats
									.replace(cancelPassenger.getTicket().getSeatNumber(), true);
								}
							}

							else if (cancelPassenger.getTicket().getCoach() == SITTING) {
								if (cancelPassenger.getTicket().train.sittingWaitingList.size() > 0) {
									addWaitingListPassenger(cancelPassenger,sittingWaitinglistSeat,sittingWaitinglistFirstSeatNumber,cancelPassenger.getTicket().getSeatNumber());
									cancelPassenger.getTicket().train.sittingWaitingList.remove();

								} else {
									cancelPassenger.getTicket().train.sittingSeats
									.add(cancelPassenger.getTicket().getSeatNumber());
									cancelPassenger.getTicket().train.availableSeats
									.replace(cancelPassenger.getTicket().getSeatNumber(), true);
								}
							}
                            currentUser.passengersList.remove(cancelPassenger);
							cancelPassenger.getTicket().train.passengerList.remove(cancelPassenger);
							System.out.println("Your Ticket has been cancelled.");
								System.out.println("Press Enter to get back.");
								util.getBack();
								break loopPassenger;
							} else {
								System.out.println("\t\t\t\t\t Invalid password");
								break loopPassenger;
							}
					}
				}
			} else {
				System.out.println("\t\t\t\t\t Invalid Passenger Id.");
			}
		
	}

	private void addWaitingListPassenger(Passenger cancelPassenger, String waitinglistSeat,
			String waitinglistFirstSeatNumber, String newSeatNumber) {
		for (Passenger passenger : cancelPassenger.getTicket().train.passengerList) {
			if (passenger.getTicket().getSeatNumber()
					.equals(waitinglistFirstSeatNumber)) {
				passenger.getTicket()
				.setSeatNumber(newSeatNumber);
			} else if (passenger.getTicket().getSeatNumber()
					.contains(waitinglistSeat)) {
				String seatnumber = passenger.getTicket().getSeatNumber();
				seatnumber = waitinglistSeat
						+ (Integer.parseInt(seatnumber.substring(4)) - 1);
				passenger.getTicket().setSeatNumber(seatnumber);
			}
		}
	}
	public Train getTrain() {
		return this.train;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(String bookedDate) {
		this.bookedDate = bookedDate;
	}

}

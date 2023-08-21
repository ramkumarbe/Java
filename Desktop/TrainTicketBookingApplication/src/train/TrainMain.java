package train;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import bank.BankAccount;
import passenger.User;

public class TrainMain {
	private static final byte bookTicket = 1;
	private static final byte viewTicket = 2;
	private static final byte cancelTicket = 3;
	private static final byte availableTicket = 4;
	private static final byte changeUser = 5;
	private static final byte viewBookingStatus = 6;
	private static final byte changeTime = 7;
	private static final byte exit = 0;
	private static final String admin = "Admin";
	private static TrainMain object = null;
    static SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
	private TrainMain() { }

	public static TrainMain getInstance() {
		if (object == null)
			object = new TrainMain();
		return object;
	}

	public static List<User> userDetails = new ArrayList<User>();
	public static List<Train> trains = new ArrayList<Train>();
	public static List<String> dates = new ArrayList<String>();
	public static List<BankAccount> bankAccountDetails = new ArrayList<BankAccount>();
    
	public static void main(String[] args) {
		new User().addUsers();
		new BankAccount().addBankAccounts();
		System.out.println("\t\t\t\t\t Online Train Booking");
		Train train = new Train();
		TrainDate.getInstance().addDate();
		train.addTrain();
		LoginPage loginpage = LoginPage.getInstance();
		loginpage.entryPage();
		System.out.println("\nWelcome, " + loginpage.getUserName()+"\n");
		User user = getUser(loginpage.getUserName());
		Ticket ticket = new Ticket();
		boolean selection = true;
		do {
			System.out.println("1.Book Ticket");
			System.out.println("2.View Booked Ticket");
			System.out.println("3.Cancel Ticket");
			System.out.println("4.View Available Ticket");
			System.out.println("5.Log out");
			if(user.getUserName().equals(admin)) {
				System.out.println("6.Booking Status");
				System.out.println("7.Change Train Time");
			}
			System.out.println("Click '0' to Exit.");

			byte choice = Util.getInstance().getByteInput();
			switch (choice) {

			case bookTicket: ticket = ticket.bookTicket(user.getUserName()); break;

			case viewTicket: 
				if(user.passengersList.size()>0) 
				    ticket.viewBookedTickets(user); 
			    else
				    System.out.println("You haven't booked any tickets\n");
			break;

			case cancelTicket: 
				if(user.passengersList.size()>0) 
					ticket.cancelTicket(user); 
			    else
				    System.out.println("You haven't booked any tickets\n"); break;

			case availableTicket: train.availableSeats(); break;

			case changeUser: loginpage.entryPage(); user = getUser(loginpage.getUserName()); break;

			case exit: selection = false; break;

			default:
				if(user.getUserName().equals(admin)) {
					if(choice == viewBookingStatus) 
						train.viewBookingStatus();
					else if(choice == changeTime) 
						train.changeTime();
					else
						System.out.println("Enter the Valid input.");
				}
				else
				System.out.println("Enter the Valid input.");
			}
		} while (selection);
	}

	public static User getUser(String userName) {
		for (User user : userDetails)
			if (user.getUserName().equals(userName))
				return user;
		return null;
	}

	public static void addUser(User user) {
		userDetails.add(user);
	}
}

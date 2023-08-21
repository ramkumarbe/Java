package train;

import java.util.Scanner;

import passenger.Passenger;

public class Main {
	public static void main(String[] args) {
    	System.out.println("\t\t\t\t\t\t Online Train Booking");
    	Scanner sc = new Scanner(System.in);
	    LoginPage loginpage = new LoginPage();
		loginpage.entryPage();
		DateAndTime dateAndTime  = new DateAndTime();
	    dateAndTime.updateDate();
	    Train.addTrains();
	    Train.updateTrain(DateAndTime.dates);
		String destination = Train.selectDestination();
	    String date = dateAndTime.getDate();
		Train train = Train.getTrain(destination, date);
	    Ticket ticket = new Ticket(train);
    	boolean selection = true;
    	do {
        	System.out.println("1.Book Ticket");
        	System.out.println("2.View Booked Ticket");
        	System.out.println("3.Cancel Ticket");
        	System.out.println("4.View Available Ticket");
        	System.out.println("5.Select Train");
        	System.out.println("6.Select Date");
        	System.out.println("7.Select Destination");
        	System.out.println("8.Log out");
        	System.out.println("Click Enter to Exit.");
    		String choice;
        	choice = sc.nextLine();
        	switch(choice) {
        	
        	case "1": ticket.bookTicket(loginpage.getUserName(), destination); break;
        	
        	case "2": ticket.viewBookedTickets(Passenger.passengerList, loginpage.getUserName()); break;
        	
        	case "3": ticket.cancelTicket(Passenger.passengerList, loginpage.getUserName()); break;
        	
        	case "4": train.availableSeats(); System.out.println("Press Enter to get back."); sc.nextLine(); break;

        	case "8": loginpage.entryPage();

        	case "7": destination = Train.selectDestination();

        	case "6": date = dateAndTime.getDate();
        	
        	case "5": train = Train.getTrain(destination, date); ticket = Ticket.getTicket(train); break;
        	 
        	case "" : selection = false; break;
        	
        	default : System.err.println("Enter the Valid input.");
        	}
    	}while(selection);
    	sc.close();
	}
}

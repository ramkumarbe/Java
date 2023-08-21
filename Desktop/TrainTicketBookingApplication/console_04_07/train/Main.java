package train;

import passenger.User;
import passenger.UserUtil;

public class Main {

	private static final byte BOOK_TICKET = 1;
	private static final byte VIEW_TICKET = 2;
	private static final byte CANCEL_TICKET = 3;
	private static final byte AVAILABLE_TICKET = 4;
	private static final byte CHANGE_USER = 5;
	private static final byte CANCEL_TRAIN = 6;
	private static final byte CHANGE_TIME = 7;
	private static final byte EXIT = 0;
	
	public static void main(String[] args) {
		Util.getInstance().loader();
		User user = UserUtil.getInstance().userLogin();
		Train train = null;
		boolean selection = true;
		do {
			System.out.println("1.Book Ticket");
			System.out.println("2.View Booked Ticket");
			System.out.println("3.Cancel Ticket");
			System.out.println("4.View Available Ticket");
			System.out.println("5.Log out");
			if(user.getUserName().equals(UserUtil.getInstance().ADMIN_USERNAME)) {
				System.out.println("6.Booking Status");
				System.out.println("7.Change Train Time");
			}
			System.out.println("Click '0' to Exit.");

			byte choice = Util.getInstance().getByteInput();
			switch (choice) {

			case BOOK_TICKET: 
				TicketHandler.getInstance().ticketBooking(user);; break;

			case VIEW_TICKET: 
				if(user.bookedTickets.size()>0) 
				    user.viewTickets(); 
			    else
				    System.out.println("You haven't booked any tickets\n");
			break;

			case CANCEL_TICKET: 
				if(user.bookedTickets.size()>0) 
					user.cancelTicket(); 
			    else
				    System.out.println("You haven't booked any tickets\n"); break;

			case AVAILABLE_TICKET: train = Util.getInstance().getTrain(); train.viewAvailableSeats(); break;

			case CHANGE_USER: user = UserUtil.getInstance().userLogin();break;

			case EXIT: selection = false; break;

			default:
				if(user.getUserName().equals(UserUtil.getInstance().ADMIN_USERNAME)) {
					if(choice == CANCEL_TRAIN) {
						train = Util.getInstance().getTrain();
						train.removeTickets();
						Util.getInstance().trains.remove(train);
					}
					else if(choice == CHANGE_TIME) {
						train = Util.getInstance().getTrain();
						train.changeTime();
					}
					else
						System.out.println("Enter the Valid input.");
				}
				else
				System.out.println("Enter the Valid input.");
			}
		} while (selection);
	}

}

package train;

import java.util.ArrayList;
import java.util.List;

import bank.BankAccount;
import passenger.User;

public class Ticket {
	private Train train;
	private Coach coach;
	private User user;
	private String bookedDate;
	private BankAccount bankAccount;
	private String ticketNumber;
	
	List<Seat> seats = new ArrayList<Seat>();
	
	public Ticket(String ticketNumber, User user, Train train, Coach coach) {
		this.ticketNumber = ticketNumber;
		this.train = train;
		this.user = user;
		this.coach = coach;
	}

	public Train getTrain() {
		return train;
	}

	public Coach getCoach() {
		return coach;
	}

	public User getUser() {
		return user;
	}

	public String getBookedDate() {
		return bookedDate;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public void setBookedDate(String bookedDate) {
		this.bookedDate = bookedDate;
	}
	
	public List<Seat> getSeats() {
		return seats;
	}

}

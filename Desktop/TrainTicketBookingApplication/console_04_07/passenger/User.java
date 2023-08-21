package passenger;

import java.util.ArrayList;
import java.util.List;

import bank.Bank;
import bank.BankAccount;
import train.Seat;
import train.Ticket;
import train.Train;
import train.Util;


public class User {
	private String userName;
	private String password;
	private String phoneNumber;
	
    public User(String userName, String password, String phoneNumber) {
		this.userName = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}

	public List<Ticket> bookedTickets = new ArrayList<Ticket>();

	public void viewTickets() {
		System.out.println("--------------------------------------------------------------");
		for(Ticket ticket:this.bookedTickets) {
			System.out.println("\t\tTicket Number  :"+ticket.getTicketNumber());
			System.out.println("\t\tTrain Number   :"+ticket.getTrain().getTrainNumber());
			System.out.println("\t\tTrain Name     :"+ticket.getTrain().getTrainName());
			System.out.println("\t\tDestination    :"+ticket.getTrain().getStation().getStationName());
			System.out.println("\t\tDeparture Date :"+ticket.getTrain().getTrainDate().getDate());
			System.out.println("\t\tDeparture Time :"+ticket.getTrain().getTime());
			System.out.println("\t\tCoach          :"+ticket.getCoach().getCoachName());
			System.out.println("\t\tBooked Time    :"+ticket.getBookedDate());
			
			for(Seat seat:ticket.getSeats()) {
				System.out.println("\t\tPassenger Name :"+seat.getPassenger().getPassengerName());
				System.out.println("\t\tPassenger Age  :"+seat.getPassenger().getAge());
				if(seat.getCompartmentName() != null)
				    System.out.println("\t\tSeat Number    :"+seat.getCompartmentName()+" - "+seat.getSeatNumber());
				else
				    System.out.println("\t\tSeat Number    :"+seat.getSeatNumber());
			}
			System.out.println("--------------------------------------------------------------");
			System.out.println();
		}
	}

	public void cancelTicket() {
		for(Ticket ticket:bookedTickets) {
			System.out.println("\t\tTicket Number: "+ticket.getTicketNumber());
			System.out.println("--------------------------------------------------------------");
			for(Seat seat:ticket.getSeats()) {
				System.out.println("\t\tPassenger Id: "+seat.getPassengerId());
				System.out.println("\t\tTicket Number: "+seat.getPassenger().getPassengerName());
			}
			System.out.println("--------------------------------------------------------------");
		}
		int refundAmount = 0;
		System.out.print("Enter the Ticket Number or Passenger Id:");
		String choice = Util.getInstance().getStringInput().toUpperCase();
		System.out.print("Enter your password:");
		String password = Util.getInstance().getStringInput();
		boolean validId = false;
		BankAccount bankAccount = null;
		if(password.equals(this.password)) {
			loopTicket:
				for(Ticket ticket:bookedTickets) {
					bankAccount = ticket.getBankAccount();
					if(choice.equals(ticket.getTicketNumber())) {
						validId = true;
						removeTicket(ticket);
						refundAmount += ticket.getSeats().size()*ticket.getCoach().getTicketPrice();
						break;
					}
					for(Seat seat:ticket.getSeats()) {
						if(choice.equals(seat.getPassengerId())) {
							validId = true;
							removePassenger(ticket,seat);
							refundAmount += ticket.getCoach().getTicketPrice();
							if(ticket.getSeats().size()==0) {
								removeTicket(ticket);
							}
							break loopTicket;
						}
					}
				}
			if(!validId) {
				System.out.println("Invalid Ticket or Passenger Id.");
				return;
			}
		    Bank.getInstance().refund(bankAccount,refundAmount);
			System.out.println("Cancellation Successful.");
		}
		else
			System.out.println("Invalid Password");
		System.out.println();
	}
    private static final String sleeperSeatKeyWord = "-";
	private static final String waitingListSeatKeyWord = "WL";
	
	private void removePassenger(Ticket ticket, Seat seat) {
		ticket.getSeats().remove(seat);
		if(ticket.getCoach().getCoachName().equals(Train.SLEEPER)) {
			if(seat.getSeatNumber().contains(sleeperSeatKeyWord)) { //To remove sleeper booked Seat
				if(ticket.getTrain().getWaitingListSeats(ticket.getCoach()).size()>0) {
					moveRACSeats(ticket,seat);
					moveWaitingListSeats(ticket,ticket.getTrain().getRACSeats().get(ticket.getTrain().getRACSeats().size()-1));
				}

				else if(ticket.getTrain().getRACSeats().size()>0){
					moveRACSeats(ticket,seat);
				}
				else {
					seat.setIsBooked(false);
				}
			}
			else if(seat.getSeatNumber().contains(waitingListSeatKeyWord)){ //To remove sleeper waiting list seat
				if(ticket.getTrain().getWaitingListSeats(ticket.getCoach()).size() == 1) {
					ticket.getTrain().getWaitingListSeats(ticket.getCoach()).remove(0);
				}
				moveWaitingListSeats(ticket,seat);
			}
			else { //To remove RAC Seat
				if(ticket.getTrain().getWaitingListSeats(ticket.getCoach()).size()>0) { //if waiting list available
					moveRACSeats(ticket,seat);
					moveWaitingListSeats(ticket,ticket.getTrain().getRACSeats().get(ticket.getTrain().getRACSeats().size()-1));
				}
				else { 
					moveRACSeats(ticket,seat);
				}
			}
		}
		else if(seat.getCompartmentName() == null || ticket.getTrain().getWaitingListSeats(ticket.getCoach()).size()>0) {  //To remove waiting list passenger
			moveWaitingListSeats(ticket,seat);
		}
		else {
			seat.setIsBooked(false);
		}
	}

	private void moveWaitingListSeats(Ticket ticket, Seat seat) {
		System.out.println(ticket.getTrain().getWaitingListSeats(ticket.getCoach()));
		List<Seat> waitingListSeats = ticket.getTrain().getWaitingListSeats(ticket.getCoach());
		int seatIndex = waitingListSeats.indexOf(seat);
		if (seat.getSeatNumber().contains(waitingListSeatKeyWord)) {
			for (int i=seatIndex+1; i<waitingListSeats.size(); i++) {
				Seat currentSeat = waitingListSeats.get(i);
				Seat previousSeat = waitingListSeats.get(i - 1);

				previousSeat.setPassenger(currentSeat.getPassenger());
				previousSeat.setPassengerId(currentSeat.getPassengerId());

				updateNewSeat(previousSeat, currentSeat);
			}
		} 

		else {
			Seat previousSeat = seat;
			Seat currentSeat = waitingListSeats.get(0);

			previousSeat.setPassenger(currentSeat.getPassenger());
			previousSeat.setPassengerId(currentSeat.getPassengerId());

			updateNewSeat(seat, waitingListSeats.get(0));
            
			for (int i = 1; i < waitingListSeats.size(); i++) {
				currentSeat = waitingListSeats.get(i);
				previousSeat = waitingListSeats.get(i - 1);

				previousSeat.setPassenger(currentSeat.getPassenger());
				previousSeat.setPassengerId(currentSeat.getPassengerId());

				updateNewSeat(previousSeat, currentSeat);
			}
		}

		ticket.getTrain().getWaitingListSeats(ticket.getCoach()).forEach(s->System.out.println(s.getSeatNumber()));
		ticket.getTrain().getRACSeats().stream().forEach(s->System.out.println(s.getSeatNumber()));
		ticket.getTrain().getWaitingListSeats(ticket.getCoach()).remove(waitingListSeats.size() - 1);

		System.out.println();
		ticket.getTrain().getWaitingListSeats(ticket.getCoach()).forEach(s->System.out.println(s.getSeatNumber()));
		ticket.getTrain().getRACSeats().stream().forEach(s->System.out.println(s.getSeatNumber()));
		ticket.getTrain().reduceWaitingListSeatCount(ticket.getCoach());
	}
	private void moveRACSeats(Ticket ticket, Seat seat) {
		List<Seat> RACSeats = ticket.getTrain().getRACSeats();
		if (seat.getSeatNumber().contains(sleeperSeatKeyWord)) {
			Seat firstSeat = RACSeats.get(0);

			seat.setPassenger(firstSeat.getPassenger());
			seat.setPassengerId(firstSeat.getPassengerId());

			updateNewSeat(seat, firstSeat);

			for (int i=1; i<RACSeats.size(); i++) {

				Seat currentSeat = RACSeats.get(i);
				Seat previousSeat = RACSeats.get(i-1);

				previousSeat.setPassenger(currentSeat.getPassenger());
				previousSeat.setPassengerId(currentSeat.getPassengerId());

				updateNewSeat(previousSeat, currentSeat);
			}
		} 
		else {
			for (int i=RACSeats.indexOf(seat)+1; i<RACSeats.size(); i++) {
				
				Seat currentSeat = RACSeats.get(i);
				Seat previousSeat = RACSeats.get(i-1);
				
				if (currentSeat.getIsBooked()) {
					previousSeat.setPassenger(currentSeat.getPassenger());
					previousSeat.setPassengerId(currentSeat.getPassengerId());

					updateNewSeat(previousSeat, currentSeat);
				}
				else {
					break;
				}
			}
		}
		boolean racFilled = true;
		for(int i=0; i<RACSeats.size(); i++) {
			if(!RACSeats.get(i).getIsBooked()) {
				RACSeats.get(i).setBooked(false);
				racFilled = false;
				break;
			}
		}
		if(racFilled&&ticket.getTrain().getWaitingListSeats(ticket.getCoach()).size()==0) {
			RACSeats.get(RACSeats.size()-1).setBooked(false);
		}
	}

	private void updateNewSeat(Seat previousSeat, Seat currentSeat) {
		for(User user:UserUtil.getInstance().userDetails) {
			for(Ticket ticket:user.bookedTickets) {
				for(Seat seat:ticket.getSeats()) {
					if(seat.getSeatNumber().equals(currentSeat.getSeatNumber())) {
						ticket.getSeats().remove(currentSeat);
						ticket.getSeats().add(previousSeat);
						break;
					}
				}
			}
		}
	}

	private void removeTicket(Ticket ticket) {
		for(Seat seat:ticket.getSeats()) 
			removePassenger(ticket,seat);
		
		ticket.getTrain().getTickets().remove(ticket);
		this.bookedTickets.remove(ticket);
	}

	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	void setPassword(String password) {
		this.password = password;
	}
}

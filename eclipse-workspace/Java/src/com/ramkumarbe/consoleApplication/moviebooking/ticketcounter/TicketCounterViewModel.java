package com.ramkumarbe.consoleApplication.moviebooking.ticketcounter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ramkumarbe.consoleApplication.moviebooking.bookticket.BookTicket;
import com.ramkumarbe.consoleApplication.moviebooking.dto.Movie;
import com.ramkumarbe.consoleApplication.moviebooking.dto.Show;
import com.ramkumarbe.consoleApplication.moviebooking.dto.Ticket;
import com.ramkumarbe.consoleApplication.moviebooking.dto.User;
import com.ramkumarbe.consoleApplication.moviebooking.repository.MovieRepository;
import com.ramkumarbe.consoleApplication.moviebooking.util.Util;

public class TicketCounterViewModel {

	private TicketCounter ticketCounter;
	List<Show> showsList;
	
	public TicketCounterViewModel(TicketCounter ticketCounter) {
		this.ticketCounter = ticketCounter;
		loadMovies();
	}
	
    private void loadMovies() {
		try {
			MovieRepository.getInstance().loadMovies();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
	public void getViewBookedTickets(User user) {
		List<Ticket> bookedTickets = new ArrayList<Ticket>();
		try {
			bookedTickets = MovieRepository.getInstance().getBookedTickets(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (bookedTickets.isEmpty()) {
			ticketCounter.showMessage("No tickets booked yet.");
		} else {
			ticketCounter.printTickets(bookedTickets);
		}
	}

	public List<Movie> getMoviesList() {
		return MovieRepository.getInstance().getAvailableMovies();
	}

	public Movie selectedMovie(int n) {
		if(n<=0||n>MovieRepository.getInstance().getAvailableMovies().size()) {
			return null;
		}
		return MovieRepository.getInstance().getAvailableMovies().get(n-1);
	}

	public Ticket bookTicket(User currentUser, Show selectedShow, List<Integer> seats) {
		BookTicket bookTicket = new BookTicket();
		Ticket ticket = bookTicket.bookTicket(currentUser, selectedShow, seats);
		ticket.getSeats().addAll(seats);
		return ticket;
	}
	
	public Show getSelectedShow(int n) {
		if(n<=0||n>showsList.size()) {
			return null;
		}
		return showsList.get(n-1);
	}
	
	public boolean confirmBooking(Ticket ticket) {
	    if(ticket != null) {
	    	ticketCounter.showMessage("Ticket Booking was Successful.");
	    	return true;
	    }
	    else {
	    	ticketCounter.showMessage("Ticket Booking was not successful.");
	    	return false;
	    }
	}

	public List<Show> getShowsList(Movie selectedmovie) {
		showsList = new ArrayList<>();
	     try {
			showsList = MovieRepository.getInstance().getShowsList(selectedmovie);
		} catch (SQLException e) {
			e.printStackTrace();
			ticketCounter.showMessage("Cannot show shows list. Try again.");
		}
	     return showsList;
	}

	public String[] getAvailableSeats(Show selectedShow) {
		String[] seats = null;
		try {
			seats = MovieRepository.getInstance().getAvailableSeats(selectedShow);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seats;
	}

	public List<Integer> getAvailableSeats(String[] seats, int numberOfSeats) {
		List<Integer> selectedSeats = new ArrayList<>();
		for(int i=1; i<=numberOfSeats; i++) {
			System.out.print("Enter the seat number"+i+": ");
			int seatNumber = Util.getInstance().getInt();
			if(seatNumber <= 0) {
				return null;
			}
			if(!seats[seatNumber-1].equals("[B]")) {
				selectedSeats.add(seatNumber);
			}
			else {
				System.out.println("This seat is already booked.");
				i--;
			}
		}
		return selectedSeats;
	}

}

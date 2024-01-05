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
	private List<Show> showsList;

	public TicketCounterViewModel(TicketCounter ticketCounter) {
		this.ticketCounter = ticketCounter;
		loadMovies();
		getConnection();
	}

	private void getConnection() {
        try {
        	MovieRepository.getInstance().getConnection();
        	MovieRepository.getInstance().getStatement();
        	MovieRepository.getInstance().loadMovies();
        }catch(SQLException | ClassNotFoundException e){
        	handleSQLException(e, "Error loading movies.");
        }
	}

	private void loadMovies() {
	}

	public void viewBookedTickets(User user) {
		List<Ticket> bookedTickets = getBookedTickets(user);
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
		if (n <= 0 || n > MovieRepository.getInstance().getAvailableMovies().size()) {
			return null;
		}
		return MovieRepository.getInstance().getAvailableMovies().get(n - 1);
	}

	public Ticket bookTicket(User currentUser, Show selectedShow, List<Integer> seats) {
		BookTicket bookTicket = new BookTicket();
		return bookTicket.bookTicket(currentUser, selectedShow, seats);
	}

	public Show getSelectedShow(int n) {
		if (n <= 0 || n > showsList.size()) {
			return null;
		}
		return showsList.get(n - 1);
	}

	public boolean confirmBooking(Ticket ticket) {
		if (ticket != null) {
			ticketCounter.showMessage("Ticket Booking was Successful.");
			return true;
		} else {
			ticketCounter.showMessage("Ticket Booking was not successful.");
			return false;
		}
	}

	public List<Show> getShowsList(Movie selectedMovie) {
		showsList = new ArrayList<>();
		try {
			showsList = MovieRepository.getInstance().getShowsList(selectedMovie);
		} catch (SQLException e) {
			handleSQLException(e, "Error fetching shows list.");
		}
		return showsList;
	}

	public String[] getAvailableSeats(Show selectedShow) {
		try {
			return MovieRepository.getInstance().getAvailableSeats(selectedShow);
		} catch (SQLException e) {
			handleSQLException(e, "Error fetching available seats.");
			return null;
		}
	}

	public List<Integer> getAvailableSeats(String[] seats, int numberOfSeats) {
		List<Integer> selectedSeats = new ArrayList<>();
		for (int i = 1; i <= numberOfSeats; i++) {
			int seatNumber = getSeatNumber(seats);
			if (seatNumber <= 0) {
				return null;
			}
			if (!seats[seatNumber - 1].equals("[B]")) {
				selectedSeats.add(seatNumber);
			} else {
				System.out.println("This seat is already booked.");
				i--;
			}
		}
		return selectedSeats;
	}

	private int getSeatNumber(String[] seats) {
		System.out.print("Enter the seat number: ");
		return Util.getInstance().getInt();
	}

	private List<Ticket> getBookedTickets(User user) {
		try {
			return MovieRepository.getInstance().getBookedTickets(user);
		} catch (SQLException e) {
			handleSQLException(e, "Error fetching booked tickets.");
			return new ArrayList<>();
		}
	}

	private void handleSQLException(Exception e, String errorMessage) {
		e.printStackTrace();
		ticketCounter.showMessage(errorMessage);
	}
}

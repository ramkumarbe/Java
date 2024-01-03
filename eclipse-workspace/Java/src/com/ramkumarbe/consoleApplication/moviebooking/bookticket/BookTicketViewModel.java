package com.ramkumarbe.consoleApplication.moviebooking.bookticket;

import java.sql.SQLException;
import java.util.List;

import com.ramkumarbe.consoleApplication.moviebooking.dto.Show;
import com.ramkumarbe.consoleApplication.moviebooking.dto.Ticket;
import com.ramkumarbe.consoleApplication.moviebooking.dto.User;
import com.ramkumarbe.consoleApplication.moviebooking.repository.MovieRepository;

public class BookTicketViewModel {

	private BookTicket bookTicket;

	public BookTicketViewModel(BookTicket bookTicket) {
		this.bookTicket = bookTicket;
	}

	public Ticket bookTicket(User user, Show show, List<Integer> seats) {
		bookTicket.payment(show.getMovie().getPrice() * seats.size());
		Ticket ticket = new Ticket(show, user);
		ticket.getSeats().addAll(seats);
	    try {
			MovieRepository.getInstance().insertBookedTickets(ticket);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticket;
	}

}

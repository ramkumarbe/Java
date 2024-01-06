package com.ramkumarbe.consoleApplication.moviebooking.ticketcounter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.ramkumarbe.consoleApplication.moviebooking.admin.AdminPanel;
import com.ramkumarbe.consoleApplication.moviebooking.dto.Movie;
import com.ramkumarbe.consoleApplication.moviebooking.dto.Show;
import com.ramkumarbe.consoleApplication.moviebooking.dto.Ticket;
import com.ramkumarbe.consoleApplication.moviebooking.dto.User;
import com.ramkumarbe.consoleApplication.moviebooking.login.Login;
import com.ramkumarbe.consoleApplication.moviebooking.util.Util;

public class TicketCounter {

	private TicketCounterViewModel viewModel;
	private User currentUser;

	public TicketCounter() {
		viewModel = new TicketCounterViewModel(this);
	}

	private static final String LINE_SEPARATOR = "+----------------------------------------+";

	public void start() {
		System.out.println("\t\t\t" + LINE_SEPARATOR);
		System.out.println("\t\t\t\tMovie Ticket Booking");
		System.out.println("\t\t\t" + LINE_SEPARATOR);
		Login login = new Login();
		currentUser = login.getUser();
		if (currentUser.getUserName().equals("admin")) {
			new AdminPanel().adminHome();
			return;
		}
		int selectedOption;
		do {
			displayMenu();
			System.out.print("Enter your choice: ");
			selectedOption = Util.getInstance().getInt();
			System.out.println();

			switch (selectedOption) {
			case 1 -> {
				showMovies();
			}
			case 2 -> {
				Movie selectedMovie = selectMovie();
				if (selectedMovie != null) {
					bookTicket(selectedMovie);
				}
				System.out.println();
			}
			case 3 -> {
				viewBookedTickets();
				System.out.println();
			}
			case 4 -> {
				currentUser = login.getUser();
			}
			case 0 -> {
				System.out.println("Exiting...");
			}
			default -> {
				System.out.println("Invalid choice. Please try again.");
			}
			}
			if (selectedOption != 0) {
				System.out.println("Press Enter to continue.");
				Util.getInstance().getString();
			}
		} while (selectedOption != 0);
	}

	private boolean bookTicket(Movie selectedmovie) {
		Show selectedShow = selectShow(selectedmovie);
		if (selectedShow == null) {
			return false;
		}
		List<Integer> seats = getSeats(selectedShow);
		if (seats == null) {
			return false;
		}
		Ticket ticket = viewModel.bookTicket(currentUser, selectedShow, seats);
		if (ticket == null) {
			return false;
		}
		return viewModel.confirmBooking(ticket);
	}

	private List<Integer> getSeats(Show selectedShow) {
		String[] seats = viewModel.getAvailableSeats(selectedShow);
		printSeats(seats);
		System.out.println("Enter the number of seats: ");
		int numberOfSeats = Util.getInstance().getInt();

		return viewModel.getAvailableSeats(seats, numberOfSeats);
	}

	private void printSeats(String[] seats) {
		int numRows = 5;
		int seatsPerRow = 10;
		int spacesBetween = 3; // Number of spaces between the first and last 5 seats

		System.out.println("+" + repeatChar('-', (seatsPerRow + spacesBetween) * 6 + 3) + "+");

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < seatsPerRow; j++) {
				int seatIndex = i * seatsPerRow + j;
				if (seatIndex < seats.length) {
					String seat = seats[seatIndex];
					if (j == 5) {
						System.out.print(repeatChar(' ', spacesBetween)); // Add spaces between the first and last 5
																			// seats
					}
					System.out.printf("| %-5s ", seat);
				} else {
					System.out.print("|      ");
				}
			}
			System.out.println("|");
			System.out.println("+" + repeatChar('-', (seatsPerRow + spacesBetween) * 6 + 3) + "+");
		}
		System.out.println("0. Back");
	}

	private String repeatChar(char ch, int count) {
		return new String(new char[count]).replace('\0', ch);
	}

	private Show selectShow(Movie selectedmovie) {
		List<Show> shows = viewModel.getShowsList(selectedmovie);
		printShows(shows);
		int n = Util.getInstance().getInt();
		return viewModel.getSelectedShow(n);
	}

	private Movie selectMovie() {
		showMovies();
		System.out.println("0. Back");
		System.out.println("Select movie for booking a ticket");
		int n = Util.getInstance().getInt();
		return viewModel.selectedMovie(n);
	}

	private void showMovies() {
		List<Movie> moviesList = viewModel.getMoviesList();
		printMoviesList(moviesList);
	}

	private void viewBookedTickets() {
		viewModel.viewBookedTickets(currentUser);
	}

	private void printShows(List<Show> shows) {
		DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
		System.out.println("+" + repeatCharacter('-', 78) + "+");
		System.out.printf("| %-15s | %-20s | %-25s | %-7s |\n", "Show Date", "Show Time", "Movie Title", "Price");
		System.out.println("+" + repeatCharacter('-', 78) + "+");

		for (Show show : shows) {
			System.out.printf("| %-15s | %-20s | %-25s | %-7d |\n", show.getDateTime().format(DATE_FORMATTER),
					show.getDateTime().format(TIME_FORMATTER), show.getMovie().getTitle(), show.getMovie().getPrice());
		}

		System.out.println("+" + repeatCharacter('-', 78) + "+");
	}

	private String repeatCharacter(char ch, int count) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < count; i++) {
			sb.append(ch);
		}
		return sb.toString();
	}

	void showMessage(String message) {
		System.out.println("+----------------------------------------+");
		System.out.println("|   " + message + "       |");
		System.out.println("+----------------------------------------+");
	}

	private void displayMenu() {
		System.out.println("+--------------------------------+");
		System.out.println("|           Main Menu            |");
		System.out.println("+--------------------------------+");
		System.out.println("| 1. Show Available Movies       |");
		System.out.println("| 2. BookTicket                  |");
		System.out.println("| 3. View Booked Tickets         |");
		System.out.println("| 4. Logout                      |");
		System.out.println("| 0. Exit                        |");
		System.out.println("+--------------------------------+");
	}

	private void printMoviesList(List<Movie> availableMovies) {
		System.out.println(
				"┌─────────────────────────────────── Available Movies ────────────────────────────────────────────┐");
		System.out.printf("|%-4s | %-25s | %-20s | %-38s |\n", "No.", "Title", "Director", "Genre");
		System.out.println(
				"|─────┼───────────────────────────┼──────────────────────┼────────────────────────────────────────|");

		for (int i = 0; i < availableMovies.size(); i++) {
			Movie movie = availableMovies.get(i);
			System.out.printf("| %-3d | %-25s | %-20s | %-38s |\n", (i + 1), truncateString(movie.getTitle(), 25),
					truncateString(movie.getDirector(), 20), truncateString(formatGenre(movie.getGenre()), 30));
		}

		System.out.println(
				"└─────────────────────────────────────────────────────────────────────────────────────────────────┘");
	}

	private String truncateString(String input, int maxLength) {
		return input.length() > maxLength ? input.substring(0, maxLength - 3) + "..." : input;
	}

	private String formatGenre(List<String> genres) {
		StringBuilder genreString = new StringBuilder();
		for (String genre : genres) {
			genreString.append(genre).append(", ");
		}
		return genreString.length() > 2 ? genreString.substring(0, genreString.length() - 2) : genreString.toString();
	}

	public void printTickets(List<Ticket> bookedTickets) {
		for (Ticket ticket : bookedTickets) {
			if (ticket.getShow().getDateTime().isAfter(LocalDateTime.now())) {
				printTicket(ticket);
			}
		}
	}

	private static void printTicket(Ticket ticket) {
		Show show = ticket.getShow();

		System.out.println("Movie: " + show.getMovie().getTitle());
		System.out.println(
				"Show Date and Time: " + show.getDateTime().toLocalDate() + " " + show.getDateTime().toLocalTime());
		System.out.println("Seats: " + ticket.getSeats());
		System.out.println("-------------------------------");
	}
}

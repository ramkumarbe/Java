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

	public void start() {
		System.out.println("\t\t\t--------------------------------------");
		System.out.println("\t\t\t\tMovie Ticekt Booking");
		System.out.println("\t\t\t--------------------------------------");
		Login login = new Login();
		currentUser = login.getUser();
		if(currentUser.getUserName().equals("admin")) {
			new AdminPanel().adminHome();
			return;
		}
		int choice;
		do {
			menu();
			System.out.print("Enter your choice: ");
			choice = Util.getInstance().getInt();
			System.out.println();

			switch (choice) {
				case 1 -> {
					Movie selectedmovie = selectMovie();
					if(selectedmovie != null) {
						System.out.println("1.Book Ticket");
						System.out.println("0.Back");
						int enteredChoice = Util.getInstance().getInt();
						if(enteredChoice==1) {
							bookTicket(selectedmovie);
						}
					}
					System.out.println();
				}
				case 2 -> {
					viewBookedTickets();
					System.out.println();
				}
				case 3 -> {
					currentUser = login.getUser();
				}
				case 0->{
					System.out.println("Exiting...");
				}
				default -> {
					System.out.println("Invalid choice. Please try again.");
				}
			}
			if(choice!=0) {
				System.out.println("Enter to continue.");
				Util.getInstance().getString();
			}
		} while (choice != 0);
	}

	private boolean bookTicket(Movie selectedmovie) {
		Show selectedShow = selectShow(selectedmovie);
		if(selectedShow == null) {
			return false;
		}
		List<Integer> seats = getSeats(selectedShow);
		if(seats==null) {
			return false;
		}
		Ticket ticket = viewModel.bookTicket(currentUser, selectedShow, seats);
		if(ticket == null) {
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

	    System.out.println("+" + repeatChar('-', seatsPerRow * 6 + 1) + "+");

	    for (int i = 0; i < numRows; i++) {
	        for (int j = 0; j < seatsPerRow; j++) {
	            int seatIndex = i * seatsPerRow + j;
	            if (seatIndex < seats.length) {
	                String seat = seats[seatIndex];
	                System.out.printf("| %-5s ", seat);
	            } else {
	                System.out.print("|      ");
	            }
	        }
	        System.out.println("|");
	        System.out.println("+" + repeatChar('-', seatsPerRow * 6 + 1) + "+");
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

	private void printShows(List<Show> shows) {
	    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
	    System.out.println("+" + repeatCharacter('-', 60) + "+");
	    System.out.printf("| %-20s | %-20s | %-5s |\n", "Show Date and Time", "Movie Title", "Price");
	    System.out.println("+" + repeatCharacter('-', 60) + "+");
        int i = 1;
	    for (Show show : shows) {
	        System.out.printf("| %-20s | %-20s | %-5d |\n",
	               i++ + ". " + show.getDateTime().format(DATE_TIME_FORMATTER),
	                show.getMovie().getTitle(),
	                show.getMovie().getPrice());
	    }

	    System.out.println("+" + repeatCharacter('-', 60) + "+");
	}

	private String repeatCharacter(char ch, int count) {
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < count; i++) {
	        sb.append(ch);
	    }
	    return sb.toString();
	}

	private Movie selectMovie() {
		showMovies();
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
	
	public void showMessage(String message) {
		 System.out.println("+----------------------------------------+");
		    System.out.println("|   " + message+"       |");
		    System.out.println("+----------------------------------------+");
	}

	private void menu() {
		System.out.println("+--------------------------------+");
		System.out.println("|           Main Menu            |");
		System.out.println("+--------------------------------+");
		System.out.println("| 1. Show Movies                 |");
		System.out.println("| 2. View Booked Tickets         |");
		System.out.println("| 3. Logout                      |");
		System.out.println("| 0. Exit                        |");
		System.out.println("+--------------------------------+");
	}

	private void printMoviesList(List<Movie> availableMovies) {
        System.out.println("┌──────────────────────────── Available Movies ────────────────────────────────────────────┐");
        System.out.printf( "|%-4s | %-25s | %-20s | %-40s |\n", "No.", "Title", "Director", "Genre");
        System.out.println("|─────┼───────────────────────────────────────────────┼──────────────────────┼────────────|");

        for (int i = 0; i < availableMovies.size(); i++) {
            Movie movie = availableMovies.get(i);
            System.out.printf("| %-3d | %-25s | %-20s | %-40s |\n", (i + 1), movie.getTitle(), movie.getDirector(), movie.getGenre());
        }

        System.out.println("└─────────────────────────────────────────────────────────────────────────────────────────┘");
        System.out.println("0. Back");
    }

	public void printTickets(List<Ticket> bookedTickets) {
		for(Ticket ticket:bookedTickets) {
			if(ticket.getShow().getDateTime().isAfter(LocalDateTime.now())) {
				printTicket(ticket);
			}
		}
	}
	
	private static void printTicket(Ticket ticket) {
        Show show = ticket.getShow();

        System.out.println("Movie: " + show.getMovie().getTitle());
        System.out.println("Show Date and Time: " + show.getDateTime().toLocalDate()+" "+show.getDateTime().toLocalTime());
        System.out.println("Seats: " + ticket.getSeats());
        System.out.println("-------------------------------");
    }
}

package com.ramkumarbe.consoleApplication.moviebooking.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ramkumarbe.consoleApplication.moviebooking.dto.Movie;
import com.ramkumarbe.consoleApplication.moviebooking.dto.Show;
import com.ramkumarbe.consoleApplication.moviebooking.dto.Ticket;
import com.ramkumarbe.consoleApplication.moviebooking.dto.User;

public class MovieRepository {
	private static MovieRepository repository = null;

	private MovieRepository() {
	}

	public static MovieRepository getInstance() {
		if (repository == null) {
			repository = new MovieRepository();

		}
		return repository;
	}

	public void addUser(User user) throws SQLException {
		String query = "INSERT INTO user (username, password, mobile_number, email) VALUES (?, ?, ?, ?)";
		PreparedStatement preparedStatement = getConnection().prepareStatement(query);

		preparedStatement.setString(1, user.getUserName());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setFloat(3, Long.parseLong(user.getPhoneNumber()));
		preparedStatement.setString(4, user.getMail());

		preparedStatement.executeUpdate();
	}

	private Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/movie_ticket_booking";
		String username = "root";
		String password = "1234";
		Connection connection = DriverManager.getConnection(url, username, password);
		return connection;
	}

	private Statement getStatement() throws SQLException {
		Statement statement = getConnection().createStatement();
		return statement;
	}

	public void loadMovies() throws SQLException {
		getMovies(getStatement(), "SELECT * FROM movie");
	}

	private void getMovies(Statement statement, String query) throws SQLException {
		ResultSet resultSet = statement.executeQuery(query);
		while (resultSet.next()) {
			Movie movie = getMovie(resultSet);
			moviesList.add(movie);
		}
	}

	private Movie getMovie(ResultSet resultSet) throws SQLException {
		String title = resultSet.getString("title");
		String director = resultSet.getString("director");
		List<String> genre = Arrays.asList(resultSet.getString("genre").split(","));
		int price = resultSet.getInt("price");

		return new Movie(title, director, genre, price);
	}

	public Map<String, User> getUsers() throws SQLException {
		return getUsers(getStatement(), "SELECT * FROM user");
	}

	private User getUser(ResultSet resultSet) throws SQLException {
		String userName = resultSet.getString("username");
		String pass = resultSet.getString("password");
		String mailId = resultSet.getString("email");
		String phoneNumber = resultSet.getString("mobile_number");
		return new User(userName, pass, mailId, phoneNumber);
	}

	private Map<String, User> getUsers(Statement statement, String query) throws SQLException {
		ResultSet resultSet = statement.executeQuery(query);
		Map<String, User> usersList = new HashMap<>();
		while (resultSet.next()) {
			User user = getUser(resultSet);
			usersList.put(user.getUserName(), user);
		}
		return usersList;
	}

	private List<Movie> moviesList = new ArrayList<>();

	public List<Movie> getAvailableMovies() {
		return moviesList;
	}

	public List<Show> getShowsList(Movie selectedMovie) throws SQLException {
		List<Show> showsList = new ArrayList<>();
		String query = "SELECT s.show_datetime," + "m.title AS movie_title,  m.director,  m.genre,  m.price "
				+ "FROM  movie_show_schedule s " + "JOIN  movie m ON s.movie_title = m.title " + "where movie_title = "
				+ "'" + selectedMovie.getTitle() + "'";
		ResultSet resultSet = getStatement().executeQuery(query);
		while (resultSet.next()) {
			LocalDateTime dateTime = getDateTime(resultSet);

			String title = resultSet.getString("movie_title");
			String director = resultSet.getString("director");
			List<String> genre = Arrays.asList(resultSet.getString("genre").split(","));
			int price = resultSet.getInt("price");

			Movie movie = new Movie(title, director, genre, price);
			;
			Show show = new Show(dateTime, movie);
			showsList.add(show);
		}
		return showsList;
	}

	private LocalDateTime getDateTime(ResultSet resultSet) throws SQLException {
		String dateString = resultSet.getString("show_datetime");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDateTime.parse(dateString, formatter);
	}

	public String[] getAvailableSeats(Show selectedShow) throws SQLException {
		String[] seats = new String[50];
		for (int i = 1; i <= 50; i++) {
			seats[i - 1] = String.valueOf(i);
		}
		String query = "SELECT seat_number FROM ticket "
				+ "JOIN movie_show_schedule ON ticket.show_id = movie_show_schedule.show_id "
				+ "WHERE movie_show_schedule.show_datetime = ?";

		PreparedStatement preparedStatement = getConnection().prepareStatement(query);
		preparedStatement.setObject(1, selectedShow.getDateTime());
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int seatNumber = resultSet.getInt("seat_number");
			seats[seatNumber - 1] = "[B]";
		}
		return seats;
	}

	public void insertBookedTickets(Ticket ticket) throws SQLException {
		String query = "INSERT INTO ticket (show_id, seat_number, username) VALUES (?, ?, ?)";
		int showId = getShowId(ticket);
		PreparedStatement preparedStatement = getConnection().prepareStatement(query);
		List<Integer> seats = ticket.getSeats();
		User user = ticket.getUser();
		System.out.println(seats);
		for (int seatNumber : seats) {
			preparedStatement.setInt(1, showId);
			preparedStatement.setInt(2, seatNumber);
			preparedStatement.setString(3, user.getUserName());
			preparedStatement.executeUpdate();
		}
	}

	private int getShowId(Ticket ticket) throws SQLException {
		int showId = -1;

		String query = "SELECT show_id FROM movie_show_schedule WHERE show_datetime = ?";

		PreparedStatement preparedStatement = getConnection().prepareStatement(query);
		preparedStatement.setTimestamp(1, Timestamp.valueOf(ticket.getShow().getDateTime()));

		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			showId = resultSet.getInt("show_id");
		}

		return showId;
	}

	public List<Ticket> getBookedTickets(User user) throws SQLException {

		List<Ticket> tickets = new ArrayList<>();
		String query = "SELECT m.title,m.director,m.genre,m.price,s.show_datetime,t.seat_number FROM movie_ticket_booking.ticket as t "
				+ "join movie_show_schedule as s on t.show_id = s.show_id "
				+ "join movie as m on s.movie_title = m.title " + "where t.username = '" + user.getUserName() + "'";
		ResultSet resultSet = getStatement().executeQuery(query);
		while (resultSet.next()) {
			LocalDateTime dateTime = getDateTime(resultSet);
			Movie movie = getMovie(resultSet);
			int seat = resultSet.getInt("seat_number");
			Show show = new Show(dateTime, movie);
			Ticket ticket = new Ticket(show, user);
			ticket.getSeats().add(seat);
			
			tickets.add(ticket);
		}
		return tickets;
	}

	public Map<Integer, String> getGenres() throws SQLException {
		Map<Integer, String> genres = new HashMap<>();
		String query = "Select * from genres";
		ResultSet resultSet = getStatement().executeQuery(query);
		while(resultSet.next()) {
			int id = resultSet.getInt("genre_id");
			String genre = resultSet.getString("genre");
			genres.put(id, genre);
		}
		return genres;
	}

	public void uploadNewShow(Show show) throws SQLException {
		String query = "Insert Into movie_show_schedule (show_datetime,movie_title)"
			     + "Values (?,?)";
		PreparedStatement statement = getConnection().prepareStatement(query);
		Timestamp timeStamp = Timestamp.valueOf(show.getDateTime());
		statement.setTimestamp(1, timeStamp);
		statement.setString(2, show.getMovie().getTitle());
		int n = statement.executeUpdate();
		System.out.println(n);
	}

	public void uploadNewMovie(Movie movie) throws SQLException {
		String query = "Insert Into movie (title,director,genre,price)"
				     + "Values (?,?,?,?)";
		PreparedStatement statement = getConnection().prepareStatement(query);
		statement.setString(1, movie.getTitle());
		statement.setString(2, movie.getDirector());
		statement.setString(3, String.join(",",movie.getGenre()));
		statement.setInt(4, movie.getPrice());
		statement.executeUpdate();
	}
}

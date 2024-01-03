package com.ramkumarbe.consoleApplication.moviebooking.admin;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import com.ramkumarbe.consoleApplication.moviebooking.dto.Movie;
import com.ramkumarbe.consoleApplication.moviebooking.dto.Show;
import com.ramkumarbe.consoleApplication.moviebooking.repository.MovieRepository;

public class AdminPanelViewModel {

	private AdminPanel adminPanel;
	public AdminPanelViewModel(AdminPanel adminPanel) {
		this.adminPanel = adminPanel;
	}
	public Map<Integer,String> getGenres() {
		Map<Integer,String> genres = null;
		try {
			genres = MovieRepository.getInstance().getGenres();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return genres;
	}
	public void addMovie(Movie movie) {
		try {
			MovieRepository.getInstance().uploadNewMovie(movie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public LocalDateTime getDateTime(String dateTimeString) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		return LocalDateTime.parse(dateTimeString, formatter);
	}
	public List<Movie> getMovies() {
		return MovieRepository.getInstance().getAvailableMovies();
	}
	public void uploadShow(Show show) {
		try {
			MovieRepository.getInstance().uploadNewShow(show);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

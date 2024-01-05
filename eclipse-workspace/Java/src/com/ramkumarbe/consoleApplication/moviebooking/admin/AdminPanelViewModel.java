package com.ramkumarbe.consoleApplication.moviebooking.admin;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    public Map<Integer, String> getGenres() {
        try {
            return MovieRepository.getInstance().getGenres();
        } catch (SQLException e) {
            handleSQLException("Error retrieving genres", e);
            return null;
        }
    }

    public void addMovie(Movie movie) {
        try {
            MovieRepository.getInstance().uploadNewMovie(movie);
        } catch (SQLException e) {
            handleSQLException("Error uploading movie", e);
        }
    }

    public LocalDateTime getDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        try {
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            adminPanel.printMessage("Invalid date-time format. Please use the format: dd-MM-yyyy HH:mm");
            return null;
        }
    }

    public List<Movie> getMovies() {
        return MovieRepository.getInstance().getAvailableMovies();
    }

    public void uploadShow(Show show) {
        try {
            MovieRepository.getInstance().uploadNewShow(show);
        } catch (SQLException e) {
            handleSQLException("Error uploading show", e);
        }
    }

    private void handleSQLException(String message, SQLException e) {
        e.printStackTrace(); 
        adminPanel.printMessage(message);
    }
}

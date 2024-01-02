package com.ramkumarbe.consoleApplication.moviebooking.dto;

import java.time.LocalDateTime;

public class Show {
	private LocalDateTime dateTime;
	private Movie movie;
	
	public Show(LocalDateTime dateTime, Movie movie) {
		this.dateTime = dateTime;
		this.movie = movie;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}

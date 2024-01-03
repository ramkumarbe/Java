package com.ramkumarbe.consoleApplication.moviebooking.dto;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
	private Show show;
	private User user;
    private List<Integer> seats;

	public Ticket(Show show, User user) {
		this.show = show;
		this.user = user;
		seats = new ArrayList<>();
	}
	
	public Show getShow() {
		return show;
	}
	public void setShow(Show movie) {
		this.show = movie;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public List<Integer> getSeats() {
		return seats;
	}
}

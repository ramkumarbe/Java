package com.ramkumarbe.consoleApplication.moviebooking.dto;

import java.util.List;

public class Movie {
	public Movie(String title, String director, List<String> genre, int price) {
		this.title = title;
		this.director = director;
		this.genre = genre;
		this.price = price;
	}
	private String title;
	private String director;
	private List<String> genre;
	private int price;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public List<String> getGenre() {
		return genre;
	}
	public void setGenre(List<String> genre) {
		this.genre = genre;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	private int seat=1;
	
}

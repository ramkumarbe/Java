package com.ramkumarbe.consoleApplication.cricketscoreboard.dto;

import java.util.ArrayList;
import java.util.List;

public class Over {
	private int numberOfOver;
	private List<Ball> balls = new ArrayList<>();

	public Over(int numberOfOver) {
		this.numberOfOver = numberOfOver;
	}

	public List<Ball> getBalls() {
		return balls;
	}

	public int getNumberOfOver() {
		return numberOfOver;
	}

	public void setNumberOfOver(int numberOfOver) {
		this.numberOfOver = numberOfOver;
	}
}

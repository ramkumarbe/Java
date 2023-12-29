package com.ramkumarbe.consoleApplication.cricketscoreboard.dto;

public class Team {
	private String name;
	private int score;
	private int wickets;
	private int target;
	private int extras;
	private Player[] players = new Player[11];
	public Team(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public Player[] getPlayers() {
		return players;
	}
	public int getWickets() {
		return wickets;
	}
	public void setWickets(int wickets) {
		this.wickets = wickets;
	}
	public int getExtras() {
		return extras;
	}
	public void setExtras(int extras) {
		this.extras = extras;
	}
}

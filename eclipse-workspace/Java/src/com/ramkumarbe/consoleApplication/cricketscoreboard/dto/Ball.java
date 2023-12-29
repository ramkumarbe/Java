package com.ramkumarbe.consoleApplication.cricketscoreboard.dto;

public class Ball {
	private Player batsman;
	private Player bowler;
	private int run;
	private int over;
	private int ballOfTheOver;

	public Ball(Player batsman, Player bowler, int run, int over, int ballOfTheOver) {
		this.batsman = batsman;
		this.bowler = bowler;
		this.run = run;
		this.over = over;
		this.ballOfTheOver = ballOfTheOver;
	}
	
	public Player getBatsman() {
		return batsman;
	}
	public void setBatsman(Player batsman) {
		this.batsman = batsman;
	}
	public Player getBowler() {
		return bowler;
	}
	public void setBowler(Player bowler) {
		this.bowler = bowler;
	}
	public int getRun() {
		return run;
	}
	public void setRun(int run) {
		this.run = run;
	}

	public int getOver() {
		return over;
	}

	public void setOver(int over) {
		this.over = over;
	}

	public int getBallOfTheOver() {
		return ballOfTheOver;
	}

	public void setBallOfTheOver(int ballOfTheOver) {
		this.ballOfTheOver = ballOfTheOver;
	}
}

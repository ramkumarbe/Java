package com.ramkumarbe.consoleApplication.cricketscoreboard.dto;

public class Player {
	private String name;
	private Role role;
	private int runs;
	private int wickets;
	private int ballsFaced;
	private int ballsBowled;
	private int fours;
	private int sixes;
	private int conceededRuns;
	private float strikeRate;
	private float economy;
	private boolean isBatted;
	private boolean canBowl=true;
	
	public Player(String name, Role role) {
		this.name = name;
		this.role = role;
	}
	public boolean equals(Player p) {
		return this.name.equals(p.name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRuns() {
		return runs;
	}
	public void setRuns(int runs) {
		this.runs = runs;
	}
	public int getWicket() {
		return wickets;
	}
	public void setWicket(int wicket) {
		this.wickets = wicket;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getWickets() {
		return wickets;
	}
	public void setWickets(int wickets) {
		this.wickets = wickets;
	}
	public int getBallsFaced() {
		return ballsFaced;
	}
	public void setBallsFaced(int ballsFaced) {
		this.ballsFaced = ballsFaced;
	}
	public int getBallsBowled() {
		return ballsBowled;
	}
	public void setBallsBowled(int ballsBowled) {
		this.ballsBowled = ballsBowled;
	}
	public float getStrikeRate() {
		return strikeRate;
	}
	public void setStrikeRate(float strikeRate) {
		this.strikeRate = strikeRate;
	}
	public float getEconomy() {
		return economy;
	}
	public void setEconomy(float economy) {
		this.economy = economy;
	}
	public boolean isBatted() {
		return isBatted;
	}
	public void setBatted(boolean isBatted) {
		this.isBatted = isBatted;
	}
	public boolean canBowl() {
		return canBowl;
	}
	public void setCanBowl(boolean canBowl) {
		this.canBowl = canBowl;
	}
	public int getConceededRuns() {
		return conceededRuns;
	}
	public void setConceededRuns(int conceededRuns) {
		this.conceededRuns = conceededRuns;
	}
	public int getFours() {
		return fours;
	}
	public void setFours(int fours) {
		this.fours = fours;
	}
	public int getSixes() {
		return sixes;
	}
	public void setSixes(int sixes) {
		this.sixes = sixes;
	}
}

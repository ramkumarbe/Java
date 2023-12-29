package com.ramkumarbe.consoleApplication.cricketscoreboard.dto;

import java.util.ArrayList;
import java.util.List;

public class Innings {
	private Team battingTeam;
	private Team bowlingTeam;
	
	List<Over> oversOfTheInnings = new ArrayList<>();
	public Innings(Team batting, Team bowling) {
		this.battingTeam = batting;
		this.bowlingTeam = bowling;
	}

	public String toString() {
	    StringBuilder scoreboard = new StringBuilder();
	    scoreboard.append("+----------------------------------------+\n");
	    scoreboard.append("|           Innings Scoreboard           |\n");
	    scoreboard.append("+----------------------------------------+\n");
	    scoreboard.append("| Batting Team: ").append(formatTeamName(battingTeam.getName())).append("\n");
	    scoreboard.append("| Score: ").append(battingTeam.getScore()).append("\n");
	    scoreboard.append("| Wickets: ").append(battingTeam.getWickets()).append("\n");
	    scoreboard.append("+----------------------------------------+\n");
	    scoreboard.append("|               Batsmen                  |\n");
	    scoreboard.append("+----------------------------------------+\n");

	    for (Player batsman : battingTeam.getPlayers()) {
	    	if(batsman.isBatted()) {
	    		scoreboard.append("| ").append(formatBatsmanInfo(batsman.getName(), batsman.getRuns(), batsman.getBallsFaced())).append("\n");
	    	}
	    }

	    scoreboard.append("+----------------------------------------+\n");
	    scoreboard.append("| Bowling Team: ").append(formatTeamName(bowlingTeam.getName())).append("\n");
	    scoreboard.append("| Wickets: ").append(bowlingTeam.getWickets()).append("\n");
	    scoreboard.append("+----------------------------------------+\n");
	    scoreboard.append("|               Bowlers                  |\n");
	    scoreboard.append("+----------------------------------------+\n");

	    for (Player bowler : bowlingTeam.getPlayers()) {
	    	if(bowler.getBallsBowled()>0){
	    		scoreboard.append("| ").append(formatBowlerInfo(bowler.getName(), bowler.getWickets(), bowler.getBallsBowled())).append("\n");
	    	}
	    }

	    scoreboard.append("+----------------------------------------+\n");

	    return scoreboard.toString();
	}

	private String formatTeamName(String teamName) {
	    return String.format("%-20s", teamName);
	}
	private String formatBatsmanInfo(String playerName, int runs, int balls) {
	    return String.format("%-18s - %-3d %s (%d balls)", playerName, runs, runs > 1 ? "runs" : "run", balls);
	}
	private String formatBowlerInfo(String playerName, int value, int balls) {
	    return String.format("%-18s - %-3d %s (%d balls)", playerName, value, value > 1 ? "runs" : "run", balls);
	}

	public Team getBattingTeam() {
		return battingTeam;
	}

	public void setBattingTeam(Team batting) {
		this.battingTeam = batting;
	}

	public Team getBowlingTeam() {
		return bowlingTeam;
	}

	public void setBowlingTeam(Team bowling) {
		this.bowlingTeam = bowling;
	}

}

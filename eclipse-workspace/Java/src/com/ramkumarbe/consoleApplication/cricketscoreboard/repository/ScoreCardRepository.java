package com.ramkumarbe.consoleApplication.cricketscoreboard.repository;

import java.util.ArrayList;
import java.util.List;

import com.ramkumarbe.consoleApplication.cricketscoreboard.dto.Over;
import com.ramkumarbe.consoleApplication.cricketscoreboard.dto.Player;
import com.ramkumarbe.consoleApplication.cricketscoreboard.dto.Role;
import com.ramkumarbe.consoleApplication.cricketscoreboard.dto.Team;
import com.ramkumarbe.consoleApplication.moviebooking.repository.MovieRepository;

public class ScoreCardRepository {
	private List<Over> firstInnings = new ArrayList<>();
	private List<Over> secondInnings = new ArrayList<>();

	private Team india = new Team("India");
	private Team australia = new Team("Australia");
	private Player[] indianPlayers = india.getPlayers();
	private Player[] australianPlayers = australia.getPlayers();
	
	public void load() {
		indianPlayers[0] = new Player("Rohit Sharma", Role.BATSMAN);
		indianPlayers[1] = new Player("Subhman Gill", Role.BATSMAN);
		indianPlayers[2] = new Player("Virat Kohli", Role.BATSMAN);
		indianPlayers[3] = new Player("Shreyas Iyer", Role.BATSMAN);
		indianPlayers[4] = new Player("KL Rahul", Role.BATSMAN);
		indianPlayers[5] = new Player("Hardik Pandya", Role.ALLROUNDER);
		indianPlayers[6] = new Player("Ravindra Jadeja", Role.ALLROUNDER);
		indianPlayers[7] = new Player("Axar Patel", Role.ALLROUNDER);
		indianPlayers[8] = new Player("Kuldeep Yadhav", Role.BOWLER);
		indianPlayers[9] = new Player("Mohammed Shami", Role.BOWLER);
		indianPlayers[10] = new Player("Jasprit Bumrah", Role.BOWLER);
		
		australianPlayers[0] = new Player("David Warner", Role.BATSMAN);
		australianPlayers[1] = new Player("Travis Head", Role.BATSMAN);
		australianPlayers[6] = new Player("Mitchell Marsh", Role.ALLROUNDER);
		australianPlayers[2] = new Player("Steve Smith", Role.BATSMAN);
		australianPlayers[3] = new Player("Marnus Labuschagne", Role.BATSMAN);
		australianPlayers[4] = new Player("Glenn Maxwell", Role.ALLROUNDER);
		australianPlayers[5] = new Player("Josh Inglis", Role.ALLROUNDER);
		australianPlayers[7] = new Player("Pat Cummins", Role.ALLROUNDER);
		australianPlayers[8] = new Player("Mitchell Starc", Role.BOWLER);
		australianPlayers[9] = new Player("Adam Zampa", Role.BOWLER);
		australianPlayers[10] = new Player("Josh Hazlewood", Role.BOWLER);
	}
	
	public List<Over> getFirstInnings() {
		return firstInnings;
	}

	public List<Over> getSecondInnings() {
		return secondInnings;
	}
	
	public Player[] getIndianPlayers() {
		return indianPlayers;
	}

	public Player[] getAustralianPlayers() {
		return australianPlayers;
	}
	
	public Team getIndia() {
		return india;
	}

	public Team getAustralia() {
		return australia;
	}

	private static ScoreCardRepository repository = null;

	private ScoreCardRepository() { }

	public static ScoreCardRepository getInstance() {
		if (repository == null) {
			repository = new ScoreCardRepository();
		}
		return repository;
	}
}

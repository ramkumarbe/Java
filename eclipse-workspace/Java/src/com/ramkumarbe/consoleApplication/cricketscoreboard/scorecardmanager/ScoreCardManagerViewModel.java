package com.ramkumarbe.consoleApplication.cricketscoreboard.scorecardmanager;

import java.util.HashMap;
import java.util.Map;

import com.ramkumarbe.consoleApplication.cricketscoreboard.dto.Ball;
import com.ramkumarbe.consoleApplication.cricketscoreboard.dto.Innings;
import com.ramkumarbe.consoleApplication.cricketscoreboard.dto.Over;
import com.ramkumarbe.consoleApplication.cricketscoreboard.dto.Player;
import com.ramkumarbe.consoleApplication.cricketscoreboard.dto.Team;
import com.ramkumarbe.consoleApplication.cricketscoreboard.repository.ScoreCardRepository;

public class ScoreCardManagerViewModel {

	private ScoreCardManager view;
	ScoreCardManagerViewModel(ScoreCardManager scoreCardManager) {
		view = scoreCardManager;
	}
	
	private Innings indBat, ausBat, currentInnings;
	private Player[] batsmen, bowlers;
	public void setInnings(int n) {
		ScoreCardRepository.getInstance().load();
		Team india = ScoreCardRepository.getInstance().getIndia();
		Team australia = ScoreCardRepository.getInstance().getAustralia();
        Innings indBat = new Innings(india,australia);
        Innings ausBat = new Innings(australia,india);
        if(n==1) {
        	currentInnings = indBat;
        }
        else {
        	currentInnings = ausBat;
        }
        setPlayers();
	}
	private void setPlayers() {
    	batsmen = currentInnings.getBattingTeam().getPlayers();
    	bowlers = currentInnings.getBowlingTeam().getPlayers();
	}
	public void printScoreBoard() {
		view.printScoreBoard(currentInnings);
	}
	
	public void setNextInnings() {
		if(currentInnings.getBattingTeam().getName().equals("India")) {
			currentInnings = ausBat;
		}
		else {
			currentInnings = indBat;
		}
    	setPlayers();
	}

	private int totalOvers;
	private Player currentBowler, previousBowler;
	private Player striker, nonStriker;
	private int numberOfOvers;
	private Over currentOver;
	public void startOver() {
		selectPlayers();
		int ballCount=0;
		boolean isValidInput;
		currentOver = new Over(++numberOfOvers);
		currentBowler = selectBowler();
		do {
			isValidInput = true;
			int resultOfTheBall = view.bowl();
			switch(resultOfTheBall) {
				case 1,2,3,4,5,6 -> {
					updateScore(resultOfTheBall);
					updateBall(ballCount,resultOfTheBall);
					if(resultOfTheBall%2!=0) {
						rotateStrike();
					}
					else if(resultOfTheBall==4||resultOfTheBall==6) {
						addBoundary(resultOfTheBall);
					}
					ballCount++;
				}
				case 7,8 -> {
					updateExtras();
				}
				case 9 -> {
					addWicket();
					updateScore(0);
					updateBall(ballCount,0);
					selectBatsman();
					ballCount++;
				}
				default -> {
					isValidInput = false;
					view.printMessage("Enter valid input.");
				}
			}
			if(isValidInput) {
			}
		}while(ballCount<6);
		rotateStrike();
		switchOver();
	}
	private void updateExtras() {
		currentBowler.setConceededRuns(currentBowler.getConceededRuns()+1);
		currentBowler.setEconomy(currentBowler.getConceededRuns()/((float)currentBowler.getBallsBowled()/6));
		currentInnings.getBattingTeam().setScore(currentInnings.getBattingTeam().getScore()+1);
		currentInnings.getBattingTeam().setExtras(currentInnings.getBattingTeam().getExtras()+1);
	}
	private void rotateStrike() {
		Player temp = striker;
		striker = nonStriker;
		nonStriker = temp;
	}
	private void selectPlayers() {
		if(striker==null)
			striker = selectBatsman();
		if(nonStriker==null)
			nonStriker = selectBatsman();
	}
	private void addBoundary(int boundary) {
		if(boundary == 4) {
			striker.setFours(striker.getFours()+1);
		}
		else {
			striker.setSixes(striker.getSixes()+1);
		}
	}
	private void addWicket() {
		currentBowler.setBallsBowled(currentBowler.getBallsBowled()+1);
		currentBowler.setWickets(currentBowler.getWicket()+1);
	}
	private void switchOver() {
		if(previousBowler!=null && previousBowler.getBallsBowled()/6<totalOvers/5) {
			previousBowler.setCanBowl(true);
		}
		currentBowler.setCanBowl(false);
		previousBowler = currentBowler;
	}
	private void updateScore(int resultOfTheBall) {
		striker.setRuns(striker.getRuns()+resultOfTheBall);
		striker.setBallsFaced(striker.getBallsFaced()+1);
		striker.setStrikeRate((float)striker.getRuns()/striker.getBallsFaced()*100);
		currentBowler.setBallsBowled(currentBowler.getBallsBowled()+1);
		currentBowler.setConceededRuns(currentBowler.getConceededRuns()+resultOfTheBall);
		currentBowler.setEconomy(currentBowler.getConceededRuns()/((float)currentBowler.getBallsBowled()/6));
	}
	private void updateBall(int ballOfTheOver, int resultOfTheBall) {
		Ball ball = new Ball(striker,currentBowler,numberOfOvers,ballOfTheOver,resultOfTheBall);
		currentOver.getBalls().add(ball);
	}
	
	private Player selectBatsman() {
		Map<Integer,Player> playersList = new HashMap<>();
		boolean isValidInput;
		int selectedNumber;
		do {
			view.printMessage("Select the Batsman: ");
			int playerNumber=1; 
			for(Player player:batsmen) {
				if(!player.isBatted()) {
					playersList.put(playerNumber, player);
					view.printPlayers(playerNumber++ +". "+player.getName());
				}
			}
			view.printMessage("\n");
			selectedNumber = view.getPlayerNumber();
			isValidInput = selectedNumber<=playersList.size();
			if(!isValidInput) {
				view.printMessage("Enter valid input.");
			}
		}while(!isValidInput);
		Player batsman = playersList.get(selectedNumber);
		batsman.setBatted(true);
		return batsman;
	}
	private Player selectBowler() {
		Map<Integer,Player> playersList = new HashMap<>();
		boolean isValidInput;
		int selectedNumber;
		do {
			view.printMessage("Select the Bowler: ");
			int playerNumber=1; 
			for(Player player:bowlers) {
				if(player.canBowl()) {
					playersList.put(playerNumber, player);
					view.printPlayers(playerNumber++ +". "+player.getName());
				}
			}
			view.printMessage("\n");
			selectedNumber = view.getPlayerNumber();
			isValidInput = selectedNumber<=playersList.size();
			if(!isValidInput) {
				view.printMessage("Enter valid input.");
			}
		}while(!isValidInput);
		return playersList.get(selectedNumber);
	}
	public void setOvers(int totalOvers) {
		this.totalOvers = totalOvers;
	}
	public boolean isInningsOver() {
		return numberOfOvers==totalOvers;
	}
	
}

package com.ramkumarbe.consoleApplication.cricketscoreboard.scorecardmanager;

import com.ramkumarbe.consoleApplication.cricketscoreboard.dto.Innings;
import com.ramkumarbe.consoleApplication.cricketscoreboard.dto.Player;
import com.ramkumarbe.consoleApplication.cricketscoreboard.dto.Team;
import com.ramkumarbe.consoleApplication.cricketscoreboard.util.Util;

public class ScoreCardManager {
	private ScoreCardManagerViewModel viewModel;
	
	public ScoreCardManager() {
		viewModel = new ScoreCardManagerViewModel(this);
	}
	Player batsman1,batsman2,bowler;
	public void startMatch() {
		System.out.print("Enter the Total number of overs: ");
		int totalOvers = Util.getInstance().getInt();
		viewModel.setOvers(totalOvers);
		selectBattingTeam();
		startInnings();
		viewModel.setNextInnings();
	}

	private void startInnings() {
		do {
			printMenu();
			int choice = Util.getInstance().getInt();
			switch(choice) {
				case 1 -> {
					viewModel.startOver();
				}
				case 2 -> {
					viewModel.printScoreBoard();
				}
			}
		}while(!viewModel.isInningsOver());
	}

	private void selectBattingTeam() {
		int choice;
		do {
			System.out.println("Select the First Batting Team: ");
			System.out.println("1.India \n2.Australia");
			choice = Util.getInstance().getInt();	
			if(choice!=1&&choice!=2) {
				System.out.println("Enter valid input.");
			}
		} while(choice!=1&&choice!=2);
		viewModel.setInnings(choice);
	}

	private void printMenu() {
		System.out.println("1.Start/Resume Game");
		System.out.println("2.View Score Board");
	}

	public void printScoreBoard(Innings currentInnings) {
		System.out.println(currentInnings);
	}

	public void printMessage(String message) {
		System.out.println(message);
	}

	public void printPlayers(String string) {
		System.out.println(string);
	}

	public int getPlayerNumber() {
		System.out.print("Select the player: ");
		return Util.getInstance().getInt();
	}

	public int bowl() {
		System.out.println("Enter 0-6 to mention the run.");
		printSpecialDeliveries();
		System.out.println("Enter the result of the ball: ");
		return Util.getInstance().getInt();
	}

	private void printSpecialDeliveries() {
		System.out.println("7. Wide");
		System.out.println("8. No-Ball");
		System.out.println("9. Wicket");
	}
	
}

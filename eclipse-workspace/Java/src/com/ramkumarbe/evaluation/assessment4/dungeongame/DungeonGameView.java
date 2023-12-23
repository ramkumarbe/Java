package com.ramkumarbe.evaluation.assessment4.dungeongame;

import java.util.Scanner;

public class DungeonGameView {

	private DungeonGameViewModel dungeonGameViewModel;
	
	public DungeonGameView() {
		dungeonGameViewModel = new DungeonGameViewModel(this);
	}
	
	public void start() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Dimensions of the dungeon(Row x Column): ");
		int length = getInt();
		int breadth = getInt();
		dungeonGameViewModel.getArea(length,breadth);
		
		boolean validInput;
		do {
			System.out.println("Position of Adventurer: ");
			length = getInt()-1;
			breadth = getInt()-1;
			validInput = dungeonGameViewModel.isValidInput(length,breadth);
			if(!validInput) {
				System.out.println("Enter the position which is inside the Area: ");
			}
		}while(!validInput);
		dungeonGameViewModel.addAdventurerPosition(length, breadth);
		
		do {
			System.out.println("Position of Gold: ");
			length = getInt()-1;
			breadth = getInt()-1;
			validInput = dungeonGameViewModel.isValidInput(length,breadth);
			if(!validInput) {
				System.out.println("Enter the position which is inside the Area: ");
			}
		}while(!validInput);
		dungeonGameViewModel.addGoldPosition(length, breadth);
		
		do {
			System.out.println("Position of Monster: ");
			length = getInt()-1;
			breadth = getInt()-1;
			validInput = dungeonGameViewModel.isValidInput(length,breadth);
			if(!validInput) {
				System.out.println("Enter the position which is inside the Area: ");
			}
		}while(!validInput);
		dungeonGameViewModel.addMonsterPosition(length, breadth);
		
		
		
//		dungeonGameViewModel.printStartingPosition();
		dungeonGameViewModel.findMinimumSteps();
	}
    private Scanner sc = new Scanner(System.in);
	
	private int getInt() {
		while(true) {
			if(sc.hasNextInt()) {
				int n = sc.nextInt(); sc.nextLine();
				if(n<0) {
					continue;
				}
				return n;
			}
			System.out.println("Enter valid Number.");
			sc.nextLine();
		}
	}

	public void printResult(int numberOfSteps) {
		System.out.println("Minimum number of steps to get the gold is "+numberOfSteps+".");
	}

	public void printResult(String result) {
		System.out.println(result);
	}

//	public void printArea(char[][] area) {
//	    System.out.println("+------------------------+");
//	    for (char[] row : area) {
//	        System.out.print("| ");
//	        for (char c : row) {
//	            System.out.print(c + " ");
//	        }
//	        System.out.println("|");
//	    }
//	    System.out.println("+------------------------+");
//	}
}

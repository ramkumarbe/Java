package com.ramkumarbe.evaluation.assessment4.dungeongame;

import com.ramkumarbe.evaluation.assessment4.dto.DungeonArea;

public class DungeonGameViewModel {

	private DungeonGameView dungeonGameView;
	
	public DungeonGameViewModel(DungeonGameView dungeonGameView) {
		this.dungeonGameView = dungeonGameView;
	}

	DungeonArea dungeonArea;
	char[][] area;
	int[] positionOfAdventurer = new int[2];
	int[] positionOfGold = new int[2];
	
	public void getArea(int length, int breadth) {
		dungeonArea = new DungeonArea(length,breadth);
		area = dungeonArea.getArea();
	}
	public boolean isValidInput(int length, int breadth) {
		return length<dungeonArea.getLength()&&breadth<dungeonArea.getBreadth();
	}
	public void addAdventurerPosition(int length, int breadth) {
		positionOfAdventurer[0]=length;
		positionOfAdventurer[1]=breadth;
		area[length][breadth] = 'A';
	}
	public void addGoldPosition(int length, int breadth) {
		positionOfGold[0]=length;
		positionOfGold[1]=breadth;
		area[length][breadth] = 'G';
	}
	
	public void findMinimumSteps() {
		int horizontalDifference = Math.abs(positionOfGold[0]-positionOfAdventurer[0]);
		int verticalDifference = Math.abs(positionOfGold[1]-positionOfAdventurer[1]);
		dungeonGameView.printResult(horizontalDifference+verticalDifference);
	}
	
//	public void printStartingPosition() {
//		dungeonGameView.printArea(area);
//	}
}

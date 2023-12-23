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
	int[] positionOfMonster = new int[2];
//	int[] positionOfTrigger = new int[2];
	
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
//		area[length][breadth] = 'A';
	}
	public void addGoldPosition(int length, int breadth) {
		positionOfGold[0]=length;
		positionOfGold[1]=breadth;
		area[length][breadth] = 'G';
	}
	private int minStepsForAdventurer=Integer.MAX_VALUE;
	private int minStepsForMonster=Integer.MAX_VALUE;
	private void getAdventurerMinimumSteps(int row, int col, int count) {
		if(row==area.length||row==-1||col==-1||col==area[0].length) {
			return;
		}
		if(area[row][col]=='P') {
			return;
		}
		if(area[row][col]=='G') {
			minStepsForAdventurer = Math.min(minStepsForAdventurer, count);
			return;
		}
		if(row<positionOfGold[0])
			getAdventurerMinimumSteps(row+1,col,count+1);
		if(col<positionOfGold[1])
			getAdventurerMinimumSteps(row,col+1,count+1);
		if(row>positionOfGold[0])
			getAdventurerMinimumSteps(row-1,col,count+1);
		if(col>positionOfGold[1])
			getAdventurerMinimumSteps(row,col-1,count+1);
	}
	public void findMinimumSteps() {
		getMonsterMinimumSteps(positionOfMonster[0],positionOfMonster[1],0);
		getAdventurerMinimumSteps(positionOfAdventurer[0],positionOfAdventurer[1],0);
		System.out.println(minStepsForAdventurer+"  "+minStepsForMonster);
		if(minStepsForAdventurer!=Integer.MAX_VALUE && minStepsForAdventurer<=minStepsForMonster) {
			dungeonGameView.printResult(minStepsForAdventurer);
		}
		else {
			dungeonGameView.printResult("No possible solution");
		}

	}

	private void getMonsterMinimumSteps(int row, int col, int count) {
		if(row==area.length||row==-1||col==-1||col==area[0].length) {
			return;
		}
		if(area[row][col]=='G') {
			minStepsForMonster = Math.min(minStepsForMonster, count);
			return;
		}
		if(row<positionOfGold[0])
			getMonsterMinimumSteps(row+1,col,count+1);
		if(col<positionOfGold[1])
			getMonsterMinimumSteps(row,col+1,count+1);
		if(row>positionOfGold[0])
			getMonsterMinimumSteps(row-1,col,count+1);
		if(col>positionOfGold[1])
			getMonsterMinimumSteps(row,col-1,count+1);
	}
	public void addMonsterPosition(int length, int breadth) {
		positionOfMonster[0]=length;
		positionOfMonster[1]=breadth;
//		area[length][breadth] = 'M';
	}
//	public void addTriggerPosition(int length, int breadth) {
//		positionOfTrigger[0]=length;
//		positionOfTrigger[1]=breadth;
////		area[length][breadth] = 'T';
//	}
	public void addPitPosition(int length, int breadth) {
		area[length][breadth] = 'P';
	}
	
//	public void printStartingPosition() {
//		dungeonGameView.printArea(area);
//	}
}

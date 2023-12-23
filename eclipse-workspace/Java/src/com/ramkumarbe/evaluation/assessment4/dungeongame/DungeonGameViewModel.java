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
		int adventureHorizontalDifference = Math.abs(positionOfGold[0]-positionOfAdventurer[0]);
		int adventureVerticalDifference = Math.abs(positionOfGold[1]-positionOfAdventurer[1]);
		int monsterHorizontalDifference = Math.abs(positionOfGold[0]-positionOfMonster[0]);
		int monsterVerticalDifference = Math.abs(positionOfGold[1]-positionOfMonster[1]);
		int adventureMinSteps = adventureHorizontalDifference+adventureVerticalDifference;
		int monsterMinSteps = monsterHorizontalDifference+monsterVerticalDifference;
		if(adventureMinSteps<=monsterMinSteps) {
			printMinPath(positionOfAdventurer[0]+1,positionOfAdventurer[1]+1,positionOfGold[0]+1,positionOfGold[1]+1);	
		}
		else {
			dungeonGameView.printResult("No possible solution");
		}
	}
	private void printMinPath(int startRow, int startCol, int endRow, int endCol) {
		if(startCol<=endCol) {
			while(startCol<=endCol) {
				dungeonGameView.printPath(startRow,startCol++);
				if(startCol!=endCol+1) {
					dungeonGameView.printResult(" -> ");
				}
			}
		}
		else {
			while(startCol>=endCol) {
				dungeonGameView.printPath(startRow,startCol--);
				if(startCol!=endCol-1) {
					dungeonGameView.printResult(" -> ");
				}
			}
		}
		if(startRow!=endRow) {
			dungeonGameView.printResult(" -> ");
		}
		if(startRow<=endRow) {
			while(startRow<endRow) {
				dungeonGameView.printPath(++startRow,endCol);
				if(startRow!=endRow) {
					dungeonGameView.printResult(" -> ");
				}
			}
		}
		else {
			while(startRow>endRow) {
				dungeonGameView.printPath(--startRow,endCol);
				if(startRow!=endRow) {
					dungeonGameView.printResult(" -> ");
				}
			}
		}
	}
	public void addMonsterPosition(int length, int breadth) {
		positionOfMonster[0]=length;
		positionOfMonster[1]=breadth;
		area[length][breadth] = 'M';
	}
	
//	public void printStartingPosition() {
//		dungeonGameView.printArea(area);
//	}
}
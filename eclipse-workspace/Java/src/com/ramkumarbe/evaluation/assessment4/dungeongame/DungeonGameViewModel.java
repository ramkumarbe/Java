package com.ramkumarbe.evaluation.assessment4.dungeongame;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import com.ramkumarbe.evaluation.assessment4.dto.DungeonArea;
import com.ramkumarbe.evaluation.assessment4.dto.Point;

public class DungeonGameViewModel {

	private DungeonGameView dungeonGameView;
	
	public DungeonGameViewModel(DungeonGameView dungeonGameView) {
		this.dungeonGameView = dungeonGameView;
	}

	DungeonArea dungeonArea;
	char[][] area;
	Point adventurerPoint;
	Point goldPoint;
	Point monsterPoint;
	Point triggerPoint;
	
	public void getArea(int length, int breadth) {
		dungeonArea = new DungeonArea(length,breadth);
		area = dungeonArea.getArea();
	}
	public boolean isValidInput(int length, int breadth) {
		return length<dungeonArea.getLength()&&breadth<dungeonArea.getBreadth();
	}
	public void addAdventurerPosition(int x, int y) {
		adventurerPoint = new Point(x,y,0);
		area[x][y] = 'A';
	}
	public void addGoldPosition(int x, int y) {
		goldPoint = new Point(x,y,0);
		area[x][y] = 'G';
	}
	

	private int minStepsForMonster = Integer.MAX_VALUE;
	
	public void findMinimumSteps() {
		getMonsterMinimumSteps(monsterPoint,goldPoint,0);

		int advToGoldSteps = getMinimumSteps(adventurerPoint,goldPoint);
		
		int advToTrigSteps = getMinimumSteps(adventurerPoint,triggerPoint);

		int trigToGoldSteps = getMinimumSteps(triggerPoint,monsterPoint);
		
		System.out.println(minStepsForMonster);
		System.out.println(advToGoldSteps);
		System.out.println(advToTrigSteps);
		System.out.println(trigToGoldSteps);
		if(advToGoldSteps<=minStepsForMonster) {
			dungeonGameView.printResult(advToGoldSteps);
		}
		else {
			if(advToTrigSteps!=Integer.MAX_VALUE && trigToGoldSteps!=Integer.MAX_VALUE) {
				dungeonGameView.printResult(advToTrigSteps+trigToGoldSteps);
			}
			else {
				dungeonGameView.printResult("No possible solution");
			}
		}
	}

	private int getMinimumSteps(Point start, Point end) {
		Queue<Point> que = new LinkedList<>();
		que.add(start);
		boolean[][] visited = new boolean[area.length][area[0].length];
		int minSteps = Integer.MAX_VALUE;
		visited[start.getX()][start.getY()] = true;
		while(!que.isEmpty()) {
			Point point = que.poll();
			int x=point.getX(), y=point.getY(), steps=point.getSteps();
			if(x!=0 && area[x-1][y]!='P' && !visited[x-1][y]) {
				if(x-1==end.getX()&&y==end.getY()) {
					minSteps = Math.min(minSteps, steps+1);
				}
				else {
					que.add(new Point(x-1,y,steps+1));
					visited[x-1][y] = true;
				}
			}
			if(y!=0 && area[x][y-1]!='P' && !visited[x][y-1]) {
				if(x==end.getX()&&y-1==end.getY()) {
					minSteps = Math.min(minSteps, steps+1);
				}
				else {
					que.add(new Point(x,y-1,steps+1));
					visited[x][y-1] = true;
				}
			}
			if(x!=area.length-1 && area[x+1][y]!='P' && !visited[x+1][y]) {
				if(x+1==end.getX()&&y==end.getY()) {
					minSteps = Math.min(minSteps, steps+1);
				}
				else {
					que.add(new Point(x+1,y,steps+1));
					visited[x+1][y] = true;
				}
			}
			if(y!=area[0].length-1 && area[x][y+1]!='P' && !visited[x][y+1]) {
				if(x==end.getX()&&y+1==end.getY()) {
					minSteps = Math.min(minSteps, steps+1);
				}
				else {
					que.add(new Point(x,y+1,steps+1));
					visited[x][y+1] = true;
				}
			}
		}
		return minSteps;
	}
	private void getMonsterMinimumSteps(Point monster, Point gold, int steps) {
		int x = monster.getX(), y = monster.getY();
		if(x==area.length||x==-1||y==-1||y==area[0].length) {
			return;
		}
		if(area[x][y]=='G') {
			minStepsForMonster = Math.min(minStepsForMonster, steps);
			return;
		}
		if(x<gold.getX()) {
			monster.setX(x+1);
			getMonsterMinimumSteps(monster, gold, steps+1);
		}
		if(y<gold.getY()){
			monster.setY(y+1);
			getMonsterMinimumSteps(monster, gold, steps+1);
		}
		if(x>gold.getX()){
			monster.setX(x-1);
			getMonsterMinimumSteps(monster, gold, steps+1);
		}
		if(y>gold.getY()){
			monster.setY(y-1);
			getMonsterMinimumSteps(monster, gold, steps+1);
		}
	}
	public void addMonsterPosition(int x, int y) {
		monsterPoint = new Point(x,y,0);
		area[x][y] = 'M';
	}
	public void addTriggerPosition(int x, int y) {
		triggerPoint = new Point(x,y,0);
		area[x][y] = 'T';
	}
	public void addPitPosition(int x, int y) {
		area[x][y] = 'P';
	}
	
	public void printStartingPosition() {
		dungeonGameView.printArea(area);
	}
}

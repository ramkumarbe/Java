package com.ramkumarbe.evaluation.assessment4.dto;

public class Point {
	private int x;
	private int y;
	private int steps;

	public Point(int x, int y, int steps) {
		this.x = x;
		this.y = y;
		this.steps = steps;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getSteps() {
		return steps;
	}
	public void setSteps(int steps) {
		this.steps = steps;
	}
	
	
}

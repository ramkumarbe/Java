package com.ramkumarbe.evaluation.assessment4.dto;

import java.util.Arrays;

public class DungeonArea {
	private int length;
	private int breadth;
	private char[][] area;
	
	public DungeonArea(int row, int column) {
		this.length= row;
		this.breadth = column;
		this.area = new char[row][column];
//		for(char[] arr:area) {
//			Arrays.fill(arr, '_');
//		}
	}

	public char[][] getArea() {
		return area;
	}

	public int getLength() {
		return length;
	}

	public int getBreadth() {
		return breadth;
	}	
}

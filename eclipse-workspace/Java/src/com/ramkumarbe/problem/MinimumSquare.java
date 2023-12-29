package com.ramkumarbe.problem;

import java.util.Scanner;

public class MinimumSquare {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the length: ");
		int l = sc.nextInt();
		System.out.print("Enter the Width: ");
		int w = sc.nextInt();
		int minSquares = new MinimumSquare().getMinSquares(l,w);
		System.out.println("output: "+minSquares);
	}

//	private int getMinSquares(int l, int w) {
//		int area = l*w;
//		int side = (int)Math.sqrt(area);
//		while(area%(side*side) != 0) {
//			side--;
//		}
//		return area/(side*side);
//	}
	private int getMinSquares(int l, int w) {
		int min = Math.min(l, w);
		int max = Math.max(l, w);
		while(min!=0 && max!=0) {
			int temp = min;
			min = max%min;
			max = temp;
		}
		return max;
	}
}

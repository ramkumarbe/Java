package com.ramkumarbe.evaluation;

import java.util.Scanner;

public class ZigzagPattern {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Input: ");
		String s = sc.nextLine();
		System.out.println("Number of Row:");
		int n = sc.nextInt();
		printZigzagPattern(s, n);
	}

	private static void printZigzagPattern(String s, int n) {
		if(n==1) {
			System.out.println(s);
			return;
		}
		int len = s.length(), index = 0;
		int row = 0, col = 0;
		boolean upward = false;
		char[][] arr = new char[n][len];
		while (index < len) {
			arr[row][col] = s.charAt(index++);
			if (row == 0) {
				upward = false;
			} else if (row == n - 1) {
				upward = true;
			}
			if (upward) {
				row--;
			} else {
				row++;
				col++;
			}
		}
		
		for (char[] ar : arr) {
			for (char c : ar) {
				System.out.print(c+" ");
			}
			System.out.println();
		}
	}

}

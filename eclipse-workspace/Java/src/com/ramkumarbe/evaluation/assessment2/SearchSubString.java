package com.ramkumarbe.evaluation.assessment2;

import java.util.Arrays;
import java.util.Scanner;

public class SearchSubString {

	public static void main(String[] args) {
		SearchSubString searchSubString = new SearchSubString();
		searchSubString.init();
	}

	private void init() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Sentence:");
		String sentence = scanner.nextLine();
		System.out.println("Enter the SubString:");
		String subString = scanner.nextLine();
		System.out.println("Enter the number of Columns");
		int numberOfColumns = scanner.nextInt();
		int[][] res = searchSubString(sentence, subString, numberOfColumns);
		if(res==null) {
			System.out.println("No output.");
			return;
		}
		for(int[] arr:res) {
			System.out.println(Arrays.toString(arr));
		}
	}

	private int[][] searchSubString(String sentence, String subString, int numberOfColumns) {
		int row=0, col=0;
		for(int i=0; i<sentence.length(); i++) {
			if(col==numberOfColumns) {
				row++;
				col=0;
			}
			if(sentence.charAt(i) == subString.charAt(0)) {
				if(hasSubstringInDown(sentence, subString, row, col, numberOfColumns)) {
					return new int[][] {{row,col},{row+subString.length()-1,col}};
				}
				if(hasSubstringInRight(sentence, subString, row, col, numberOfColumns)) {
					return new int[][] {{row,col},{row,col+subString.length()-1}};
				}
			}
			col++;
		}
		return null;
	}

	private boolean hasSubstringInRight(String sentence, String subString, int row, int col, int numberOfColumns) {
		int n = subString.length();
		for(int i=0; i<n; i++) {
			if(col+i==numberOfColumns||sentence.charAt(row*numberOfColumns+col+i)!=subString.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	private boolean hasSubstringInDown(String sentence, String subString, int row, int col, int numberOfColumns) {
		int n = subString.length();
		for(int i=0; i<n; i++) {
			if((i+row)*numberOfColumns+col>=sentence.length()||sentence.charAt((row+i)*numberOfColumns+col)!=subString.charAt(i)) {
				return false;
			}
		}
		return true;
	}
}

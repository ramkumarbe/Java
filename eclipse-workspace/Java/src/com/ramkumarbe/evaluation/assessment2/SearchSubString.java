package com.ramkumarbe.evaluation.assessment2;

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
		searchSubString(sentence, subString, numberOfColumns);
	}

	private void searchSubString(String sentence, String subString, int numberOfColumns) {
		char[][] chars = new char[sentence.length()/numberOfColumns][numberOfColumns];
		for(int i=0; i<chars.length; i++) {
			for(int j=0; j<numberOfColumns; j++) {
				if(chars[i][j] == subString.charAt(0)) {
					
				}
			}
		}
		
	}

}

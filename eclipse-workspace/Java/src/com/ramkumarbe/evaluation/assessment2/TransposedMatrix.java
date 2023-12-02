package com.ramkumarbe.evaluation.assessment2;

import java.util.Scanner;

public class TransposedMatrix {

	public static void main(String[] args) {
		TransposedMatrix transposedMatrix = new TransposedMatrix();
		transposedMatrix.init();
	}

	private void init() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Number of Rows:");
		int numberOfRows = scanner.nextInt();
		System.out.println("Enter the Number of Columns:");
		int numberOfColumns = scanner.nextInt();
		int n = numberOfRows>=numberOfColumns ? numberOfRows:numberOfColumns;
		int[][] nums = new int[n][n];
		System.out.println("Enter the Elements:");
		for(int i=0; i<numberOfRows; i++) {
			for(int j=0; j<numberOfColumns; j++) {
				nums[i][j] = scanner.nextInt(); 
			}
		}
		transposeMatrix(nums, n);
		System.out.println("Output:");
		printTransposedArray(nums,numberOfColumns,numberOfRows);
	}

	private void printTransposedArray(int[][] nums, int rows, int columns) {
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns; j++) {
				System.out.printf("%3d",nums[i][j]);
			}
			System.out.println("\n");
		}
	}

	private void transposeMatrix(int[][] nums, int numberOfRows) {
		for(int i=0; i<numberOfRows; i++) {
			for(int j=i+1; j<numberOfRows; j++) {
				int temp = nums[i][j];
				nums[i][j] = nums[j][i];
				nums[j][i] = temp;
			}
		}
	}
}

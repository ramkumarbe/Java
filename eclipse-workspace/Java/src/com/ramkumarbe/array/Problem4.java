package com.ramkumarbe.array;

import java.util.Arrays;
import java.util.Scanner;

public class Problem4 {

	//problem: Matrix multiplication
	public static void function() {
		System.out.println("Enter number of Rows and Columns of the first Array1(split using space): ");
		int[][] arr1 = getArrayInput();

		System.out.println("Enter number of Rows and Columns of the second Array2(split using space): ");
		int[][] arr2 = getArrayInput();
		
		if(arr1[0].length!=arr2.length) {
			System.out.println("Array multiplication is not possible for given length.");
			return;
		}
		
		int[][] multipliedArray = getMultipliedArray(arr1, arr2);
		for(int[] arr:multipliedArray) {
			System.out.println(Arrays.toString(arr));
		}
	}

	private static int[][] getArrayInput() {  //get the 2d array input.
		Scanner sc = new Scanner(System.in);
		int row = sc.nextInt();
		int col = sc.nextInt();
		int[][] arr = new int[row][col];
		System.out.println("Enter the values of Array: ");
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		return arr;
	}

	private static int[][] getMultipliedArray(int[][] arr1, int[][] arr2) {  //get the multiplied array.
		int[][] multipliedArray = new int[arr1.length][arr2[0].length];
		
		for(int i=0; i<multipliedArray.length; i++) {
			for(int j=0; j<multipliedArray[i].length; j++) {
				for(int k=0; k<multipliedArray.length; k++) {
					multipliedArray[i][j] += arr1[i][k]*arr2[k][j];
				}	
			}
		}
		return multipliedArray;
	}

}

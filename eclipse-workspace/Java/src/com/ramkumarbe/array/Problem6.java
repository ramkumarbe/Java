package com.ramkumarbe.array;

import java.util.Arrays;
import java.util.Scanner;

//problem: Rotate an array by given number
public class Problem6 {

	public static void function(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of elements: ");
		int n = sc.nextInt();
		int[] arr = new int[n];
		
		System.out.println("Enter the elements of an array: " );
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		
		System.out.print("Enter the number of Rotation: ");
		int b = sc.nextInt();
		
		rotateArray(arr, n, b);
		System.out.println(Arrays.toString(arr));
	}

	private static void rotateArray(int[] arr, int n, int b) {
		for(int i=0; i<b; i++) {
			int first = arr[0];
			for(int j=1; j<n; j++) {
				arr[j-1] = arr[j];
			}
			arr[n-1] = first;
		}
	}
}

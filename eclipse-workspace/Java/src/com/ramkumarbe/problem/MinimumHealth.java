package com.ramkumarbe.problem;

import java.util.Scanner;

public class MinimumHealth {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of values: ");
		int n = sc.nextInt();
		int[] arr = new int[n];
		System.out.println("Enter the values:");
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		new MinimumHealth().findMinimumHealth(arr,n);
	}

	private void findMinimumHealth(int[] arr, int n) {
		for(int i=1; i<n; i++) {
			arr[i] = getGCD(arr[i-1],arr[i]);
		}
		System.out.println("output: "+arr[n-1]);
	}

	private int getGCD(int num1, int num2) {
		int min = Math.min(num1, num2);
		int max = Math.max(num1, num2);
		while(min!=0 && max!=0) {
			int temp = min;
			min = max%min;
			max = temp;
		}
		return max;
	}
}

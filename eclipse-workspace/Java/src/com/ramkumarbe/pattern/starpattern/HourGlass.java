package com.ramkumarbe.pattern.starpattern;

import java.util.Scanner;

public class HourGlass {
	
	public static void function() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Number: ");
		int n = sc.nextInt();
		printPattern(n);
	}
	
	private static void printPattern(int n) {
		for(int i=0; i<n; i++) {
			for(int k=0; k<i; k++) {
				System.out.print(" ");
			}
			for(int j=0; j<n-i; j++) {
				if(i==0 || j==0 || i+j==n-1)
					System.out.print("* ");
				else
					System.out.print("  ");
			}
			System.out.println();
		}
		for(int i=1; i<n; i++) {
			for(int k=n-1; k>i; k--) {
				System.out.print(" ");
			}
			for(int j=0; j<=i; j++) {
				if(i==n-1 || j==0 || i==j)
					System.out.print("* ");
				else
					System.out.print("  ");
			}
			System.out.println();
		}
	}
}

package com.ramkumarbe.pattern.starpattern;

import java.util.Scanner;

public class DownwardLeftTriangle {
	
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
				if(i==0 || j==0 || j==n-i-1)
				   System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}
}

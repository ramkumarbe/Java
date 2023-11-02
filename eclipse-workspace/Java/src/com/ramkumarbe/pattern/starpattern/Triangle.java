package com.ramkumarbe.pattern.starpattern;

import java.util.Scanner;

public class Triangle {
	
	public static void function() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Number: ");
		int n = sc.nextInt();
		printPattern(n);
	}
	
	private static void printPattern(int n) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n+i; j++) {
				if(j==n-i-1 || j==n+i-1 || i==n-1)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}
}

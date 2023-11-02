package com.ramkumarbe.pattern.numberpattern;

import java.util.Scanner;

public class Pyramid5 {
	
	public static void function() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Number: ");
		int n = sc.nextInt();
		printPyramid(n);
	}
	
	private static void printPyramid(int n) {
		for(int i=1; i<=n; i++) {
			for(int k=1; k<=n-i; k++) {
				System.out.print("  ");
			}
			for(int j=1; j<=i; j++) {
				System.out.print(j+" ");
			}
			for(int j=i-1; j>=1; j--) {
				System.out.print(j+" ");
			}
			System.out.println();
		}
	}
}

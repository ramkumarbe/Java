package com.ramkumarbe.pattern.starpattern;

import java.util.Scanner;

public class Butterfly {
	
	public static void function() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Number: ");
		int n = sc.nextInt();
		printPattern(n);
	}
	
	private static void printPattern(int n) {
		for(int i=1; i<=n; i++) {
			for(int k=1; k<=i; k++) {
				System.out.print("*");
			}
			for(int j=2*(n-1); j>=2*i-1; j--) {
				System.out.print(" ");
			}
			for(int k=1; k<=i; k++) {
				System.out.print("*");
			}
			System.out.println();
		}
		for(int i=1; i<n; i++) {
			for(int k=n; k>i; k--) {
				System.out.print("*");
			}
			for(int j=1; j<=2*i; j++) {
				System.out.print(" ");
			}
			for(int k=n; k>i; k--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}

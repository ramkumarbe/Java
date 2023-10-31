package com.ramkumarbe.pattern.starpattern;

import java.util.Scanner;

public class Pattern14 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number: ");
		int n = sc.nextInt();
		
		printPattern14(n);
	}

	private static void printPattern14(int n) {
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

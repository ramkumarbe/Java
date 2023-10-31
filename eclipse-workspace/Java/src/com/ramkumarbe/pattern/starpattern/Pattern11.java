package com.ramkumarbe.pattern.starpattern;

import java.util.Scanner;

public class Pattern11 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number: ");
		int n = sc.nextInt();
		
		printPattern11(n);
	}

	private static void printPattern11(int n) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}

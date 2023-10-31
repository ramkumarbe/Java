package com.ramkumarbe.pattern.starpattern;

import java.util.Scanner;

public class Pattern4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number: ");
		int n = sc.nextInt();
		
		printPattern4(n);
	}

	private static void printPattern4(int n) {
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

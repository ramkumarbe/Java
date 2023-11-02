package com.ramkumarbe.pattern.starpattern;

import java.util.Scanner;

public class LeftPascal {
	
	public static void function() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Number: ");
		int n = sc.nextInt();
		printPattern(n);
	}
	
	private static void printPattern(int n) {
		for(int i=0; i<n; i++) {
			for(int k=n; k>i+1; k--) {
				System.out.print(" ");
			}
			for(int j=0; j<=i; j++) {
				if(j==0 || i==j)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
		for(int i=n-1; i>=1; i--) {
			for(int k=n-1; k>=i; k--) {
				System.out.print(" ");
			}
			for(int j=1; j<=i; j++) {
				if(j==1 || i==j)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}

}

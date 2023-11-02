package com.ramkumarbe.pattern.starpattern;

import java.util.Scanner;

public class RightPascal {
	
	public static void function() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Number: ");
		int n = sc.nextInt();
		printPattern(n);
	}
	
	private static void printPattern(int n) {
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<=i; j++) {
				if(j==0 || i==j)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
		for(int i=n-1; i>0; i--) {
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

package com.ramkumarbe.pattern.numberpattern;

import java.util.Scanner;

public class Pyramid4 {
	
	public static void function() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Number: ");
		int n = sc.nextInt();
		printPyramid(n);
	}
	
	private static void printPyramid(int n) {
		for(int i=1; i<=n; i++) {
			for(int k=1; k<=n; k++) {
				if(k<=n-i)
					System.out.print("  ");
				else
				    System.out.print(k+" ");
			}
			for(int j=1; j<i; j++) {
				System.out.print(n-j+" ");
			}
			
			System.out.println();
		}
	}
}

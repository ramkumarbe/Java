package com.ramkumarbe.pattern.starpattern;

import java.util.Scanner;

public class Pattern1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number: ");
		int n = sc.nextInt();
		
		printPattern1(n);
	}

	private static void printPattern1(int n) {
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

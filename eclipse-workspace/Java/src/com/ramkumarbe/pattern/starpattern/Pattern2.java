package com.ramkumarbe.pattern.starpattern;

import java.util.Scanner;

public class Pattern2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number: ");
		int n = sc.nextInt();
		
		printPattern2(n);
	}

	private static void printPattern2(int n) {
		for(int i=0; i<n; i++) {
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

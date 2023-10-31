package com.ramkumarbe.pattern.starpattern;

import java.util.Scanner;

public class Pattern6 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number: ");
		int n = sc.nextInt();
		
		printPattern6(n);
	}

	private static void printPattern6(int n) {
		for(int i=0; i<n; i++) {
			for(int k=n; k>i; k--) {
				System.out.print(" ");
			}
			for(int j=0; j<n; j++) {
				if(i==0 || i==n-1 || j==0 || j==n-1)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}

}

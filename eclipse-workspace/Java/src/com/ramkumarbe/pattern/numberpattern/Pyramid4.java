package com.ramkumarbe.pattern.numberpattern;

import java.util.Scanner;

public class Pyramid4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number: ");
		int n = sc.nextInt();
		
		printPyramid4(n);
	}

	private static void printPyramid4(int n) {
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

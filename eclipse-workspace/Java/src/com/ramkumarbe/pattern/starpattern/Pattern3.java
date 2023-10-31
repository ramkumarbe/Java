package com.ramkumarbe.pattern.starpattern;

import java.util.Scanner;

public class Pattern3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number: ");
		int n = sc.nextInt();
		
		printPattern3(n);
	}

	private static void printPattern3(int n) {
		for(int i=0; i<n; i++) {
			for(int k=0; k<i; k++) {
				System.out.print(" ");
			}
			for(int j=0; j<n*2-i-1; j++) {
				if(i==0 || j==0 || 2*(n-i-1)==j) {
					System.out.print("*");
				}
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}

}

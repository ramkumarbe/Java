package com.ramkumarbe.pattern.starpattern;

import java.util.Scanner;

public class Pattern10 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number: ");
		int n = sc.nextInt();
		printPattern10(n);
	}

	private static void printPattern10(int n) {
		for(int i=0; i<n; i++) {
			for(int j=n-1; j>=i; j--)
			   System.out.print("*");
			for(int k=0; k<i*2; k++)
			   System.out.print(" ");
			for(int j=n-1; j>=i; j--) 
			   System.out.print("*");
			System.out.println();
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<=i; j++)
			   System.out.print("*");
			for(int k=2*n-2; k>2*i; k--)
			   System.out.print(" ");
			for(int j=0; j<=i; j++)
			   System.out.print("*");
			System.out.println();
		}
	}
}

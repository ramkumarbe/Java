package com.ramkumarbe.pattern.starpattern;

import java.util.Scanner;

public class Pattern9 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number: ");
		int n = sc.nextInt();
		
		printPattern9(n);
	}

	private static void printPattern9(int n) {
		for(int i=0; i<n; i++) {
			for(int k=n-1; k>i; k--) {
				System.out.print(" ");
			}
			for(int j=0; j<2*i+1; j++) {
				if(j==0 || j==2*i) {
					System.out.print("*");
				}
				else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		for(int i=n-1; i>0; i--) {
			for(int k=n-1; k>=i; k--) {
				System.out.print(" ");
			}
			for(int j=0; j<2*i-1; j++) {
				if(j==0 || j==2*i-2) {
					System.out.print("*");
				}
				else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

}

package com.ramkumarbe.pattern.numberpattern;

import java.util.Scanner;

public class Pyramid2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number: ");
		int n = sc.nextInt();
		
		printPyramid2(n);
	}

	private static void printPyramid2(int n) {
		for(int i=n; i>0; i--) {
			for(int k=n; k>i; k--) {
				System.out.print(" ");
			}
			for(int j=1; j<=i; j++) {
				System.out.print(i+" ");
			}
			System.out.println();
		}
	}

}

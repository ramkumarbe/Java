package com.ramkumarbe.evaluation;

import java.util.Scanner;

public class JumbledNumber {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Number: ");
		int n = sc.nextInt();
		boolean flag = isJumbledNumber(n);
		if(flag)
			System.out.println(n+" is a Jumbled number.");
		else
			System.out.println(n+" is not a Jumbled number.");
	}

	private static boolean isJumbledNumber(int n) {
		int last = n%10;
		while(n>0) {
			if(Math.abs(last-n%10)<=1) {
				last = n%10;
				n /= 10;
			}
			else
				return false;
		}
		return true;
	}

}

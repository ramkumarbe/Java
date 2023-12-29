package com.ramkumarbe.problem;

import java.util.Scanner;

public class DividedBy3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the input value: ");
		String input = sc.nextLine();
		boolean canDivide = new DividedBy3().canDivide(input);
		if(canDivide) {
			System.out.println("Yes");
		}
		else {
			System.out.println("No");
		}
	}

	private boolean canDivide(String input) { //238609294   1024819115206086200
		if(input.equals("0")) {
			return false;
		}
		int sum=0;
		for(char c:input.toCharArray()) {
			sum += c-'0';
		}
		return sum%3==0;
	}
}

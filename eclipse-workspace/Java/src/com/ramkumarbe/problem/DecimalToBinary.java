package com.ramkumarbe.problem;

import java.util.Scanner;

public class DecimalToBinary {

	public static void function() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the demimal value: ");
		int d = 0; 
		while(true) {
			if(sc.hasNextInt()) {
		       d = sc.nextInt();
		       break;
			}
			else
				System.out.println("Enter valid number.");

			sc.nextLine();
		}
		sc.nextLine();
		System.out.println(demimalToBinaryConversion(d));

	}
	private static String demimalToBinaryConversion(int d) {
		StringBuilder result = new StringBuilder();

		while (d > 0) {
			result.append(d % 2);
			d /= 2;
		}
		return result.reverse().toString();
	}

}

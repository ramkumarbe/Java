package com.ramkumarbe.evaluation;

import java.util.Scanner;

public class LexicographicalOrder {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of strings to be entered: ");
		int n = sc.nextInt();
		sc.nextLine();
		for (int i = 1; i <= n; i++) {
			System.out.print("Enter the String" + i + ":");
			String s = sc.nextLine();
			System.out.println("String" + i + ":" + loxicographicalOrder(s));
		}
	}

	private static String  loxicographicalOrder(String s) {
		char[] arr = s.toCharArray();
		for(int i=0; i<s.length()-1; i++) {
			for(int j=i+1; j<s.length(); j++) {
				if(arr[i]<arr[j]) {
					char temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return new String(arr);
	}

}

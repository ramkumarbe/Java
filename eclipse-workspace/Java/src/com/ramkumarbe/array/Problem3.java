package com.ramkumarbe.array;

import java.util.Scanner;

public class Problem3 {

	//problem: X-pattern.
	public static void function() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the String: ");
		String string = sc.nextLine();
		if(string.length()%2 !=0)
		    printPattern(string);
		else
			System.out.println("o");
	}

	private static void printPattern(String str) {
		int len = str.length();
		for(int i=0; i<len; i++) {
			for(int j=0; j<len; j++) {
				if(i==j) {
					System.out.print(str.charAt(i)+" ");
				}
				else if(len-1-i==j && i!=j) {
					System.out.print(str.charAt(i)+" ");
				}
				else {
					System.out.print("  ");
				}
			}
			System.out.println();
		}
	}
}

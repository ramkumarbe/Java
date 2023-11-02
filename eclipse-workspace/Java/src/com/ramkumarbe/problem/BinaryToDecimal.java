package com.ramkumarbe.problem;

import java.util.Scanner;

public class BinaryToDecimal {

	public static void function() {
		Scanner sc = new Scanner(System.in);
		
		String b = null;
		do{
			System.out.print("Enter the Valid Binary value: ");
		    b = sc.nextLine();
		}while(!b.matches("[0-1]{1,32}"));
		System.out.println(binaryToDecimalConversion(b));
	}

	private static int binaryToDecimalConversion(String b) {
		int len = b.length();
		int ans = 0;
		for(int i=0; i<len; i++) {
			ans += (b.charAt(i)-'0')*Math.pow(2,len-1-i);
		}
		return ans;
	}
}

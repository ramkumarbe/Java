package com.ramkumarbe.problem;

import java.util.Scanner;

public class RemoveNumber {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Input: ");
		int n = sc.nextInt();
		int[] res = {-1};
		String str = String.valueOf(n);
		new RemoveNumber().getMinimumRemovedNumber(str,"",0,res);
		
		System.out.println("Output: "+ (res[0] == -1 ? -1:str.length()-res[0]));
	}

	private void getMinimumRemovedNumber(String str, String num, int count, int[] res) {
		if(str.length()==0) {
			if(num.length()!=0) {
				int n = Integer.parseInt(num);
				int sqr = (int)Math.sqrt(n);
				if(sqr*sqr==n && num.length()>res[0]) {
					res[0] = num.length();
				}
			}
			return;
		}
		
		getMinimumRemovedNumber(str.substring(1), num+str.charAt(0), count+1, res);
		getMinimumRemovedNumber(str.substring(1), num, count, res);
	}

}

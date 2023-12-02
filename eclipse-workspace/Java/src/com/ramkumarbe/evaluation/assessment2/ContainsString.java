package com.ramkumarbe.evaluation.assessment2;

import java.util.Scanner;

public class ContainsString {

	public static void main(String[] args) {
		ContainsString containsString = new ContainsString();
		containsString.init();
	}

	private void init() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the String1:");
		String string1 = scanner.nextLine();
		System.out.println("Enter the String2:");
		String string2 = scanner.nextLine();
		if(string1.length()<string2.length()) {
			String temp = string1;
			string1 = string2;
			string2 = temp;
		}
		int[] freq = new int[26];
		for(char c:string1.toCharArray()) {
			if(c<='Z')
			    freq[c-'A']++;
			else
			    freq[c-'a']++;
		}
		System.out.println(iscontainsString(freq, string2));
	}

	private boolean iscontainsString(int[] freq, String string2) {
		for(char c:string2.toCharArray()) {
			if(c<='Z') {
				if(freq[c-'A']==0) {
					return false;
				}
				freq[c-'A']--;
			}
			else{

				if(freq[c-'a']==0) {
					return false;
				}
				freq[c-'a']--;
			}
		}
		return true;
	}
}

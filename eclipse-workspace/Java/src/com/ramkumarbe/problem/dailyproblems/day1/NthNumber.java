package com.ramkumarbe.problem.dailyproblems.day1;

import java.util.ArrayDeque;
import java.util.Scanner;

/*
 * 2. Form a number system with only 3 and 4. Find the nth number of the number system.
Eg.) The numbers are: 3, 4, 33, 34, 43, 44, 333, 334, 343, 344, 433, 434, 443, 444, 3333, 3334, 3343, 3344, 3433, 3434, 3443, 3444 ….
 */
public class NthNumber {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number: ");
		int n = sc.nextInt();
		String res = new NthNumber().getNthNumber(n);
		System.out.println(res);
	}

	private String getNthNumber(int n) {
		ArrayDeque<String> que = new ArrayDeque<>();
		que.add("");
		for (int i = 0; i < n; i += 2) {
			String res = que.poll();
			que.add(res + "3");
			que.add(res + "4");
		}
		if (n % 2 == 0) {
			return que.removeLast();
		} else {
			que.removeLast();
			return que.removeLast();
		}
	}
}

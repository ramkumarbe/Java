package com.ramkumarbe.consoleApplication.cricketscoreboard.util;

import java.util.Scanner;

public class Util {
	private static Util obj;
	private Util() { }
	public static Util getInstance() {
		if(obj==null) {
			obj = new Util();
		}
		return obj;
	}
	
	public void endLine() {
		System.out.println("\n-----------------------------------------------------------------\n");
	}

	Scanner sc = new Scanner(System.in);
	
	public int getInt() {
		while(true) {
			if(sc.hasNextInt()) {
				int n = sc.nextInt(); sc.nextLine();
				return n;
			}
			System.out.println("Enter valid Number.");
			sc.nextLine();
		}
	}
	public long getLong() {
		while(true) {
			if(sc.hasNextLong()) {
				long n = sc.nextLong(); sc.nextLine();
				return n;
			}
			System.out.println("Enter valid Number.");
			sc.nextLine();
		}
	}
	public String getString() {
		return sc.nextLine();
	}
}


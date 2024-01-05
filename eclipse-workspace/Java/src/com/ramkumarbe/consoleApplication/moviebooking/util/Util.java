package com.ramkumarbe.consoleApplication.moviebooking.util;

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
	
	public String getString() {
		return sc.nextLine();
	}
}

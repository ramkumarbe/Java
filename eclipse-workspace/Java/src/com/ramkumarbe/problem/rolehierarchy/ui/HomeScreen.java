package com.ramkumarbe.problem.rolehierarchy.ui;

import java.util.Scanner;

import com.ramkumarbe.problem.rolehierarchy.ui.addrole.AddRole;

public class HomeScreen {
	
	public void start() {
		Scanner sc = new Scanner(System.in);
		AddRole addRole = new AddRole();
//	    addRole.addRole();
		int n = 2;
		do {
			printMenu();
			System.out.print("Operation to be performed:");
			int choice = sc.nextInt();
			switch (choice) {
			case 1 -> {
				addRole.addSubRole();
			}
			case 0 -> {
				System.out.println("Exiting..");
				System.exit(0);
			}
			default -> {
				System.out.println("Enter valid Input.");
			}
			}
			System.out.println();
		} while (n-- > 0);
	}

	private void printMenu() {
		System.out.println("Operations");
		System.out.println("1.Add Sub Role");
	}
}

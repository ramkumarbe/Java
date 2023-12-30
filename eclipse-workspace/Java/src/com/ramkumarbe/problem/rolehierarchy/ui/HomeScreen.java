package com.ramkumarbe.problem.rolehierarchy.ui;

import java.util.Scanner;

import com.ramkumarbe.problem.rolehierarchy.ui.addrole.AddRole;

public class HomeScreen {
	
	public void start() {
		Scanner sc = new Scanner(System.in);
		AddRole addRole = new AddRole();
//	    addRole.addRole();
		int n = 0;
		do {
			printMenu();
			System.out.print("Operation to be performed:");
			int choice = sc.nextInt();
			switch (choice) {
			case 1 -> {
				addRole.addSubRole();
			}
			case 2 -> {
				addRole.displayRoles();
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
		} while (true);
	}

	private void printMenu() {
		System.out.println("Operations");
		System.out.println("1.Add Sub Role");
		System.out.println("2.Display Roles");
		System.out.println("0.Exit");
	}
}

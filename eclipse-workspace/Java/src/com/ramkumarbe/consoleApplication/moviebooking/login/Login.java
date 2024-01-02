package com.ramkumarbe.consoleApplication.moviebooking.login;

import com.ramkumarbe.consoleApplication.moviebooking.dto.User;
import com.ramkumarbe.consoleApplication.moviebooking.util.Util;

public class Login {

	private LoginPresenter presenter;

	public Login() {
		presenter = new LoginPresenter(this);
	}

	public User getUser() {
		User user = null;
		int choice;
		do {
			System.out.println("1.Login");
			System.out.println("2.Signup");
//			System.out.println("0.Exit");
			System.out.print("Enter your choice: ");
			choice = Util.getInstance().getInt();
			switch (choice) {
			case 1 -> {
				user = login();
			}
			case 2 -> {
				user = signup();
			}
			default -> {
				System.out.println("Enter valid input.");
			}
			}
		} while (choice!=1&&choice!=2);
		return user;
	}

	public User signup() {
		return presenter.addNewUser();
	}

	public User login() {
		User user;
		do {

			System.out.print("Enter the UserName: ");
			String userName = Util.getInstance().getString();

			System.out.print("Enter the Password: ");
			String password = Util.getInstance().getString();
	        user = presenter.getUser(userName,password);
			
	        if(user==null) {
	        	System.out.println("Invalid username or password.");
	        }
		} while (user==null);

		Util.getInstance().endLine();
		return user;
	}
}

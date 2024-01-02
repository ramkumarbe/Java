package com.ramkumarbe.consoleApplication.moviebooking.adduser;

import com.ramkumarbe.consoleApplication.moviebooking.dto.User;
import com.ramkumarbe.consoleApplication.moviebooking.util.Util;

public class AddUser {
	private AddUserViewModel addUserViewModel;
	public AddUser() {
		addUserViewModel = new AddUserViewModel(this);
	}
	
	public User addNewUser() {
		String userName=null, password=null, mailId=null, phoneNumber=null;
		
		boolean isValidUserName = false;
		do {
			System.out.println("Enter the Name of the user: ");
			userName = Util.getInstance().getString();
			isValidUserName = addUserViewModel.validateUserName(userName);
		}while(!isValidUserName);
		
		boolean isValidPassword = false;
		do {
			System.out.println("Enter the Password: ");
			password = Util.getInstance().getString();
			isValidPassword = addUserViewModel.validatePassword(password);
		}while(!isValidPassword);
		
		boolean isValidPhoneNumber = false;
		do {
			System.out.println("Enter the Phone number: ");
			phoneNumber = Util.getInstance().getString();
			isValidPhoneNumber = addUserViewModel.validatePhoneNumber(phoneNumber);
		}while(!isValidPhoneNumber);
		
		boolean isValidMailId = false;
		do {
			System.out.println("Enter the Mail Address: ");
			mailId = Util.getInstance().getString();
			isValidMailId = addUserViewModel.validateMailId(mailId);
		}while(!isValidMailId);
		
		User user = new User(userName,password,phoneNumber,mailId);
		
		addUserViewModel.addUser(user);
		
		return user;
	}
	
	public void printErrorMessage(String errorMessage) {
		System.out.println(errorMessage);
	}

	public void onSuccess() {
		System.out.println("User added successfully.");
	}

	public void onFailure() {
		System.out.println("Cannot add user. Try again.");
	}
}

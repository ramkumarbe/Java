package com.ramkumarbe.consoleApplication.moviebooking.login;

import com.ramkumarbe.consoleApplication.moviebooking.dto.User;

public class LoginPresenter {

	private Login view;
	private LoginModel model;
	public LoginPresenter(Login login) {
		this.view = login;
		this.model = new LoginModel(this);
	}
	
	public User addNewUser() {
		return model.addNewUser();
	}
	
	public User getUser(String userName, String password) {
		return model.getUser(userName,password);
	}

	public void showMessage(String message) {
		view.showMessage(message);
	}
}

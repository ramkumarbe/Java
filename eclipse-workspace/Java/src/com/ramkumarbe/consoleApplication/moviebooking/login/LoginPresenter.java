package com.ramkumarbe.consoleApplication.moviebooking.login;

import com.ramkumarbe.consoleApplication.moviebooking.dto.User;

public class LoginPresenter {

	private Login login;
	private LoginModel model;
	public LoginPresenter(Login login) {
		this.login = login;
		this.model = new LoginModel(this);
	}
	
	public User addNewUser() {
		return model.addNewUser();
	}
	
	public User getUser(String userName, String password) {
		return model.getUser(userName,password);
	}
}

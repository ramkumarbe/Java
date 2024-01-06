package com.ramkumarbe.consoleApplication.moviebooking.login;

import java.sql.SQLException;
import java.util.Map;

import com.ramkumarbe.consoleApplication.moviebooking.adduser.AddUser;
import com.ramkumarbe.consoleApplication.moviebooking.dto.User;
import com.ramkumarbe.consoleApplication.moviebooking.repository.MovieRepository;

public class LoginModel {
	private LoginPresenter presenter;
	
	LoginModel(LoginPresenter loginPresenter) {
		this.presenter = loginPresenter;
	}
	
	public User getUser(String userName, String password) {
		User currentUser = null;
		try {
			currentUser = MovieRepository.getInstance().getUser(userName,password);
		} catch (SQLException e) {
			presenter.showMessage("Cannot get user details");
		}
		if(currentUser==null) {
			return null;
		}
		return currentUser;
	}
	
	public User addNewUser() {
		return new AddUser().addNewUser();
	}

}

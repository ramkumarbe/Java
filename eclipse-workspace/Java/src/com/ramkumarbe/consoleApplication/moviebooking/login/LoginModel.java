package com.ramkumarbe.consoleApplication.moviebooking.login;

import java.sql.SQLException;
import java.util.Map;

import com.ramkumarbe.consoleApplication.moviebooking.adduser.AddUser;
import com.ramkumarbe.consoleApplication.moviebooking.dto.User;
import com.ramkumarbe.consoleApplication.moviebooking.repository.MovieRepository;

public class LoginModel {
	private Map<String,User> usersList;
	private LoginPresenter presenter;
	
	LoginModel(LoginPresenter loginPresenter) {
		this.presenter = loginPresenter;
		getUsersList();
	}
	
	private void getUsersList() {
		try {
			usersList = MovieRepository.getInstance().getUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public User getUser(String userName, String password) {
		User currentUser = usersList.get(userName);
		if(currentUser==null || !currentUser.getPassword().equals(password)) {
			return null;
		}
		return currentUser;
	}
	
	public User addNewUser() {
		return new AddUser().addNewUser();
	}

}

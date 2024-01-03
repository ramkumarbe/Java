package com.ramkumarbe.consoleApplication.moviebooking.dto;

public class User {
	
	public User(String userName, String password, String phoneNumber, String mail) {
		this.name = userName;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.mail = mail;
	}
	private String name;
	private String password;
	private String phoneNumber;
	private String mail;
	
	public String getUserName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
}

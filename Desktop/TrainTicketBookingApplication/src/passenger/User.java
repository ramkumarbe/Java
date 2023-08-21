package passenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import train.TrainMain;


public class User {
	private String userName;
	private String password;
	private String phoneNumber;
	
	public List<Passenger> passengersList = new ArrayList<Passenger>();
	
	public User() { }
    public User(String userName, String password, String phoneNumber) {
		this.setUserName(userName);
		this.setPassword(password);
		this.setPhoneNumber(phoneNumber);
	}

	public void addUsers() {
		TrainMain.userDetails.add(new User("1", "1", "9877543210"));
		TrainMain.userDetails.add(new User("Admin", "12345678", "6876533210"));
		TrainMain.userDetails.add(new User("Elon Musk", "password", "8876543211"));
		TrainMain.userDetails.add(new User("Einstien", "password", "9776543212"));
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
}

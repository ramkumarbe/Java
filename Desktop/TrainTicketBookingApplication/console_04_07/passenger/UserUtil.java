package passenger;

import java.util.ArrayList;
import java.util.List;

import train.Util;

public class UserUtil {
	private static UserUtil obj = null;
	private UserUtil() { }
	public static UserUtil getInstance() {
		if(obj == null)
			obj = new UserUtil();
		return obj;
	}

	public List<User> userDetails = new ArrayList<User>();
	public User getUser(String userName) {
		for (User user : userDetails) {
			if (user.getUserName().equals(userName)) {
				return user;
			}
		}
		return null;
	}
	public static int PASSENGER_ID = 1;
	public static String getPassengerId() {
		return "P"+PASSENGER_ID++;
	}
	private final static int ADMIN_LOGIN = 123123;
	private final static int LOGIN = 1;
	private final static int SIGNUP = 2;
	private final static int CHANGE_PASSWORD = 3;
	private final static int EXIT = 4;
    
	public final String ADMIN_USERNAME = "Admin";

	private User adminLogin() {
		User user = getUser(ADMIN_USERNAME);
		System.out.print("Enter the password: ");
		if (Util.getInstance().getPassword().equals(user.getPassword()))
			return user;
		System.out.println("Invalid Password.");
		return null;
	}

	private User login() {
		System.out.println("LOGIN PAGE");
		System.out.print("Enter the UserName: ");
		String userName = Util.getInstance().getUserName();
		System.out.print("Enter the password: ");
		User user = getUser(userName);
		if (user!=null && user.getPassword().equals(Util.getInstance().getPassword()))
			return user;
		System.out.println("Invalid Username or Password.");
		return null;
	}

	private User signup() {
		String userName;
		String password;
		String phoneNumber;

		boolean isPhoneNumberExist = false;
		boolean isUserNameExist = false;
		User user = null;
		System.out.println("SIGNUP PAGE");
		do {
			System.out.print("Enter Your Mobile Number: ");
			phoneNumber = Util.getInstance().getPhoneNumber();
			if (!isValidUserName(phoneNumber)) {
				System.out.println("Invalid Mobile Number.");
			}
		} while (!isValidPhoneNumber(phoneNumber));

		for (User userObj : UserUtil.getInstance().userDetails) {
			if (userObj.getPhoneNumber().equals(phoneNumber))
				isPhoneNumberExist = true;
		}
		if (isPhoneNumberExist) {
			System.out.println("Phone Number is already registered.");
			return null;
		} else {
			do {
				System.out.print("Enter the UserName: ");
				userName = Util.getInstance().getUserName();
				if (!isValidUserName(userName)) {
					System.out.println("UserName must only contain small letters,dot(.) and underscore(_)");
				}
			} while (!isValidUserName(userName));

			for (User userObj : UserUtil.getInstance().userDetails)
				if (userObj.getUserName().equals(userName))
					isUserNameExist = true;

			if (isUserNameExist) {
				System.out.println("Username already taken. Try another one.");
				return null;
			} else {
				boolean passwordCheck = true;
				do {
					System.out.print("Enter the password: ");
					password = Util.getInstance().getPassword();
					if (isValidPassword(password))
						passwordCheck = false;
					else
						System.out.println("Password strength is low.");
					if (userName.equals(password)) {
						System.out.println("Username and Password Cannot be same.");
						passwordCheck = true;
					}
				} while (passwordCheck);
				user = new User(userName, password, phoneNumber);
				UserUtil.getInstance().userDetails.add(user);
			}
		}
		return user;
	}

	public void changePassword() {
		String password;
		System.out.print("Enter the User Name: ");
		String userName = Util.getInstance().getUserName();
		User user = getUser(userName);
		if (user.getUserName().equals(userName)) {
			System.out.println("Enter the Password: ");
			password = Util.getInstance().getPassword();
			if (user.getPassword().equals(password)) {
				System.out.print("Enter the New Password: ");
				String newPassword = Util.getInstance().getPassword();
				user.setPassword(newPassword);
				System.out.println("Your password has been changed successfully.");
			} else
				System.out.println("Wrong UserName or Password.");
		}
	}
	public User userLogin() {

		User user = null;
		boolean isValidUser;
		do {
			isValidUser = true;
			System.out.println("1.Login");
			System.out.println("2.Signup");
			System.out.println("3.Change Password");
			System.out.println("4.Exit");
			int select = Util.getInstance().getNumber();
			switch (select) {
			case ADMIN_LOGIN:
				user = adminLogin();
				break;
			case LOGIN:
				user = login();
				break;
			case SIGNUP:
				user = signup();
				break;
			case CHANGE_PASSWORD:
				changePassword();
				break;
			case EXIT:
				System.exit(0);
			default:
				System.out.println("Enter valid input...");
				isValidUser = false;
			}
			if(user == null)
				isValidUser = false;
		} while (!isValidUser);
		return user;
	}

	public boolean isValidPhoneNumber(String phoneNumber) {
		String phoneNumberRegex = "[6-9][0-9]{9}";
		return phoneNumber.matches(phoneNumberRegex);
	}

	public boolean isValidUserName(String userName) {
		String userNameRegex = "^[[a-z]+[0-9][._]]{4,}";
		return userName.matches(userNameRegex);
	}

	public boolean isValidPassword(String password) {
		String passwordRegex = "^[[A-Z]+[a-z]+[0-9]+[!@#$%^&*()_<,.>/?]+]{8,}";
		return password.matches(passwordRegex);
	}

	private final String passengerNameRegex = "[A-Za-z]{3,}";
	public boolean isValidPassengerName(String passengerName) {
		return passengerName.matches(passengerNameRegex);
	}
}

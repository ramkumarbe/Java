package train;

import passenger.User;

public class LoginPage {
	private String userName;
	private static LoginPage object = null;
	private final String admin = "Admin";
	private final String adminPassword = "12345678";
	private final int adminLogin = 123123;
	private final int login = 1;
	private final int signup = 2;
	private final int changePassword = 3;
	private final int exit = 4;
    
	private LoginPage() { }
	public static LoginPage getInstance() {
		if (object == null)
			object = new LoginPage();
		return object;
	}

	Util util = Util.getInstance();

	public void entryPage() {
		boolean isValidUser = false;
		do {
			System.out.println("1.Login");
			System.out.println("2.Signup");
			System.out.println("3.Change Password");
			System.out.println("4.Exit");
			int select = util.getNumber();
			switch (select) {
			case adminLogin:
				isValidUser = adminLogin();
				break;
			case login:
				isValidUser = login();
				break;
			case signup:
				isValidUser = signup();
				break;
			case changePassword:
				changePassword();
				break;
			case exit:
				System.exit(0);
			default:
				System.out.println("Enter valid input...");
				isValidUser = false;
			}
		} while (!isValidUser);
	}

	private boolean adminLogin() {
		userName = admin;
		String password;
		System.out.print("Enter the password: ");
		password = util.getUserName();
		if (password.equals(adminPassword))
			return true;
		System.out.println("Invalid Password.");
		return false;
	}

	private boolean login() {
		String password;
		System.out.println("LOGIN PAGE");
		System.out.print("Enter the UserName: ");
		userName = util.getUserName();
		System.out.print("Enter the password: ");
		password = util.getPassword();
		for (User user : TrainMain.userDetails)
			if (user.getUserName().equals(userName) && user.getPassword().equals(password))
				return true;
		System.out.println("Invalid Username or Password.");
		return false;
	}

	private boolean signup() {
		String password;
		String phoneNumber;

		boolean isPhoneNumberExist = false;
		boolean isUserNameExist = false;
		System.out.println("SIGNUP PAGE");
		do {
			System.out.print("Enter Mobile Number: ");
			phoneNumber = util.getPhoneNumber();
			if (!util.isValidUserName(phoneNumber)) {
				System.out.println("Invalid Mobile Number.");
			}
		} while (!util.isValidPhoneNumber(phoneNumber));

		for (User user : TrainMain.userDetails) {
			if (user.getPhoneNumber().equals(phoneNumber))
				isPhoneNumberExist = true;
		}
		if (isPhoneNumberExist) {
			System.out.println("Phone Number already registered.");
			return false;
		} else {
			do {
				System.out.print("Enter the UserName: ");
				userName = util.getUserName();
				if (!util.isValidUserName(userName)) {
					System.out.println("Invalid UserName.");
				}
			} while (!util.isValidUserName(userName));

			for (User user : TrainMain.userDetails)
				if (user.getUserName().equals(userName))
					isUserNameExist = true;

			if (isUserNameExist) {
				System.out.println("Username already taken. Try another one.");
				return false;
			} else {
				boolean passwordCheck = true;
				do {
					System.out.print("Enter the password: ");
					password = util.getPassword();
					if (util.isValidPassword(password))
						passwordCheck = false;
					else
						System.out.println("Password strength is low.");
					if (userName.equals(password)) {
						System.out.println("Username and Password Cannot be same.");
						passwordCheck = true;
					}
				} while (passwordCheck);
				TrainMain.addUser(new User(userName, password, phoneNumber));
			}
		}
		return true;
	}

	private void changePassword() {
		String password;
		System.out.print("Enter the User Name: ");
		userName = util.getUserName();
		for (User user : TrainMain.userDetails) {
			if (user.getUserName().equals(userName)) {
				System.out.println("Enter the Password: ");
				password = util.getPassword();
				if (user.getPassword().equals(password)) {
					System.out.print("Enter the New Password: ");
					String newPassword = util.getPassword();
					user.setPassword(newPassword);
				} else
					System.out.println("Wrong UserName or Password.");
			}
		}
	}

	public String getUserName() {
		return userName;
	}
}

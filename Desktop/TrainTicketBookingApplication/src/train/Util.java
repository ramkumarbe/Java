package train;

import java.util.Scanner;

public class Util {
	private static Util obj = null;
	private Util() { }
	public static Util getInstance() {
		if(obj == null)
			obj = new Util();
		return obj;
	}
    public Scanner sc = new Scanner(System.in);
	public String getUserName() {
		String input = sc.nextLine().trim();
		return input;
	}
	public String getPassword() {
		String input = sc.nextLine().trim();
		return input;
	}
	public String getPhoneNumber() {
		String input = sc.nextLine().trim();
		return input;
	}
	public String getDateInput() {
		String input = sc.nextLine().trim();
		return input;
	}

	public String getPassengerName() {
		String input = sc.nextLine().trim();
		return input;
	}

	public String getBankId() {
		String input = sc.nextLine().trim();
		return input;
	}

	public String getUpiId() {
		String input = sc.nextLine().trim();
		return input;
	}

	public String getBankingPassword() {
		String input = sc.nextLine().trim();
		return input;
	}
	
	public String getUpiPassword() {
		String input = sc.nextLine().trim();
		return input;
	}

	public String getCardNumber() {
		String input = sc.nextLine().trim();
		return input;
	}

	public String getCardHolderName() {
		String input = sc.nextLine().trim();
		return input;
	}

	public String getCardExpiryDate() {
		String input = sc.nextLine().trim();
		return input;
	}

	public String getSeatNumber() {
		String input = sc.nextLine().trim();
		return input;
	}
	
	public String getNewTime() {
		String newTime = sc.nextLine().trim();
		return newTime;
	}
	
	public int getNumber() {
		boolean isNotInt = true;
		int number = 0;
		do {
			if (sc.hasNextInt()) {
				number = sc.nextInt();
				sc.nextLine();
				isNotInt = false;
			} else {
				sc.nextLine();
				System.err.println("Invalid Input");
			}
		} while (isNotInt);
		return number;
	}

	public byte getByteInput() {
		boolean isNotByte = true;
		byte byteValue = 0;
		do {
			if (sc.hasNextByte()) {
				byteValue = sc.nextByte();
				sc.nextLine();
				isNotByte = false;
			} else {
				sc.nextLine();
				System.err.println("Invalid Input");
			}
		} while (isNotByte);
		return byteValue;
	}

	public String getBack() {
		return sc.nextLine();
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

	public boolean isValidPassengerName(String passengerName) {
		String passengerNameRegex = "[A-Za-z]{3,}";
		return passengerName.matches(passengerNameRegex);
	}
	
	public boolean isValidTime(String time) {
		String timeRegex = "^([01][0-9]|2[0-3]):[0-5][0-9]$";
		return time.matches(timeRegex);
	}
}

package bank;

import java.util.ArrayList;
import java.util.List;

import train.Util;

public class Bank {
	private static Bank obj = null;

	private Bank() {
	}

	public static Bank getInstance() {
		if (obj == null)
			obj = new Bank();
		return obj;
	}

	public List<BankAccount> bankAccountDetails = new ArrayList<BankAccount>();

	private final static byte NETBANKING = 1;
	private final static byte UPI_TRANSACTION = 2;
	private final static byte CARD_TRANSACTION = 3;
	private final static byte BACK = 4;
	
	public BankAccount payment(double amount) {
		System.out.println("--------------------------- Payment Methods -------------------------");
		BankAccount bankAccount = null;
		boolean validInput = false;
		byte choice;
		do {
			validInput = true;
			System.out.println("1.Net Banking");
			System.out.println("2.UPI Transaction");
			System.out.println("3.Credit/Debit Card payment");
			System.out.println("4.Exit");
			choice = Util.getInstance().getByteInput();

			switch (choice) {
			case NETBANKING:
				bankAccount = netBanking(amount);
				break;
			case UPI_TRANSACTION:
				bankAccount = upiTransaction(amount);
				break;
			case CARD_TRANSACTION:
				bankAccount = cardPayment(amount);
				break;
			case BACK:
				return null;
			default:
				System.out.println("Invalid Input.");
				validInput = false;
			}
			if (bankAccount == null) {
				System.out.println("Press '1' to try again or press other numbers to exit.");
				int choice1 = Util.getInstance().getByteInput();
				if (choice1 == 1)
					validInput = false;
			}
		} while (!validInput);
		return bankAccount;
	}

	private BankAccount netBanking(double amount) {
		System.out.print("Enter Your Id: ");
		String id = Util.getInstance().getBankId();
		System.out.print("Enter Password: ");
		String password = Util.getInstance().getBankingPassword();
		NetBanking netbanking = Bank.getInstance().getNetBanking(id, password);
		if (netbanking == null) {
			System.out.println("Wrong NetBanking Id or Password.");
			return null;
		}
		BankAccount bankAccount = getBankAccount(netbanking);
		System.out.println("Total payment :" + amount);
		System.out.println("Press '1' to Confirm.");
		int choice = Util.getInstance().getByteInput();
		if (choice == 1) {
			if (bankAccount.getAmount() < amount) {
				System.out.println("Insufficient amount");
				return null;
			}
			bankAccount.setAmount(bankAccount.getAmount() - amount);
			System.out.println("Wait for a moment. This will take few seconds...");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Payment Transaction is successful.");
			return bankAccount;
		} else
			return null;
	}

	private BankAccount getBankAccount(NetBanking netbanking) {
		for (BankAccount bankAccount : Bank.getInstance().bankAccountDetails)
			if (bankAccount.getNetBanking().getNetBankingId().equals(netbanking.getNetBankingId()))
				return bankAccount;
		return null;
	}

	private BankAccount upiTransaction(double amount) {
		System.out.print("Enter the UPI Id: ");
		String upiId = Util.getInstance().getUpiId();
		System.out.print("Enter Password: ");
		String password = Util.getInstance().getUpiPassword();
		UPI upi = Bank.getInstance().getUpi(upiId, password);
		if (upi == null) {
			System.out.println("Wrong Upi Id or Password.");
			return null;
		}
		BankAccount bankAccount = getBankAccount(upi);
		System.out.println("Total payment :" + amount);
		System.out.println("Press '1' to Confirm.");
		int choice = Util.getInstance().getByteInput();
		if (choice == 1) {
			if (bankAccount.getAmount() < amount) {
				System.out.println("Insufficient amount");
				return null;
			}
			bankAccount.setAmount(bankAccount.getAmount() - amount);
			System.out.println("Wait for a moment. This will take few seconds...");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Payment Transaction is successful.");
			return bankAccount;
		} else
			return null;
	}

	private BankAccount getBankAccount(UPI upi) {
		for (BankAccount bankAccount : Bank.getInstance().bankAccountDetails)
			if (bankAccount.getUpi().getUpiId().equals(upi.getUpiId()))
				return bankAccount;
		return null;
	}

	private BankAccount cardPayment(double amount) {
		System.out.print("Enter Your Card Number: ");
		String cardNumber = Util.getInstance().getCardNumber();
		System.out.print("Enter Card Holder Name: ");
		String cardHolderName = Util.getInstance().getCardHolderName();
		String expiryDate = Util.getInstance().getCardExpiryDate();
		Card card = Bank.getInstance().getCard(cardNumber, cardHolderName, expiryDate);
		if (card == null) {
			System.out.println("Wrong Details");
			return null;
		}
		BankAccount bankAccount = getBankAccount(card);
		System.out.println("Total payment :" + amount);
		System.out.println("Press '1' to Confirm.");
		int choice = Util.getInstance().getByteInput();
		if (choice == 1) {
			if (bankAccount.getAmount() > amount) {
				System.out.println("Insufficient amount");
				return null;
			}
			bankAccount.setAmount(bankAccount.getAmount() - amount);
			System.out.println("Wait for a moment. This will take few seconds...");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Payment Transaction is successful.");
			return bankAccount;
		} else
			return null;
	}

	private BankAccount getBankAccount(Card card) {
		for (BankAccount bankAccount : Bank.getInstance().bankAccountDetails)
			if (bankAccount.getCard().getCardNumber().equals(card.getCardNumber()))
				return bankAccount;
		return null;
	}

	Card getCard(String cardNumber, String cardHolderName, String expiryDate) {
		for (BankAccount bankAccount : bankAccountDetails)
			if (bankAccount.getCard().getCardNumber().equals(cardNumber)
					&& bankAccount.getCard().getCardHolderName().equals(cardHolderName)
					&& bankAccount.getCard().getExpiryDate().equals(expiryDate))
				return bankAccount.getCard();
		return null;
	}

	NetBanking getNetBanking(String netBankingId, String password) {
		for (BankAccount bankAccount : bankAccountDetails)
			if (bankAccount.getNetBanking().getNetBankingId().equals(netBankingId)
					&& bankAccount.getNetBanking().getPassword().equals(password))
				return bankAccount.getNetBanking();

		return null;
	}

	public UPI getUpi(String upiId, String password) {
		for (BankAccount bankAccount : bankAccountDetails)
			if (bankAccount.getUpi().getUpiId().equals(upiId) && bankAccount.getUpi().getPassword().equals(password))
				return bankAccount.getUpi();

		return null;
	}

	public void refund(BankAccount bankAccount, int refundAmount) {
		bankAccount.setAmount(bankAccount.getAmount()-refundAmount);
	}

}

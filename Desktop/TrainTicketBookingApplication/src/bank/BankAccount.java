package bank;

import train.TrainMain;
import train.Util;

public class BankAccount {
	private String accountHolderName;
	private String accountNumber;
	private Card card;
	private NetBanking netBanking;
	private UPI upi;
	private int amount;
	private final static byte CASE_NETBANKING = 1;
	private final static byte CASE_UPI_TRANSACTION = 2;
	private final static byte CASE_CARD_TRANSACTION = 3;
	private final static byte BACK = 4;
	Util util = Util.getInstance();
	
	public BankAccount() { }
    public BankAccount(String accountHolderName, String accountNumber, Card card, NetBanking netBanking, UPI upi, int amount) {
    	this.setAccountHolderName(accountHolderName);
    	this.setAccountNumber(accountNumber);
    	this.setCard(card);
    	this.setNetBanking(netBanking);
    	this.setUpi(upi);
    	this.setAmount(amount);
    }
    
	public void addBankAccounts() {
		TrainMain.bankAccountDetails.add(new BankAccount("Ramkumar M","6476450171",new Card("9845236764523097", "RAMKUMAR M", "03/25"),new NetBanking("ram10", "ram"),new UPI("ram@okaxis", "ram"), 10000));
		TrainMain.bankAccountDetails.add(new BankAccount("Muthukumar","6546750192",new Card("4523634709764523", "MUTHUKUMAR", "07/24"),new NetBanking("muthu007", "muthu"),new UPI("muthu@okaxis", "muthu"), 25000));
		TrainMain.bankAccountDetails.add(new BankAccount("Rama selva thangam","6376450943",new Card("7097645984523623", "RAMA SELVA THANGAM", "11/23"),new NetBanking("thangam", "thangam"),new UPI("thangam@okaxis", "thangam"), 8000));
	}
	
	public BankAccount payment(int amount) {
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
			choice = util.getByteInput();

			switch (choice) {
			case CASE_NETBANKING:
				bankAccount = netBanking(amount);
				break;
			case CASE_UPI_TRANSACTION:
				bankAccount = upiTransaction(amount);
				break;
			case CASE_CARD_TRANSACTION:
				bankAccount = cardPayment(amount);
				break;
			case BACK:
				return null;
			default:
				System.out.println("Invalid Input.");
				validInput = false;
			}
			if(bankAccount==null) {
				System.out.println("Press '1' to try again or press other numbers to exit.");
				int choice1 = util.getByteInput();
				if (choice1 == 1)
					validInput = false;
			}
		} while(!validInput);
		return bankAccount;
	}

	private BankAccount netBanking(int amount) {
		System.out.print("Enter Your Id: ");
		String id = util.getBankId();
		System.out.print("Enter Password: ");
		String password = util.getBankingPassword();
		NetBanking netbanking = NetBanking.getNetBanking(id, password);
		if(netbanking == null) {
			System.out.println("Wrong NetBanking Id or Password.");
			return null;
		}
		BankAccount bankAccount = getBankAccount(netbanking);
		System.out.println("Total payment :" + amount);
		System.out.println("Press '1' to Confirm.");
		int choice = util.getByteInput();
		if (choice == 1) {
			if(bankAccount.getAmount()<amount) {
				System.out.println("Insufficient amount");
				return null;
			}
			bankAccount.setAmount(bankAccount.getAmount()-amount);
			System.out.println("Wait for a moment. This will take few seconds...");
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			System.out.println("Payment Transaction is successful.");
			return bankAccount;
		} else
			return null;
	}
	
	private BankAccount getBankAccount(NetBanking netbanking) {
		for(BankAccount bankAccount:TrainMain.bankAccountDetails)
			if(bankAccount.netBanking.getId().equals(netbanking.getId()))
				return bankAccount;
		return null;
	}

	private BankAccount upiTransaction(int amount) {
		System.out.print("Enter the UPI Id: ");
		String upiId = util.getUpiId();
		System.out.print("Enter Password: ");
		String password = util.getUpiPassword();
		UPI upi = UPI.getUpi(upiId, password);
		if(upi == null) {
			System.out.println("Wrong Upi Id or Password.");
			return null;
		}
		BankAccount bankAccount = getBankAccount(upi);
		System.out.println("Total payment :" + amount);
		System.out.println("Press '1' to Confirm.");
		int choice = util.getByteInput();
		if (choice == 1) {
			if(bankAccount.getAmount()<amount) {
				System.out.println("Insufficient amount");
				return null;
			}
			bankAccount.setAmount(bankAccount.getAmount()-amount);
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
		for(BankAccount bankAccount:TrainMain.bankAccountDetails)
			if(bankAccount.upi.getUpiId().equals(upi.getUpiId()))
				return bankAccount;
		return null;
	}

	private BankAccount cardPayment(int amount) {
		System.out.print("Enter Your Card Number: ");
		String cardNumber = util.getCardNumber();
		System.out.print("Enter Card Holder Name: ");
		String cardHolderName = util.getCardHolderName();
		String expiryDate = util.getCardExpiryDate();
		Card card = Card.getCard(cardNumber, cardHolderName, expiryDate);
		if(card == null) {
			System.out.println("Wrong Details");
			return null;
		}
		BankAccount bankAccount = getBankAccount(card);
		System.out.println("Total payment :" + amount);
		System.out.println("Press '1' to Confirm.");
		int choice = util.getByteInput();
		if (choice == 1) {
			if(bankAccount.getAmount()>amount) {
				System.out.println("Insufficient amount");
				return null;
			}
			bankAccount.setAmount(bankAccount.getAmount()-amount);
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
		for(BankAccount bankAccount:TrainMain.bankAccountDetails)
			if(bankAccount.upi.getUpiId().equals(upi.getUpiId()))
				return bankAccount;
		return null;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public NetBanking getNetBanking() {
		return netBanking;
	}

	public void setNetBanking(NetBanking netBanking) {
		this.netBanking = netBanking;
	}

	public UPI getUpi() {
		return upi;
	}

	public void setUpi(UPI upi) {
		this.upi = upi;
	}
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
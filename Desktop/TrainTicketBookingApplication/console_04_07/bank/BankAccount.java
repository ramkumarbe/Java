package bank;


public class BankAccount {
	private String accountHolderName;
	private String accountNumber;
	private Card card;
	private NetBanking netBanking;
	private UPI upi;
	private double amount;
    public BankAccount(String accountHolderName, String accountNumber, Card card, NetBanking netBanking, UPI upi, double amount) {
    	this.setAccountHolderName(accountHolderName);
    	this.setAccountNumber(accountNumber);
    	this.setCard(card);
    	this.setNetBanking(netBanking);
    	this.setUpi(upi);
    	this.setAmount(amount);
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
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
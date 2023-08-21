package bank;

import train.TrainMain;

public class Card {
    private String cardHolderName;
    private String expiryDate;
    private String cardNumber;
    
    public Card(String cardNumber, String cardHolderName, String expiryDate) {
    	this.setCardHolderName(cardHolderName);
    	this.setExpiryDate(expiryDate);
    	this.setCardNumber(cardNumber);
    }

	public String getCardHolderName() {
		return cardHolderName;
	}

	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public static Card getCard(String cardNumber, String cardHolderName, String expiryDate) {
		for(BankAccount bankAccount:TrainMain.bankAccountDetails) {
			if(bankAccount.getCard().cardNumber.equals(cardNumber)&&bankAccount.getCard().cardHolderName.equals(cardHolderName)&&bankAccount.getCard().expiryDate.equals(expiryDate))
				return bankAccount.getCard();
		}
		return null;
	}
}

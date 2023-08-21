package bank;

public class Card {
    private String cardHolderName;
    private String expiryDate;
    private String cardNumber;
    public Card(String cardNumber, String cardHolderName, String expiryDate) {
    	this.cardHolderName = cardHolderName;
    	this.expiryDate = expiryDate;
    	this.cardNumber = cardNumber;
    }
	public String getCardHolderName() {
		return cardHolderName;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public String getCardNumber() {
		return cardNumber;
	}
}

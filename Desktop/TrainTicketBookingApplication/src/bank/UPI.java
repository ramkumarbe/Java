package bank;

import train.TrainMain;

public class UPI {
    private String upiId;
    private String password;
    
    public UPI(String upiId, String password) {
    	this.setUpiId(upiId);
    	this.setPassword(password);
    }

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static UPI getUpi(String upiId, String password) {
		for(BankAccount bankAccount:TrainMain.bankAccountDetails) {
			if(bankAccount.getUpi().upiId.equals(upiId)&&bankAccount.getUpi().password.equals(password))
				return bankAccount.getUpi();
		}
		return null;
	}
	
}

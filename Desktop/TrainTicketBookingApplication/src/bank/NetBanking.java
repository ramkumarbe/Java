package bank;

import train.TrainMain;

public class NetBanking {
    private String id;
    private String password;
	public NetBanking(String id, String password) {
		this.setId(id);
		this.setPassword(password);
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public static NetBanking getNetBanking(String id, String password) {
		for(BankAccount bankAccount:TrainMain.bankAccountDetails) {
			if(bankAccount.getNetBanking().id.equals(id)&&bankAccount.getNetBanking().password.equals(password))
				return bankAccount.getNetBanking();
		}
		return null;
	}
}

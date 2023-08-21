package bank;

public class NetBanking {
    private String netBankingId;
    private String password;

	public NetBanking(String netBankingId, String password) {
		this.netBankingId = netBankingId;
		this.setPassword(password);
	}
    
	public String getNetBankingId() {
		return netBankingId;
	}
	public void setNetBankingId(String id) {
		this.netBankingId = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

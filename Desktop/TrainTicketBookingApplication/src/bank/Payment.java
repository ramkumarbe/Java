package bank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
public class Payment {
	Scanner sc = new Scanner(System.in);
	boolean paid;
	public static Map<String,String> netBankingDetails = new HashMap<String,String>();
	public static Set<String> upiTransactionDetails = new HashSet<String>(Arrays.asList("ram@okaxis","muthu@okaxis","thangam@okaxis"));
	
    public boolean pay(int amount) {
    	String choice;
    	System.out.println("--------------------------- Payment Methods -------------------------");
    	System.out.println("1.Net Banking");
    	System.out.println("2.UPI transaction");
    	System.out.println("Press Enter to get back");
    	choice = sc.nextLine();
    	
    	switch(choice) {
    	case "1": paid = netBanking(amount); break;
    	case "2": paid = upiTransaction(amount); break;
    	case "" : break;
    	default : System.out.println("Invalid Input."); pay(amount);
    	}
    	return paid;
    }

	private boolean netBanking(int amount) {
		System.out.print("Enter Your Id: ");
		String id = sc.nextLine();
		System.out.print("Enter Password: ");
		String password = sc.nextLine();
		if(!(netBankingDetails.containsKey(id)&&netBankingDetails.get(id).equals(password))) {
			System.out.println("Invalid User Id or Password");
			return false;
		}
		System.out.println("Total payment :"+amount);
		System.out.println("Press '1' to Confirm.");
		String choice = sc.nextLine();
		if(choice.equals("1")) {
			System.out.println("Wait for a moment. This will take 5 to 10 seconds...");
			try { Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Payment Transaction is successful.");
			return true;
		}
		else
			return false;
	}
	
	private boolean upiTransaction(int amount) {
		System.out.print("Enter the UPI Id: ");
		String upiId = sc.nextLine();
		if(upiId.equals(""))
			System.err.println("Invalid UPI Id");
		if(!upiTransactionDetails.contains(upiId)) 
			return false;
		System.out.println("Total payment :"+amount);
		System.out.println("Press '1' to Confirm.");
		String choice = sc.nextLine();
		if(choice.equals("1")) {
			System.out.println("Wait for a moment. This will take 5 to 10 seconds...");
			try { Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
			System.out.println("Payment Transaction is successful.");
			return true;
		}
		else
			return false;
	}
}

//private boolean creditCard(int amount) {
//	System.out.print("Enter Your Card Number: ");
//	sc.nextLine();
//	System.out.println("Enter Card Holder Name");
//	sc.nextLine();
//	System.out.print("Enter cvv: ");
//	sc.nextLine();
//	System.out.println("Total payment :"+amount);
//	System.out.println("Press '1' to Confirm.");
//	String choice = sc.nextLine();
//	if(choice.equals("1")) {
//		System.out.println("Wait for a moment. This will take 5 to 10 seconds...");
//		try { Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
//		System.out.println("Payment Transaction is successful.");
//		return true;
//	}
//	else
//		return false;
//}
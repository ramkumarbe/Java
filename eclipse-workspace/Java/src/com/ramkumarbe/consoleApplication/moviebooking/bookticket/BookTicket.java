package com.ramkumarbe.consoleApplication.moviebooking.bookticket;

import java.util.List;

import com.ramkumarbe.consoleApplication.moviebooking.dto.Show;
import com.ramkumarbe.consoleApplication.moviebooking.dto.Ticket;
import com.ramkumarbe.consoleApplication.moviebooking.dto.User;
import com.ramkumarbe.consoleApplication.moviebooking.util.Util;

public class BookTicket {
	private BookTicketViewModel viewModel;
	
	public BookTicket() {
		viewModel = new BookTicketViewModel(this);
	}

	public Ticket bookTicket(User user, Show show, List<Integer> seats) {
		return viewModel.bookTicket(user,show, seats);
	}

	public void payment(int amount) {
	    int wayOfPay;
	    boolean isValid;
	    do {
	        printPaymentBox(amount);
	        System.out.print("Enter your choice: ");
	        wayOfPay = Util.getInstance().getInt();
	        isValid = wayOfPay == 1 || wayOfPay == 2;
	        if (!isValid) {
	            System.out.println("Invalid choice. Please try again.");
	        }
	    } while (!isValid);
	}

	private void printPaymentBox(int amount) {
	    System.out.println("+----------------------------------------+");
	    System.out.println("|                Payment                 |");
	    System.out.println("+----------------------------------------+");
	    System.out.println("| Amount to pay: " + amount + "Rs.                  |");
	    System.out.println("| Choose the way of payment:             |");
	    System.out.println("| 1. Net Banking                         |");
	    System.out.println("| 2. UPI                                 |");
	    System.out.println("+----------------------------------------+");
	}

}

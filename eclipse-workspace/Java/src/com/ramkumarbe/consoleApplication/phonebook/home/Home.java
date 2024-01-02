package com.ramkumarbe.consoleApplication.phonebook.home;

import java.util.Set;

import com.ramkumarbe.consoleApplication.phonebook.addcontact.AddContact;
import com.ramkumarbe.consoleApplication.phonebook.dto.Contact;
import com.ramkumarbe.consoleApplication.phonebook.util.Util;

public class Home {

	private AddContact addContact;
	private HomeViewModel viewModel;

	public Home() {
		viewModel = new HomeViewModel(this);
	}

	public void menu() {
		addContact = new AddContact();
		int selection;
		boolean wantToContinue;
		do {
			wantToContinue = true;
			printMenuBox();
			System.out.print("Enter your choice: ");
			selection = Util.getInstance().getInt();

			switch (selection) {
			case 1 -> {
				addContact.addContact();
			}
			case 2 -> {
				Set<Contact> contacts = viewModel.getContacts();
				printContacts(contacts);
			}
			case 3 -> {
				searchContact();
			}
			case 4 -> {
				deleteContact();
			}
			case 0 -> {
				viewModel.finalize();
				System.out.println("Exiting Program...");
				wantToContinue = false;
			}
			default -> {
				System.out.println("Enter Valid Input.");
			}
			}
			System.out.println("");
		} while (wantToContinue);
	}

	private void deleteContact() {
		System.out.println("Enter the Name or Number: ");
		String input = Util.getInstance().getString();
		viewModel.deleteContact(input);
	}

	private void searchContact() {
		System.out.println("Enter the Name or Number: ");
		String name = Util.getInstance().getString();
		viewModel.searchContact(name);
	}

	public static void printContacts(Set<Contact> contacts) {
		System.out.println("Contacts:");

		for (Contact contact : contacts) {
			System.out.println("Name: " + contact.getContactName());
			System.out.println("Phone Number: " + contact.getPhoneNumber());
			System.out.println("------------------------");
		}
	}

	public void printMenuBox() {
		System.out.println("+-----------------------------------+");
		System.out.println("|            Contact Menu           |");
		System.out.println("+-----------------------------------+");
		System.out.println("| 1. Add new contact                |");
		System.out.println("| 2. Show Contacts                  |");
		System.out.println("| 3. Search Contact                 |");
		System.out.println("| 4. Delete Contact                 |");
		System.out.println("| 0. Exit                           |");
		System.out.println("+-----------------------------------+");
	}

	public void printContact(Contact contact) {
		System.out.println("Name: " + contact.getContactName());
		System.out.println("Phone Number: " + contact.getPhoneNumber());
		System.out.println("------------------------");
	}
	public void printMessage(String message) {
		System.out.println(message);
	}

}

package com.ramkumarbe.consoleApplication.phonebook.addcontact;

import com.ramkumarbe.consoleApplication.phonebook.dto.Contact;
import com.ramkumarbe.consoleApplication.phonebook.util.Util;

public class AddContact {
	AddContactViewModel viewModel;
	
	public AddContact() {
		viewModel = new AddContactViewModel(this); 
	}
	public void addContact() {
		String contactName;
		boolean isExist;
		do {
			System.out.print("Enter the Name of the Contact: ");
			contactName = Util.getInstance().getString();
			isExist = viewModel.isContactNameExist(contactName);
			if(isExist) {
				System.out.println("Contact Name already exists.");
			}
		}while(isExist);

		String phoneNumber;
		do {
			System.out.println("Enter the Contact Number: ");
			phoneNumber = String.valueOf(Util.getInstance().getLong());
			isExist = viewModel.isContactNumberExist(contactName);
			if(isExist) {
				System.out.println("Contact Number already exists.");
			}
		}while(isExist);
		Contact contact = new Contact(contactName,phoneNumber);
		viewModel.addNewContact(contact);
	}
	public void showMessage(String message) {
		System.out.println(message);
	}
}

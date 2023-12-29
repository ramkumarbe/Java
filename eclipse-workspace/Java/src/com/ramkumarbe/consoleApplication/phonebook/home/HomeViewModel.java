package com.ramkumarbe.consoleApplication.phonebook.home;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.ramkumarbe.consoleApplication.phonebook.dto.Contact;
import com.ramkumarbe.consoleApplication.phonebook.repository.ContactRepository;

public class HomeViewModel {

	private Home home;

	public HomeViewModel(Home home) {
		this.home = home;
	}

	public void showContacts() {
		List<Contact> contacts = new ArrayList<>();
		if(contacts.isEmpty()) {
			home.printMessage("There is no records.");
		}
		for(Contact contact:contacts) {
			home.printContact(contact);
		}
	}

	public void searchContact(String input) {
		Set<Contact> contacts = ContactRepository.getInstance().getContacts();
		if(contacts.isEmpty()) {
			home.printMessage("Contacts list is empty.");
		}
		boolean contactExists = false;
		for(Contact contact:contacts) {
			if(contact.getContactName().contains(input) || contact.getPhoneNumber().contains(input)) {
				home.printContact(contact);
				contactExists = true;
			}
		}
		if(contactExists) {
			home.printMessage("There is no such contact.");
		}
	}

	private Contact getContact(String input) {
		for(Contact contact:ContactRepository.getInstance().getContacts()) {
			if(contact.getContactName().equals(input) || contact.getPhoneNumber().equals(input)) {
				return contact;
			}
		}
		return null;
	}
	public void deleteContact(String input) {
		Contact contact = getContact(input);
		if(contact == null) {
			home.printMessage("No contact is related to the give information.");
		}
		else {
			ContactRepository.getInstance().getContacts().remove(contact);
			home.printMessage("Contact Deleted Successfully.");
		}
	}

}

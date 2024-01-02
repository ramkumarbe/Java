package com.ramkumarbe.consoleApplication.phonebook.addcontact;

import com.ramkumarbe.consoleApplication.phonebook.dto.Contact;
import com.ramkumarbe.consoleApplication.phonebook.repository.ContactRepository;

public class AddContactViewModel {

	private AddContact addContact;
	public AddContactViewModel(AddContact addContact) {
		this.addContact = addContact;
	}

	public void addNewContact(Contact contact) {
		ContactRepository.getInstance().getContacts().add(contact);
		addContact.showMessage("Contact added successfully.");
	}

	public boolean isContactNumberExist(String contactNumber) {
		for(Contact contact:ContactRepository.getInstance().getContacts()) {
			if(contact.getPhoneNumber().equals(contactNumber)) {
				return true;
			}
		}
		return false;
	}

	public boolean isContactNameExist(String contactName) {
		for(Contact contact:ContactRepository.getInstance().getContacts()) {
			if(contact.getContactName().equals(contactName)) {
				return true;
			}
		}
		return false;
	}
}

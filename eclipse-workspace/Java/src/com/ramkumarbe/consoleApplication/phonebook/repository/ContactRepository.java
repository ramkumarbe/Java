package com.ramkumarbe.consoleApplication.phonebook.repository;

import java.util.Set;
import java.util.TreeSet;

import com.ramkumarbe.consoleApplication.phonebook.dto.Contact;

public class ContactRepository {
	private Set<Contact> contacts = new TreeSet<>();
	private static ContactRepository repository = null;

	private ContactRepository() { }

	public static ContactRepository getInstance() {
		if (repository == null) {
			repository = new ContactRepository();
		}
		return repository;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}
	
}


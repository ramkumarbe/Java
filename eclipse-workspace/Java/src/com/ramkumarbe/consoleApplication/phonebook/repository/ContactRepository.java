package com.ramkumarbe.consoleApplication.phonebook.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramkumarbe.consoleApplication.phonebook.dto.Contact;

public class ContactRepository {
	private Set<Contact> contactsList;
	private static ContactRepository repository = null;

	private ContactRepository() {}

	public static ContactRepository getInstance() {
		if (repository == null) {
			repository = new ContactRepository();
		}
		return repository;
	}

	public void loadContacts() throws IOException {
		String filePath = "C:\\Users\\Admin\\eclipse-workspace\\Java\\src\\com\\ramkumarbe\\consoleApplication\\phonebook\\repository\\Phone_Book.json";

		ObjectMapper objectMapper = new ObjectMapper();
		
		JsonNode jsonNode = objectMapper.readTree(new File(filePath));

		JsonNode contactsArray = jsonNode.get("Contacts");
		
		contactsList = new TreeSet<>();
		
		for (JsonNode contactNode : contactsArray) {

			String contactName = contactNode.get("contactName").asText();
			
			String phoneNumber = contactNode.get("phoneNumber").asText();
			
			Contact contact = new Contact(contactName, phoneNumber);

			contactsList.add(contact);
		}
	}

	public void writeContactListToJsonFile() throws IOException {
		String filePath = "C:\\Users\\Admin\\eclipse-workspace\\Java\\src\\com\\ramkumarbe\\consoleApplication\\phonebook\\repository\\Phone_Book.json";

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode updatedContactsArray = objectMapper.valueToTree(contactsList);

		JsonNode rootNode = objectMapper.createObjectNode().set("Contacts", updatedContactsArray);

		objectMapper.writeValue(new File(filePath), rootNode);

	}

	public Set<Contact> getContacts() {
		return contactsList;
	}
}

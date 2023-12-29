package com.ramkumarbe.consoleApplication.phonebook.dto;

public class Contact implements Comparable<Contact>{
	private String contactName;
	private String phoneNumber;
	
	public Contact(String contactName, String phoneNumber) {
		this.contactName = contactName;
		this.phoneNumber = phoneNumber;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public int compareTo(Contact c) {
		return this.contactName.compareTo(c.contactName);
	}
	
	public String toString() {
		return "Name: "+this.contactName +"\nPhone Number: "+this.phoneNumber;
	}
}

package passenger;

import train.Ticket;

public class Passenger {  
	private int passengerId;
	private String passengerName;
	private int age;
	private String gender;
	private Ticket ticket;
    
    public Passenger(int passengerId, String passengerName,int age, String gender, Ticket ticket) {
    	this.setPassengerId(passengerId);
    	this.setPassengerName(passengerName);
    	this.setAge(age);
    	this.setGender(gender);
    	this.setTicket(ticket);
    }
    
	public int getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}

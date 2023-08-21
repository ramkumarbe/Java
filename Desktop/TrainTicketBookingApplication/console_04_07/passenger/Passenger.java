package passenger;

public class Passenger {  
	private String passengerName;
	private int age;
	private String gender;
    
    public Passenger(String passengerName,int age, String gender) {
    	this.passengerName = passengerName;
    	this.age = age;
    	this.gender = gender;
    }
    
	public String getPassengerName() {
		return passengerName;
	}
	public int getAge() {
		return age;
	}
	public String getGender() {
		return gender;
	}
}

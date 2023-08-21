package train;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class DateAndTime {
	String date;
	public static List<String> dates = new ArrayList<String>();
    static SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    public void updateDate() {
    	for(byte i=1; i<=4; i++) {
        	Date currentDate = new Date();
        	currentDate.setSeconds(i*86400);
    	    String date = dateformat.format(currentDate);
        	dates.add(date);
        }
    }
    public String getDate() {
    	for(byte i=0; i<4; i++) {
        	Date currentDate = new Date();
        	currentDate.setSeconds((i+1)*86400);
    	    String date = dateformat.format(currentDate);
    	    System.out.println(i+1+"."+date);
        }
    	System.out.println("5.Enter the Date you want");
    	
    	Scanner sc = new Scanner(System.in);
    	String choice = sc.nextLine();
    	String date = null;
    	
    	switch(choice) {
    	case "1": date = dates.get(0); break;
    	case "2": date = dates.get(1); break;
    	case "3": date = dates.get(2); break;
    	case "4": date = dates.get(3); break;
    	case "5": date = givenDate(); Train.updateTrain(date); break;
    	default : System.err.println("Enter valid input."); date = getDate();
    	}
		return date;
    }
    private String givenDate() {
    	Date currentDate = new Date();
    	Scanner sc = new Scanner(System.in);
    	System.out.print("Enter the Date(dd/mm/yyyy): ");
    	String bookingDate = sc.nextLine();
    	Date lastDate = new Date(currentDate.getTime()+604800000L);
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
    	Date givenDate = null;
		try { givenDate = dateFormat.parse(bookingDate); } 
		catch (ParseException e) { 
			System.err.println("Invalid Date Input"); givenDate(); }
    	if(givenDate.after(currentDate) && givenDate.before(lastDate)) {
    		String date = dateformat.format(givenDate);
    		if(!dates.contains(date))
    			dates.add(date);
    		return date;
    	}
    	
    	else {
    		System.err.println("\t\t\t\t\t\t Only dates 1 week from today are available");
    		return givenDate();
    	}
    }
}


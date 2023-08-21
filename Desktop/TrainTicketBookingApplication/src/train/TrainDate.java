package train;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrainDate {
	String date;
	private final byte firstDate = 1;
	private final byte secondDate = 2;
	private final byte thirdDate = 3;
	private final byte forthDate = 4;
	private final byte givenDate = 5;
    private static TrainDate obj = null;
    private TrainDate() { }
    public static TrainDate getInstance() {
    	if(obj == null) 
    		obj = new TrainDate();
    	return obj;
    }

    static SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
    Util util = Util.getInstance();

	public void addDate(){
    	for(byte i=1; i<=4; i++) {
        	Date currentDate = new Date();
        	currentDate.setSeconds(i*86400);
    	    String date = dateformat.format(currentDate);
        	TrainMain.dates.add(date);
        }
    }
	
    public String getDate() {
    	boolean validInput = false;
		String date = null;
    	do {
    		validInput = true;
    		byte i=0;
    		for(String day:TrainMain.dates) {
    			System.out.println(++i +". "+day);
    		}
    		System.out.println("5.Enter the Date you want");
    		int choice = util.getByteInput();
    		switch(choice) {
    		case firstDate : date = TrainMain.dates.get(0); break;
    		case secondDate: date = TrainMain.dates.get(1); break;
    		case thirdDate : date = TrainMain.dates.get(2); break;
    		case forthDate : date = TrainMain.dates.get(3); break;
    		case givenDate : date = givenDate(); new Train().updateTrains(date); break;
    		default : System.out.println("Enter valid input."); validInput = false;
    		}
    	}while(!validInput);
    	return date;
    }
    private String givenDate() {
    	Date currentDate = new Date();
    	System.out.print("Enter the Date(dd/mm/yyyy): ");
    	String bookingDate = util.getDateInput();
    	Date lastDate = new Date(currentDate.getTime()+604800000L);
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
    	Date givenDate = null;
		try { givenDate = dateFormat.parse(bookingDate); } 
		catch (ParseException e) { 
			System.out.println("Invalid Date Input"); givenDate(); }
    	if(givenDate.after(currentDate) && givenDate.before(lastDate)) {
    		String date = dateformat.format(givenDate);
    		if(!TrainMain.dates.contains(date))
    			TrainMain.dates.add(date);
    		return date;
    	}
    	else {
    		System.out.println("\t\t\t\t\t\t Only dates 1 week from today are available");
    		return givenDate();
    	}
    }
}


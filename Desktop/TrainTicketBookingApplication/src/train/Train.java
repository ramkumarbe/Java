package train;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import passenger.Passenger;

public class Train {
	private String trainName;
	private String time;
	private String date;
	private String destination;
	public boolean bookingStatus = true;
	private int firstClassWaitingSeatNumber;
	private int secondClassWaitingSeatNumber;
	private int sittingWaitingSeatNumber;
	private int sleeperWaitingSeatNumber;
	private final static String nellaiExpress = "Nellai Express";
	private final static String ananthapuriExpress = "Ananthapuri Express";
	private final static String kanyakumariExpress = "Kanyakumari Express";
	private final static String GUVExpress = "GUV Express";
	private final static String nellaiExpressTime = "19:30";
	private final static String ananthapuriExpressTime = "19:55";
	private final static String kanyakumariExpressTime = "19:05";
	private final static String GUVExpressTime = "8:40";
	private final static String firstClass = "First Class";
	private final static String secondClass = "Second Class";
	private final static String sleeper = "Sleeper";
	private final static String sitting = "Sitting";
	private final static String firstClassWaitinglistSeat = "FCWL";
	private final static String secondClassWaitinglistSeat = "SCWL";
	private final static String sleeperWaitinglistSeat = "SLWL";
	private final static String sittingWaitinglistSeat = "SIWL";
	private final static byte toTirunelveli = 1;
	private final static byte toChennai = 2;
	private final static byte trainNellai = 1;
	private final static byte trainAnanthapuri = 2;
	private final static byte trainKanyakumari = 3;
	private final static byte trainGUV = 4;
	private final static byte caseUpper = 1;
	private final static byte caseLower = 2;
	private final static byte caseMiddle = 3;
	private final static byte caseWindow = 1;
	private final static char upper = 'U';
	private final static char lower = 'L';
	private final static char middle = 'M';
	private final static char window = 'W';
	public static List<String> destinations = new ArrayList<String>(
			Arrays.asList("Chennai To Tirunelveli", "Tirunelveli To Chennai"));
	
    public List<Passenger> passengerList= new ArrayList<Passenger>();
	static TrainMain main = TrainMain.getInstance();

	final static int firstClassPrice = 2400;
	final static int secondClassPrice = 1200;
	final static int sleeperPrice = 600;
	final static int sittingPrice = 300;

	public Train() { }
	public Train(String destination, String date, String trainName, String time) {
		this.setDestination(destination);
		this.setDate(date);
		this.setTrainName(trainName);
		this.setTime(time);
	}
    Util util = Util.getInstance();
	List<String> firstClassSeats = new LinkedList<String>(
			Arrays.asList("FC1", "FC2", "FC3", "FC4", "FC5", "FC6", "FC7", "FC8", "FC9", "FC10"));
	
	List<String> secondClassSeats = new LinkedList<String>(
			Arrays.asList("SC1-L", "SC2-U", "SC3-L", "SC4-U", "SC5-L","SC6-U", "SC7-L", "SC8-U", "SC9-L", 
					"SC10-U", "SC11-L", "SC12-U", "SC13-L", "SC14-U", "SC15-L", "SC16-U"));
	
	List<String> sleeperSeats = new LinkedList<String>(
			Arrays.asList("SL1-L", "SL2-M", "SL3-U", "SL4-L", "SL5-M", "SL6-U", "SL7-L", "SL8-M", "SL9-U", 
					"SL10-L", "SL11-M", "SL12-U", "SL13-L", "SL14-M", "SL15-U", "SL16-L", "SL17-M", "SL18-U"));
	
	List<String> sittingSeats = new LinkedList<String>(
		Arrays.asList("SI1-W", "SI2", "SI3", "SI4", "SI5-W", "SI6-W", "SI7", "SI8", "SI9", "SI10-W", "SI11-W", "SI12", "SI13",
			       "SI14", "SI15-W", "SI16-W", "SI17", "SI18", "SI19", "SI20-W", "SI21-W", "SI22", "SI23", "SI24", "SI25-W"));
	
	List<String> RACSeats = new LinkedList<String>(
			Arrays.asList("RAC1", "RAC2", "RAC3", "RAC4", "RAC5", "RAC6", "RAC7", "RAC8", "RAC9", "RAC10"));
	
	Queue<String> firstClassWaitingList = new LinkedList<String>();
	Queue<String> secondClassWaitingList = new LinkedList<String>();
	Queue<String> sleeperWaitingList = new LinkedList<String>();
	Queue<String> sittingWaitingList = new LinkedList<String>();
	Map<String, Boolean> availableSeats = new HashMap<String, Boolean>();
	
	public void addTrain() { // To add trains in train array
		for (String destination : destinations) {
			for (String date : TrainMain.dates) {
				TrainMain.trains.add(new Train(destination, date, nellaiExpress, nellaiExpressTime));
				TrainMain.trains.add(new Train(destination, date, ananthapuriExpress, ananthapuriExpressTime));
				TrainMain.trains.add(new Train(destination, date, kanyakumariExpress, kanyakumariExpressTime));
				TrainMain.trains.add(new Train(destination, date, GUVExpress, GUVExpressTime));
			}
		}
	
		for (Train train : TrainMain.trains) {
			for (String seats : train.firstClassSeats)
				train.availableSeats.put(seats, train.bookingStatus);
			for (String seats : train.secondClassSeats)
				train.availableSeats.put(getSeatNumber(seats), train.bookingStatus);
			for (String seats : train.sleeperSeats)
				train.availableSeats.put(getSeatNumber(seats), train.bookingStatus);
			for (String seats : train.sittingSeats)
				train.availableSeats.put(getSeatNumber(seats), train.bookingStatus);
			for (String seats : train.RACSeats)
				train.availableSeats.put(getSeatNumber(seats), train.bookingStatus);
		}
	}
	
	public void availableSeats() { // To print remaining Tickets
		Train train = getTrain(selectDestination(),TrainDate.getInstance().getDate());
		System.out.println("\t\t\t ----------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t First Class :" + train.firstClassSeats.size());
		System.out.println("\t\t\t\t\t Second Class:" + train.secondClassSeats.size());
		System.out.println("\t\t\t\t\t Sleeper     :" + train.sleeperSeats.size());
		System.out.println("\t\t\t\t\t Sitting     :" + train.sittingSeats.size());
		System.out.println("\t\t\t ----------------------------------------------------------------------");
	}
    
	public void updateTrains(String date) { // To add trains in train array
		boolean trainAlreadyAvailable = false;
    	for(Train train:TrainMain.trains) {
    		if(train.getDate().equals(date)) {
    			trainAlreadyAvailable = true;
    			break;
    		}
    	}
    	if(!trainAlreadyAvailable) {
    		for (String destination : destinations) {
    			TrainMain.trains.add(new Train(destination, date, nellaiExpress, nellaiExpressTime));
    			TrainMain.trains.add(new Train(destination, date, ananthapuriExpress, ananthapuriExpressTime));
    			TrainMain.trains.add(new Train(destination, date, kanyakumariExpress, kanyakumariExpressTime));
    			TrainMain.trains.add(new Train(destination, date, GUVExpress, GUVExpressTime));
    		}
    	}
	}

	public String selectDestination() {
		String destination = null;
		boolean validInput = false;
		do {
			validInput = true;
			System.out.println("1.Tirunelveli to Chennai");
			System.out.println("2.Chennai to Tirunelveli");

			int choice = util.getByteInput();
			switch (choice) {
			case toChennai:
				destination = destinations.get(0);
				break;
			case toTirunelveli:
				destination = destinations.get(1);
				break;
			default:
				System.out.println("Enter valid input.");
				validInput = false;
			}
		}while(!validInput);
		return destination;
	}

	public Train getTrain(String destination, String date) { // To select a train
		for(Train train:TrainMain.trains) {
			if (train.destination.equals(destination) && train.date.equals(date) 
				&& train.trainName.equals(nellaiExpress))
			    System.out.println("1."+nellaiExpress+" - "+train.time);
			if (train.destination.equals(destination) && train.date.equals(date)
					&& train.trainName.equals(ananthapuriExpress)) 
			    System.out.println("2."+ananthapuriExpress+" - "+ train.time);
			if (train.destination.equals(destination) && train.date.equals(date)
					&& train.trainName.equals(kanyakumariExpress))
			    System.out.println("3."+kanyakumariExpress+" - "+ train.time);
			if (train.destination.equals(destination) && train.date.equals(date)
						&& train.trainName.equals(GUVExpress))
			    System.out.println("4."+GUVExpress+" - "+ train.time);
		}
		
		Train trainObj = null;
		byte trainNumber = util.getByteInput();  
		boolean validInput = false;
		do {
			validInput = true;
			switch (trainNumber) {
			case trainNellai:
				for (Train train : TrainMain.trains) {
					if (train.destination.equals(destination) && train.date.equals(date) 
							&& train.trainName.equals(nellaiExpress)) {
						trainObj = train;
					}
				}
				break;

			case trainAnanthapuri:
				for (Train train : TrainMain.trains) {
					if (train.destination.equals(destination) && train.date.equals(date)
							&& train.trainName.equals(ananthapuriExpress)) {
						trainObj = train;
					}
				}
				break;

			case trainKanyakumari:
				for (Train train : TrainMain.trains) {
					if (train.destination.equals(destination) && train.date.equals(date)
							&& train.trainName.equals(kanyakumariExpress)) {
						trainObj = train;
					}
				}
				break;

			case trainGUV:
				for (Train train : TrainMain.trains) {
					if (train.destination.equals(destination) && train.date.equals(date)
							&& train.trainName.equals(GUVExpress)) {
						trainObj = train;
					}
				}
				break;

			default:
				System.out.println("Invalid input.");
				validInput = false;
			}
		}while(!validInput);
		return trainObj;
	}
	
	public void viewBookingStatus() {
		System.out.println("Select Destination: ");
		String destination = selectDestination();
		System.out.println("Select Date: ");
		String date = TrainDate.getInstance().getDate();
		System.out.println("Select Train: ");
		Train train = getTrain(destination, date);
		boolean bookingStatus = false;
		boolean validSeatNumber = false;
		do {
			System.out.print("Enter the Seat Number: ");
			String seatNumber = util.getSeatNumber().toUpperCase();
			for(Train trainObj:TrainMain.trains) {
				if(trainObj.getDestination().equals(destination)&&
						trainObj.getDate().equals(date)&&trainObj.getTrainName().equals(train.getTrainName())){
					if(trainObj.availableSeats.containsKey(seatNumber)) {
						bookingStatus = trainObj.availableSeats.get(seatNumber.toUpperCase());
						validSeatNumber = true;
					}
				}
			}
			if(!validSeatNumber) {
				System.out.println("Invalid Seat Number.");
				System.out.println("Press '0' to Exit.");
				System.out.println("Press other numbers to continue.");
				byte choice = util.getByteInput();
				if(choice == 0) {
					return;
				}
			}
		}while(!validSeatNumber);
		if(bookingStatus)
			System.out.println("Seat is still available.");
		else
			System.out.println("Seat was Booked.");
		System.out.println("Press Enter to get Back.");
		util.getBack();
	}

	public void changeTime() {
		System.out.println("Select Destination: ");
		String destination = selectDestination();
		System.out.println("Select Date: ");
		String date = TrainDate.getInstance().getDate();
		System.out.println("Select Train: ");
		Train train = getTrain(destination, date);
		String newTime = null;
		do {
			System.out.print("Enter the New Time(HH:MM):");
			newTime = util.getNewTime();
			if(!util.isValidTime(newTime))
				System.out.println("Invalid Time.");
		}while(!util.isValidTime(newTime));
		
		for(Train train2:TrainMain.trains) {
			if(train2.getDestination().equals(destination)&&
					train2.getDate().equals(date)&&train2.getTrainName().equals(train.getTrainName())){
				train2.setTime(newTime);
			}
		}
		System.out.println("Time was updated.");
	}
	
	public String getSeats(String coach) {
		String seatNumber = null;
		boolean validInput = true;
		do {
			validInput = true;
			switch (coach) {
			case firstClass:
				seatNumber = getFirstClassSeats();
				break;
			case secondClass:
				seatNumber = getSecondClassSeats();
				break;
			case sleeper:
				seatNumber = getSleeperSeats();
				break;
			case sitting:
				seatNumber = getSittingSeats();
				break;
			default:
				validInput = false;
			}
		}while(!validInput);
		return seatNumber;
	}

	private String getFirstClassSeats() {
		if (firstClassSeats.size() > 0) {
			availableSeats.replace(firstClassSeats.get(0), false);
			return firstClassSeats.remove(0);
		}
		firstClassWaitingList.add(firstClassWaitinglistSeat + ++firstClassWaitingSeatNumber);
		return firstClassWaitinglistSeat + firstClassWaitingSeatNumber;
	}

	private String getSecondClassSeats() {
		System.out.println("Berth Preference :");
		System.out.println("1.Upper  2.Lower");
		System.out.println("Press any other key to select random berth.");
		int choice = util.getByteInput();
		if (secondClassSeats.size() > 0) {
			switch (choice) {
			case caseUpper:
				return seatPreference(secondClassSeats, upper);
			case caseLower:
				return seatPreference(secondClassSeats, lower);
			}
			availableSeats.replace(secondClassSeats.get(0), false);
			return secondClassSeats.remove(0);
		} else {
			secondClassWaitingList.add(secondClassWaitinglistSeat + ++secondClassWaitingSeatNumber);
			return secondClassWaitinglistSeat + secondClassWaitingSeatNumber;
		}
	}

	private String getSleeperSeats() {
		System.out.println("Berth Preference :");
		System.out.println("1.Upper   2.Lower   3.Middle");
		System.out.println("Press any other key to select random berth.");
		int choice = util.getByteInput();
		if (sleeperSeats.size() > 0) {
			switch (choice) {
			case caseUpper:
				return seatPreference(sleeperSeats, upper);
			case caseLower:
				return seatPreference(sleeperSeats, lower);
			case caseMiddle:
				return seatPreference(sleeperSeats, middle);
			}
			availableSeats.replace(sleeperSeats.get(0), false);
			return sleeperSeats.remove(0);
		} else if (RACSeats.size() > 0) {
			availableSeats.replace(RACSeats.get(0), false);
			return RACSeats.remove(0);
		} else {
			sleeperWaitingList.add(sleeperWaitinglistSeat + ++sleeperWaitingSeatNumber);
			return sleeperWaitinglistSeat + sleeperWaitingSeatNumber;
		}
	}

	private String getSittingSeats() {
		System.out.println("Press any key to continue");
		System.out.println("Press '1' for Window Seat preference");
		int choice = util.getByteInput();
		if (sittingSeats.size() > 0) {
			if(choice == caseWindow) {
				return seatPreference(sittingSeats, window);
			}
			availableSeats.replace(sittingSeats.get(0), false);
			return sittingSeats.remove(0);
		} 
		else {
			sittingWaitingList.add(sittingWaitinglistSeat + ++sittingWaitingSeatNumber);
		    return sittingWaitinglistSeat + sittingWaitingSeatNumber;
		}
	}

	private String seatPreference(List<String> seats, char berth) {
		for (byte i = 0; i < seats.size(); i++) {
			String seatnumber = seats.get(i);
			if (seatnumber.charAt(seatnumber.length() - 1) == berth) {
				availableSeats.replace(seatnumber, false);
				return seats.remove(i);
			}
		}
		return null;
	}

	private static String getSeatNumber(String seat) {
		String seatNumber = null;
		if(seat.contains("-")) {
			String[] coachArray = seat.split("-");
			seatNumber = coachArray[0];
		}
		else
			seatNumber = seat;
		return seatNumber;
	}
	
	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

    public void addPassenger(Passenger passenger) {
    	passengerList.add(passenger);
    }
}

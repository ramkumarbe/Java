package train;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import bank.Bank;
import bank.BankAccount;
import bank.Card;
import bank.NetBanking;
import bank.UPI;
import passenger.User;
import passenger.UserUtil;

public class Util {
	private static Util obj = null;
	private Util() { }
	public static Util getInstance() {
		if(obj == null)
			obj = new Util();
		return obj;
	}
    private static int ticketNumber = 1001;
	public List<Train> trains = new ArrayList<Train>();
	public List<TrainDate> dates = new ArrayList<TrainDate>();
	public Station[] stations = {new Station("Chennai to Tirunelveli"), new Station("Tirunelveli to Chennai")};
	
    public Scanner sc = new Scanner(System.in);
    

    private String[] firstClassCompartmentNames = {"A1", "A2"};
	private String[] secondClassCompartmentNames = {"B1", "B2", "B3", "B4"};
	private String[] sleeperCompartmentNames = {"S1", "S2"/*, "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10"*/};
	private String[] sittingCompartmentNames = {"D1", "D2", "D3"};
	
	private String[] firstClassSeats = {"FC1",/* "FC2", "FC3", "FC4", "FC5", "FC6", "FC7", "FC8", "FC9", "FC10"*/};

	private String[] secondClassSeats = {"SC1-L"/*, "SC2-U", "SC3-L", "SC4-U", "SC5-L","SC6-U", "SC7-L", "SC8-U", 
			        "SC9-L","SC10-U", "SC11-L", "SC12-U", "SC13-L", "SC14-U", "SC15-L", "SC16-U"*/};

	private String[] sleeperSeats = {"SL1-L"/*, "SL2-M", "SL3-U", "SL5-L", "SL6-M", "SL7-U", "SL9-L", "SL10-M", 
					"SL11-U", "SL13-L", "SL14-M", "SL15-U", "SL17-L", "SL18-M", "SL19-U", "SL21-L", "SL22-M","SL23-U"*/};

	private String[] RACSeats = {"SL4", /*"SL8", "SL12", "SL16", "SL20", "SL24"*/};

	private String[] sittingSeats = {"S1-W", "S2", "S3", "S4", "S5-W", "S6-W","S7", "S8", "S9","S10-W",
			        "S11-W", "S12", "S13","S14", "S15-W", "S16-W", "S17", "S18", "S19", "S20-W"};
    
	private Queue<String> firstClassWaitingList = new LinkedList<String>();
	private Queue<String> secondClassWaitingList = new LinkedList<String>();
	private Queue<String> sleeperWaitingList = new LinkedList<String>();
	private Queue<String> sittingWaitingList = new LinkedList<String>();

	private final static byte CHENNAI_TO_TIRUNELVELI = 1;
	private final static byte TIRUNELVELI_TO_CHENNAI = 2;
	public Station selectStation() {
		Station station = null;
		boolean validInput = false;
		do {
			validInput = true;
			System.out.println("1.Chennai to Tirunelveli");
			System.out.println("2.Tirunelveli to Chennai");

			int choice = getByteInput();
			switch (choice) {
			case CHENNAI_TO_TIRUNELVELI:
				station = stations[0];
				break;
			case TIRUNELVELI_TO_CHENNAI:
				station = stations[1];
				break;
			default:
				System.out.println("Enter valid input.");
				validInput = false;
			}
		}while(!validInput);
		return station;
	}

	private final static int NELLAI_EXPRESS_NUMBER = 12631;
	private final static int ANANTHAPURI_EXPRESS_NUMBER = 16724;
	private final static int KANYAKUMARI_EXPRESS_NUMBER = 12633;
	private final static int GUV_EXPRESS_NUMBER = 166127;
	
	private final static String NELLAI_EXPRESS = "Nellai Express";
	private final static String ANANTHAPURI_EXPRESS = "Ananthapuri Express";
	private final static String KANYAKUMARI_EXPRESS = "Kanyakumari Express";
	private final static String GUV_EXPRESS = "GUV Express";

	private final static String NELLAI_EXPRESS_TIME = "19:30";
	private final static String ANANTHAPURI_EXPRESS_TIME = "19:55";
	private final static String KANYAKUMARI_EXPRESS_TIME = "19:05";
	private final static String GUV_EXPRESS_TIME = "8:40";
	
	public void updateTrains(TrainDate trainDate) { // To add trains in train array
		boolean isTrainAlreadyAvailable = false;
    	for(Train train:trains) {
    		if(train.getTrainDate().getDate().equals(trainDate.getDate())) {
    			isTrainAlreadyAvailable = true;
    			break;
    		}
    	}
    	if(!isTrainAlreadyAvailable) {
    		Train train = null;
    		for (Station station : stations) {
    			train  = new Train(station, trainDate, NELLAI_EXPRESS_NUMBER, NELLAI_EXPRESS, NELLAI_EXPRESS_TIME);
    			trains.add(train);
    			addNewSeat(train);
    			train = new Train(station, trainDate, ANANTHAPURI_EXPRESS_NUMBER, ANANTHAPURI_EXPRESS, ANANTHAPURI_EXPRESS_TIME);
    			trains.add(train);
    			addNewSeat(train);
    			train = new Train(station, trainDate, KANYAKUMARI_EXPRESS_NUMBER, KANYAKUMARI_EXPRESS, KANYAKUMARI_EXPRESS_TIME);
    			trains.add(train);
    			addNewSeat(train);
    			train = new Train(station, trainDate, GUV_EXPRESS_NUMBER, GUV_EXPRESS, GUV_EXPRESS_TIME);
    			trains.add(train);
    			addNewSeat(train);
    		}
    	}
	}

	private final byte FIRST_DATE = 1;
	private final byte SECOND_DATE = 2;
	private final byte THIRD_DATE = 3;
	private final byte FORTH_DATE = 4;
	private final byte USER_GIVEN_DATE = 5;

    private SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yy");
    
    public TrainDate selectTrainDate() {
    	boolean validInput = false;
		TrainDate date = null;
    	do {
    		validInput = true;
    		
    		System.out.println("1."+dates.get(0).getDate());
    		System.out.println("2."+dates.get(1).getDate());
    		System.out.println("3."+dates.get(2).getDate());
    		System.out.println("4."+dates.get(3).getDate());
    		System.out.println("5.Enter the Date you want");
    		int choice = getByteInput();
    		switch(choice) {
    		case FIRST_DATE : date = dates.get(0); break;
    		case SECOND_DATE: date = dates.get(1); break;
    		case THIRD_DATE : date = dates.get(2); break;
    		case FORTH_DATE : date = dates.get(3); break;
    		case USER_GIVEN_DATE : date = addNewDate(); updateTrains(date); break;
    		default : System.out.println("Enter valid input."); validInput = false;
    		}
    	}while(!validInput);
    	return date;
    }
    
	@SuppressWarnings("deprecation")
	void loader() {
		UserUtil.getInstance().userDetails.add(new User("1", "1", "9877543210"));
		UserUtil.getInstance().userDetails.add(new User("Admin", "12345678", "6876533210"));
		UserUtil.getInstance().userDetails.add(new User("Elon Musk", "password", "8876543211"));
		UserUtil.getInstance().userDetails.add(new User("Einstien", "password", "9776543212"));
		
		byte dateCount = 4;
    	for(byte i=0; i<dateCount; i++) {
        	Date currentDate = new Date();
        	currentDate.setSeconds(i*86400);
    	    String date = dateformat.format(currentDate);
        	dates.add(new TrainDate(date));
        }
    	
    	for (Station station : stations) {
    		for (TrainDate trainDate : dates)  {
    			trains.add(new Train(station, trainDate, NELLAI_EXPRESS_NUMBER, NELLAI_EXPRESS, NELLAI_EXPRESS_TIME));
    			trains.add(new Train(station, trainDate, ANANTHAPURI_EXPRESS_NUMBER, ANANTHAPURI_EXPRESS, ANANTHAPURI_EXPRESS_TIME));
    			trains.add(new Train(station, trainDate, KANYAKUMARI_EXPRESS_NUMBER, KANYAKUMARI_EXPRESS, KANYAKUMARI_EXPRESS_TIME));
    			trains.add(new Train(station, trainDate, GUV_EXPRESS_NUMBER, GUV_EXPRESS, GUV_EXPRESS_TIME));
    		}
    	}
		
		byte compartmentCount=0;
		for(Train train:trains) {
			compartmentCount=0;
			for(String compartmentName:firstClassCompartmentNames) {
				train.getFirstClassCoaches()[compartmentCount++] = new FirstClassCoach(Train.FIRST_CLASS,compartmentName);
			}
			compartmentCount=0;
			for(String compartmentName:secondClassCompartmentNames) {
				train.getSecondClassCoaches()[compartmentCount++] = new SecondClassCoach(Train.SECOND_CLASS,compartmentName);
			}
			compartmentCount=0;
			for(String compartmentName:sleeperCompartmentNames) {
				train.getSleeperCoaches()[compartmentCount++] = new SleeperCoach(Train.SLEEPER,compartmentName);
			}
			compartmentCount=0;
			for(String compartmentName:sittingCompartmentNames) {
				train.getSittingCoaches()[compartmentCount++] = new SittingCoach(Train.SITTING,compartmentName);
			}
		}

		for (Train train : trains) 
			addNewSeat(train);
	
		Bank.getInstance().bankAccountDetails.add(new BankAccount("Ramkumar M","6476450171",new Card("9845236764523097", "RAMKUMAR M", "03/25"),new NetBanking("ram10", "ram"),new UPI("ram@okaxis", "ram"), 100000));
		Bank.getInstance().bankAccountDetails.add(new BankAccount("Muthukumar","6546750192",new Card("4523634709764523", "MUTHUKUMAR", "07/24"),new NetBanking("muthu007", "muthu"),new UPI("muthu@okaxis", "muthu"), 25000));
		Bank.getInstance().bankAccountDetails.add(new BankAccount("Rama selva thangam","6376450943",new Card("7097645984523623", "RAMA SELVA THANGAM", "11/23"),new NetBanking("thangam", "thangam"),new UPI("thangam@okaxis", "thangam"), 8000));
	}
	public void addNewSeat(Train train) {
		for(FirstClassCoach coach:train.getFirstClassCoaches()) 
			for (String seat:firstClassSeats) 
				coach.getSeats().add(new Seat(coach.getCompartmentName(),seat));

		for(SecondClassCoach coach:train.getSecondClassCoaches()) 
			for (String seat:secondClassSeats) 
				coach.getSeats().add(new Seat(coach.getCompartmentName(),seat));

		for(SleeperCoach coach:train.getSleeperCoaches()) {
			for (String seat:sleeperSeats) {
				coach.getSeats().add(new Seat(coach.getCompartmentName(),seat));
			}

			for (String seat:RACSeats) {
				train.getRACSeats().add(new Seat(coach.getCompartmentName(),seat));
			}
		}

		for(SittingCoach coach:train.getSittingCoaches()) 
			for (String seat:sittingSeats)
				coach.getSeats().add(new Seat(coach.getCompartmentName(),seat));

	}
    public TrainDate addNewDate() {
    	Date currentDate = new Date();
    	System.out.print("Enter the Date(dd/mm/yyyy): ");
    	String bookingDate = getDateInput();
    	Date lastDate = new Date(currentDate.getTime()+2628002880L);
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
    	Date givenDate = null;
		try { givenDate = dateFormat.parse(bookingDate); } 
		catch (ParseException e) { 
			System.out.println("Invalid Date Input"); addNewDate(); }
    	if(givenDate.after(currentDate) && givenDate.before(lastDate)) {
    		String date = dateformat.format(givenDate);
    		TrainDate trainDate = new TrainDate(date);
    		if(!dates.contains(trainDate))
    			dates.add(trainDate);
    		return trainDate;
    	}
    	else {
    		System.out.println("\t\t\t\t\t\t Only dates 1 month from today are available");
    		return addNewDate();
    	}
    }
    
    private final static byte TRAIN_NELLAI = 1;
	private final static byte TRAIN_ANANTHAPURI = 2;
	private final static byte TRAIN_KANYAKUMARI = 3;
	private final static byte TRAIN_GUV = 4;
	
	public Train getTrain() { // To select a train
		Station station = selectStation();
		TrainDate trainDate = selectTrainDate();
		for(Train train:trains) {
			if (train.getStation().equals(station) && train.getTrainDate().getDate().equals(trainDate.getDate()) 
				&& train.getTrainName().equals(NELLAI_EXPRESS))
			    System.out.println("1."+NELLAI_EXPRESS+" - "+train.getTime());
			if (train.getStation().equals(station) && train.getTrainDate().getDate().equals(trainDate.getDate())
					&& train.getTrainName().equals(ANANTHAPURI_EXPRESS)) 
			    System.out.println("2."+ANANTHAPURI_EXPRESS+" - "+ train.getTime());
			if (train.getStation().equals(station) && train.getTrainDate().getDate().equals(trainDate.getDate())
					&& train.getTrainName().equals(KANYAKUMARI_EXPRESS))
			    System.out.println("3."+KANYAKUMARI_EXPRESS+" - "+ train.getTime());
			if (train.getStation().equals(station) && train.getTrainDate().getDate().equals(trainDate.getDate())
						&& train.getTrainName().equals(GUV_EXPRESS))
			    System.out.println("4."+GUV_EXPRESS+" - "+ train.getTime());
		}
		
		Train trainObj = null;
		byte trainNumber = getByteInput();  
		boolean validInput = false;
		do {
			validInput = true;
			switch (trainNumber) {
			case TRAIN_NELLAI:
				for (Train train : trains) {
					if (train.getStation().equals(station) && train.getTrainDate().getDate().equals(trainDate.getDate()) 
							&& train.getTrainName().equals(NELLAI_EXPRESS)) {
						trainObj = train;
					}
				}
				break;

			case TRAIN_ANANTHAPURI:
				for (Train train : trains) {
					if (train.getStation().equals(station) && train.getTrainDate().getDate().equals(trainDate.getDate())
							&& train.getTrainName().equals(ANANTHAPURI_EXPRESS)) {
						trainObj = train;
					}
				}
				break;

			case TRAIN_KANYAKUMARI:
				for (Train train : trains) {
					if (train.getStation().equals(station) && train.getTrainDate().getDate().equals(trainDate.getDate())
							&& train.getTrainName().equals(KANYAKUMARI_EXPRESS)) {
						trainObj = train;
					}
				}
				break;

			case TRAIN_GUV:
				for (Train train : trains) {
					if (train.getStation().equals(station) && train.getTrainDate().getDate().equals(trainDate.getDate())
							&& train.getTrainName().equals(GUV_EXPRESS)) {
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
	public String getUserName() {
		return sc.nextLine().trim();
	}
	public String getPassword() {
		return sc.nextLine().trim();
	}
	public String getPhoneNumber() {
		return sc.nextLine().trim();
	}
	public String getDateInput() {
		return sc.nextLine().trim();
	}

	public String getPassengerName() {
		return sc.nextLine().trim();
	}

	public String getBankId() {
		return sc.nextLine().trim();
	}

	public String getUpiId() {
		return sc.nextLine().trim();
	}

	public String getBankingPassword() {
		return sc.nextLine().trim();
	}
	
	public String getUpiPassword() {
		return sc.nextLine().trim();
	}

	public String getCardNumber() {
		return sc.nextLine().trim();
	}

	public String getCardHolderName() {
		return sc.nextLine().trim();
	}

	public String getCardExpiryDate() {
		return sc.nextLine().trim();
	}

	public String getSeatNumber() {
		return sc.nextLine().trim();
	}
	
	public String getNewTime() {
		return sc.nextLine().trim();
	}

	private final static int NOT_A_NUMBER = -1;
	private final static int NEGATIVE_INTEGER = -2;
	public int getNumber() {
		int number = 0;
		if (sc.hasNextInt()) {
			number = sc.nextInt();
			sc.nextLine();
			if(number<0)
				return NEGATIVE_INTEGER;
		} else {
			sc.nextLine();
			return NOT_A_NUMBER;
		}
		return number;
	}

	public byte getByteInput() {
		byte number = 0;
		if (sc.hasNextByte()) {
			number = sc.nextByte();
			sc.nextLine();
			if(number<0)
				return NEGATIVE_INTEGER;
		} else {
			sc.nextLine();
			return NOT_A_NUMBER;
		}
		return number;
	}
    public String getStringInput() {
    	return sc.nextLine().trim();
    }
	public String getBack() {
		return sc.nextLine();
	}

	public boolean isValidTime(String time) {
		String timeRegex = "^([01][0-9]|2[0-3]):[0-5][0-9]$";
		return time.matches(timeRegex);
	}
	
	public Queue<String> getFirstClassWaitingList() {
		return firstClassWaitingList;
	}
	public Queue<String> getSecondClassWaitingList() {
		return secondClassWaitingList;
	}
	public Queue<String> getSleeperWaitingList() {
		return sleeperWaitingList;
	}
	public Queue<String> getSittingWaitingList() {
		return sittingWaitingList;
	}
	private static final String ticketNumberKeyWord = "TN";
	public static String getTicketNumber() {
		return ticketNumberKeyWord+ticketNumber++;
	}
    
    int getSittingCompartmentCount() {
		return sittingCompartmentNames.length;
	}
    int getSleeperCompartmentCount() {
		return sleeperCompartmentNames.length;
	}
    int getSecondClassCompartmentCount() {
		return secondClassCompartmentNames.length;
	}
    int getFirstClassCompartmentCount() {
		return firstClassCompartmentNames.length;
	}
}

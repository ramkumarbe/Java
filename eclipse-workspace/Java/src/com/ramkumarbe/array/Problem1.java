package com.ramkumarbe.array;

import java.util.Scanner;

//problem: Find the number of grand children.
public class Problem1 {  
	public static void funtion() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of Duo: ");
		int numberOfEntries = sc.nextInt();
		sc.nextLine();
		String[][] familyNames = new String[numberOfEntries][];
		
		System.out.println("Enter the Son and Father Name(Seperate using comma(,)): ");
		for(int i=0; i<numberOfEntries; i++) {
			familyNames[i] = sc.nextLine().split(",");
		}
		
		System.out.print("Enter the Name of the Grandfather: ");
		String name = sc.nextLine();
		sc.close();
		
		int sonCount = getSonCount(familyNames, name);
		String[] sons = getSons(familyNames, name, sonCount);
		int grandChildCount = 0;
		
		if(sonCount != 0) {
			for(String son:sons) {
				grandChildCount += getSonCount(familyNames, son);
			}
		}
		
		System.out.println("Number of GrandChildren "+name+" has is "+ grandChildCount);
	}
    
	//get the list of sons of a given father name
	private static String[] getSons(String[][] familyNames, String name, int sonCount) {
		String[] sons = new String[sonCount];
		int i=0;
		for(String[] arr:familyNames) {
			if(arr[1].equals(name)) {
				sons[i++] = arr[0];
			}
		}
		return sons;
	}

	//get the count of a given father name
	private static int getSonCount(String[][] familyNames, String sonName) {
		int count=0;
		for(String[] arr:familyNames) {
			if(arr[1].equals(sonName)) {
				count++;
			}
		}
		return count;
	}
}

package com.ramkumarbe.array;

import java.util.Arrays;
import java.util.Scanner;

//problem: Sort the given numbers by their weight in descending order.
public class Problem2 {
    public static void function() {
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.print("Enter the Number of Elements: ");
    	int numberOfElements = sc.nextInt();
    	int[][] numbers = new int[numberOfElements][2];
    	System.out.println("Enters the numbers(split them using space): ");
    	for(int i=0; i<numberOfElements; i++) {
    		numbers[i][0] = sc.nextInt();
    		numbers[i][1] = getSumOfWeights(numbers[i][0]);
    	}
    	sc.close();
    	printByOrder(numbers);
    }

	private static void printByOrder(int[][] numbers) { //sort and print in descending order 
	    for(int i=0; i<numbers.length; i++) {
	    	for(int j=i+1; j<numbers.length; j++) {
	    		if(numbers[i][1]<numbers[j][1]) {
	    			int[] temp = numbers[i];
	    			numbers[i] = numbers[j];
	    			numbers[j] = temp;
	    		}
	    	}
	    	System.out.print(Arrays.toString(numbers[i])+" ");
	    }
	}

	private static int getSumOfWeights(int num) {  //calculate and return the total weight of a number
		int sum = 0;
		if(isPerfectSquare(num))
			sum += 5;
		if(isMultipleOf4and6(num))
			sum += 4;
		if(isEvenNumber(num))
			sum += 3;
		return sum;
	}

	private static boolean isEvenNumber(int num) {
		return num%2==0;
	}

	private static boolean isMultipleOf4and6(int num) {
		return num%4==0 && num%6==0;
	}

	private static boolean isPerfectSquare(int num) {
		return Math.sqrt(num)*Math.sqrt(num) == num;
	}
}

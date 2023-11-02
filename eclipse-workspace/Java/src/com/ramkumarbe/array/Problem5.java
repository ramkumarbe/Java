package com.ramkumarbe.array;

import java.util.Arrays;
import java.util.Scanner;

//problem: Rearrange the elements at even positions are greater than all elements before it and 
//elements at odd positions are less than all elements before it
public class Problem5 {

	public static void function() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the number of Elements");
		int len = sc.nextInt();
		int[] numbers = new int[len];
		
		System.out.print("Enter the Array Elements(split them using space): ");
		for(int i=0; i<len; i++) {
			numbers[i] = sc.nextInt();
		}
		
		Arrays.sort(numbers);  //sorting the given array in ascending order.
		oddFrontEvenReverseSort(numbers);
		System.out.println(Arrays.toString(numbers));
	}

	private static void oddFrontEvenReverseSort(int[] numbers) {
		int len = numbers.length-1;
		if(len%2 != 0)
			len--;
		
		for(int i=len; i>0; i-=2) {
			int currentSmall = numbers[0];
			for(int j=1; j<=i; j++) {
				numbers[j-1] = numbers[j];
			}
			numbers[i] = currentSmall;
		}
	}

}

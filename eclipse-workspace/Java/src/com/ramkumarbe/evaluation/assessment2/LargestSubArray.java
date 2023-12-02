package com.ramkumarbe.evaluation.assessment2;

import java.util.Scanner;

public class LargestSubArray {

	public static void main(String[] args) {
		LargestSubArray largestSubArray = new LargestSubArray();
		int[] result = largestSubArray.getLargestSubArray();
		largestSubArray.printArray(result);
	}

	private void printArray(int[] result) {
		for(int number:result) {
			System.out.print(number+" ");
		}
	}

	private int[] getLargestSubArray() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Number of Elements:");
		int numberOfElements = scanner.nextInt();
		int[] numbers = new int[numberOfElements];
		int sum=0,max=Integer.MIN_VALUE;
		System.out.println("Enter the Elements:");
		for(int i=0; i<numberOfElements; i++) {
			numbers[i] = scanner.nextInt();
			sum += numbers[i];
		}
		int left=0, right=numberOfElements-1;
		int from=0, to=0;
		while(left<right) {
			if(numbers[left]<numbers[right]) {
				sum -= numbers[left];
				left++;
			}
			else {
				sum -= numbers[right];
				right--;
			}
			if(sum>max) {
				max = sum;
				from = left;
				to = right;
			}
		}
		return getArray(numbers, from, to);
	}

	private int[] getArray(int[] numbers, int from, int to) {
		int[] result = new int[to-from+1];
		for(int i=0; i<result.length; i++) {
			result[i] = numbers[i+from];
		}
		return result;
	}

}

package com.ramkumarbe.problem.dailyproblems.day1;

import java.util.Scanner;

public class SearchInRotatedSortedArray {
	/*
	 * 4. Given a sorted array of n integers that has been rotated an unknown number
	 * of times, design and implement an O(log n) algorithm that finds an element in
	 * the array. You may assume that the array was originally sorted in increasing
	 * order. Input: Arr = [15,16,19,20,25,1,3,4,5,7,10,14], K=5 Output: 8(index of
	 * 5) Return -1 if the element is not present. Input : arr[] = {5, 6, 7, 8, 9,
	 * 10, 1, 2, 3}, key = 3 Output : 8 Input : arr[] = {5, 6, 7, 8, 9, 10, 1, 2,
	 * 3}, key = 30 Output : -1
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of Elements: ");
		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			System.out.print("Enter the value " + i + ": ");
			arr[i] = sc.nextInt();
		}
		System.out.print("key: ");
		int key = sc.nextInt();
		int res = new SearchInRotatedSortedArray().getIndex(arr, key);
		System.out.println("Index: "+res);
	}

	private int getIndex(int[] arr, int key) {
		int left = 0, right = arr.length-1;
		while(left<=right) {
			int mid = left + (right-left)/2;
			if(arr[mid] == key) {
				return mid;
			}
			if(arr[left]<arr[mid]) {
				if(arr[left]<=key&&arr[mid]>key) {
					right = mid-1;
				}
				else {
					left = mid+1;
				}
			}
			else {
				if(arr[right]>=key && arr[mid]<key) {
					left = mid+1;
				}
				else {
					right = mid-1;
				}
			}
		}
		return -1;
	}

}

package com.ramkumarbe.problem.dailyproblems.day1;

import java.util.Scanner;

public class MoveKthNode {

	/*
	 * 3. Give a Singly Linked List, write a program to delete the last element and
	 * move K-th last element to last position. Your solution must be in O(n) time
	 * complexity. Example : Input: 7 -> 16 -> 100 -> 33 -> 21-> 9 -> 63 -> 11 -> 49
	 * -> 199 -> 59 and K= 5 Output: 7 -> 16 -> 100 -> 33 -> 21 -> 9 -> 11 -> 49 ->
	 * 199 -> 63 Element 63 is the 5th element from the last and its moved to last
	 * position and 59 is deleted which was the last element previously.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MoveKthNode moveKthNode = new MoveKthNode();
		System.out.print("Enter the number of nodes: ");
		int n = sc.nextInt();
		Node head = moveKthNode.getList(n);
		System.out.print("Enter the value of k: ");
		int k = sc.nextInt();
		moveKthNode.moveNode(head, k);
		System.out.println("Output: ");
		while (head != null) {
			System.out.print(head.val);
			if (head.next != null)
				System.out.print(" -> ");
			head = head.next;
		}
	}

	private Node getList(int n) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the value1: ");
		Node head = new Node(sc.nextInt());
		Node temp = head;
		for (int i = 2; i <= n; i++) {
			System.out.print("Enter the value" + i + ": ");
			temp.next = new Node(sc.nextInt());
			temp = temp.next;
		}
		return head;
	}

	private void moveNode(Node head, int k) {
		Node fast = head;
		Node slow = head;
		while (--k > 0) {
			System.out.println("first " + fast.val);
			fast = fast.next;
		}
		while (fast.next.next != null) {
			System.out.println("fast " + fast.val);
			System.out.println("slow " + slow.val);
			fast = fast.next;
			slow = slow.next;
		}

		fast.next = slow.next;
		slow.next = fast.next.next;
		fast.next.next = null;
	}

	class Node {
		int val;
		Node next;

		public Node() {
		}

		public Node(int val) {
			this.val = val;
		}

		public Node(int val, Node next) {
			this.val = val;
			this.next = next;
		}
	}
}

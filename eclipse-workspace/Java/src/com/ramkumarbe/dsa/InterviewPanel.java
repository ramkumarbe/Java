package com.ramkumarbe.dsa;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import com.fasterxml.jackson.databind.ObjectMapper;

public class InterviewPanel {

	static Queue<String> queue = new LinkedList<>();

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
        int choice;

        InterviewPanel interview = new InterviewPanel();
        interview.interviewPanel();

        do {
            System.out.println("1. Enter candidate details");
            System.out.println("2. Waiting List");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();  

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter the candidate name: ");
                    String name = sc.nextLine();
                    queue.add(name);
                    System.out.println(name + " added to the queue.");
                }
                case 2 -> {
                	System.out.println(queue);
                }
                case 0 -> {
                    System.out.println("Exiting program.");
                }
                default -> {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        } while (choice != 0);
        System.out.println();
        sc.close();
	}

	private void interviewPanel() {
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				while (!queue.isEmpty()) {
					System.out.println(queue.poll() + " is completed.");
				}
			}
		};
		timer.schedule(timerTask, 7000, 20000);

	}

}

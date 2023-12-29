package com.ramkumarbe.consoleApplication.mobileShop.mobile;

import java.util.Scanner;

public class ShowProducts {
	ShowProductsViewModel showProductsViewModel;
	public ShowProducts(){
		showProductsViewModel = new ShowProductsViewModel(this);
	}
	public void show() {
		showProductsViewModel.start();
		Scanner sc = new Scanner(System.in);
		do {
			showProductsViewModel.printData();
			System.out.println("0 to go back.");
			System.out.println("-1 to exit.\n");
		    System.out.println("Enter your Choice: ");
			int selection = sc.nextInt();
			if(selection==0) {
				showProductsViewModel.getBack();
				continue;
			}
			else if(selection==-1) {
				System.out.println("Thank you for visiting our website.");
				break;
			}
			else if(showProductsViewModel.isValidInput(selection)) {
				showProductsViewModel.selectData(selection);
			}
			else {
				System.out.println("Enter valid input.\n");
			}
			System.out.println();
		}while(true);
	}
	void printValue(String key, String value) {
		System.out.println(key+": "+value);
	}
}

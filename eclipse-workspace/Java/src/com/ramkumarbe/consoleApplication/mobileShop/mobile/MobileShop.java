package com.ramkumarbe.consoleApplication.mobileShop.mobile;

import java.util.Map;
import java.util.Stack;

public class MobileShop {
	Stack<Map<String,Object>> stack = new Stack<>();
	public static void main(String[] args) {
		MobileShop shop = new MobileShop();
		shop.start();
	}

	private void start() {
		ShowProducts showProducts = new ShowProducts();
		showProducts.show();
		
	}
}

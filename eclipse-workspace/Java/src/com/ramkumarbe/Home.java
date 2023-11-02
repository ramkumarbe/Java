																																																																																																																																																																												package com.ramkumarbe;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Home {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		Stack<String> currentLocation = new Stack<>();
		Stack<String> currentPackage = new Stack<>();
		currentLocation.push("C:\\Users\\Admin\\eclipse-workspace\\Java\\src\\com\\ramkumarbe");
		currentPackage.push("com.ramkumarbe");
		String file = null;
		do {
			Map<Integer, String> files = getList(currentLocation.peek());
			System.out.println("Press '0' to get back.");
			System.out.println("Press '-1' to Exit.");
			System.out.println("Enter your choice: ");
			int n = sc.nextInt();
			sc.nextLine();
			if (n == 0) {
				if(currentLocation.size()>1) {
					currentLocation.pop();
					currentPackage.pop();
				}
				continue;
			}
			if (n == -1){
				System.out.println("\t\t\t----------");
				break;
			}
			if(n >=files.size()) {
				System.out.println("Enter the valid number");
				continue;
			}
			file = files.get(n);
			currentLocation.push(currentLocation.peek() + "\\" + file);
			currentPackage.push(currentPackage.peek() + "." + file);
			if (currentPackage.peek().endsWith("java")) {
				String classPath = currentPackage.peek().substring(0, currentPackage.peek().length() - 5);
				callMethod(classPath);
				currentLocation.pop();
				currentPackage.pop();
				System.out.println("Press Enter to get back.");
				sc.nextLine();
			}
		} while (true);
	}

	private static void callMethod(String classPath) throws Exception {

		Class<?> cls = Class.forName(classPath);

		Method method = cls.getDeclaredMethod("function");

		method.invoke(method, null);
	}

	private static Map<Integer, String> getList(String path) {
		Map<Integer, String> fileList = new HashMap<>();
		File packagePath = new File(path);
		int orderNumber = 0;
		for (String fileName : packagePath.list()) {
			if (!fileName.startsWith("Home")) {
				System.out.println(++orderNumber + ". " + fileName);
				fileList.put(orderNumber, fileName);
			}
		}
		return fileList;
	}
}

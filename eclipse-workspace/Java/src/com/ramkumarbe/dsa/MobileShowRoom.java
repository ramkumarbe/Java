package com.ramkumarbe.dsa;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class MobileShowRoom {
	Stack<Map> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException, ParseException {
		MobileShowRoom obj = new MobileShowRoom();
		obj.showRoom();
	}

	private void showRoom() throws FileNotFoundException, IOException, ParseException {
		Scanner sc = new Scanner(System.in);
		String path = "src/Data.json";
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(new FileReader(path));
		Map<String, Object> map = (Map) obj;
		stack.push((Map) map.get("mobileBrands"));
		do {
			printValue(stack.peek());
			System.out.println("0 to go back.");
			System.out.println("-1 to exit.");
			int selection = sc.nextInt();
			if(selection==0) {
				if(stack.size()>1) {
					stack.pop();
				}
				continue;
			}
			else if(selection==-1) {
				System.out.println("Thank you for visit our website.");
				break;
			}
			else if(selection<stack.peek().size()) {
		        stack.push((Map<String,String>)map.get(stack.peek().get(selection+"")));
		        
			}
			else {
				System.out.println("Enter valid input.\n");
			}
			System.out.println();
		}while(true);
	}

	private void printValue(Object values) {
		Map<String,Object> maps = (Map)values;
		for (Map.Entry<String, Object> map : maps.entrySet()) {
			System.out.println(map.getKey()+": "+map.getValue());
		}
	}
}

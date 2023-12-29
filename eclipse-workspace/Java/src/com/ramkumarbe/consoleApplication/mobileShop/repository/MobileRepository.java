package com.ramkumarbe.consoleApplication.mobileShop.repository;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MobileRepository {
	private static MobileRepository obj;
	private MobileRepository() {  }
	
	public static MobileRepository getInstance() {
		if(obj==null) {
			obj = new MobileRepository();
		}
		return obj;
	}
    @SuppressWarnings("unchecked")
	public Map<String, Object> getData() { 
    	String path = "C:\\Users\\Admin\\eclipse-workspace\\Java\\src\\com\\ramkumarbe\\consoleApplication\\mobileShop\\Data.json";
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(new FileReader(path));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return (Map<String, Object>) obj;
    }
}

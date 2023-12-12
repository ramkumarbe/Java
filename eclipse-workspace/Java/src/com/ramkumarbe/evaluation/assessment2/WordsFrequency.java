package com.ramkumarbe.evaluation.assessment2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WordsFrequency {

	public static void main(String[] args) {
		WordsFrequency wordsFrequency = new WordsFrequency();
		wordsFrequency.init();
	}

	private void init() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Sentence:");
		String sentence = scanner.nextLine();
		StringBuilder word = new StringBuilder();
		Map<String, Integer> map = new HashMap<>();
		char[] arr = sentence.toCharArray();
		char c = ' ';
		for(int i=0; i<=arr.length; i++) {
			if(i!=arr.length)
			    c = arr[i];
			if(i!=arr.length&&c!=' '&&c!=','&&c!='.') {
				word.append(c);
			}
			else {
				String str = word.toString();
				if(map.containsKey(str)) {
				    map.put(str, map.get(str)+1);
				}
				else {
					
					map.put(str, 1);
				}
				word = new StringBuilder();
			}
		}
		List<Map.Entry<String,Integer>> sortedList = sortMap(map);
		printWords(sortedList);
	}

	private List<Map.Entry<String,Integer>> sortMap(Map<String, Integer> map) {
		List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, (e1,e2)-> e2.getValue()-e1.getValue());
		return list;
	}

	private void printWords(List<Map.Entry<String,Integer>> list) {
		for(Map.Entry<String, Integer> entry:list) {
			System.out.print(entry.getKey()+"-"+entry.getValue()+", ");
		}
	}

}

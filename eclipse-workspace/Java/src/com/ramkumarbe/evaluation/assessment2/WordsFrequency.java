package com.ramkumarbe.evaluation.assessment2;

import java.util.HashMap;
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
		printWords(map);
	}

	private void printWords(Map<String, Integer> map) {
		for(Map.Entry<String, Integer> entry:map.entrySet()) {
			System.out.print(entry.getKey()+"-"+entry.getValue()+", ");
		}
	}

}

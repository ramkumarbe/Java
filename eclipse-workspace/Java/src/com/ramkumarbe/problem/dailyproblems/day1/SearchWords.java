package com.ramkumarbe.problem.dailyproblems.day1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/*
1. Given a 2D grid of characters, you have to search for all the words in a dictionary by
moving only along two directions, either right or down. Print the word if it occurs.
Sample :
 a z o l
 n x h o
 v y i v
 o r s e
 Dictionary = {van, zoho, love, are, is}
 Output:
 zoho
 love
 Is
 */
public class SearchWords {

	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
		char[][] grid = { { 'a', 'z', 'o', 'l' }, { 'n', 'x', 'h', 'o' }, { 'v', 'y', 'i', 'v' },
				{ 'o', 'r', 's', 'e' } };

		String[] dictionary = { "van", "zoho", "love", "are", "is" };
		List<String> res = new SearchWords().searchWords(grid, dictionary);
		System.out.println(res);
	}

	private List<String> searchWords(char[][] grid, String[] dictionary) {
		List<String> words = new ArrayList<>();
		for (int w = 0; w < dictionary.length; w++) {
			char first = dictionary[w].charAt(0);
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					if (first == grid[i][j]) {
						if (contains(grid, dictionary[w], i, j)) {
							words.add(dictionary[w]);
						}
					}
				}
			}
		}
		return words;
	}

	private boolean contains(char[][] grid, String word, int x, int y) {
		Queue<Point> que = new LinkedList<>();
		que.add(new Point(x, y));
		boolean flag;
		for (int i = 1; i < word.length(); i++) {
			Point p = que.poll();
			flag = true;
			if (p.x != grid.length - 1 && grid[p.x + 1][p.y] == word.charAt(i)) {
				que.add(new Point(p.x + 1, p.y));
				flag = false;
			}
			if (p.y != grid[p.x].length - 1 && grid[p.x][p.y + 1] == word.charAt(i)) {
				que.add(new Point(p.x, p.y + 1));
				flag = false;
			}
			if (flag) {
				return false;
			}
		}
		return true;
	}

	private class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

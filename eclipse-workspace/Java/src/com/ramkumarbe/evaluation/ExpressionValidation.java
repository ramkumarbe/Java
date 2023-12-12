package com.ramkumarbe.evaluation;

import java.util.Scanner;
import java.util.Stack;

public class ExpressionValidation {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Expression: ");
		String expression = sc.nextLine();
		boolean flag = isValidExpression(expression);
		if(flag)
			System.out.println("Valid");
		else
			System.out.println("Invalid");
	}

	private static boolean isValidExpression(String s) {
		char[] arr = s.toCharArray();
		Stack<Character> stack = new Stack<>();
		for(int i=0; i<arr.length; i++) {
			char c = arr[i];
			if(stack.isEmpty()&& c==')')
				return false;
			if(c=='(') {
				stack.push(c);
				continue;
			}
			if(c==')') {
				stack.pop();
				continue;
			}
			if((i==0||i==arr.length-1)&& !Character.isLetterOrDigit(c))
				return false;
			if(!Character.isLetterOrDigit(c)&&!Character.isLetterOrDigit(arr[i-1])&&arr[i-1]!=')')
				return false; 
			if(!Character.isLetterOrDigit(c)&&!Character.isLetterOrDigit(arr[i+1])&&arr[i+1]!='(')
			    return false;
		}
		return stack.isEmpty();
	}

}

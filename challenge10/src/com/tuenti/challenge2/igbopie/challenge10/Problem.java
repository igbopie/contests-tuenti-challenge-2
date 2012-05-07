package com.tuenti.challenge2.igbopie.challenge10;

import java.util.Scanner;
import java.util.Stack;

public class Problem {

	public static void execProblem(String command) {
		String[] tokens = command.split("\\s"); // this language needs a
												// whitespace between everything
		Stack<Integer> paramsStack = new Stack<Integer>();
		for (String token : tokens) {
			if (isDigit(token)) {
				paramsStack.add(Integer.parseInt(token));
			} else if (isCommand(token)) {
				Integer result = execute(token, paramsStack);
				if (result != null) {
					paramsStack.push(result);
				}
			} else if (isEnd(token)) {
				System.out.println(paramsStack.pop());
			} else {
				throw new RuntimeException("Unrecognized operator: " + token);
			}
		}

	}

	public static Integer execute(String cmd, Stack<Integer> paramsStack) {
		
		if (cmd.equalsIgnoreCase("mirror")) {
			return paramsStack.pop() * -1;
			
		} else if (cmd.equalsIgnoreCase("breadandfish")) {
			int a = paramsStack.pop();

			paramsStack.push(a);
			paramsStack.push(a);

		} else if (cmd.equalsIgnoreCase("#")) {
			int b = paramsStack.pop();
			int a = paramsStack.pop();

			return a * b;
		} else if (cmd.equalsIgnoreCase("fire")) {
			paramsStack.pop();

		} else if (cmd.equalsIgnoreCase("$")) {
			int b = paramsStack.pop();
			int a = paramsStack.pop();

			return a - b;

		} else if (cmd.equalsIgnoreCase("dance")) {
			int b = paramsStack.pop();
			int a = paramsStack.pop();

			paramsStack.push(b);
			paramsStack.push(a);

		} else if (cmd.equalsIgnoreCase("conquer")) {
			int b = paramsStack.pop();
			int a = paramsStack.pop();

			return a % b;

		} else if (cmd.equalsIgnoreCase("&")) {
			int b = paramsStack.pop();
			int a = paramsStack.pop();

			return a / b;

		} else if (cmd.equalsIgnoreCase("@")) {
			int b = paramsStack.pop();
			int a = paramsStack.pop();

			return a + b;

		}
		return null;
	}

	public static boolean isDigit(String token) {
		return token.matches("\\d+");
	}

	public static boolean isCommand(String token) {
		return token.equalsIgnoreCase("mirror")
				|| token.equalsIgnoreCase("breadandfish")
				|| token.equalsIgnoreCase("#")
				|| token.equalsIgnoreCase("fire")
				|| token.equalsIgnoreCase("$")
				|| token.equalsIgnoreCase("dance")
				|| token.equalsIgnoreCase("conquer")
				|| token.equalsIgnoreCase("&") || token.equalsIgnoreCase("@");
	}

	public static boolean isEnd(String token) {
		return token.equalsIgnoreCase(".");
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			try {
				String line = in.nextLine();
				execProblem(line);
			} catch (Exception e) {
				System.out.println("Error");
				System.err.println(e);
			}
		}
		in.close();

	}

}

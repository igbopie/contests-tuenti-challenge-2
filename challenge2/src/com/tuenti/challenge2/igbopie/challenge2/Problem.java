package com.tuenti.challenge2.igbopie.challenge2;

import java.util.Scanner;

public class Problem {

	public static void execProblem(String line, int nCase) {
		int n = Integer.parseInt(line);
		int result = 0;
		for (int x = 0; x <= n / 2; x++) {
			int sum = sumBinary(x) + sumBinary(n - x);
			result = sum > result ? sum : result;
		}
		System.out.println("Case #" + nCase + ": " + result);
	}

	public static int sumBinary(int x) {
		int sum = 0;
		String binX = Integer.toBinaryString(x);
		for (int i = 0; i < binX.length(); i++) {
			char c = binX.charAt(i);
			if (c == '1') {
				sum++;
			}
		}
		return sum;
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int i = 0;
		while (in.hasNextLine()) {
			String line = in.nextLine();
			try {
				if (i > 0) {
					execProblem(line, i);
				}
			} catch (Exception e) {
				System.out.println("Error");
				System.err.println(e);
			}
			i++;
		}
	}
}

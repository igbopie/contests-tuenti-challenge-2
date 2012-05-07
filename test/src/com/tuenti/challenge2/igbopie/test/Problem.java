package com.tuenti.challenge2.igbopie.test;

import java.math.BigInteger;
import java.util.Scanner;

public class Problem {

	public static void execProblem(String line) {

		String[] tokens = line.split(" ");
		boolean isSumBig = false;
		int sum = 0;
		BigInteger sumBig = null;
		for (String token : tokens) {
			token = token.trim();
			try{
				//Dirty input! :S
				if(token.length()>0){
					
					//java don't like '+'
					if(token.startsWith("+")){
						token=token.substring(1);
					}
					
					if (token.length() > 9 || isSumBig) {
						// bigSum
						if (!isSumBig) {
							sumBig = new BigInteger("" + sum);
							isSumBig = true;
						}
		
						sumBig=sumBig.add(new BigInteger(token));
		
					} else {
						// standar sum
						sum += Integer.parseInt(token);
					}
					
				}
			}catch(NumberFormatException ex){
				//do nothing...
			}
		}
		
		if (!isSumBig) {
			System.out.println(sum);
		} else {
			System.out.println(sumBig);
		}
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		while (in.hasNextLine()) {
			try{
				execProblem(in.nextLine());
			}catch(Exception e){
				System.out.println("Error");
				System.err.println(e);
			}

		}
	}
}

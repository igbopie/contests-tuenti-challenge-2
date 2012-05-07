package com.tuenti.challenge2.igbopie.challenge15;

import java.io.IOException;

public class Problem {
	
	
	/**
	 * Found the original article. Mixed with the original in photoshop et voila! dots under desired letters :) which revealed a awesome message
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// the secret has been revealed to solve the challenge, which is the twentieth emirp?
		int number=FIRST_EMIRPS;
		for(int i=1;i<20;i++){
			number=nextEmirps(number);
		}
		System.out.println(number);
	}
	
	//LAST YEAR!!!
	public static final int FIRST_EMIRPS=13;
	
	public static int nextEmirps(int n){
		while(true){
			n+=2;
			if(isPrime(n)){
				int revN=reverseNumber(n);
				if(isPrime(revN)&&revN!=n){
					return n;
				}
			}
		}
	}
	
	public static boolean isPrime(int n){
		if(n==2){
			return true;
		}
		if(n%2==0){
			return false;
		}
		int sqrt=(int) Math.sqrt(n);
		for (int i = 3; i <= sqrt ; i += 2){
			if (n%i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static int reverseNumber(int n){
		return Integer.parseInt(new StringBuffer(n+"").reverse().toString());
	}
}

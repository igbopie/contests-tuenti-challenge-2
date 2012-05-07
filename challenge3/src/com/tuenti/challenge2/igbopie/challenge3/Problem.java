package com.tuenti.challenge2.igbopie.challenge3;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem {

	public static void execProblem(ArrayList<Integer>stock) {
		int maxInit=0;
		int maxEnd=0;
		int maxPrice=0;
		for (int i = 0; i < stock.size() ; i++) {
			for (int j = i+1; j < stock.size() ; j++) {
				int priceStart=stock.get(i);
				int priceEnd=stock.get(j);
				int dif=priceEnd-priceStart;
				if(dif>maxPrice){
					maxPrice=dif;
					maxInit=i*100;
					maxEnd=j*100;
					
				}
				
			}
		}
		System.out.println(maxInit+" "+maxEnd+" "+maxPrice);
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		ArrayList<Integer>stock=new ArrayList<Integer>();
		while (in.hasNextLine()) {
			stock.add(new Integer(in.nextLine()));
		}
		try {
			execProblem(stock);
		} catch (Exception e) {
			System.out.println("Error");
			System.err.println(e);
		}
	}
}

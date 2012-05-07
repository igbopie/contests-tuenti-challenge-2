package com.tuenti.challenge2.igbopie.challenge5;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Problem {

	static int[] oldTransitions = { 6, 2, 5, 5, 4, 5, 6, 3, 7, 6 };

	static int[][] newTransitions = { 	//0  1  2  3  4  5  6  7  8  9
										{ 0, 0, 1, 1, 1, 1, 1, 0, 1, 1 },// 0
										{ 4, 0, 4, 3, 2, 4, 5, 1, 5, 4 },// 1
										{ 2, 1, 0, 1, 2, 2, 2, 1, 2, 2 },// 2
										{ 2, 0, 1, 0, 1, 1, 2, 0, 2, 1 },// 3
										{ 3, 0, 3, 2, 0, 2, 3, 1, 3, 2 },// 4
										{ 2, 1, 2, 1, 1, 0, 1, 1, 2, 1 },// 5
										{ 1, 1, 1, 1, 1, 0, 0, 1, 1, 1 },// 6
										{ 3, 0, 3, 2, 2, 3, 4, 0, 4, 3 },// 7
										{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },// 8
										{ 1, 0, 1, 0, 0, 0, 1, 0, 1, 0 } // 9
	};

	public static void execProblem(Calendar from, Calendar to) {
		int oldEnergy = 6 * 6; // init energy to show 00:00:00
		int newEnergy = 6 * 6;

		int lastSeconds = 0;
		int lastMinutes = 0;
		int lastHours = 0;

		from.add(Calendar.SECOND, 1);

		while (from.before(to) || from.equals(to)) {
			int hours = from.get(Calendar.HOUR_OF_DAY);
			int minutes = from.get(Calendar.MINUTE);
			int seconds = from.get(Calendar.SECOND);

			oldEnergy += oldTransitions[seconds / 10];
			oldEnergy += oldTransitions[seconds % 10];

			oldEnergy += oldTransitions[minutes / 10];
			oldEnergy += oldTransitions[minutes % 10];

			oldEnergy += oldTransitions[hours / 10];
			oldEnergy += oldTransitions[hours % 10];

			newEnergy += newTransitions[lastSeconds / 10][seconds / 10];
			newEnergy += newTransitions[lastSeconds % 10][seconds % 10];

			newEnergy += newTransitions[lastMinutes / 10][minutes / 10];
			newEnergy += newTransitions[lastMinutes % 10][minutes % 10];

			newEnergy += newTransitions[lastHours / 10][hours / 10];
			newEnergy += newTransitions[lastHours % 10][hours % 10];

			lastHours = hours;
			lastMinutes = minutes;
			lastSeconds = seconds;
			from.add(Calendar.SECOND, 1);
		}

		System.out.println(oldEnergy - newEnergy);
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			try {
				String line = in.nextLine();

				String[] tokens = line.split(" - ");
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");

				Calendar from = Calendar.getInstance();
				from.setTime(sdf.parse(tokens[0]));

				Calendar to = Calendar.getInstance();
				to.setTime(sdf.parse(tokens[1]));

				execProblem(from, to);
			} catch (Exception e) {
				System.out.println("Error");
				System.err.println(e);
			}

		}
		in.close();
		
//		This code was used to calculate the transition matrix
//		calcTransitionValues();
//		for(int[]i:transitionValues){
//			System.out.print("{");
//			for(int j:i){
//				System.out.print(j+",");
//			}
//			System.out.println("}");
//		}
		

	}
	
//	//   2
//	//  ------
//	// 3|	 |1
//	//  --6---
//	// 4| 	 |0
//	//  ------
//	//     5 
//	public static String CERO="1111110";
//
//	//   2
//	//  
//	// 3	 |1
//	//     6
//	// 4 	 |0
//	//  
//	//     5 
//	public static String ONE="1100000";
//
//	//   2
//	//  ------
//	// 3	 |1
//	//  --6---
//	// 4| 	  0
//	//  ------
//	//     5 
//	public static String TWO="0110111";
//
//	//   2
//	//  ------
//	// 3	 |1
//	//  --6---
//	// 4 	 |0
//	//  ------
//	//     5 
//	public static String THREE="1110011";
//
//	//   2
//	//  
//	// 3|	 |1
//	//  --6---
//	// 4 	 |0
//	//  
//	//     5 
//	public static String FOUR="1101001";
//	//   2
//	//  ------
//	// 3|	 1
//	//  --6---
//	// 4 	 |0
//	//  ------
//	//     5 
//	public static String FIVE="1011011";
//	//   2
//	//  ------
//	// 3|	 1
//	//  --6---
//	// 4| 	 |0
//	//  ------
//	//     5 
//	public static String SIX="1011111";
//	//   2
//	//  ------
//	// 3	 |1
//	//    6
//	// 4 	 |0
//	//  
//	//     5 
//	public static String SEVEN="1110000";
//	//   2
//	//  ------
//	// 3|	 |1
//	//  --6---
//	// 4| 	 |0
//	//  ------
//	//     5 
//	public static String EIGHT="1111111";
//	//   2
//	//  ------
//	// 3|	 |1
//	//  --6---
//	// 4 	 |0
//	//  ------
//	//     5 
//	public static String NINE="1111011";
//	
//
//	private static int[][]transitionValues;
//	private static void calcTransitionValues(){
//		transitionValues=new int[10][10];
//		for(int i=0;i<transitionValues.length;i++){
//			for(int j=0;j<transitionValues[i].length;j++){
//				String iP=takePattern(i);
//				String jP=takePattern(j);
//				int value=0;
//				for(int z=0;z<iP.length();z++){
//					if(iP.charAt(z)!=jP.charAt(z)&&iP.charAt(z)=='0'){//only turning on!
//						value++;
//					}
//				}
//				transitionValues[i][j]=value;
//			}
//		}
//	}
//	private static String takePattern(int i){
//		switch(i){
//		case 0:
//			return CERO;
//		case 1:
//			return ONE;
//		case 2:
//			return TWO;
//		case 3:
//			return THREE;
//		case 4:
//			return FOUR;
//		case 5:
//			return FIVE;
//		case 6:
//			return SIX;
//		case 7:
//			return SEVEN;
//		case 8:
//			return EIGHT;
//		case 9:
//			return NINE;
//		}
//		return "0000000";
//	}
}

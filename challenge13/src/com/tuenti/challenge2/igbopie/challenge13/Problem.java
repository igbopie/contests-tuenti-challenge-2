package com.tuenti.challenge2.igbopie.challenge13;

import java.io.IOException;
import java.util.Scanner;

public class Problem {

	public static void execProblem(int nCase, int nCards, int nCardsFirstSet) {
		int[]transformationArray=new int[nCards];
		int[]rotation=new int[nCards];
		int[]frotation=new int[nCards];
		int[] cards = new int[nCards];

		// init cards
		for (int i = 0; i < cards.length; i++) {
			cards[i] = i;
		}
		
		//we calc the transformation array
		for (int i = 0,b = cards.length-1,a = nCardsFirstSet-1; i < cards.length; i++) {
			
			if (i % 2 == 0 && a >= 0) {
				transformationArray[i] = cards[a];
				a--;
			} else if (i % 2 != 0 && b >= nCardsFirstSet ) {
				transformationArray[i] = cards[b];
				b--;
			} else if (a >= 0) {
				transformationArray[i] = cards[a];
				a--;
			} else if (b >= nCardsFirstSet) {
				transformationArray[i] = cards[b];
				b--;
			}
			
		}

		// sabemos que el nœmero de rotaciones m‡ximo
		// es igual al nœmero de cartas que hay
		for (int rotations = 0; rotations < nCards; rotations++) {
			
			//we calc the transformation array
			for (int i = 0; i < nCards; i++) {
				cards[i]=transformationArray[cards[i]];
				if(cards[i] != i){
					rotation[i]++;
				} else {
					frotation[i]=rotation[i]+1;
					rotation[i]=0;
				}
			}
			
		}
		// ahora tenemos que sacar el m’nimo comun mœltiplo
		long multiplo=lcm(frotation);
		
		System.out.println("Case #"+nCase+": " + multiplo);
	}
	private static long lcm(long a, long b) {
	    return a * (b / gcd(a, b));
	}

	private static long lcm(int[] input) {
	    long result = input[0];
	    for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
	    return result;
	}
	private static long gcd(long a, long b) {
	    while (b > 0) {
	        long temp = b;
	        b = a % b; // % is remainder
	        a = temp;
	    }
	    return a;
	}


	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int nProblems = Integer.parseInt(in.nextLine().trim());
		for (int i = 0; i < nProblems; i++) {
			try {
				String[] tokens = in.nextLine().split("\\s");
				execProblem(i + 1, Integer.parseInt(tokens[0]),
						Integer.parseInt(tokens[1]));
			} catch (Exception e) {
				System.out.println("Error");
				System.err.println(e);
			}
		}
		in.close();
		

	}

}

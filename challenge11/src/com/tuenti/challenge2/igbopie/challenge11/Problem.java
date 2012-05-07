package com.tuenti.challenge2.igbopie.challenge11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Problem {
	
	// 2.5MB seems reasonable to store it in memory
	private static String DICCIONARY_FILE = "words/descrambler_wordlist.txt"; 
	// posible optimization is to have an indexed dictionary
	private static ArrayList<String> diccionary = new ArrayList<String>();
	private static Map<Character, Integer> pointsMatrix = new HashMap<Character, Integer>();

	/**
	 * 
	 * @param myChars
	 */
	public static void execProblem(String myRack, String wordOnBoard) {
		wordOnBoard = wordOnBoard.toUpperCase(); // just in case...
		myRack = myRack.toUpperCase();

		List<Character> myRackSet = new ArrayList<Character>();
		for (char c : myRack.toCharArray()) {
			myRackSet.add(c);
		}

		int maxPoints = 0;
		String maxWord = "";

		for (String word : diccionary) {
			word = word.toUpperCase();

			for (int i = 0; i < wordOnBoard.length(); i++) {

				// Does the word fit on the board
				if (word.contains(wordOnBoard.charAt(i) + "")) {

					char intersection = wordOnBoard.charAt(i);

					int points = 0;
					List<Character> myRackSetAux = new ArrayList<Character>(
							myRackSet);

					// Do I have all chars?
					boolean fits = true;
					for (int z = 0; z < word.length() && fits; z++) {
						if (intersection != word.charAt(z)) {
							int indexOfObject=myRackSetAux.indexOf(word.charAt(z));
							if (indexOfObject>=0) {
								// value
								myRackSetAux.remove(indexOfObject);
							} else {
								fits = false;
							}
						} else {
							// only one intersection so once we use it, we cannot use it again
							intersection = ' ';
						}

						points += pointsMatrix.get(word.charAt(z));
					}

					if (fits && points > maxPoints) {
						maxPoints = points;
						maxWord = word;
					}

				}
			}

		}

		System.out.println(maxWord + " " + maxPoints);

	}

	public static void main(String[] args) throws IOException {
		// Read diccionary :)
		FileInputStream fileIn = new FileInputStream(DICCIONARY_FILE);
		Reader reader = new InputStreamReader(fileIn, Charset.defaultCharset());
		BufferedReader buffer = new BufferedReader(reader);
		String line;
		while ((line = buffer.readLine()) != null) {
			diccionary.add(line.trim());
		}

		buffer.close();
		reader.close();
		fileIn.close();

		// Fill pointsMatrix
		/*
		 * 1 point: A E I L N O R S T U 2 points: D G 3 points: B C M P 4
		 * points: F H V W Y 5 points: K 8 points: J X 10 points: Q Z
		 */
		pointsMatrix.put('A', 1);
		pointsMatrix.put('E', 1);
		pointsMatrix.put('I', 1);
		pointsMatrix.put('L', 1);
		pointsMatrix.put('N', 1);
		pointsMatrix.put('O', 1);
		pointsMatrix.put('R', 1);
		pointsMatrix.put('S', 1);
		pointsMatrix.put('T', 1);
		pointsMatrix.put('U', 1);
		pointsMatrix.put('D', 2);
		pointsMatrix.put('G', 2);
		pointsMatrix.put('B', 3);
		pointsMatrix.put('C', 3);
		pointsMatrix.put('M', 3);
		pointsMatrix.put('P', 3);
		pointsMatrix.put('F', 4);
		pointsMatrix.put('H', 4);
		pointsMatrix.put('V', 4);
		pointsMatrix.put('W', 4);
		pointsMatrix.put('Y', 4);
		pointsMatrix.put('K', 5);
		pointsMatrix.put('J', 8);
		pointsMatrix.put('X', 8);
		pointsMatrix.put('Q', 10);
		pointsMatrix.put('Z', 10);

		// Solve
		Scanner in = new Scanner(System.in);
		int nProblems = Integer.parseInt(in.nextLine().trim());
		for (int i = 0; i < nProblems; i++) {
			try {
				String[] tokens = in.nextLine().split("\\s");
				execProblem(tokens[0], tokens[1]);
			} catch (Exception e) {
				System.out.println("Error");
				System.err.println(e);
			}
		}
		in.close();

	}

}

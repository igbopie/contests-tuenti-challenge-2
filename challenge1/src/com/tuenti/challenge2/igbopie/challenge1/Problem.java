package com.tuenti.challenge2.igbopie.challenge1;

import java.util.Scanner;

public class Problem {

	private static int CHANGE_CASE_KEY = 10;
	private static int[][] transitions = { { 1, 2, 3 }, { 4, 5, 6 },
			{ 7, 8, 9 }, { -1, 0, CHANGE_CASE_KEY }, };

	public static void execProblem(String line) {

		int time = 0;
		int[] lastKey = { 0, 0, 0 };// key 0
		int uppercase = 0;
		for (int i = 0; i < line.length(); i++) {

			char c = line.charAt(i);
			int[] key = getKey(c);
			// change to uppercase or lowercase
			if (key[2] >= 0 && uppercase != key[2]) {
				uppercase = key[2];

				// move finger
				time += getKeyTransitionTime(lastKey[0], CHANGE_CASE_KEY);

				// press key
				time += 100;

				// move finger
				time += getKeyTransitionTime(CHANGE_CASE_KEY, key[0]);

			} else if (lastKey[0] == key[0]) {

				// If same key wait 500ms
				time += 500;
			} else {

				time += getKeyTransitionTime(lastKey[0], key[0]);
			}
			time += key[1] * 100;// press button

			lastKey = key;

		}

		System.out.println(time);
	}

	public static int getKeyTransitionTime(int a, int b) {
		int[] posA = findPos(a);
		int[] posB = findPos(b);

		int time = 0;

		int difI = Math.abs(posA[0] - posB[0]);
		int difJ = Math.abs(posA[1] - posB[1]);

		// diagonal moves
		while (difI > 0 && difJ > 0) {// calc difference
			time += 350;
			difI--;
			difJ--;
		}

		// up-down
		while (difI > 0) {
			time += 300;
			difI--;
		}

		// right-left
		while (difJ > 0) {
			time += 200;
			difJ--;
		}

		return time;

	}

	public static int[] findPos(int a) {
		int posAi = 0;
		int posAj = 0;
		boolean found = false;
		for (; posAi < transitions.length && !found; posAi++) {
			for (posAj = 0; posAj < transitions[posAi].length && !found; posAj++) {
				if (transitions[posAi][posAj] == a) {
					found = true;
				}
			}
		}
		int pos[] = { --posAi, --posAj };
		return pos;
	}

	/**
	 * Pos 0 key pressed Pos 1 times Pos 2 uppercase
	 * 
	 * @param c
	 * @return
	 */
	public static int[] getKey(char c) {
		int keyPressed = 0;
		int times = 1;
		int uppercase = Character.isLetter(c) ? Character.isUpperCase(c) ? 1
				: 0 : -1;
		char cLowerCase = Character.toLowerCase(c);
		switch (cLowerCase) {
		case '0':
			keyPressed = 0;
			break;

		case '1':
			times++;
		case ' ':
			keyPressed = 1;
			break;

		case '2':
			times++;
		case 'c':
			times++;
		case 'b':
			times++;
		case 'a':
			keyPressed = 2;
			break;

		case '3':
			times++;
		case 'f':
			times++;
		case 'e':
			times++;
		case 'd':
			keyPressed = 3;
			break;

		case '4':
			times++;
		case 'i':
			times++;
		case 'h':
			times++;
		case 'g':
			keyPressed = 4;
			break;

		case '5':
			times++;
		case 'l':
			times++;
		case 'k':
			times++;
		case 'j':
			keyPressed = 5;
			break;

		case '6':
			times++;
		case 'o':
			times++;
		case 'n':
			times++;
		case 'm':
			keyPressed = 6;
			break;

		case '7':
			times++;
		case 's':
			times++;
		case 'r':
			times++;
		case 'q':
			times++;
		case 'p':
			keyPressed = 7;
			break;

		case '8':
			times++;
		case 'v':
			times++;
		case 'u':
			times++;
		case 't':
			keyPressed = 8;
			break;

		case '9':
			times++;
		case 'z':
			times++;
		case 'y':
			times++;
		case 'x':
			times++;
		case 'w':
			keyPressed = 9;
			break;

		// No default

		}

		int[] key = { keyPressed, times, uppercase };
		return key;
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int i = 0;
		while (in.hasNextLine()) {
			String line = in.nextLine();
			try {
				if (i > 0) {
					execProblem(line);
				}
			} catch (Exception e) {
				System.out.println("Error");
				System.err.println(e);
			}
			i++;
		}
	}
}

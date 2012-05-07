package com.tuenti.challenge2.igbopie.challenge4;

import java.util.LinkedList;
import java.util.Scanner;

public class Problem {

	public static void execProblem(int nKarts, int nRaces, int[] groups) {
		LinkedList<Integer> qeue = new LinkedList<Integer>();
		// init qeue
		for (int group : groups) {
			qeue.add(group);
		}
		int litres = 0;
		for (int i = 0; i < nRaces; i++) {
			int availableKarts = nKarts;
			int group = qeue.poll();
			LinkedList<Integer> unqeued = new LinkedList<Integer>();
			boolean quit = false;
			while (availableKarts >= group && !quit) {
				availableKarts -= group;
				unqeued.add(group);
				if (qeue.size() > 0) {
					group = qeue.poll();
				} else {
					quit = true;
				}
			}
			if (!quit) {
				qeue.addFirst(group);
			}
			qeue.addAll(unqeued);
			litres += nKarts - availableKarts;
		}

		System.out.println(litres);
	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int nProblems = Integer.parseInt(in.nextLine());
		for (int i = 0; i < nProblems; i++) {
			String[] tokens = in.nextLine().split(" ");
			int nRaces = Integer.parseInt(tokens[0]);
			int nKarts = Integer.parseInt(tokens[1]);
			int nGroups = Integer.parseInt(tokens[2]);
			int[] groups = new int[nGroups];
			tokens = in.nextLine().split(" ");
			for (int j = 0; j < tokens.length; j++) {
				groups[j] = Integer.parseInt(tokens[j]);
			}

			try {
				execProblem(nKarts, nRaces, groups);
			} catch (Exception e) {
				System.out.println("Error");
				System.err.println(e);
			}

		}
		in.close();

	}
}

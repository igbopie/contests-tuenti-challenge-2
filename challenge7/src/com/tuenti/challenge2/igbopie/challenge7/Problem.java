package com.tuenti.challenge2.igbopie.challenge7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Problem {

	public static void execProblem(List<String> rules) {
		// Cool solution that almost work :(
		// boolean change=true;
		// HashMap<Character,Integer>pass=new HashMap<Character,Integer>();
		// while(change){
		// change=false;
		//
		// for(String rule:rules){
		// int weight=1;
		// for(int i=0;i<rule.length();i++){
		// Character c=rule.charAt(i);
		// if(pass.containsKey(c)){
		// int lastWeight=pass.get(c);
		// if(lastWeight<weight){
		// pass.remove(c);
		// pass.put(c, weight);
		//
		// change=true;
		// }else{
		// weight=lastWeight;
		// }
		// }else{
		// pass.put(c, weight);
		// change=true;
		// }
		// weight++;
		// }
		// }
		//
		// }

		// We will try all permutations

		// we create a list of all chars
		Set<Character> list = new HashSet<Character>();
		for (String rule : rules) {
			for (int i = 0; i < rule.length(); i++) {
				Character c = rule.charAt(i);
				if (!list.contains(c)) {
					list.add(c);
				}
			}
		}
		//create all permutations...
		List<String> solutions = new ArrayList<String>();
		print(list, new StringBuffer(), rules, solutions);

		for (String solution : solutions) {
			System.out.println(solution);
		}

	}

	public static void print(Set<Character> list, StringBuffer s,
			List<String> rules, List<String> solutions) {
		if (list.isEmpty()) {
			if (checkRules(s, rules)) {
				solutions.add(s.toString());
			}
			return;
		}
		for (char key : list) {
			Set<Character> listAux = new HashSet<Character>(list);
			listAux.remove(key);
			StringBuffer aux = new StringBuffer(s);
			aux.append(key);
			print(listAux, aux, rules, solutions);

		}
		return;
	}

	public static boolean checkRules(StringBuffer s, List<String> rules) {
		for (String rule : rules) {
			int lastCharIndex = 0;
			for (int i = 0; i < rule.length(); i++) {
				Character c = rule.charAt(i);
				int charIndex = s.lastIndexOf(c + "");
				if (charIndex < lastCharIndex) {
					return false;
				}
				lastCharIndex = charIndex;
			}
		}
		return true;
	}

	public static void main(String[] args) {

		List<String> rules = new ArrayList<String>();
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			rules.add(in.nextLine());
		}
		in.close();

		try {
			execProblem(rules);
		} catch (Exception e) {
			System.out.println("Error");
			System.err.println(e);
		}

	}

}

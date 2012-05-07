package com.tuenti.challenge2.igbopie.challenge9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Problem {

	public static String DOC_FOLDER = "documents/";
	public static int MAX_DOCUMENTS = 800;
	public static DecimalFormat docNameFormat = new DecimalFormat("0000");

	public static void execProblem(String word, int times) throws Exception {

		boolean found = false;
		int foundTimes = 0;

		for (int docNumber = 1; docNumber < MAX_DOCUMENTS && !found; docNumber++) {

			FileInputStream in = new FileInputStream(DOC_FOLDER
					+ docNameFormat.format(docNumber));
			Reader reader = new InputStreamReader(in, Charset.defaultCharset());
			BufferedReader buffer = new BufferedReader(reader);

			int nLine = 1;
			String line;
			while ((line = buffer.readLine()) != null && !found) {
				String[] words = line.split("\\s");
				int nWord = 1;
				for (String lineWord : words) {
					if (lineWord.equalsIgnoreCase(word)) {
						foundTimes++;
						if (foundTimes == times) {
							System.out.println(docNumber + "-" + nLine + "-"
									+ nWord);
							found = true;
						}
					}
					nWord++;
				}
				nLine++;
			}

			buffer.close();
			reader.close();
			in.close();
		}

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int nProblems = Integer.parseInt(in.nextLine().trim());
		for (int i = 0; i < nProblems; i++) {
			String[] tokens = in.nextLine().split(" ");
			try {
				execProblem(tokens[0], Integer.parseInt(tokens[1]));
			} catch (Exception e) {
				System.out.println("Error");
				System.err.println(e);
			}
		}
		in.close();

	}

}

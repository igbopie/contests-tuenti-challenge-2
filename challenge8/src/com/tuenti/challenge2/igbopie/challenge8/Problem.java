package com.tuenti.challenge2.igbopie.challenge8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Problem {

	public static String auxFile1 = "tmp1.bin";
	public static String auxFile2 = "tmp2.bin";
	
	public static int BUFFER_SIZE=1024*1024;

	public static void execProblem(String queue,
			List<Map<Character, String>> rules) throws Exception {
		File f = new File(auxFile1);
		if (f.exists()) {
			f.delete();
		}
		FileWriter fstream = new FileWriter(auxFile1);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(queue);
		out.close();

		String inFile = auxFile1;
		String outFile = auxFile2;
		for (Map<Character, String> roundRules : rules) {

			FileInputStream in = new FileInputStream(inFile);
			Reader reader = new InputStreamReader(in, Charset.defaultCharset());
			BufferedReader buffer = new BufferedReader(reader);
			
			f = new File(outFile);
			if (f.exists()) {
				f.delete();
			}

			fstream = new FileWriter(outFile);
			out = new BufferedWriter(fstream);
			int r;
			/*
			String patternString = "(" + roundRules.keySet().toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(", ", "|") + ")";
			Pattern pattern = Pattern.compile(patternString);*/
			do {

				char[] buf = new char[BUFFER_SIZE];
				r = buffer.read(buf);
				if (r > 0) {
					/*String s=new String(buf,0,r);
					for(Entry<String, String> rule:roundRules.entrySet()){
						s=s.replaceAll(rule.getKey(), rule.getValue());
					}*/
					StringBuilder sb=new StringBuilder(BUFFER_SIZE);
					for(int i=0;i<r;i++){
						sb.append( roundRules.get(buf[i]) != null ? roundRules.get(buf[i]) : buf[i] );
					}
					/*
					Matcher matcher = pattern.matcher(new String(buf,0,r));
					
					StringBuffer sb = new StringBuffer();
					while(matcher.find()) {
					    matcher.appendReplacement(sb, roundRules.get(matcher.group(1)));
					}
					matcher.appendTail(sb);*/
					out.write(sb.toString());
				}
			} while (r != -1);
			
			out.close();
			fstream.close();
			
			buffer.close();
			reader.close();
			in.close();
			

			String aux = inFile;
			inFile = outFile;
			outFile = aux;
			
			f = new File(outFile);
			if (f.exists()) {
				f.delete();
			}
		}

		System.out.println(getMD5Checksum(inFile));
		
		f = new File(inFile);
		if (f.exists()) {
			f.delete();
		}

	}

	/**
	 * Borrow from here:
	 * http://stackoverflow.com/questions/304268/getting-a-files
	 * -md5-checksum-in-java
	 * 
	 * @param md5
	 * @return
	 */
	public static byte[] createChecksum(String filename) throws Exception {
		InputStream fis = new FileInputStream(filename);

		byte[] buffer = new byte[BUFFER_SIZE];
		MessageDigest complete = MessageDigest.getInstance("MD5");
		int numRead;

		do {
			numRead = fis.read(buffer);
			if (numRead > 0) {
				complete.update(buffer, 0, numRead);
			}
		} while (numRead != -1);

		fis.close();
		return complete.digest();
	}

	// see this How-to for a faster way to convert
	// a byte array to a HEX string
	public static String getMD5Checksum(String filename) throws Exception {
		byte[] b = createChecksum(filename);
		String result = "";

		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}
	/**
	 * END stack overflow :)
	 * @param args
	 */

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String queueString = in.nextLine();
		List<Map<Character, String>> rules = new ArrayList();
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] tokens = line.split(",");
			Map<Character, String> roundRules = new HashMap();
			rules.add(roundRules);
			for (String token : tokens) {
				String[] rule = token.split("=>");
				roundRules.put(rule[0].charAt(0), rule[1]);
			}
		}
		in.close();

		try {
			execProblem(queueString, rules);
		} catch (Exception e) {
			System.out.println("Error");
			System.err.println(e);
		}

	}

}

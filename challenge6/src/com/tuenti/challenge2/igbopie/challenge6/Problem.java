package com.tuenti.challenge2.igbopie.challenge6;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem {

	

	public static void execProblem(int nCase,int width,int height,int resolution,String msg) {
		int msgLength=msg.length();
		String[]words=msg.split(" ");
		int maxWordSize=0;
		int nWords=words.length;
		int nChars=0;
		for(String word:words){
			maxWordSize=Math.max(maxWordSize, word.length());
			nChars+=word.length();
		}
		double maxFontSizeInches=0;
		for(int nCharsPerLine=maxWordSize;nCharsPerLine<=msgLength;nCharsPerLine++){

			ArrayList<String>lines=new ArrayList<String>();
			StringBuffer lastLine=new StringBuffer();
			for(String word:words){
				int estimatedLineSizeWithWord=lastLine.length()+word.length();
				if(lastLine.length()>0){
					estimatedLineSizeWithWord++;// 1 is a whitespace beetween words
				}
				
				if(estimatedLineSizeWithWord>nCharsPerLine){
					//new line
					lines.add(lastLine.toString());
					lastLine=new StringBuffer();
				}
				if(lastLine.length()>0){
					lastLine.append(" ");
				}
				lastLine.append(word);
			}
			if(lastLine.length()>0){
				lines.add(lastLine.toString());
			}
			
			int nLines=lines.size();
			
			double maxHeightFontSize=((double)height)/((double)nLines);
			double maxWidthFontSize=(((double)width)/((double)nCharsPerLine));
			double fontSizeInches=Math.min(maxHeightFontSize,maxWidthFontSize);
			
			maxFontSizeInches=Math.max(maxFontSizeInches, fontSizeInches);
			//System.out.println(lines+" "+maxHeightFontSize+" "+maxWidthFontSize);
		}

		//System.out.println(maxFontSizeInches);
		
		
		int maxFontSizePixels=(int)(maxFontSizeInches*resolution);
		double nStichesPerChar=Math.pow(maxFontSizePixels,2)/2;
		double inchesThreadPerChar=nStichesPerChar/resolution;
		

		//System.out.println(maxFontSizePixels);
		
		double result=inchesThreadPerChar*nChars;
		//System.out.println(result);
		
		DecimalFormat df = new DecimalFormat("#");
		df.setRoundingMode(RoundingMode.UP);
		
		System.out.println("Case #"+nCase+": "+df.format(result));

	}

	public static void main(String[] args) {

		
		Scanner in = new Scanner(System.in);
		int nProblems = Integer.parseInt(in.nextLine().trim());
		for (int i = 0; i < nProblems; i++) {
			String[] tokens = in.nextLine().split(" ");
			int width=Integer.parseInt(tokens[0].trim());
			int height=Integer.parseInt(tokens[1].trim());
			int resolution=Integer.parseInt(tokens[2].trim());
			String msg=in.nextLine();
			try {
				execProblem(i+1,width,height,resolution,msg);
			} catch (Exception e) {
				System.out.println("Error");
				System.err.println(e);
			}

		}
		in.close();
		
	}
	
}

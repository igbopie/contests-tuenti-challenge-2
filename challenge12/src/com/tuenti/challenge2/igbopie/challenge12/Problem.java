package com.tuenti.challenge2.igbopie.challenge12;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Problem {
	
	
	public static String KEY1="a541714a17804ac281e6ddda5b707952";//PNG COMMENTS FIELD
	public static String KEY2="ed8ce15da9b7b5e2ee70634cc235e363";//BIDI
	public static String KEY3="62cd275989e78ee56a81f0265a87562e";//LSB
	
	/*
	 * TEST KEYS
	public static String KEY1="a182";//BIDI
	public static String KEY2="1357";//PNG COMMENTS FIELD
	public static String KEY3="23f1";
	*/

	/**
	 * 
	 * @param myChars
	 */
	public static void execProblem(String input) {
		StringBuffer result=new StringBuffer();
		for(int i=0;i<input.length();i++){
			int hex1=Integer.parseInt(KEY1.charAt(i)+"", 16); 
			int hex2=Integer.parseInt(KEY2.charAt(i)+"", 16); 
			int hex3=Integer.parseInt(KEY3.charAt(i)+"", 16); 
			//int clue=Integer.parseInt(CLUE.charAt(i)+"", 16); 
			
			int hexInput=Integer.parseInt(input.charAt(i)+"", 16); 
			
			int intResult=(hex1+hex2+hex3+hexInput)%16;
			result.append(Integer.toHexString(intResult));
			
		}
		System.out.println(result);

	}

	public static void main(String[] args) throws IOException {
		// Run this to get last password
		// stego();
		// Solve
		Scanner in = new Scanner(System.in);
		while(in.hasNextLine()){
			try {
				execProblem(in.nextLine());
			} catch (Exception e) {
				System.out.println("Error");
				System.err.println(e);
			}
		}
		in.close();
		
	}	
	
	
	static int shift=7;
	static int aux=0;
	static BufferedWriter bw;
	public static void stego() throws IOException{
		BufferedImage image=ImageIO.read(new File( "CANTTF.png" ));
		bw=new BufferedWriter(new FileWriter("secret.txt"));
		
		for (int y=0; y<image.getHeight(); y++) {
		    for (int x=0; x<image.getWidth(); x++) {
		        int pixel = image.getRGB(x,y);
		        int r = (pixel >> 16) & 0xff;                  
                int g  =(pixel >> 8) & 0xff;
                int b = (pixel) & 0xff;
	
		        lsb(r);
		        lsb(g);
		        lsb(b);
		        
		    }
		}
		bw.close();
		
	}
	
	static void lsb(int value) throws IOException{
		int bit= (value & 0x1); 
		int shifted=  bit << shift;
		aux =  aux + shifted  ;
		shift--;  
		if(shift == -1){
        	bw.write((char)aux);
        	aux=0;
        	shift=7;
	     }
	}
	
}

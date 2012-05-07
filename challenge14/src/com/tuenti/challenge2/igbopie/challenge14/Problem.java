package com.tuenti.challenge2.igbopie.challenge14;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Problem {
	
	//static String nails="00001010001101100011011001011101110011100011011101111100010001000100001000110100010001001101110111110011100100101100010010011101010001101000";

	public static String ERROR="Error!";
	
	public static void execProblem(String line) {

		StringBuffer text=new StringBuffer();
		StringBuffer data=new StringBuffer();
		//NOTE 7 DOTS as humming(7,4)
		String[]tokens=line.split("(?<=\\G.......)");
		for(String token:tokens){
			
			if(token.length()!=7){
				System.out.println(ERROR);
				return;
			}
			
			int[] parity=new int[3];
			
			for(int i=0;i<token.length();i++){
				
				int bit=Integer.parseInt(""+token.charAt(i));
				
				if(bit == 1 && (i == 0 || i == 2 || i == 4 || i == 6)){
					parity[0]++;
				}
				
				if(bit == 1 && (i == 1 || i == 2 || i == 5 || i == 6)){
					parity[1]++;
				}
				
				if(bit == 1 && (i == 3 || i == 4 || i == 5 || i == 6)){
					parity[2]++;
				}
				
			}

			boolean error =false;
			StringBuffer errorPos=new StringBuffer();
			for(int i=parity.length-1;i>=0;i--){
				if(parity[i] % 2 != 0){
						error=true;
						errorPos.append(1);
				}else{
						errorPos.append(0);
				}
			}
				
			char[]binNumber=token.toCharArray();
			if(error){
				
				int errorPosInt=Integer.parseInt(errorPos.toString(), 2) -1;
				if(binNumber[errorPosInt] == '1'){
					binNumber[errorPosInt] = '0' ;
				}else {
					binNumber[errorPosInt] = '1' ;
				}
				
				token=Arrays.toString(binNumber);
			}
			
			// Get Data
			for(int i=0,power=0,pos=1;i<binNumber.length;i++,pos++){
				while(Math.pow(2, power)<pos){
					power++;
				}
				
				if(pos != Math.pow(2, power)){
					data.append(binNumber[i]);
				}

			}
 			
		}
		
		//translate
		tokens=data.toString().split("(?<=\\G........)");
		for(String token:tokens){
			char c=(char)Integer.parseInt(token, 2);
			if((c+"").matches("[^\u0000-\u0080]+")){
				System.out.println(ERROR);
				return;
			}
			text.append(c);
		}
		
		System.out.println(text);
	}
	

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		while ( in.hasNextLine() ) {
			try {
				execProblem(in.nextLine());
			} catch (Exception e) {
				System.out.println("Error");
				System.err.println(e);
				e.printStackTrace();
			}
		}
		in.close();

	}


}

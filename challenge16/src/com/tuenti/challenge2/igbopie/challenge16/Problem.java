package com.tuenti.challenge2.igbopie.challenge16;

import java.io.IOException;
import java.util.Scanner;

public class Problem {
	
	public static final int MALWARE=1;
	public static final int SAFE=0;
	
	public static void main(String[] args) throws IOException {

	
		Scanner in = new Scanner(System.in);
		
		int nKnownSoftwareReports=Integer.parseInt(in.nextLine());
		int nUnknownSoftwareReports=Integer.parseInt(in.nextLine());
		int nSystemCalls=Integer.parseInt(in.nextLine());
		
		int[][]knownSoftwareReports=new int[nKnownSoftwareReports][nSystemCalls+1];
		
		int sumSystemCalls=0;
		
		for(int i = 0; i< nKnownSoftwareReports; i++){
			 String[] tokens=in.nextLine().split("\\s");
			 int j=0;
			 for(String token:tokens){
				if(j == 0 && token.equalsIgnoreCase("M")){
					knownSoftwareReports[i][j]=MALWARE; // else SAFE
				}else if( j != 0){
					knownSoftwareReports[i][j]=Integer.parseInt(token);
				}
					
				j++;
			 }
		}

		for(int i = 0; i< nUnknownSoftwareReports; i++){
			double minimunDistance=-1;
			int softwareType=SAFE;
			int[]data=new int[nSystemCalls];

			//we parse it
			String[] tokens=in.nextLine().split("\\s");
			int j=0;
			for(String token:tokens){
				data[j]=Integer.parseInt(token);
				j++;
			}
			// we clasify it with the minimun distance
			for(int[]knownSoftware:knownSoftwareReports){

				double distance=0;
				for(j=1;j<knownSoftware.length;j++){
					if(data[j-1] != 0){
						distance+=Math.pow(knownSoftware[j]-data[j-1],2);
					}
				}
				distance=Math.sqrt(distance);
				
				if(minimunDistance < 0 || distance < minimunDistance){
					softwareType=knownSoftware[0];
					minimunDistance=distance;
				}
			}
			
			// we sum system calls if malware 
			if( softwareType == MALWARE){
				for(j=0;j<data.length;j++){
					sumSystemCalls+=data[j];
				}
			}
			
		}
	
		in.close();
	
		System.out.println(sumSystemCalls);
	
	}
	

}

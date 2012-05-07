package com.tuenti.challenge2.igbopie.challenge17;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem {
	
	int nCase;
	
	ArrayList<Ingredient> ingredients=new ArrayList<Ingredient>();
	
	ArrayList<Double>possibleSolutions=new ArrayList<Double>();
 	
	public boolean solve(){
		
		// Lo primero es cambiar a coordenadas polares y crear todos los vertices
		for(Ingredient ingredient:ingredients){
			for(IngredientReference iRef:ingredient.figures){
				calcVertexes(iRef);
				
				possibleSolutions.add(iRef.minAngle);
				possibleSolutions.add(iRef.maxAngle);
			}
		}
		
		
		for(Double angleSolution:possibleSolutions){
			
			boolean isSolution=true;
			for(Ingredient ingredient:ingredients){
				int nIngredientsSide1=0;
				int nIngredientsSide2=0;
				for(IngredientReference iRef:ingredient.figures){
					//desplazamos el eje para facilitar las operaciones
					double offSetMinAngle=substractAngles(iRef.minAngle, angleSolution);
					double offSetMaxAngle=substractAngles(iRef.maxAngle, angleSolution);
					
					//Cruza el 0.
					if( offSetMinAngle > offSetMaxAngle ){
						isSolution=false;
						break;
					}
					//Cruza 180 
					if( offSetMinAngle < (Math.PI) && offSetMaxAngle > (Math.PI)){
						isSolution=false;
						break;
					}
					
					if( offSetMinAngle <  (Math.PI) ){
						nIngredientsSide1++;
					} else {
						nIngredientsSide2++;
					}
					
					
				}
				if(nIngredientsSide1 != nIngredientsSide2 || !isSolution){
					isSolution=false;
					break;
				}
			}
			
			if(isSolution){
				return true;
			}
		}
		
		return false;
	}
	
	
	
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int nCases=Integer.parseInt(in.nextLine().trim());
		
		for(int i=0;i<nCases;i++){
			
			Problem p=new Problem();
			p.nCase=i+1;
			
			String[]tokens=in.nextLine().split("\\s");
			double pizzaCenterX = Double.parseDouble(tokens[0]);
			double pizzaCenterY= Double.parseDouble(tokens[1]);
			double pizzaRadius= Double.parseDouble(tokens[2]); //À? do we need to check that the ingredients are inside?
			
			//init pizza
			int nIngredients=Integer.parseInt(in.nextLine().trim());
			
			for(int j=0;j<nIngredients;j++){
				
				tokens=in.nextLine().split("\\s");
				Ingredient ingredient=new Ingredient();
				ingredient.name=tokens[0];
				ingredient.edges=Integer.parseInt(tokens[1]);
				ingredient.n=Integer.parseInt(tokens[2]);
				
				for(int z=0;z<ingredient.n;z++){
					
					tokens=in.nextLine().split("\\s");
					double ingredientCenterX = Double.parseDouble(tokens[0]);
					double ingredientCenterY= Double.parseDouble(tokens[1]);
					double ingredientVertexX= Double.parseDouble(tokens[2]);
					double ingredientVertexY= Double.parseDouble(tokens[3]);
					
					//we center it
					CartesianPoint center=new CartesianPoint();
					center.x=ingredientCenterX-pizzaCenterX; 
					center.y=ingredientCenterY-pizzaCenterY;

					CartesianPoint vertex=new CartesianPoint();
					vertex.x=ingredientVertexX-pizzaCenterX;
					vertex.y=ingredientVertexY-pizzaCenterY;
					
					IngredientReference iRef=new IngredientReference();
					iRef.center=center;
					iRef.vertex=vertex;
					
					iRef.type=ingredient;
					
					ingredient.figures.add(iRef);
					
				}
				
				p.ingredients.add(ingredient);
			}
			
			System.out.println("Case #"+p.nCase+": "+(p.solve()+"").toUpperCase());
		}
		in.close();
	
	
	}
	
	public static class CartesianPoint{
		double x;
		double y;
	}
	public static class PolarPoint{
		double distance;
		double angle;
	}

	public static class Ingredient{
		String name;
		int edges;
		int n;
		ArrayList<IngredientReference> figures=new ArrayList<IngredientReference>();
	}
	
	public static class IngredientReference{
		CartesianPoint center;
		CartesianPoint vertex;
		Ingredient type;//cuidado con esto. Referencias circulares
		double minAngle=0;
		double maxAngle=0;
		ArrayList<PolarPoint> vertexes=new ArrayList<PolarPoint>();
	}
	
	public static class Figure{
		ArrayList<PolarPoint> vertex=new ArrayList<PolarPoint>();
	}
	
	public static double distance(CartesianPoint a, CartesianPoint b){
		return Math.sqrt( Math.pow(a.x-b.x, 2 ) + Math.pow(a.y-b.y, 2 ) );
	}
	public static CartesianPoint substract(CartesianPoint a, CartesianPoint b){
		CartesianPoint result=new CartesianPoint();
		result.x=a.x-b.x;
		result.y=a.y-b.y;
		return result;
	}
	public static CartesianPoint add(CartesianPoint a, CartesianPoint b){
		CartesianPoint result=new CartesianPoint();
		result.x=a.x+b.x;
		result.y=a.y+b.y;
		return result;
	}
	
	public static PolarPoint cartesianToPolar(CartesianPoint cp){
		PolarPoint pp=new PolarPoint();
		pp.distance=Math.sqrt( Math.pow(cp.x, 2 ) + Math.pow(cp.y, 2 ) );
		pp.angle=Math.atan(cp.y/cp.x);
		if(cp.x > 0 && cp.y >0){
			//nothing
		}else if(cp.x <= 0 && cp.y >= 0){
			pp.angle+=Math.PI;
		}else if(cp.x <= 0 && cp.y <= 0){
			pp.angle+=Math.PI;
		}else if(cp.x >= 0 && cp.y <= 0){
			pp.angle+=Math.PI*2;
		}
		pp.angle%=Math.PI*2;//just in case...
		return pp;
	}
	
	public static CartesianPoint polarToCartesian(PolarPoint pp){
		CartesianPoint cp=new CartesianPoint();
		cp.x= Math.abs(pp.distance * Math.cos(pp.angle));
		cp.y= Math.abs(pp.distance * Math.sin(pp.angle));
		if(pp.angle >= 0 && pp.angle < (Math.PI/2)){
			cp.x*=1;
			cp.y*=1;
		}else if(pp.angle >= (Math.PI/2) && pp.angle < (Math.PI)){
			cp.x*=-1;
			cp.y*=1;
		}else if(pp.angle >= (Math.PI) && pp.angle < (3*Math.PI/2)){
			cp.x*=-1;
			cp.y*=-1;
		}else if(pp.angle >= (3*Math.PI/2) && pp.angle < (2*Math.PI)){
			cp.x*=1;
			cp.y*=-1;
		}
		
		
		return cp;
	}
	
	public static double substractAngles(double a,double b){
		double result=a-b;
		if(result<0){
			result+=Math.PI*2;
		}
		result%=Math.PI*2;
		return result;
	}
	
	public static void calcVertexes(IngredientReference iRef){
		// distance from center
		//double distanceFromCenter=distance(iRef.center,iRef.vertex);
		
		CartesianPoint vertexFromCenter=substract(iRef.vertex,iRef.center);
		PolarPoint polarFromCenter= cartesianToPolar(vertexFromCenter);
		
		double diffAngle = 2*Math.PI / iRef.type.edges;
		
		double vertexAngel=polarFromCenter.angle;
		
		for(int i=0;i< iRef.type.edges;i++,vertexAngel=(vertexAngel+diffAngle)%(2*Math.PI)){
			
			PolarPoint polarVertexFromCenter=new PolarPoint();
			polarVertexFromCenter.angle=vertexAngel;
			polarVertexFromCenter.distance=polarFromCenter.distance;
			
			CartesianPoint cartesianVertexFromCenter=polarToCartesian(polarVertexFromCenter);
			
			CartesianPoint vertexCartesian=add(cartesianVertexFromCenter, iRef.center);
			
			iRef.vertexes.add(cartesianToPolar(vertexCartesian));
		}
		
		//calc max and min
		double maxAngle=0;
		
		for(PolarPoint pp1:iRef.vertexes){
			for(PolarPoint pp2:iRef.vertexes){
				double diff1=substractAngles(pp2.angle,pp1.angle);
				double diff2=substractAngles(pp1.angle,pp2.angle);
				double angle= Math.min(diff1,diff2);
				if(angle>maxAngle){
					if(angle==diff1){ //maldito cero!
						iRef.minAngle=pp1.angle;
						iRef.maxAngle=pp2.angle;
					}else{
						iRef.minAngle=pp2.angle;
						iRef.maxAngle=pp1.angle;
					}
					maxAngle=angle;
				}
			}
			
		}
		
		
	}
	
	
	
}

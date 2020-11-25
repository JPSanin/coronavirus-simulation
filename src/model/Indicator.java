package model;

import processing.core.PApplet;
import processing.core.PConstants;

public class Indicator implements Comparable<Indicator>{
	
	private int amount;
	private int posX;
	//1 is Healthy
	//2 is Infected
	//3 is Recovered
	private int type;
	private PApplet app;
	
	
	
	
	
	public Indicator(int amount, int type, PApplet app) {
		this.amount = amount;
		posX= 200;
		this.type=type;
		this.app=app;
	}
	
	
	public void draw(int posY) {
		switch(type) {
		case 1:
			app.fill(0,255,0);
			app.textAlign(PConstants.CENTER);
			app.text("Healthy: "+ amount, posY,posX);
			break;
		case 2:
			app.fill(255,0,0);
			app.textAlign(PConstants.CENTER);
			app.text("Infected: "+ amount, posY,posX);
			break;
		case 3:
			app.fill(0,0,255);
			app.textAlign(PConstants.CENTER);
			app.text("Recovered: "+ amount, posY,posX);
			break;
		
		
		}
	}




	@Override
	public int compareTo(Indicator i1) {
		return amount-i1.amount;
	}





	
	
	

}

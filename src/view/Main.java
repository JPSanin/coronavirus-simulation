package view;

import model.Healthy;
import model.Infected;
import model.Person;
import model.Recovered;
import processing.core.PApplet;

public class Main extends PApplet{
	
	private Person[] ps= new Person[3];

	public static void main(String[] args) {
		PApplet.main(Main.class.getName());
	}
	
	public void settings() {
		size(400,300);
		
	}
	
	public void setup() {
		ps[0]=new Healthy(this);
		ps[1]=new Infected(this);
		ps[2]= new Recovered(this);
	}
	
	public void draw() {
		background(0);
	
		fill(255);
		stroke(0);
		rect(25, 50, 350, 225);
		for (int i = 0; i < ps.length; i++) {
			ps[i].move();
			ps[i].bounce();
			ps[i].draw();
		}
		
	}
	

}

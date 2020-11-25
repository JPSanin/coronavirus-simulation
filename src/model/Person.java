package model;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Person{
	
	
	private final static int SIZE=7;
	private PVector location;
	private PVector velocity;
	private PApplet app;
	
	
	public Person(PApplet app) {
		location= 	new PVector (app.random(29, 371),app.random(54, 271));
		velocity=	new PVector (app.random(-2, 2),app.random(-2, 2));
		this.app=app;
	}
	
	
	public void move() {
		location.add(velocity);
	}
	
	public void bounce() {
		if((location.x>375-SIZE) || (location.x<25+SIZE)) {
			velocity.x = velocity.x * -1;
		}
		
		if((location.y>275-SIZE) || (location.y<50+SIZE)) {
			velocity.y = velocity.y * -1;
		}
	}
	
	public abstract void draw();


	public static int getSize() {
		return SIZE;
	}


	public PVector getLocation() {
		return location;
	}


	public PVector getVelocity() {
		return velocity;
	}


	public PApplet getApp() {
		return app;
	}
	
	

}

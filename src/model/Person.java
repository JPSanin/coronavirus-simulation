package model;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Person extends Thread{
	
	
	private final static int SIZE=7;
	private PVector location;
	private PVector velocity;
	private PApplet app;
	private boolean chocar;
	
	
	public Person(PApplet app) {
		location= 	new PVector (app.random(35, 765),app.random(60, 565));
		velocity=	new PVector (app.random(-2, 2),app.random(-2, 2));
		this.app=app;
		chocar=false;
	}
	
	
	public void move() {
		location.add(velocity);
	}
	
	public void bounce() {
		if((location.x>775-SIZE) || (location.x<25+SIZE)) {
			velocity.x = velocity.x * -1;
		}
		
		if((location.y>575-SIZE) || (location.y<50+SIZE)) {
			velocity.y = velocity.y * -1;
		}
		
		if(chocar) {
			velocity.x = velocity.x * -1;
			velocity.y = velocity.y * -1;
			chocar=false;
		}
	}
	
	public abstract void draw();

	
	public void run() {
		move();
		bounce();
		try {
			sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static int getSize() {
		return SIZE;
	}


	public PVector getLocation() {
		return location;
	}


	public PVector getVelocity() {
		return velocity;
	}


	public boolean isChocar() {
		return chocar;
	}


	public void setChocar(boolean chocar) {
		this.chocar = chocar;
	}


	public PApplet getApp() {
		return app;
	}
	
	

}

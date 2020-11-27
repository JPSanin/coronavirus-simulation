package model;

import processing.core.PApplet;
import processing.core.PVector;

public class Recovered extends Person {

	public Recovered(PApplet app) {
		super(app);
	}
	
	public Recovered(PVector location, PVector velocity, PApplet app) {
		super(location, velocity, app);
	}
	
	

	public void run() {
		move();
		bounce();
		try {
			sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw() {
		getApp().noStroke();
		getApp().fill(0,0,255);
		getApp().ellipse(getLocation().x, getLocation().y, getSize(), getSize());
	}
	
	

}

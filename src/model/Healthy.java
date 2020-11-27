package model;

import processing.core.PApplet;

public class Healthy extends Person{

	public Healthy(PApplet app) {
		super(app);
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
		getApp().fill(0,255,0);
		getApp().ellipse(getLocation().x, getLocation().y, getSize(), getSize());
		
	}

}

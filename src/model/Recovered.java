package model;

import processing.core.PApplet;

public class Recovered extends Person {

	public Recovered(PApplet app) {
		super(app);
	}

	@Override
	public void draw() {
		getApp().noStroke();
		getApp().fill(0,0,255);
		getApp().ellipse(getLocation().x, getLocation().y, getSize(), getSize());
	}
	
	

}

package model;

import processing.core.PApplet;

public class Healthy extends Person{

	public Healthy(PApplet app) {
		super(app);
	}

	@Override
	public void draw() {
		getApp().noStroke();
		getApp().fill(0,255,0);
		getApp().ellipse(getLocation().x, getLocation().y, getSize(), getSize());
		
	}

}

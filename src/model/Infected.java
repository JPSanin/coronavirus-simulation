package model;

import processing.core.PApplet;
import processing.core.PVector;

public class Infected extends Person{

	public Infected(PApplet app) {
		super(app);
	}
	
	public Infected(PVector location, PVector velocity, PApplet app) {
		super(location, velocity, app);
	}



	@Override
	public void draw() {
		getApp().noStroke();
		getApp().fill(255,0,0);
		getApp().ellipse(getLocation().x, getLocation().y, getSize(), getSize());
	}

}

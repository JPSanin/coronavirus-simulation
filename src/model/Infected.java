package model;

import processing.core.PApplet;

public class Infected extends Person{

	public Infected(PApplet app) {
		super(app);
	}

	@Override
	public void draw() {
		getApp().noStroke();
		getApp().fill(255,0,0);
		getApp().ellipse(getLocation().x, getLocation().y, getSize(), getSize());
	}

}

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

	
	public void run() {
		while(!super.isRecover()) {
			move();
			bounce();
			try {
				sleep(14000);
				//System.out.println("Recupera");
				super.setRecover(true);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}


	@Override
	public void draw() {
		getApp().noStroke();
		getApp().fill(255,0,0);
		getApp().ellipse(getLocation().x, getLocation().y, getSize(), getSize());
	}

}

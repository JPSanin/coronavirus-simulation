package controller;

import model.Logic;
import processing.core.PApplet;

public class Controller {
	
	private Logic logic;

	public Controller(int healthyStart, int infectedStart, int recoveredStart, PApplet app) {
		logic= new Logic(healthyStart,infectedStart,recoveredStart,app);
	}
	
	
	public void sortIndicatorsAmount() {
		logic.sortIndicatorsAmount();
	}

	public void sortIndicatorsColor() {
		logic.sortIndicatorsColor();
	}
	
	public void hitcheck() {
		logic.hitcheck();
	}
	
	
	

}

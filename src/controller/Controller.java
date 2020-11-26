package controller;

import exceptions.InfectedException;
import exceptions.ThirtyPercentException;
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
	
	public void hitCheckInfection() throws InfectedException  {
		logic.hitCheckInfection();
	}
	
	public void collisions() {
		logic.collisions();
	}

	public void checkPercentage() throws ThirtyPercentException {
		logic.checkPercentage();
	}
	
	public int getHealthySize() {
		return logic.getHealthyPeople().size();
	}

	public int getInfectedSize() {
		return logic.getInfectedPeople().size();
	}
	
	public int getRecoveredSize() {
		return logic.getRecoveredPeople().size();
	}
	
	public Logic getLogic() {
		return logic;
	}


	
	
	
	

}

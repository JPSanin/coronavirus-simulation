package model;

import java.util.ArrayList;

import processing.core.PApplet;

public class Logic {

	private ArrayList<Healthy> healthyPeople;
	private ArrayList<Infected> infectedPeople;
	private ArrayList<Recovered> recoveredPeople;

	private Indicator[] indicators;


	public Logic(int healthyStart, int infectedStart, int recoveredStart, PApplet app ) {
		healthyPeople= new ArrayList<>();
		infectedPeople= new ArrayList<>();
		recoveredPeople= new ArrayList<>();

		for (int i = 0; i < healthyStart; i++) {
			healthyPeople.add(new Healthy(app));
		}

		for (int i = 0; i < infectedStart; i++) {
			infectedPeople.add(new Infected(app));
		}

		for(int i=0; i<recoveredStart; i++) {
			recoveredPeople.add(new Recovered(app));
		}

		indicators= new Indicator[3];
		indicators[0]= new Indicator(healthyStart, 1, app);
		indicators[1]= new Indicator(infectedStart, 2, app);
		indicators[2]= new Indicator(recoveredStart, 3, app);

	}


	public void sortIndicatorsAmount() {

	}

	public void sortIndicatorsColor() {

	}
	
	public void hitcheck() {
		
	}
	
	



}

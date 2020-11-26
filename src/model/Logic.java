package model;

import java.util.ArrayList;
import java.util.Arrays;

import exceptions.InfectedException;
import processing.core.PApplet;

public class Logic {

	private ArrayList<Healthy> healthyPeople;
	private ArrayList<Infected> infectedPeople;
	private ArrayList<Recovered> recoveredPeople;

	private Indicator[] indicators;

	private ColorComparator cc;
	private PApplet app;
	
	

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
		
		cc= new ColorComparator();
		
		this.app=app;
		

	}


	public void sortIndicatorsAmount() {
		Arrays.sort(indicators);
	}

	public void sortIndicatorsColor() {
		Arrays.sort(indicators,cc);
	}
	
	public void hitCheckInfection() throws InfectedException{
		
		boolean hit=false;
		for (int i = 0; i < healthyPeople.size() && hit==false; i++) {
			for (int j = 0; j < infectedPeople.size() && hit==false ; j++) {
				healthyPeople.get(i).checkCollision(infectedPeople.get(j));
				if(healthyPeople.get(i).isHit()) {
					Infected newInfected= new Infected(healthyPeople.get(i).getLocation(),
							healthyPeople.get(i).getVelocity(),app);
					healthyPeople.remove(i);
					infectedPeople.add(newInfected);
					hit=true;
					throw new InfectedException();
				}
			}
		}
		
		
	}

	public void hitCheckCollisions() {
		
	}

	public ArrayList<Healthy> getHealthyPeople() {
		return healthyPeople;
	}


	public ArrayList<Infected> getInfectedPeople() {
		return infectedPeople;
	}


	public ArrayList<Recovered> getRecoveredPeople() {
		return recoveredPeople;
	}


	public Indicator[] getIndicators() {
		return indicators;
	}
	
	
	
	



}

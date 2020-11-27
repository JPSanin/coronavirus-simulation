package model;

import java.util.ArrayList;
import java.util.Arrays;

import exceptions.InfectedException;
import exceptions.ThirtyPercentException;
import processing.core.PApplet;

public class Logic{

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
	
	public void recover() {
		if(app.frameCount%15==0) {
			for (int i = 0; i < infectedPeople.size(); i++) {
				if(infectedPeople.get(i).isRecover()) {
					infectedPeople.get(i).setRecover(false);
					Recovered newRecovered= new Recovered(infectedPeople.get(i).getLocation(),
							infectedPeople.get(i).getVelocity(),app);
					infectedPeople.remove(i);
					recoveredPeople.add(newRecovered);
					if(indicators[0].getType()==2) {
						indicators[0].setAmount(indicators[0].getAmount()-1);
					}else if(indicators[1].getType()==2) {
						indicators[1].setAmount(indicators[1].getAmount()-1);
					}else {
						indicators[2].setAmount(indicators[2].getAmount()-1);
					}
					
					
					if(indicators[0].getType()==3) {
						indicators[0].setAmount(indicators[0].getAmount()+1);
					}else if(indicators[1].getType()==3) {
						indicators[1].setAmount(indicators[1].getAmount()+1);
					}else {
						indicators[2].setAmount(indicators[2].getAmount()+1);
					}
			
				}
			}
		}
		
	}
	
	public boolean hitCheckInfection() throws InfectedException{
		
		boolean hit=false;
		for (int i = 0; i < healthyPeople.size() && hit==false; i++) {
			for (int j = 0; j < infectedPeople.size() && hit==false ; j++) {
				healthyPeople.get(i).checkCollision(infectedPeople.get(j),1);
				if(healthyPeople.get(i).isHit()) {
					int random= (int) app.random(11);
					if(random<=9) {
						Infected newInfected= new Infected(healthyPeople.get(i).getLocation(),
								healthyPeople.get(i).getVelocity(),app);
						healthyPeople.remove(i);
						infectedPeople.add(newInfected);
						hit=true;
						
						if(indicators[0].getType()==1) {
							indicators[0].setAmount(indicators[0].getAmount()-1);
						}else if(indicators[1].getType()==1) {
							indicators[1].setAmount(indicators[1].getAmount()-1);
						}else {
							indicators[2].setAmount(indicators[2].getAmount()-1);
						}
						
						
						if(indicators[0].getType()==2) {
							indicators[0].setAmount(indicators[0].getAmount()+1);
						}else if(indicators[1].getType()==2) {
							indicators[1].setAmount(indicators[1].getAmount()+1);
						}else {
							indicators[2].setAmount(indicators[2].getAmount()+1);
						}
						
						throw new InfectedException();
					}else {
						healthyPeople.get(i).setHit(false);
					}
				
				}
			}
		}
		
		return hit;
		
		
	}
	
	public void checkPercentage() throws ThirtyPercentException{
		int total= indicators[0].getAmount()+indicators[1].getAmount()+indicators[2].getAmount();
		int percentage= (int) ((int) total*0.3);
		
		if(infectedPeople.size()==percentage) {
			throw new ThirtyPercentException();
		}
		
		
	}
	
	public void collisions() {
		hitCheckCollisionsHH();
		hitCheckCollisionsRR();
		hitCheckCollisionsII();
		hitCheckCollisionsIR();
		hitCheckCollisionsHR();
		
	}

	private void hitCheckCollisionsHH() {

		for (int i = 0; i < healthyPeople.size(); i++) {
			for (int j = 0; j < healthyPeople.size() && i!=j; j++) {
				healthyPeople.get(i).checkCollision(healthyPeople.get(j),0);
		
			}
		}
	}
	
	private void hitCheckCollisionsRR() {
		
		for (int i = 0; i < recoveredPeople.size() ; i++) {
			for (int j = 0; j < recoveredPeople.size()&& i!=j; j++) {
				recoveredPeople.get(i).checkCollision(recoveredPeople.get(j),0);
				
			}
		}
	}
	
	private void hitCheckCollisionsII() {
	
		for (int i = 0; i < infectedPeople.size() ; i++) {
			for (int j = 0; j < infectedPeople.size() &&  i!=j; j++) {
				 infectedPeople.get(i).checkCollision( infectedPeople.get(j),0);
				
			}
		}
	}
	
	private void hitCheckCollisionsIR() {
		
		for (int i = 0; i < infectedPeople.size(); i++) {
			for (int j = 0; j < recoveredPeople.size() && i!=j; j++) {
				 infectedPeople.get(i).checkCollision( recoveredPeople.get(j),0);
		
			}
		}
	}
	
	private void hitCheckCollisionsHR() {
	
		for (int i = 0; i < healthyPeople.size(); i++) {
			for (int j = 0; j < recoveredPeople.size()  && i!=j; j++) {
				healthyPeople.get(i).checkCollision( recoveredPeople.get(j),0);
			
			}
		}
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

package view;

import controller.Controller;
import exceptions.InfectedException;
import exceptions.ThirtyPercentException;
import processing.core.PApplet;
import processing.core.PConstants;

public class Main extends PApplet{

	private Controller controller;
	private boolean thrown;

	public static void main(String[] args) {
		PApplet.main(Main.class.getName());
	}

	public void settings() {
		size(800,600);

	}

	public void setup() {
		String[] initialValues= loadStrings("data/initialValues.txt");
		String[] healthy= initialValues[0].split(":");
		String[] infected= initialValues[1].split(":");
		String[] recovered= initialValues[2].split(":");

		int healthyStart= Integer.parseInt(healthy[1].trim());
		int infectedStart= Integer.parseInt(infected[1].trim());
		int recoveredStart= Integer.parseInt(recovered[1].trim());


		controller= new Controller(healthyStart, infectedStart, recoveredStart, this);
		thrown= false;
	
	}

	public void draw() {
		background(255);

		fill(255);
		stroke(0);
		rect(25, 50, 750, 525);
	

		for(int i=0; i<controller.getHealthySize();i++) {
			controller.getLogic().getHealthyPeople().get(i).draw();
			new Thread(controller.getLogic().getHealthyPeople().get(i)).start();

		}

		for(int i=0; i<controller.getInfectedSize();i++) {
			controller.getLogic().getInfectedPeople().get(i).draw();
			new Thread(controller.getLogic().getInfectedPeople().get(i)).start();
		}

		for(int i=0; i<controller.getRecoveredSize();i++) {
			controller.getLogic().getRecoveredPeople().get(i).draw();
			new Thread(controller.getLogic().getRecoveredPeople().get(i)).start();
		}
		
		for(int i=0; i< controller.getLogic().getIndicators().length;i++) {
			controller.getLogic().getIndicators()[i].draw(450+(i*100));
		}
		
		if(thrown==false) {
			try {
				controller.checkPercentage();
			} catch (ThirtyPercentException e1) {
				thrown=true;
				System.out.println(e1.getMessage());
			}
		}
		
		controller.collisions();
		
		controller.recover();
		
		try {
			controller.hitCheckInfection();
		} catch (InfectedException e) {
			System.out.println(e.getMessage());
		}
		
		fill(0);
		textAlign(PConstants.CENTER);
		textSize(16);
		text("SORT BY AMOUNT", 150,30);
		text("SORT BY COLOR", 300,30);
		text(mouseX+ ","+mouseY, mouseX,mouseY);

	}
	
	
	public void mousePressed() {
		
		if (mouseX > 80 && mouseX < 220 && mouseY > 15 && mouseY < 30) {
			controller.sortIndicatorsAmount();
		}
		
		if (mouseX > 235 && mouseX < 360 && mouseY > 15 && mouseY < 30) {
			controller.sortIndicatorsColor();
		}
		
	}


}

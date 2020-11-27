package model;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Person extends Thread{


	private final static int SIZE=7;
	private PVector location;
	private PVector velocity;
	private PApplet app;
	private boolean hit, recover;
	private float m;


	public Person(PApplet app) {
		location= 	new PVector (app.random(35, 765),app.random(60, 565));
		velocity=	new PVector (app.random(-2, 2),app.random(-2, 2));
		this.app=app;
		hit=false;
		recover=false;
		m= (float) (SIZE*.1);
	}




	public Person(PVector location, PVector velocity, PApplet app) {
		this.location = location;
		this.velocity = velocity;
		this.app = app;
		hit=false;
		recover=false;
		m= (float) (SIZE*.1);
	}




	public void move() {
		location.add(velocity);
	}

	public void bounce() {
		if (location.x > 775-SIZE/2) {
			location.x = (float) (775-SIZE/2);
			velocity.x *= -1;
		} else if (location.x < 25+SIZE/2) {
			location.x =  (float) (25+SIZE/2);
			velocity.x *= -1;
		} else if (location.y > 575-SIZE/2) {
			location.y = 575-SIZE/2;
			velocity.y *= -1;
		} else if (location.y < 50+SIZE/2) {
			location.y = 50+SIZE/2;
			velocity.y *= -1;
		}

	}

	public void checkCollision(Person other, int collisionType) {

		// Get distances between the balls components
		PVector distanceVect = PVector.sub(other.location, location);

		// Calculate magnitude of the vector separating the balls
		float distanceVectMag = distanceVect.mag();

		// Minimum distance before they are touching
		float minDistance = SIZE;

		if (distanceVectMag < minDistance) {
			float distanceCorrection = (float) ((minDistance-distanceVectMag)/2.0);
			PVector d = distanceVect.copy();
			PVector correctionVector = d.normalize().mult(distanceCorrection);
			other.location.add(correctionVector);
			location.sub(correctionVector);

			// get angle of distanceVect
			float theta  = distanceVect.heading();
			// Recalculate rig values
			float sine = PApplet.sin(theta);
			float cosine = PApplet.cos(theta);

			/* bTemp will hold rotated ball positions. You 
		       just need to worry about bTemp[1] position*/
			PVector[] bTemp = {
					new PVector(), new PVector()
			};

			/* this ball's position is relative to the other
		       so you can use the vector between them (bVect) as the 
		       reference point in the rotation expressions.
		       bTemp[0].position.x and bTemp[0].position.y will initialize
		       automatically to 0.0, which is what you want
		       since b[1] will rotate around b[0] */
			bTemp[1].x  = cosine * distanceVect.x + sine * distanceVect.y;
			bTemp[1].y  = cosine * distanceVect.y - sine * distanceVect.x;

			// rotate Temporary velocities
			PVector[] vTemp = {
					new PVector(), new PVector()
			};

			vTemp[0].x  = cosine * velocity.x + sine * velocity.y;
			vTemp[0].y  = cosine * velocity.y - sine * velocity.x;
			vTemp[1].x  = cosine * other.velocity.x + sine * other.velocity.y;
			vTemp[1].y  = cosine * other.velocity.y - sine * other.velocity.x;

			/* Now that velocities are rotated, you can use 1D
		       conservation of momentum equations to calculate 
		       the final velocity along the x-axis. */
			PVector[] vFinal = {  
					new PVector(), new PVector()
			};

			// final rotated velocity for b[0]
			vFinal[0].x = ((m - other.m) * vTemp[0].x + 2 * other.m * vTemp[1].x) / (m + other.m);
			vFinal[0].y = vTemp[0].y;

			// final rotated velocity for b[0]
			vFinal[1].x = ((other.m - m) * vTemp[1].x + 2 * m * vTemp[0].x) / (m + other.m);
			vFinal[1].y = vTemp[1].y;

			// hack to avoid clumping
			bTemp[0].x += vFinal[0].x;
			bTemp[1].x += vFinal[1].x;

			/* Rotate ball positions and velocities back
		       Reverse signs in rig expressions to rotate 
		       in the opposite direction */
			// rotate balls
			PVector[] bFinal = { 
					new PVector(), new PVector()
			};

			bFinal[0].x = cosine * bTemp[0].x - sine * bTemp[0].y;
			bFinal[0].y = cosine * bTemp[0].y + sine * bTemp[0].x;
			bFinal[1].x = cosine * bTemp[1].x - sine * bTemp[1].y;
			bFinal[1].y = cosine * bTemp[1].y + sine * bTemp[1].x;

			// update balls to screen position
			other.location.x = location.x + bFinal[1].x;
			other.location.y = location.y + bFinal[1].y;

			location.add(bFinal[0]);

			// update velocities
			velocity.x = cosine * vFinal[0].x - sine * vFinal[0].y;
			velocity.y = cosine * vFinal[0].y + sine * vFinal[0].x;
			other.velocity.x = cosine * vFinal[1].x - sine * vFinal[1].y;
			other.velocity.y = cosine * vFinal[1].y + sine * vFinal[1].x;
			if(collisionType==1) {
				hit=true; 
			}

		}
	}

	public abstract void draw();


	

	public static int getSize() {
		return SIZE;
	}


	public PVector getLocation() {
		return location;
	}


	public PVector getVelocity() {
		return velocity;
	}


	public boolean isHit() {
		return hit;
	}


	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public boolean isRecover() {
		return recover;
	}

	public void setRecover(boolean recover) {
		this.recover = recover;
	}




	public PApplet getApp() {
		return app;
	}



}

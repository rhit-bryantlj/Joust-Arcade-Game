package main;

import java.awt.Color;
import java.awt.geom.Dimension2D;

/**
 * shootingMonster class
 * 
 * extends monster class
 * 
 * stores the methods and variables for the shooting monster
 *
 * @author Logan Bryant & Phil Theryan
 *
 */
public class ShootingMonster extends Monster {

	private int counter = 0;
	private boolean shouldShoot = false;

	// constructor
	public ShootingMonster(int x, int y, int xVel, int yVel, int radius, Color color) {
		super(x, y, xVel, yVel, radius, color);
		this.yVel = yVel;
	}

	// shoots a projectile if this.shouldShoot is true
	public boolean shoot() {
		return this.shouldShoot;
	}

	//controls the movement of the shooting monster
	@Override
	public void update(Dimension2D dim, Levels level) {
		this.xPos += this.xVel;
		this.yPos += this.yVel;
		if (xPos > dim.getWidth() || xPos < 0) {
			xPos = (int) Math.min(Math.max(xPos, 0), dim.getWidth());
			xVel = -xVel;
		}
		if (yPos > dim.getHeight() || yPos < 0) {
			yVel = -yVel;
			yPos = (int) Math.min(Math.max(yPos, 0), dim.getHeight());
		}
		
		this.counter++;
		if (this.counter >= 60) {
			this.shouldShoot = true;
			this.counter = 0;
		}
		else
			this.shouldShoot = false;
	}

}

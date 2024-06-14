package main;

import java.awt.Color;
import java.awt.geom.Dimension2D;
import java.util.ArrayList;

/**
 * Projectile class
 * 
 * extends monster class
 * 
 * stores methods and information for the projectile that shot out of the shooting monster
 * 
 * @author Logan Bryant & Phil Theryan
 *
 */
public class Projectile extends Monster {

	// constructor
	public Projectile(int x, int y, int xV, int yV, int r, Color c) {
		super(x, y, xV, yV, r, c);
	}

	// controls movements of the projectile
	@Override
	public void update(Dimension2D dim, Levels level) {
		xPos += xVel;
		yPos += yVel;

		if (xPos > dim.getWidth() || xPos < 0) {
			xPos = (int) Math.min(Math.max(xPos, 0), dim.getWidth());
			xVel = -xVel;
		}
		if (yPos > dim.getHeight() || yPos < 0) {
			yPos = (int) Math.min(Math.max(yPos, 0), dim.getHeight());
			yVel = -yVel;
		}

	}

	// kills the hero if it comes into contact with the hero
	@Override
	public boolean overlapsWith(Hero hero) {

		if (Math.abs(this.xPos - hero.xPos) < this.radius * 2 && Math.abs(this.yPos - hero.yPos) < hero.radius * 2) {
			hero.shouldRemove = true;
		}

		return this.shouldRemove;

	}
}

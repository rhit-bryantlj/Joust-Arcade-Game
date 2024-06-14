package main;

import java.awt.Color;

import java.awt.geom.Dimension2D;

/**
 * Monster class the stores each basic monster's data monster is apart of the
 * Joust game objects so extends JoustObjects super class
 * 
 * @author Logan Bryant & Phil Theryan
 *
 */
public class Monster extends JoustObjects {

	protected int xVel;
	protected int yVel;

	protected int startX;
	protected int startY;

	// monster class constructor
	public Monster(int x, int y, int xVel, int yVel, int radius, Color color) {
		super(x, y, xVel, yVel, radius, color);
		this.xVel = xVel;
	}

	@Override
	// this method moves the monster in the game
	public void update(Dimension2D dim, Levels level) {
		this.xPos += this.xVel;
		if (xPos > dim.getWidth() || xPos < 0) {
			xPos = (int) Math.min(Math.max(xPos, 0), dim.getWidth());
			xVel = -xVel;
		}

	}

	// this method checks with
	public boolean overlapsWith(Hero hero) {

		if (Math.abs(this.xPos - hero.xPos) < hero.radius * 2 && Math.abs(this.yPos - hero.yPos) < hero.radius * 2) {
			if (hero.yPos < this.yPos) {
				this.shouldRemove = true;
			}
			if (hero.yPos > this.yPos) {
				hero.shouldRemove = true;
			}
		}

		return this.shouldRemove;

	}

}

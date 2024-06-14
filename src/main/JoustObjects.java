package main;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.util.ArrayList;

/**
 * JoustObjects abstract super class
 * 
 * the super class for all movable objects in the joust games
 * 
 * stores all common information
 * 
 * @author Logan Bryant & Phil Theryan
 *
 */

public abstract class JoustObjects {

	protected int xPos;
	protected int yPos;
	protected int xVel;
	protected int yVel;
	protected int radius;
	private Color color;
	private boolean onPlatform;
	private boolean underPlatform;

	protected boolean shouldRemove;

	public JoustObjects(int x, int y, int xV, int yV, int r, Color c) {
		this.xPos = x;
		this.yPos = y;
		this.xVel = xV;
		this.yVel = yV;
		this.radius = r;
		this.color = c;
		this.shouldRemove = false;
	}

	public void drawOn(Graphics2D g) {
		g = (Graphics2D) g.create();	
		g.setColor(this.color);
		g.translate(xPos - radius, yPos - radius);
		g.fillOval(0, 0, radius * 2, radius * 2);
	}

	public void update(Dimension2D dim, Levels level) {
		xPos += xVel;
		yPos = yPos + yVel;
		ArrayList<Tile> tiles = level.getTiles();
		this.onPlatform = false;
		this.underPlatform = false;
		int i = 0;
		while (i < tiles.size() && this.onPlatform == false && this.underPlatform == false) {
			if (yPos < tiles.get(i).getY() && tiles.get(i).getY() - yPos <= this.radius
					&& (xPos >= tiles.get(i).getX() && xPos < tiles.get(i).getX() + 50)) {
				yVel = 0;
				this.onPlatform = true;
				return;
			}
			if (yPos == tiles.get(i).getY() + 50 && xPos >= tiles.get(i).getX() && xPos < tiles.get(i).getX() + 50) {
				yVel = 0;
				this.underPlatform = true;
				return;
			}
			i++;
		}
		if (this.onPlatform == false && this.yVel < 10)
			this.yVel += 1;

		if (xPos > dim.getWidth() || xPos < 0) {
			xPos = (int) Math.min(Math.max(xPos, 0), dim.getWidth());
			xVel = -xVel;
		}
		if (yPos > dim.getHeight() || yPos < 0) {
			yVel = 0;
			yPos = (int) Math.min(Math.max(yPos, 0), dim.getHeight());
		}

	}

	public boolean onPlatform() {
		return this.onPlatform;
	}

	public boolean underPlatform() {
		return this.underPlatform;
	}

	public void setPosition(int newX, int newY) {
		this.xPos = newX;
		this.yPos = newY;

		this.xVel = 0;
		this.yVel = 0;
		this.shouldRemove = false;
	}

}

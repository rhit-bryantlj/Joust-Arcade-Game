package main;

import java.awt.Color;

import java.awt.geom.Dimension2D;


/**
 *The Egg class
 *
 * extends the JoustObjects
 * 
 * Stores the eggs' methods and variable
 * @author theryat
 *
 */
public class Egg extends JoustObjects{

	private static final Color EGG_COLOR = Color.yellow;
	private static final int EGG_RADIUS = 10;
	private boolean isInvincible;
	private int counter;
	private boolean spawnMonster;
	
	// constructor
	public Egg(int x, int y) {
		super(x, y,0, 0, EGG_RADIUS, EGG_COLOR);
		this.isInvincible = true;
		this.spawnMonster = false;
	}
	
	// spawns a basic monster when the boolean is true
	public boolean spawnMonster() {
		return this.spawnMonster;
	}

	// checks if the egg should be removed if the hero jousted the egg in time
	public boolean overlapsWith(Hero hero) {
		if(this.isInvincible)
			return false;
		
		if(Math.abs(this.xPos - hero.xPos) < hero.radius*2 && Math.abs(this.yPos - hero.yPos) < hero.radius*2) {
			if(hero.yPos < this.yPos) {
				this.shouldRemove = true;
			}
		}
		
		return this.shouldRemove;
	}
	
	// controls the movement of the egg
	@Override
	public void update(Dimension2D dim, Levels level) {
		super.update(dim, level);
		this.counter++;
		if(counter >= 20)
			this.isInvincible = false;
		if(counter >= 100) {
			this.spawnMonster = true;
		}
	}

}

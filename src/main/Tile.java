package main;

import java.awt.Color;


import java.awt.Graphics2D;

/**
 * This converts the text files from the levels folder into tiles to play the game
 * 
 * @author Logan Bryant & Phil Theryan
 */
public class Tile {
	
	private static int TILE_WIDTH = 50;
	private static int TILE_HEIGHT = 50;
	private int xPos;
	private int yPos;
	
	// constructor
	public Tile(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
	
	//draws each tile based on the text files
	public void drawOn(Graphics2D g) {
		g = (Graphics2D)g.create();
		g.setColor(Color.BLACK);
		g.fillRect(xPos, yPos, TILE_WIDTH, TILE_HEIGHT);
	}
	
	// returns the x position of each tile
	public int getX(){
		return this.xPos;
	}
	
	// returns the y position of each tile
	public int getY(){
		return this.yPos;
	}
}

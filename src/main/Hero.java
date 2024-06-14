package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;


/**
 * Hero of the game
 * 
 * extends JoustObject class
 * 
 * stores the methods to control the hero
 * 
 * @author Logan Bryant & Phil Theryan
 *
 */
public class Hero extends JoustObjects{
	
	public static final int HERO_RADIUS = 25;
	public static final Color HERO_COLOR = Color.RED;
	public int lives;
	public int score;

	public Hero(int x, int y, int xV, int yV) {
		super(x, y, xV, yV, HERO_RADIUS, HERO_COLOR);
		this.lives = 3;
	}
	
	public void drawOn(Graphics2D g, JComponent JoustComp, String imageName) {
		g = (Graphics2D) g.create();
		g.translate(xPos - radius, yPos - radius);
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(imageName));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		java.awt.Image dimg = img.getScaledInstance(this.radius*2, this.radius*2, 0);
		ImageIcon image = new ImageIcon(dimg);
		
		image.paintIcon(JoustComp, g, 0, 0);
	}
	
	//controls the movement of the hero
	public void move(int addedXVelocity, int addedYVelocity) {
		if(this.xVel <= 5 && this.xVel >= -5)
		this.xVel += addedXVelocity;
		if(this.yVel <= 8 && this.yVel >= -8)
		this.yVel += addedYVelocity;
	}
	
	// prevents hero from running off to the left or right
	public void stop() {
		this.xVel = 0;
	}
	
}

package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.sun.prism.Image;

/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start by running
 * main here.
 * 
 * Also don't forget to write javadocs for your classes and functions!
 * 
 * @author Logan Bryant & Phil Theryan
 *
 */
public class Main {

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 1000;

	private static final int DELAY = 50;

	// makes window to play the game
	private void makeWindow() {
		JFrame frame = new JFrame("JOUST");
		frame.setSize(WIDTH, HEIGHT);

		JPanel panel = new JPanel();
		JLabel scoreLabel = new JLabel();
		JLabel livesLabel = new JLabel();
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("Levels/background.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		java.awt.Image dimg = img.getScaledInstance(1200, 1000, 0);
		ImageIcon imageIcon = new ImageIcon(dimg);

		JoustComponent joustComp = new JoustComponent(scoreLabel, livesLabel);
		joustComp.addHero();
		joustComp.addLevel();
		frame.add(joustComp);
		panel.add(scoreLabel);
		panel.add(livesLabel);
		frame.add(panel, BorderLayout.NORTH);
		frame.addKeyListener(new KeyListener(joustComp));
		// timer to update the game
		Timer t = new Timer(DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.add(new JLabel(imageIcon));
				
				joustComp.update();
				joustComp.repaint();
				frame.repaint();
			}
		});

		t.start();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Write your cool arcade game here!");
		Main viewer = new Main();
		viewer.makeWindow();
	}
}
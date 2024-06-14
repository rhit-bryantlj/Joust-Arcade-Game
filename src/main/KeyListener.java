package main;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
/**
 *The event listener for the keyboard
 * Controls the hero and levels when a certain key is pressed
 * 
 * @author Logan Bryant & Phil Theryan
 *
 */

public class KeyListener implements java.awt.event.KeyListener {

	private JoustComponent JComp;

	public KeyListener(JoustComponent joustComp) {
		this.JComp = joustComp;
	}

	// moves the hero in the direction of the arrow pressed and shifts between
	// levels
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_U) {
			this.JComp.changeLevel(1);
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			this.JComp.changeLevel(-1);
		}

		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.JComp.getHero().move(5, 0);
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.JComp.getHero().move(-5, 0);
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (this.JComp.getHero().underPlatform())
				return;
			this.JComp.getHero().move(0, -5);
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (this.JComp.getHero().onPlatform())
				return;
			this.JComp.getHero().move(0, 5);
		}
	}

	// stops the horizontal movement of the hero when the key is released
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.JComp.getHero().stop();
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.JComp.getHero().stop();
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}

package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * JoustComponent class
 * 
 * Controls the levels and the hero in the game
 * 
 * @author theryat
 *
 */
public class JoustComponent extends JComponent {

	private JLabel scoreLabel;
	private JLabel livesLabel;
	private Hero hero;
	private ArrayList<Levels> levels = new ArrayList<Levels>();
	private ArrayList<String> fileNames = new ArrayList<String>();
	private int levelCounter;
	private Graphics2D g2;

	// constructor
	public JoustComponent(JLabel scoreLabel, JLabel livesLabel) {
		this.scoreLabel = scoreLabel;
		this.livesLabel = livesLabel;
		this.fileNames.add("levels/gameover.txt");
		this.fileNames.add("levels/level1.txt");
		this.fileNames.add("levels/level2.txt");
		this.fileNames.add("levels/level3.txt");
		this.fileNames.add("levels/level4.txt");
		this.fileNames.add("levels/level5.txt");
		this.fileNames.add("levels/winner.txt");
		this.levelCounter = 1;
	}

	
	// adds hero to the game
	public void addHero() {
		this.hero = new Hero(550, 850, 0, 0);
	}

	// adds levels to the game
	public void addLevel() {
		for (int i = 0; i < fileNames.size(); i++) {
			Levels level = new Levels(fileNames.get(i), this.hero);
			this.levels.add(level);
		}
	}
	
	// returns the hero
	public Hero getHero() {
		return this.hero;
	}

	// switches between levels
	public void changeLevel(int i) {
		if (levelCounter < this.levels.size() - 1 && i > 0) {
			levelCounter += i;
			this.levels.get(levelCounter).restartPosition();
		}
		if (levelCounter > 0 && i < 0) {
			levelCounter += i;
			this.levels.get(levelCounter).restartPosition();
		}
	}

	// paints each level and the hero
	@Override
	protected void paintComponent(Graphics g) {
		
		this.g2 = (Graphics2D) g;
		this.levels.get(levelCounter).drawOn(g2);
		
		this.hero.drawOn(g2, this, "Levels/hero.png");
	}

	// tracks the score, lives, and movement of all the objects
	public void update() {
		this.scoreLabel.setText("Score: " + this.hero.score);
		this.livesLabel.setText("Lives: " + this.hero.lives);
		if (this.hero.lives == 0) {
			this.g2.setColor(Color.RED);
			this.g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		this.hero.update(this.getSize(), this.levels.get(levelCounter));
		this.levels.get(levelCounter).update(this.getSize());
		
		if(this.levels.get(levelCounter).getMonsterSize() == 0 
				&& this.levels.get(levelCounter).getShootingMonsterSize() == 0
				&& this.levels.get(levelCounter).getEggSize() == 0) {
			changeLevel(1);
		}
		if(this.hero.lives == 0) {
			this.levelCounter = 0;
		}
	}
}

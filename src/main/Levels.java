package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Levels class
 * 
 * creates the levels and stores the data of each object in the joust game
 * controls when events occur in the game
 * 
 * @author Logan Bryant & Phil Theryan
 *
 */

public class Levels {

	char[][] level = new char[24][20];
	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	private ArrayList<Monster> monsters = new ArrayList<Monster>();
	private ArrayList<ShootingMonster> shootingMonsters = new ArrayList<ShootingMonster>();
	private ArrayList<Projectile> bullets = new ArrayList<Projectile>();
	private ArrayList<Egg> eggs = new ArrayList<Egg>();
	private Hero hero;
	private int levelHeroX;
	private int levelHeroY;
	

	// levels constructor
	public Levels(String fileName, Hero hero) {
		Scanner scanner;
		this.hero = hero;
		try {
			scanner = new Scanner(new File(fileName));

			int i = 0;
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				for (int j = 0; j < line.length(); j++) {
					this.level[j][i] = line.charAt(j);
				}
				i++;
			}

		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
			e.printStackTrace();
			return;
		}
		scanner.close();
		makeLevel();
	}

	// makes each level
	public void makeLevel() {
		for (int i = 0; i < level.length; i++) {
			for (int j = 0; j < level[i].length; j++) {
				if (this.level[i][j] == '_') {
					this.tiles.add(new Tile(50 * i, 50 * j));
				}
				if (this.level[i][j] == 'M') {
					Monster m = new Monster(50 * i, 50 * j, 5, 0, 25, Color.BLUE);
					m.startX = 50 * i;
					m.startY = 50 * j;
					this.monsters.add(m);
				}
				if (this.level[i][j] == 'S') {
					ShootingMonster sm = new ShootingMonster(50 * i, 50 * j, -5, 5, 25, Color.GREEN);
					sm.startX = 50 * i;
					sm.startY = 50 * j;
					this.shootingMonsters.add(sm);
				}
				if (this.level[i][j] == 'H') {
					this.levelHeroX = i * 50;
					this.levelHeroY = j * 50;
				}
			}
		}
	}

	// draws each level and its components
	public void drawOn(Graphics2D g) {
		for (Tile t : this.tiles) {
			t.drawOn(g);
		}
		for (Monster monsters : this.monsters) {
			monsters.drawOn(g);
		}
		for (ShootingMonster smonsters : this.shootingMonsters) {
			smonsters.drawOn(g);
		}
		for (Projectile bullet : this.bullets)
			bullet.drawOn(g);

		for (Egg egg : this.eggs)
			egg.drawOn(g);

	}

	// returns the ArrayList of tiles
	public ArrayList<Tile> getTiles() {
		return this.tiles;
	}

	// returns the ArrayList of monsters
	public ArrayList<Monster> getMonsters() {
		return this.monsters;
	}

	// returns the ArrayList of ShootingMonsters
	public ArrayList<ShootingMonster> getShootingMonsters() {
		return this.shootingMonsters;
	}

	// updates all the components in the game with their movement
	public void update(Dimension size) {
		for (int i = 0; i < this.monsters.size(); i++) {
			this.monsters.get(i).update(size, this);
		}
		for (int i = 0; i < this.shootingMonsters.size(); i++) {
			this.shootingMonsters.get(i).update(size, this);
			if (shootingMonsters.get(i).shoot()) {
				this.bullets.add(new Projectile(shootingMonsters.get(i).xPos, shootingMonsters.get(i).yPos, 5, 5, 5,
						Color.CYAN));
			}
		}
		for (int i = 0; i < this.bullets.size(); i++) {
			this.bullets.get(i).update(size, this);
		}
		for (int i = 0; i < this.eggs.size(); i++) {
			this.eggs.get(i).update(size, this);
		}
		collidesWith();
	}

	// removes each monster if the hero wins and reposition everyone if the hero
	// dies
	public void collidesWith() {
		ArrayList<JoustObjects> removed = new ArrayList<JoustObjects>();
		ArrayList<JoustObjects> gameObjects = new ArrayList<JoustObjects>();
		gameObjects.addAll(this.monsters);
		gameObjects.addAll(this.shootingMonsters);
		gameObjects.addAll(this.bullets);
		if (this.monsters.size() >= 1) {
			for (Monster m : this.monsters) {

				if (m.overlapsWith(this.hero)) {
					removed.add(m);
					hero.score += 5;
					eggs.add(new Egg(m.xPos, m.yPos));
				}
			}
		}
		if (this.shootingMonsters.size() >= 1) {
			for (ShootingMonster s : this.shootingMonsters) {
				if (s.overlapsWith(this.hero)) {
					removed.add(s);
					hero.score += 10;
					eggs.add(new Egg(s.xPos, s.yPos));
				}
			}
		}

		if (bullets.size() >= 1) {
			for (Projectile bullet : this.bullets) {
				if (bullet.overlapsWith(this.hero)) {
					removed.add(bullet);
				}
			}
		}
		if (eggs.size() >= 1) {
			for (Egg egg : this.eggs) {
				if (egg.overlapsWith(this.hero)) {
					removed.add(egg);
					hero.score += 2;
				}
				if (egg.spawnMonster()) {
					removed.add(egg);
					this.monsters.add(new Monster(egg.xPos, egg.yPos, 5, 0, 25, Color.RED));
				}
			}
		}

		if (this.hero.shouldRemove) {
			this.hero.lives--;
			restartPosition();
		}

		if (removed.size() >= 1) {
			for (int i = 0; i < removed.size(); i++) {
				this.monsters.remove(removed.get(i));
				this.shootingMonsters.remove(removed.get(i));
				this.bullets.remove(removed.get(i));
				this.eggs.remove(removed.get(i));
			}
		}
	}

	// restarts all the objects to their originial position
	public void restartPosition() {
		this.hero.setPosition(levelHeroX, levelHeroY);

		for (Monster m : this.monsters) {
			m.setPosition(m.startX, m.startY);
		}

		for (ShootingMonster sm : this.shootingMonsters) {
			sm.setPosition(sm.startX, sm.startY);
		}
		this.bullets.clear();

	}

	// returns the size of the monster ArrayList
	public int getMonsterSize() {
		return this.monsters.size();
	}

	// returns the size of the shooting monster ArrayList
	public int getShootingMonsterSize() {
		return this.shootingMonsters.size();
	}

	// returns the size of the eggs ArrayList
	public int getEggSize() {
		return this.eggs.size();
	}

}

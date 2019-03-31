package co.megadodo.mackycheese.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import co.megadodo.mackycheese.framework.Entity;
import co.megadodo.mackycheese.framework.Game;
import co.megadodo.mackycheese.framework.GameRunner;
import co.megadodo.mackycheese.framework.GameSettings;
import co.megadodo.mackycheese.framework.Utils;

@SuppressWarnings("serial")
public class MyGame extends Game {
	public static final int MOVE_TIME = 4;
	
	public static void main(String[] args) {
		GameRunner runner = new GameRunner("Game");
		
		runner.run(new MyGame());
	}
	
	public World w;
	
	
	public MyGame() {
		long seed = (long) (Math.random() * Long.MAX_VALUE);
		Utils.seedRandom(seed);
		w = new World(Generator.generate(seed, GameSettings.gameW/32, GameSettings.gameH/32), seed);
		this.addEntity(new EntityPlayer(this, 2, 2));
	}
	int randTileX() {
		return Utils.randInt(12, GameSettings.gameW/32);
	}
	int randTileY() {
		return Utils.randInt(12, GameSettings.gameH/32);
	}
	void spawnMonster() {
		int x = randTileX();
		int y = randTileY();
		while(w.tileAt(x, y).isLiquid() && x > 10 && y > 10 && Math.abs(x-y) > 10) {
			x = randTileX();
			y = randTileY();
		}
		this.addEntity(new EntityMonster(this, x, y));
	}
	
	public void update() {
		super.update();
		if(Utils.randInt(1, 1)==1 && this.getEntByName("player") != null && this.entities.size() < 75) // one-millionth
		{
			for(int i = 0; i < 10; i++)
				spawnMonster();
		}
		Stats.time++;
	}

	int distance(int x1, int y1, int x2, int y2) {
		return (int) Math.round(Math.sqrt(square(x2-x1) + square(y2-y1)));
	}
	int square(int x) {
		return x*x;
	}
	
	public void drawWorld(Graphics2D g2d) {
		for(int x = 0; x < w.width(); x++) {
			for(int y = 0; y < w.height(); y++) {
				Tile t = w.tileAt(x, y);
				Image img = Toolkit.getDefaultToolkit().getImage(t.tilesheet());
				Utils.drawSectionOfImage(g2d, img, x*32, y*32, 32, 32, t.x(), t.y(), 32, 32, this);
			}
		}
	}
	
	public void drawShading(Graphics2D g2d) {
		int interval = 32;
		int posX = getEntByName("player").getPosX();
		int posY = getEntByName("player").getPosY();
		for(int i = 0; i < GameSettings.gameW; i+=interval) {
			for(int j = 0; j < GameSettings.gameH; j+=interval) {
				int distance = distance(i,j,posX,posY);
				if(distance > 0) {
					int n = 1;
//					distance*=2;
					distance = distance / n * n;
					distance = Utils.constrain(0, 255, distance);
					g2d.setColor(new Color(0, 0, 0, distance));
					g2d.fillRect(i, j, interval, interval);
				}
			}
		}
	}
	
	public void drawHealthMeter(Graphics2D g2d) {

		Image lifeMeter = Toolkit.getDefaultToolkit().getImage("mistertiles/lifebar-32x32.png");
		EntityPlayer p = (EntityPlayer) this.getEntByName("player");
		int health = (p == null ? 0 : p.health);
		


		health = 10 - health;
		int lifeBarW = 40;
		int lifeBarH = 40;
		Utils.drawSectionOfImage(g2d, lifeMeter, 0, 0, lifeBarW, lifeBarH, health*32, 0, 32, 32, this);
	}
	
	public void drawStats(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.drawString("Current Life: " + Stats.curLife, 0, 10);
		g2d.drawString("Monsters Killed: " + Stats.monstersKilled, 0, 25);
		g2d.drawString("Time: " + Stats.time, 0, 40);
		g2d.drawString("Score: " + Stats.calculateScore(), 0, 55);
	}
	
	public void drawEntities(Graphics2D g2d) {
		ArrayList<Entity> monsters = this.getAllEntsWithName("monster");
		ArrayList<Entity> bloodsplash = this.getAllEntsWithName("bloodsplash");
		Entity player = this.getEntByName("player");
		
		drawEntities(g2d, bloodsplash);
		drawEntities(g2d, monsters);
		player.draw(g2d);
	}
	
	public void drawEntities(Graphics2D g2d, ArrayList<Entity> ents) {
		for(Entity a : ents) {
			a.draw(g2d);
		}
	}
	
	public void draw(Graphics2D g2d) {
		drawWorld(g2d);
		
		drawEntities(g2d);

		drawShading(g2d);
	
		drawStats(g2d);
		

	}

	
	public void mousePressed(MouseEvent evt)
	{
		
	}
	
	public void mouseDragged(MouseEvent evt)
	{
		mousePressed(evt);
		mouseMoved(evt);
	}
	
	public void mouseMoved(MouseEvent evt)
	{
			
	}
	
	
	void print(String s)
	{
		System.out.println(s);
	}
}

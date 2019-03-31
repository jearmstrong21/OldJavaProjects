
package co.megadodo.newsuperresidentraver.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Game extends JPanel {
	public static int MILLIS_PER_FRAME = 0;
	public static Game inst;
	public static int curFrame = 0;
	public static int W = 0;
	public static int H = 0;
	public static int floorLvl = 300;
	public Entity player;
	public ArrayList<Entity> bullets;
	public ArrayList<Entity> zombies;
	public ArrayList<Entity> spawners;
	public ArrayList<Entity> ornaments;
	public ArrayList<Character> keys;
	// ornament list
	
	public boolean getKey(char c) {
		return keys.contains(c);
	}
	
	public static Image loadImage(String name) {
		return Toolkit.getDefaultToolkit().getImage(name);
	}
	
	public void image(Graphics2D g2d, Image img, int x, int y) {
		g2d.drawImage(img, x, y, this);
	}
	
	public void background(Graphics2D g2d, Color col) {
		g2d.setColor(col);
		g2d.fillRect(0, 0, W, H);
	}
	
	public Game(int w, int h) {
		bullets=new ArrayList<>();
		zombies=new ArrayList<>();
		spawners=new ArrayList<>();
		ornaments=new ArrayList<>();
		keys=new ArrayList<>();
		inst = this;
		W=w;
		H=h;
	}
	
	public void update() {
		curFrame++;
		if(player != null) player.update();
		for(Entity ent : bullets) ent.update();
		for(Entity ent : zombies) ent.update();
		for(Entity ent : spawners) ent.update();
		for(Entity ent : ornaments) ent.update();
	}
	
	@Override
	public final void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		update();
		draw(g2d);
	}
	
	public void draw(Graphics2D g2d) {
		for(Entity ent : ornaments) ent.draw(g2d);
		// spawners don't get drawn
		for(Entity ent : zombies) ent.draw(g2d);
		for(Entity ent : bullets) ent.draw(g2d);
		player.draw(g2d);
	}
	
	public void removeEntity(Entity ent) {
		if(!ent.dead)ent.kill();
		if(ent.equalsEnt(player)) player = null;
		if(bullets.contains(ent)) bullets.remove(ent);
		if(zombies.contains(ent)) zombies.remove(ent);
		if(spawners.contains(ent)) spawners.remove(ent);
		// ornament check
	}
	
}

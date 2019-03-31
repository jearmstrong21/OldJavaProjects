package co.megadodo.newsuperresidentraver;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

import co.megadodo.newsuperresidentraver.engine.Game;
import co.megadodo.newsuperresidentraver.engine.Vector;

public class MyGame extends Game {
	
	public Image imgScanLines;
	public Image imgLightingEffect;

	public MyGame(int w, int h) {
		super(w, h);
		imgScanLines = loadImage("images/scan-lines.png");
		imgLightingEffect = loadImage("images/lighting-effect.png");
		player = new EntityPlayer(new Vector(W/2-85/2, 0), new Vector(85,85));
	}
	
	public void update() {
		super.update(); // update all entities
	}
	
	public void draw(Graphics2D g2d) {
		background(g2d,Color.WHITE);
		image(g2d,imgLightingEffect,0,0);
		image(g2d,imgScanLines,0,0);
		super.draw(g2d);
	}

}

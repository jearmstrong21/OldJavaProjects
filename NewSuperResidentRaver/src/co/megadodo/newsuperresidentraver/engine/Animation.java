package co.megadodo.newsuperresidentraver.engine;

import java.awt.Graphics2D;
import java.awt.Image;

public class Animation {
	
	public Image[] images;
	public String name;
	public int framesPerAnim;
	public int curAnimFrame;
	
	public int frame() {
		return curAnimFrame;
	}
	
	public Animation(int framesPerAnim, String name, String directory, String fileExtension, String... images) {
		this.images = new Image[images.length];
		int i=0;
		for(String s : images) {
			String file = directory + s + fileExtension;
			this.images[i] = Game.loadImage(file);
//			System.out.println("ADD FILE <" + file + ">");
			i++;
		}
		this.name = name;
		this.framesPerAnim = framesPerAnim;
		this.curAnimFrame = 0;
	}
	
	public void nextFrame() {
		curAnimFrame++;
		if(curAnimFrame>=images.length) curAnimFrame=0;
	}
	
	public void update() {
		if(Game.curFrame%framesPerAnim==1) {
			nextFrame();
		}
	}
	
	public void draw(Graphics2D g2d, int x, int y, int w, int h) {
		g2d.drawImage(images[curAnimFrame], x, y, w, h, null);
	}

}

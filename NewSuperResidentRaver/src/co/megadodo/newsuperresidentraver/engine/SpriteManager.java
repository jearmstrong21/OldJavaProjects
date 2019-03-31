package co.megadodo.newsuperresidentraver.engine;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class SpriteManager {

	public ArrayList<Animation> anims;
	public Animation curAnim;
	
	public SpriteManager() {
		curAnim = null;
		anims = new ArrayList<Animation>();
	}
	
	public void addAnim(Animation anim) {
		anims.add(anim);
		if(curAnim == null) curAnim = anim;
	}
	
	public void update() {
		if(curAnim != null) curAnim.update();
	}
	
	public void makeAnim(String name) {
		if(!curAnim.name.equals(name)) setAnim(name);
	}
	
	public void setAnim(String name) {
		for(Animation anim : anims) {
			if(anim.name.equals(name)) {
				curAnim = anim;
				return;
			}
		}
	}
	
	public void draw(Graphics2D g2d, int x, int y, int w, int h) {
		if(curAnim != null) curAnim.draw(g2d, x, y, w, h);
	}
	
}

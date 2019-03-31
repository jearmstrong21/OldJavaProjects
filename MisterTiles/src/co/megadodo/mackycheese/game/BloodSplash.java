package co.megadodo.mackycheese.game;

import java.awt.Graphics2D;

import co.megadodo.mackycheese.framework.Entity;
import co.megadodo.mackycheese.framework.Game;
import co.megadodo.mackycheese.framework.Utils;
import co.megadodo.mackycheese.framework.animation.Animation;
import co.megadodo.mackycheese.framework.animation.AnimationIndex;
import co.megadodo.mackycheese.framework.animation.NamedSheet;
import co.megadodo.mackycheese.framework.animation.SheetAnimation;
import co.megadodo.mackycheese.framework.animation.SpriteManager;

public class BloodSplash extends Entity {

	int curFrame, frameNum;
	public BloodSplash(Game g, int posX, int posY, int frameNum) {
		super(g, posX, posY, 64, 64, "bloodsplash");
		this.spriteManager = new SpriteManager();
		this.name = "bloodsplash";
		
		curFrame = 0;
		this.frameNum = frameNum;
		NamedSheet sheet = new NamedSheet("mistertiles/extras-32x32.png", "sheet");
		Animation anim = new SheetAnimation(32, 32, 10, 1, sheet, "anim", true, 1);
		anim.setFrames(Utils.randInt(6, 8));
		this.spriteManager.addAnim(anim);
		this.spriteManager.startAnim("anim");
		
	}
	
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
		curFrame++;
		this.spriteManager.draw(this.game, g2d, posX, posY, sizeX, sizeY);
		if(curFrame > frameNum) {
			this.kill();
		}
	}
	public void update() {
		super.update();
		this.spriteManager.update(game.getFrames());
	}

}

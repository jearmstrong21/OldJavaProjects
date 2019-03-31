package co.megadodo.mackycheese.game;

import java.awt.Graphics2D;

import co.megadodo.mackycheese.framework.Axis;
import co.megadodo.mackycheese.framework.DW;
import co.megadodo.mackycheese.framework.Entity;
import co.megadodo.mackycheese.framework.EntityType;
import co.megadodo.mackycheese.framework.Game;
import co.megadodo.mackycheese.framework.animation.NamedSheet;
import co.megadodo.mackycheese.framework.animation.SheetAnimation;
import co.megadodo.mackycheese.framework.animation.SpriteManager;

public class EntityPacman extends Entity {

	String curAnim	= "I";
	String nextAnim	= "I";
	
	int spriteW = 32;
	int spriteH = 32;
	public EntityPacman(Game g) {
//		super(g, 244-34, 256+8, 32, 32, "pacman");
		super(g,16,16,32,32,"pacman");
		this.type = EntityType.createEntityType("pacman-entity");
		this.spriteManager = new SpriteManager();
		NamedSheet sheet = new NamedSheet("graphics/sprites.png", "sprites.png");
		int animUpdates = 3;
		SheetAnimation U = new SheetAnimation(spriteW, spriteH, 8, 8, sheet, "U", true, animUpdates);	U.setFrames( 0,  1,  2);	// UP
		SheetAnimation D = new SheetAnimation(spriteW, spriteH, 8, 8, sheet, "D", true, animUpdates);	D.setFrames( 3,  4,  5);	// DOWN
		SheetAnimation L = new SheetAnimation(spriteW, spriteH, 8, 8, sheet, "L", true, animUpdates);	L.setFrames( 6,  7,  8);	// LEFT
		SheetAnimation R = new SheetAnimation(spriteW, spriteH, 8, 8, sheet, "R", true, animUpdates);	R.setFrames( 9, 10, 11);	// RIGHT
		SheetAnimation I = new SheetAnimation(spriteW, spriteH, 8, 8, sheet, "I", true, animUpdates);	I.setFrames(11, 11, 11);	// IDLE
		this.spriteManager.addAnim(U);
		this.spriteManager.addAnim(D);
		this.spriteManager.addAnim(L);
		this.spriteManager.addAnim(R);
		this.spriteManager.addAnim(I);
		this.spriteManager.startAnim(this.curAnim);
	}
	
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
		this.spriteManager.draw(this.game, g2d, posX, posY, spriteW, spriteH);
	}
	
	public void update() {
		super.update();
		this.spriteManager.update(this.game.getFrames());
		if(this.keysDown.contains('a')) {
			nextAnim = "L";
		} else if(this.keysDown.contains('d')) {
			nextAnim = "R";
		} else if(this.keysDown.contains('w')) {
			nextAnim = "U";
		} else if(this.keysDown.contains('s')) {
			nextAnim = "D";
		}
		if(!curAnim.equals(nextAnim)) {
			this.spriteManager.startAnim(nextAnim);
			curAnim = nextAnim;
		}
		
		int speed = 4;
		switch(curAnim) {
			case "L": this.dirX = -speed; this.dirY =  0    ; break;
			case "R": this.dirX =  speed; this.dirY =  0    ; break;
			case "U": this.dirX =  0    ; this.dirY = -speed; break;
			case "D": this.dirX =  0    ; this.dirY =  speed; break;
		}
	}
	
	@Override
	public void collide(Entity other, Axis ax) {
		super.collide(other, ax);
	}

}

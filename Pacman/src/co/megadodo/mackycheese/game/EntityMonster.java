package co.megadodo.mackycheese.game;

import java.awt.Graphics2D;
import java.awt.Image;

import co.megadodo.mackycheese.framework.Entity;
import co.megadodo.mackycheese.framework.EntityType;
import co.megadodo.mackycheese.framework.Game;
import co.megadodo.mackycheese.framework.animation.NamedSheet;
import co.megadodo.mackycheese.framework.animation.SheetAnimation;
import co.megadodo.mackycheese.framework.animation.SpriteManager;

public abstract class EntityMonster extends Entity {

	int timerIndex = 0; // 0 = 7sec scatter mode, 1 = 30sec chase mode, 2=7sec scatter mode, >=3 is infinite chase mode
	Dir curDir;
	int scx, scy;
	public EntityMonster(Game g) {
		super(g, 0, 0, 32, 32, "monster");
		this.type = EntityType.createEntityType("pacman-entity");
	}
	
	protected void setup(int posX, int posY, int firstFrame, int scx, int scy) { // scx = scatter corner x, scy = scatter corner y
		this.posX = posX;
		this.posY = posY;
		this.scx = scx;
		this.scy = scy;
		
		NamedSheet sheet = new NamedSheet("graphics/sprites.png", "sprites.png");
		int animUpdates = 5;
		SheetAnimation U = new SheetAnimation(32, 32, 8, 8, sheet, "U", true, animUpdates); U.setFrames(firstFrame+16,firstFrame+17);	// UP
		SheetAnimation D = new SheetAnimation(32, 32, 8, 8, sheet, "D", true, animUpdates); D.setFrames(firstFrame+18,firstFrame+19);	// DOWN
		SheetAnimation L = new SheetAnimation(32, 32, 8, 8, sheet, "L", true, animUpdates); L.setFrames(firstFrame+20,firstFrame+21);	// LEFT
		SheetAnimation R = new SheetAnimation(32, 32, 8, 8, sheet, "R", true, animUpdates);	R.setFrames(firstFrame+22,firstFrame+23);	// RIGHT
		SheetAnimation I = new SheetAnimation(32, 32, 8, 8, sheet, "I", true, animUpdates); I.setFrames(48);							// IDLE, CHERRY
		SheetAnimation B = new SheetAnimation(32, 32, 8, 8, sheet, "B", true, animUpdates); B.setFrames(12,13);							// BLUE
		SheetAnimation F = new SheetAnimation(32, 32, 8, 8, sheet, "F", true, animUpdates); F.setFrames(12,13,14,15);					// FLASH
		
		this.spriteManager = new SpriteManager();
		this.spriteManager.addAnim(U);
		this.spriteManager.addAnim(D);
		this.spriteManager.addAnim(L);
		this.spriteManager.addAnim(R);
		this.spriteManager.addAnim(I);
		this.spriteManager.addAnim(B);
		this.spriteManager.addAnim(F);
		
		this.spriteManager.startAnim("I");
	}
	class pos {int x; int y; pos(int x, int y) { this.x = x; this.y = y; }}

	public abstract pos calcTargetTileScared();
	public abstract pos calcTargetTileChase();
	public pos calcTargetTileScatter() {
		return new pos(scx, scy);
	}
	
	public void update() {
		super.update();
		this.spriteManager.update(this.game.getFrames());
		Dir d;
		

	}
	
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
		this.spriteManager.draw(this.game, g2d, posX, posY, 32, 32);
	}
	
	public enum Dir {
		L,R,U,D;
	}
	
	public enum Mode {
		FRIGHTENED, CHASE, SCATTER;
	}
	

}

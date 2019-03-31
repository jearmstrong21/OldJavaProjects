package co.megadodo.mackycheese.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import co.megadodo.mackycheese.framework.Axis;
import co.megadodo.mackycheese.framework.DW;
import co.megadodo.mackycheese.framework.Entity;
import co.megadodo.mackycheese.framework.EntityType;
import co.megadodo.mackycheese.framework.Game;
import co.megadodo.mackycheese.framework.GameSettings;
import co.megadodo.mackycheese.framework.Utils;
import co.megadodo.mackycheese.framework.animation.Animation;
import co.megadodo.mackycheese.framework.animation.NamedSheet;
import co.megadodo.mackycheese.framework.animation.SheetAnimation;
import co.megadodo.mackycheese.framework.animation.SpriteManager;
import co.megadodo.mackycheese.game.EntityMonster.Dir;

public class EntityPlayer extends Entity {
	public int health = 10;
	int lastPosX, lastPosY;
	enum Dir {
		L, R, U, D;
	}
	Dir curDir = Dir.R;
	public EntityPlayer(Game g, int tileX, int tileY) {
		super(g, tileX*32, tileY*32, 32, 32, "player");
		
		this.type = EntityType.createEntityType("type", "type");
		this.spriteManager = new SpriteManager();
		Image imgBig = Toolkit.getDefaultToolkit().getImage("mistertiles/characters-big-64x64.png");
		Image imgSmall = Toolkit.getDefaultToolkit().getImage("mistertiles/characters-32x32.png");
		NamedSheet sheetBig = new NamedSheet(imgBig, "sheetBig");
		NamedSheet sheetSmall = new NamedSheet(imgSmall, "sheetSmall");
		Animation animBig = new SheetAnimation(64, 64, 10, 1, sheetBig, "animBig", true, 1);
		Animation animSmall = new SheetAnimation(32, 32, 10, 1, sheetSmall, "animSmall", true, 1);
		animBig.setFrames(0);
		animSmall.setFrames(0);
		this.spriteManager.addAnim(animBig);
		this.spriteManager.addAnim(animSmall);
		this.spriteManager.startAnim("animSmall");
		lastPosX = posX;
		lastPosY = posY;
	}
	int distance(int x1, int y1, int x2, int y2) {
		return (int) Math.round(Math.sqrt(square(x2-x1) + square(y2-y1)));
	}
	int square(int x) {
		return x*x;
	}
	public void draw(Graphics2D g2d) {
		this.spriteManager.draw(this.game, g2d, posX, posY, 40, 40);
		super.draw(g2d);


		Image lifeMeter = Toolkit.getDefaultToolkit().getImage("mistertiles/lifebar-32x32.png");
		
		int lifeBarW = sizeX;
		int lifeBarH = sizeY;
		Utils.drawSectionOfImage(g2d, lifeMeter, posX, posY, lifeBarW, lifeBarH, (10-health)*32, 0, 32, 32, this.game);
	}
	
	public boolean canMoveToPos(int x, int y) {
		int tileX = x/32;
		int tileY = y/32;
		boolean canMove = ((MyGame)this.game).w.tileAt(tileX, tileY).isSolid();
		
		if(canMove) {
			DW.log("CAN MOVE TO TILE " + tileX + " " + tileY);
		} else {
			DW.log("CANNOT MOVE TO TILE " + tileX + " " + tileY);
		}
		
		return canMove;
	}
	public void update () {
		lastPosX = posX;
		lastPosY = posY;
		if(this.health < 1) this.kill();
		posX = Utils.constrain(0, GameSettings.gameW, posX);
		posY = Utils.constrain(0, GameSettings.gameH, posY);
		this.spriteManager.update(game.getFrames());

		
		ArrayList<Character> keys = this.keysDown;
		if(keys.contains('k')) this.kill();
		if(this.game.getFrames() % MyGame.MOVE_TIME == 0) {
			if(keys.contains('w')) curDir = Dir.U;
			if(keys.contains('s')) curDir = Dir.D;
			if(keys.contains('a')) curDir = Dir.L;
			if(keys.contains('d')) curDir = Dir.R;
			
			
			if(keys.contains('w') && canMoveToPos(posX, posY-32)) this.posY-=32;
			if(keys.contains('s') && canMoveToPos(posX, posY+32)) this.posY+=32;
			if(keys.contains('a') && canMoveToPos(posX-32, posY)) this.posX-=32;
			if(keys.contains('d') && canMoveToPos(posX+32, posY)) this.posX+=32;
		}
		MyGame g = (MyGame) game;
		if(g.w.tileAt(posX/32, posY/32) == Tile.TAN_OUTSET_1) {
			g.entities.clear();
			long seed = (long)(Long.MAX_VALUE * Math.random());
			Utils.seedRandom(seed);
			g.w = new World(Generator.generate(seed, GameSettings.gameW/32, GameSettings.gameH/32), seed);
			Stats.reset();
			this.kill();
		}
	}
	
	public void collide(Entity other, Axis ax) {
		posX = lastPosX;
		posY = lastPosY;
	}
	
	public void kill() {
		this.game.addEntity(new BloodSplash(game, posX-16, posY-16, 500));
		this.game.addEntity(new EntityPlayer(this.game, 2, 2));
		Stats.curLife++;
		super.kill();
//		System.out.println("Time: " + Stats.time);
//		System.exit(0);
	}

}

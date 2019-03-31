package co.megadodo.mackycheese.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import co.megadodo.mackycheese.framework.Axis;
import co.megadodo.mackycheese.framework.Entity;
import co.megadodo.mackycheese.framework.EntityType;
import co.megadodo.mackycheese.framework.Game;
import co.megadodo.mackycheese.framework.GameSettings;
import co.megadodo.mackycheese.framework.Utils;
import co.megadodo.mackycheese.framework.animation.NamedSheet;
import co.megadodo.mackycheese.framework.animation.SheetAnimation;
import co.megadodo.mackycheese.framework.animation.SpriteManager;

public class EntityMonster extends Entity {
	public int health = 0;
	int lastPosX, lastPosY;
	int aiFrame = 4;
	int monsterType;
	enum Dir {
		L,R,U,D;
		// L=left R=right U=up D=down
		
	}
	Dir randDir() {
		return Dir.values()[Utils.randInt(0, 3)];
	}
	public EntityMonster(Game g, int tileX, int tileY) {
		super(g, tileY*32, tileY*32, 32, 32, "monster");
		this.health = Utils.randInt(7, 10);
		this.type = EntityType.createEntityType("type", "type");
		this.spriteManager = new SpriteManager();
		NamedSheet sheet = new NamedSheet(Toolkit.getDefaultToolkit().getImage("mistertiles/characters-32x32.png"), "sheet");
		SheetAnimation anim = new SheetAnimation(32, 32, 10, 1, sheet, "idle", true, 1);
		monsterType = Utils.randInt(1,9);
		anim.setFrames(monsterType);
		this.spriteManager.addAnim(anim);
		this.spriteManager.startAnim("idle");
		lastPosX = posX;
		lastPosY = posY;
	}
	public void draw(Graphics2D g2d) {
		this.spriteManager.draw(this.game, g2d, posX, posY, 32, 32);
		super.draw(g2d);


		Image lifeMeter = Toolkit.getDefaultToolkit().getImage("mistertiles/lifebar-32x32.png");
		
		int lifeBarW = sizeX;
		int lifeBarH = sizeY;
		Utils.drawSectionOfImage(g2d, lifeMeter, posX, posY, lifeBarW, lifeBarH, (10-health)*32, 0, 32, 32, this.game);
	}
	public void update() {
		super.update();
		if(health < 1) this.kill();
		if(game.getFrames() % aiFrame == 0) {
			
		} else {
			return;
		}
		lastPosX = posX;
		lastPosY = posY;
		if(posX < 0) posX = 0;
		if(posY < 0) posY = 0;
		if(posX > GameSettings.gameW) posX = GameSettings.gameW;
		if(posY > GameSettings.gameH) posY = GameSettings.gameH;
		
		Dir playerDir = findDirectionToMove();
		moveInDirection(playerDir);
	}
	void moveInDirection(Dir d) {
		switch(d) {
			case L: if(canMoveTo(d)) posX-=32; break;
			case R: if(canMoveTo(d)) posX+=32; break;
			case U: if(canMoveTo(d)) posY-=32; break;
			case D: if(canMoveTo(d)) posY+=32; break;
		}
	}
	
	public void collide(Entity other, Axis ax) {
		posX = lastPosX;
		posY = lastPosY;
		if(health <= 1) {
			Stats.monstersKilled++;
		}
		if(other instanceof EntityPlayer) {
			((EntityPlayer) other).health--;
		}
		this.health--;
		
	}
	public boolean canMoveTo(int tileX,int tileY) {
		MyGame g = (MyGame) game;
		Tile t = g.w.tileAt(tileX, tileY);
		if(t.isLiquid() || t == Tile.GREEN_OUTSET_1 || t == Tile.GREEN_OUTSET_2 || t == Tile.GREEN_OUTSET_3 || t == Tile.GREEN_OUTSET_4) {
			return false;
		}
		return true;
	}
	public boolean canMoveTo(Dir d) {
		int tx = posX/32;
		int ty = posY/32;
		
		switch(d) {
			case L: return canMoveTo( tx-1 , ty   );
			case R: return canMoveTo( tx+1 , ty   );
			case U: return canMoveTo( tx   , ty-1 );
			case D: return canMoveTo( tx   , ty+1 );
		}
		
		return false;
	}
	int square(int x) {
		return x*x;
	}	
	int distance(int x1, int y1, int x2, int y2) {
		return (int) Math.round(Math.sqrt(square(x2-x1) + square(y2-y1)));
	}
	int minIndex(int[] lst, Dir[] d) {
		int ind = 0;
		for(int i = 1; i < lst.length; i++) {
			if(lst[i] < lst[ind] && canMoveTo(d[i])) {
				ind = i;
			}
		}
		return ind;
	}
	Dir findDirectionToMove() {
		int playerX = game.getEntByName("player").getPosX()/32;
		int playerY = game.getEntByName("player").getPosY()/32;
		int tileX = posX/32;
		int tileY = posY/32;
		
		int distance = distance(playerX, playerY, tileX, tileY);
		int distanceL = distance(playerX, playerY, tileX-1, tileY);
		int distanceR = distance(playerX, playerY, tileX+1, tileY);
		int distanceU = distance(playerX, playerY, tileX, tileY-1);
		int distanceD = distance(playerX, playerY, tileX, tileY+1);
		
		int[] distances = new int[] { distanceL, distanceR, distanceU, distanceD };
		Dir[] dirs = new Dir[] { Dir.L, Dir.R, Dir.U, Dir.D };
		boolean[] possible = new boolean[4];
		int possibilities = 0;
		for(int i = 0; i < 4; i++) {
			possible[i] = canMoveTo(dirs[i]);
		}
		for(int i = 0; i < 4; i++) {
			if(possible[i]) {
				possibilities++;
			}
		}
		switch(possibilities) {
			case 0: return dirs[Utils.randInt(0, 3)];
			case 1: for(int i = 0; i < 4; i++) if(possible[i]) return dirs[i];
			case 2: 
			case 3:
			case 4: return dirs[minIndex(distances, dirs)];
		}
		
		return dirs[minIndex(distances, dirs)];
	}
	
	public void kill() {
		super.kill();
		this.game.addEntity(new BloodSplash(game, posX-16 + Utils.randInt(-10, 10), posY-16 + Utils.randInt(-10, 10), 500));
	}
}

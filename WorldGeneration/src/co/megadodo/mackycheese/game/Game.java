package co.megadodo.mackycheese.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import co.megadodo.mackycheese.framework.Utils;
import co.megadodo.mackycheese.game.terrain.blocks.Block;
import co.megadodo.mackycheese.game.terrain.generator.WorldGenerator;
import co.megadodo.mackycheese.game.terrain.generator.WorldGeneratorLeftToRight;
import co.megadodo.mackycheese.game.terrain.generator.WorldGeneratorTopDown;

public class Game extends co.megadodo.mackycheese.framework.Game {

//	public static final int BLOCK_W = 64;
//	public static final int BLOCK_H = 53;
	static int s = 10;
	public static final int BLOCK_W = 160*16/s;
	public static final int BLOCK_H = 160*16/s;
	public static final int TILE_W = 16;
	public static final int TILE_H = 16;
	
//	public int CUR_TILE_X = BLOCK_W/2;
	public int CUR_TILE_X = 0;
//	public int CUR_TILE_Y = BLOCK_H/2;
	public int CUR_TILE_Y = 0;
	
	public static double DEST_SCALE = 0.0625*s;
//	public static double DEST_SCALE = 1;
//	public static double DEST_SCALE = 0.5;
	public static final Image TILE_SHEET = Toolkit.getDefaultToolkit().getImage("graphics/terrain-16x16.png");
	
	void drawTile(int destX, int destY, Block tileType, Graphics2D g2d) {
		Utils.drawSectionOfImage(g2d, TILE_SHEET, (int)Math.round(destX*DEST_SCALE), (int)Math.round(destY*DEST_SCALE), (int)Math.round(TILE_W*DEST_SCALE), (int)Math.round(TILE_H*DEST_SCALE), tileType.y * TILE_W, tileType.x * TILE_H, TILE_W, TILE_H, this);
	}
	
	Block[][] world;
	int gen = 0;
	WorldGenerator generator;
	
	public Game() {
		super();
//		generator = new WorldGeneratorTopDown();
		generator = new WorldGeneratorLeftToRight();
		if(generator instanceof WorldGeneratorTopDown) {
			((WorldGeneratorTopDown)generator).generateWorld(BLOCK_W, BLOCK_H);
			world = ((WorldGeneratorTopDown)generator).genN(0, BLOCK_W, BLOCK_H);
//			gen = ((WorldGeneratorTopDown) generator).CYCLES-1;
		} else if(generator instanceof WorldGeneratorLeftToRight) {
			WorldGeneratorLeftToRight wgltr = ((WorldGeneratorLeftToRight)generator);
//			world = wgltr.generateWorld(BLOCK_W, BLOCK_H, (int)(Math.random()*Integer.MAX_VALUE));
			world = new Block[BLOCK_W][BLOCK_H];
			for(int x = 0; x < BLOCK_W; x++) {
				for(int y = 0; y < BLOCK_H; y++) {
//					int rand = (int)(Math.random() * 256);
//					world[x][y] = Block.values()[rand];
					world[x][y] = Block.DIRT;
				}
			}
		}
	}
	
	public void update() {
		super.update();
		if(frames < 5) {
			System.out.print(frames + " ");
			return;
		}
		if(generator instanceof WorldGeneratorTopDown) {
			WorldGeneratorTopDown wgtd = (WorldGeneratorTopDown) generator;
			if(frames > 3 && this.frames % 1 == 0 && gen < wgtd.CYCLES) {
				System.out.println("ADVANCE TO GEN " + gen);
				gen++;
				world = wgtd.genN(gen,BLOCK_W,BLOCK_H);
			}
		}
		 else if(generator instanceof WorldGeneratorLeftToRight) {
			WorldGeneratorLeftToRight wgltr =(WorldGeneratorLeftToRight) generator;
			if(frames == 11) {
				long startTime,endTime,diffTime;
				startTime = System.currentTimeMillis();
				world = wgltr.generateWorld(BLOCK_W, BLOCK_H);
				endTime = System.currentTimeMillis();
				diffTime = endTime-startTime;
				System.out.println("MILLIS: " + diffTime);
			}
			if(frames > 10 && this.frames % 1 == 0 && gen < wgltr.CYCLES) {
				System.out.println("ADVANCE TO GEN " + gen);
				if(BLOCK_W > 100) gen+=50;
				else gen++;
				
				world = wgltr.genN(gen, BLOCK_W, BLOCK_H);
			}
		}
		
	}

	@Override
	public void draw(Graphics2D g2d) {
		
		for(int i = CUR_TILE_X; i < CUR_TILE_X + BLOCK_W && i < BLOCK_W; i++) {
			for(int j = CUR_TILE_Y; j < CUR_TILE_Y + BLOCK_H && j < BLOCK_H; j++) {
				if(world[i][j] == Block.EMPTY1) continue;
//				if(i*j%2==0)continue;
				drawTile( (i-CUR_TILE_X)*TILE_W, (j-CUR_TILE_Y)*TILE_H, world[i][j], g2d);
//				drawTile(i-CUR_TILE_X,j-CUR_TILE_Y, world[i][j], g2d);
			}
		}
	}
	
	void limitTileXY() {
		CUR_TILE_X = Utils.constrain(0, BLOCK_W-1, CUR_TILE_X);
		CUR_TILE_Y = Utils.constrain(0, BLOCK_H-1, CUR_TILE_Y);
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		char key = keyEvent.getKeyChar();
		int  keyCode = keyEvent.getKeyCode();
		
		key = Character.toUpperCase(key);
		int scaleScalar = 10;
		int TILE_DIFF = 10;
		switch(key) {
			case 'Z':
				DEST_SCALE*=2;
				break;
			case 'X':
				DEST_SCALE/=2;
				break;
			case 'W':
				CUR_TILE_Y-=TILE_DIFF;
				break;
			case 'A':
				CUR_TILE_X-=TILE_DIFF;
				break;
			case 'S':
				CUR_TILE_Y+=TILE_DIFF;
				break;
			case 'D':
				CUR_TILE_X+=TILE_DIFF;
				break;
		}
		limitTileXY();
	}
}

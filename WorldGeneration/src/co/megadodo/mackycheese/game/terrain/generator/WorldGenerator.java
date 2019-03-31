package co.megadodo.mackycheese.game.terrain.generator;

import co.megadodo.mackycheese.game.terrain.blocks.Block;

public abstract class WorldGenerator {
	public Block[][] defaultWorld(int w, int h) {
		Block[][] world = new Block[w][h];
		
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				world[i][j] = Block.EMPTY1;
			}
		}
		return world;
	}
	public abstract Block[][] generateWorld(int w, int h);
}

package co.megadodo.mackycheese.game.terrain.generator;

import co.megadodo.mackycheese.game.noise.PerlinNoiseGenerator;
import co.megadodo.mackycheese.game.noise.SimplexNoise;
import co.megadodo.mackycheese.game.terrain.blocks.Block;

public class WorldGeneratorTopDown extends WorldGenerator {

	public int CYCLES = 100;
	double[][][] matrix;
	int sqrt(int i) {
		return (int) Math.sqrt(i);
	}
	int sqre(int i) {
		return i*i;
	}
	int dist(int x1, int y1, int x2, int y2) {
		return sqrt( sqre(x1-x2) + sqre(y1-y2));
	}
	void generateWorld1(int w, int h) {
		Block[][] world = defaultWorld(w,h);
		PerlinNoiseGenerator perlin = new PerlinNoiseGenerator();
		 matrix = new double[w][h][CYCLES+1];
		int[] occurence = new int[4];
		for(int i = 0; i < w; i++) {
			for(int j = 0; j < h; j++) {
				matrix[i][j][0] = Math.random();
				double a = 1.25;
				double b = a/1.2;
				if(matrix[i][j][0] <= 0.5)matrix[i][j][0]/=a;
				else matrix[i][j][0]*=b;
			}
		}
		System.out.println(occurence[0] + " " + occurence[1] + " " + occurence[2] + " " + occurence[3]);
		
		for(int z = 1; z <= CYCLES; z++) {
			System.out.println("CYCLE " + z);
			for(int x = 0; x < w; x++) {
				for(int y = 0; y < h; y++) {
					boolean xMore0 = x > 0;
					boolean yMore0 = y > 0;
					boolean xLessW = x < w-1;
					boolean yLessH = y < h-1;
//					double numN = 1;
//					double num = matrix[x][y][z];
					double numN = 1, num = matrix[x][y][z-1];
					
					// xMore   yMore
					// xLess   yLess
					
					if(xMore0 && yMore0) {
						num+=matrix[x-1][y-1][z-1];
						numN++;
					}
					if(xMore0 && yLessH) {
						num+=matrix[x-1][y+1][z-1];
						numN++;
					}
					
					if(xLessW && yMore0) {
						num+=matrix[x+1][y-1][z-1];
						numN++;
					}
					
					if(xLessW && yLessH) {
						num+=matrix[x+1][y+1][z-1];
						numN++;
					}
					
					if(xMore0) {
						num+=matrix[x-1][y][z-1];
						numN++;
					}
					if(xLessW) {
						num+=matrix[x+1][y][z-1];
						numN++;
					}
					
					if(yMore0) {
						num+=matrix[x][y-1][z-1];
						numN++;
					}
					
					if(yLessH) {
						num+=matrix[x][y+1][z-1];
						numN++;
					}
					if(numN!=0)matrix[x][y][z] = num/numN;
					else { matrix[x][y][z] = 0; }
				}
			}
		}
	}
	
	public Block[][] genN(int n,int w,int h) {
		
		Block[][] world = new Block[w][h];
		if(n >= CYCLES) return genN(CYCLES-1,w,h);

		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				double d = matrix[x][y][n];
//				if(n == 3) world[x][y] = Block.DIRT;
//				if(n == 1) world[x][y] = Block.randomWater();
//				if(n == 2) world[x][y] = Block.SNOW;
				
				if(d < 0.48) world[x][y] = Block.LAPIS_BLOCK;
				else if(d > 0.515) world[x][y] = Block.SNOW;
				else if(d > 0.51) world[x][y] = Block.STONE;
				else if(d > 0.5) world[x][y] = Block.EMERALD_BLOCK;
				else if(d > 0.4) world[x][y] = Block.DIRT;
				else world[x][y] = Block.SAND;

				
			}
		}
		
		
		return world;
	}

	@Override
	public Block[][] generateWorld(int w, int h) {
		generateWorld1(w,h);
		return genN(CYCLES,w,h);
	}

}

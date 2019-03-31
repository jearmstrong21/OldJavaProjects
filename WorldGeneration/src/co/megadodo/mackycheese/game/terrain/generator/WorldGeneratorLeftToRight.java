package co.megadodo.mackycheese.game.terrain.generator;

import co.megadodo.mackycheese.game.terrain.blocks.Block;

public class WorldGeneratorLeftToRight extends WorldGenerator {

	public int CYCLES = 1000;
	public int TREE_GAP = 3;
	public int FAUNA_GAP = 2;
	public double[][] matrix;
	public double[][] stoneLvlMatrix;
	public double[][] treeLvlMatrix;
	
	// TODO: make averaging a matrix a generic function
	
	public void generateTreeLvl(int w, int h) {
		treeLvlMatrix = new double[CYCLES][w];
		int a = 4;
		int b = 5;
		treeLvlMatrix[0][0] = h/a*b;
		treeLvlMatrix[0][w-1] = h/a*b;
		for(int x = 1; x < w-1; x++) {
			treeLvlMatrix[0][x] = Math.random() * h/a*b;
		}
		for(int gen = 1; gen < CYCLES; gen++) {
			for(int x = 1; x < w-1; x++) {
				treeLvlMatrix[gen][x] = treeLvlMatrix[gen-1][x-1] + treeLvlMatrix[gen-1][x] + treeLvlMatrix[gen-1][x+1];
				treeLvlMatrix[gen][x]/=3;
			}
			treeLvlMatrix[gen][0] = treeLvlMatrix[gen-1][0] + treeLvlMatrix[gen-1][1];
			treeLvlMatrix[gen][0]/=2;
			
			treeLvlMatrix[gen][w-1] = treeLvlMatrix[gen-1][w-1] + treeLvlMatrix[gen-1][w-2];
			treeLvlMatrix[gen][w-1]/=2;
		}
	}
	
	public void generateStoneLvl(int w,int h) {
		stoneLvlMatrix = new double[CYCLES][w];
		int a = 3;
		int b = 4;
		for(int x = 0; x < w; x++) {
			stoneLvlMatrix[0][x] = Math.random() * h/a*b;
		}
		stoneLvlMatrix[0][0] = h/a*b;
		stoneLvlMatrix[0][w-1] = h/a*b;
		for(int gen = 1; gen < CYCLES; gen++) {
			for(int x = 1; x < w-1; x++) {
				stoneLvlMatrix[gen][x] = stoneLvlMatrix[gen-1][x-1] + stoneLvlMatrix[gen-1][x] + stoneLvlMatrix[gen-1][x+1];
				stoneLvlMatrix[gen][x]/=3;
			}
			stoneLvlMatrix[gen][0] = stoneLvlMatrix[gen-1][0] + stoneLvlMatrix[gen-1][1];
			stoneLvlMatrix[gen][0]/=2;
			
			stoneLvlMatrix[gen][w-1] = stoneLvlMatrix[gen-1][w-1] + stoneLvlMatrix[gen-1][w-2];
			stoneLvlMatrix[gen][w-1]/=2;
		}
	}
	
	public Block[][] generateWorld(int w, int h, int seed) {
		CYCLES = w;
		generateStoneLvl(w,h);
		generateTreeLvl(w,h);
		Block[][] world = defaultWorld(w,h);
		matrix = new double[w][CYCLES+1];
		for(int i = 0; i < w; i++) {
			matrix[i][0] = (int) (Math.random()*h);
		}
//		matrix[0][0] = h/2;
//		matrix[w-1][0] = h/2;
		for(int i = 1; i < CYCLES; i++) {
			System.out.print(i + " ");
			for(int j = 1; j < w-1; j++) {
//				matrix[0][i] = matrix[0][i-1];
//				matrix[w-1][i] = matrix[w-1][i-1];
				matrix[j][i] = matrix[j-1][i-1] + matrix[j+1][i-1] + matrix[j][i-1];
				matrix[j][i]/=3; 
				
			}
			matrix[0][i] = matrix[0][i-1] + matrix[1][i-1];
			matrix[0][i]/=2;
			
			matrix[w-1][i] = matrix[w-1][i-1] + matrix[w-2][i-1];
			matrix[w-1][i]/=2;
			System.out.println();
		}
		return genN(0,w,h);
	}

	@Override
	public Block[][] generateWorld(int w, int h) {
		// TODO Auto-generated method stub
		return generateWorld(w,h,(int)(Math.random()*Integer.MAX_VALUE));
	}

	public Block[][] genN(int gen, int w, int h) {
		try{
		if(gen >= CYCLES) return genN(CYCLES-1,w,h);
		Block[][] world = defaultWorld(w,h);
		
		for(int i = 0; i < w; i++) {
			for(int j = h-1; j >= h-matrix[i][gen] && j >= 0; j--) {
				world[i][j] = Block.DIRT;
				if(j == h-(int)(matrix[i][gen])) world[i][j] = Block.GRASS;
			}
		}
		int seaLvl = h/2;
		for(int x = 0; x < w;x ++) {
			for(int y = h-1; y >= 0; y--) {

				double stoneLvl = stoneLvlMatrix[CYCLES-1][x];
					
				if(matrix[x][gen] <= seaLvl) {
					if(y > seaLvl) {
						if(y < h-matrix[x][gen]) world[x][y] = Block.randomWater();
						else {
//							int m = (int)matrix[x][gen];
//							int diff = h/9;
							world[x][y] = Block.SAND;
						}
					} else world[x][y] = Block.EMPTY1;
				}

				if(y >= stoneLvl) {
					world[x][y] = Block.STONE;
				}
			}
		}
		for(int x = 0; x < w; x++) {
			int treeLvl = (int)treeLvlMatrix[gen][x];
			int landLvl = (int)matrix[x][gen];
			int stoneLvl = (int)stoneLvlMatrix[gen][x];
			if(treeLvl > landLvl && landLvl > seaLvl && x % FAUNA_GAP == 0 && landLvl < stoneLvl) {
				world[x][h-landLvl-1] = Block.GREY_FERN;
			}
			
			if(treeLvl > landLvl && landLvl >= seaLvl-1 && x % TREE_GAP == 0 && landLvl < stoneLvl) {
				// create tree
//				int treeHeight = 4;
				int treeHeight = (int) (Math.random() * 2 + 2);
				for(int i = landLvl; i <= landLvl + treeHeight; i++) {
					world[x][h-i-1] = Block.OAK_WOOD_SIDE;
				}
				for(int i = -1; i <= 1; i++) {
					if(i+x<0||i+x>=w)continue;
					world[i+x][h-treeHeight-landLvl-2] = Block.LEAF1_GREY;
				}
				for(int i = -2; i <= 2; i++) {
					if(i+x<0||i+x>=w)continue;
					world[i+x][h-treeHeight-landLvl-1] = Block.LEAF1_GREY;
				}
				world[x][h-landLvl] = Block.DIRT;
			}
			if(gen != CYCLES-1)world[x][h-1-treeLvl] = Block.OAK_WOOD_TOP;
		}
		for(int x=0;x<w;x++){
			if(gen != CYCLES-1)world[x][h-1-(int)matrix[x][gen]] = Block.OBSIDIAN;
		}

		return world;
		} catch(Throwable t) {
			return genN(gen,w,h);
		}
	}

}

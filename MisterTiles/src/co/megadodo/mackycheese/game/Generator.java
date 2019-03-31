package co.megadodo.mackycheese.game;

import co.megadodo.mackycheese.framework.DW;
import co.megadodo.mackycheese.framework.Utils;

@SuppressWarnings("unused")
public class Generator {
	private static int roomW = 5;
	private static int roomH = 5;
	private static Tile[][] spawnRoom() {
		Tile[][] sr = room1();
		
		for(int i = 0; i < roomW; i++) {
			for(int j = 0; j < roomH; j++) {
				if(sr[i][j].isLiquid()) {
					sr[i][j] = Tile.randomGreenOutset();
				}
			}
		}
		
		return sr;
	}
	private static Tile[][] endRoom() {
		Tile[][] sr = room1();
		for(int i = 0; i < roomW; i++) {
			for(int j = 0; j < roomH; j++) {
				sr[i][j] = Tile.TAN_OUTSET_1;
			}
		}
		return sr;
	}
	private static Tile[][] room1() {
		Tile[][] r1 = new Tile[roomW][roomH];
		for(int x = 0; x < roomW; x++) {
			for(int y = 0; y < roomH; y++) {
				r1[x][y] = Tile.randomLava();
			}
		}
		return r1;
	}
	private static Tile[][] room2() {
		// ROOM2 - CROSS OF RANDOM GREY BRICKS
		Tile[][] room2 = room1();
		for(int i = 0; i < roomW; i++) {
			room2[i][2] = Tile.randomGreyBrick();
			room2[2][i] = Tile.randomGreyBrick();
		}
		return room2;
	}
	private static Tile[][] room3() {
		// ROOM3 - COMPLETELY FILLED & PATTERNED
		Tile[][] room3 = room1();
		int posses[] = {0, 1, 3, 4};
		for(int i = 0; i < posses.length; i++) {
			for(int j = 0; j < posses.length; j++) {
				room3[posses[i]][posses[j]] = Tile.randomGreyBrick();
				room3[2][posses[i]] = Tile.randomBlueBrick();
				room3[posses[j]][2] = Tile.randomBlueBrick();
			}
		}
		room3[2][2] = Tile.randomRedCheckerBrick();
		
		room3[0][0] = Tile.randomLava();
		room3[0][roomH-1] = Tile.randomLava();
		room3[roomW-1][0] = Tile.randomLava();
		room3[roomW-1][roomH-1] = Tile.randomLava();
		return room3;
	}
	private static Tile[][] room4() {
		// ROOM4 - CROSS & PATTERNED
		Tile[][] r4 = room1();
		
		int posses[] = {0, 1, 3, 4};
		for(int i = 0; i < posses.length; i++) {
			for(int j = 0; j < posses.length; j++) {
				//r4[posses[i]][posses[j]] = Tile.randomGreyBrick();
				r4[2][posses[i]] = Tile.randomBlueBrick();
				r4[posses[j]][2] = Tile.randomBlueBrick();
			}
		}
		r4[2][2] = Tile.randomRedCheckerBrick();
		
		return r4;
	}
	private static Tile[][] room5() {
		// ROOM5 - RUINS (hoppable)
		Tile[][] r5 = room1();
		
		for(int i = 0; i < roomW; i++) {
			for(int j = 0; j < roomH; j++) {
				if(Utils.randInt(0, 4) != 0) {
					r5[i][j] = Tile.randomBlueBrick();
				}
			}
		}
		return r5;
	}
	private static Tile[][] room6() {
		// ROOM6 - FRAME
		Tile[][] r6 = room1();
		
		for(int i = 0; i < roomW; i++) {
			r6[i][0] = Tile.randomGreyBrick();
			r6[0][i] = Tile.randomGreyBrick();
			r6[roomW-1][i] = Tile.randomGreyBrick();
			r6[i][roomH-1] = Tile.randomGreyBrick();
		}
		
		return r6;
	}
	static {


		
		
	}
	private static Tile[][] setBlock(Tile t, int x, int y, Tile[][] world) {
		if(x<0||x>=world.length||y<0||y>=world[0].length)return world;
		world[x][y] = t;
		return world;
	}
	private static Tile[][] createBlock(Tile t, int x, int y, Tile[][] world) {
		if(x<0||x>=world.length||y<=0||y>=world[0].length||world[x][y]!=Tile.EMPTY)return world;
		world[x][y] = t;
		return world;
	}
	public static Tile[][] generate(long seed, int w, int h) {
		Utils.seedRandom(seed);
		Tile[][] world = new Tile[w][h];
		for(int x = 0; x < w; x++) for(int y = 0; y < h; y++) world[x][y] = Tile.EMPTY;
		for(int x = 0; x < w/roomW; x++) {
			for(int y = 0; y < h/roomH; y++) {
				
				Tile[][] room = genRoom();
				if(x == 0 && y == 0) {
					room = spawnRoom();
				} else if(x*roomW == (w/10)*5 && y*roomH == (h/10)*5) {
					room = endRoom();
					DW.log("END AT " + x + " " + y);
				} else if(x < 2 && y < 2) {
					room = room3();
				}
				
				for(int i = x*roomW; i<x*roomW+roomW; i++) {
					for(int j = y*roomH; j < y*roomH+roomH; j++) {
						world = setBlock(Tile.randomLava(), i, j, world);
					}
				}
				for(int i = 0; i < room.length; i++) {
					for(int j = 0; j < room[i].length; j++) {
						int realX = x*roomW+i;
						int realY = y*roomH+j;
						world = setBlock(room[i][j], realX, realY, world);
					}
				}
				
			}
		}
		
		return world;
	}
	
	public static Tile[][] genRoom() {
		// R2 = CROSS OF RANDOM GREY BRICKS
		// R3 = COMPLETELY FILLED & PATTERNED
		// R4 = CROSS & PATTERNED
		// R5 = RUINS (HOPPABLE)
		// R6 = FRAME
		Tile[][][] rooms = new Tile[100][][];
		int r2 = 5;
		int r3 = 30;
		int r4 = 20;
		int r5 = 35;
		int r6 = 10;
		for(int i = 0; i < r2; i++) {
			rooms[i] = room2();
		}
		for(int i = r2; i < r2+r3; i++) {
			rooms[i] = room3();
		}
		for(int i = r2+r3; i < r2+r3+r4; i++) {
			rooms[i] = room4();
		}
		for(int i = r2+r3+r4; i < r2+r3+r4+r5; i++) {
			rooms[i] = room5();
		}
		for(int i = r2+r3+r4+r5; i < r2+r3+r4+r5+r6; i++) {
			rooms[i] = room6();
		}
		return rooms[Utils.randInt(0, rooms.length - 1) ];
	}
}

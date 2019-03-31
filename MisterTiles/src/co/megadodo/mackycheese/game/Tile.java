package co.megadodo.mackycheese.game;

import co.megadodo.mackycheese.framework.Utils;

public enum Tile {
	EMPTY(0,0,"wall-tiles-32x32"),
	RED_CHECKER_BRICK_1(2, 0, "floor-tiles-32x32"),
	RED_CHECKER_BRICK_2(3, 0, "floor-tiles-32x32"),
	RED_CHECKER_BRICK_3(4, 0, "floor-tiles-32x32"),
	BLUE_BRICK_1( 5, 0, "floor-tiles-32x32"),
	BLUE_BRICK_2( 6, 0, "floor-tiles-32x32"),
	BLUE_BRICK_3( 7, 0, "floor-tiles-32x32"),
	BLUE_BRICK_4( 8, 0, "floor-tiles-32x32"),
	BLUE_BRICK_5( 9, 0, "floor-tiles-32x32"),
	BLUE_BRICK_6(10, 0, "floor-tiles-32x32"),
	GREY_BRICK_1(11, 0, "floor-tiles-32x32"),
	GREY_BRICK_2(12, 0, "floor-tiles-32x32"),
	GREY_BRICK_3(13, 0, "floor-tiles-32x32"),
	GREY_BRICK_4(14, 0, "floor-tiles-32x32"),
	GREY_BRICK_5(15, 0, "floor-tiles-32x32"),
	GREY_BRICK_6(16, 0, "floor-tiles-32x32"),
	LAVA_1(0, 12, "floor-tiles-32x32"),
	LAVA_2(1, 12, "floor-tiles-32x32"),
	LAVA_3(2, 12, "floor-tiles-32x32"),
	WATER_1(3, 12, "floor-tiles-32x32"),
	WATER_2(4, 12, "floor-tiles-32x32"),
	WATER_3(5, 12, "floor-tiles-32x32"),
	ACID_1(6, 12, "floor-tiles-32x32"),
	ACID_2(7, 12, "floor-tiles-32x32"),
	ACID_3(8, 12, "floor-tiles-32x32"),

	RED_INSET_1(0, 1),
	RED_INSET_2(1, 1),
	RED_INSET_3(2, 1),
	RED_INSET_4(3, 1),
	BLUE_OUTSET_1(0,2),
	BLUE_OUTSET_2(1,2),
	BLUE_OUTSET_3(2,2),
	BLUE_OUTSET_4(3,2),
	TAN_OUTSET_1(0,3),
	TAN_OUTSET_2(1,3),
	TAN_OUTSET_3(2,3),
	TAN_OUTSET_4(3,3),
	GREEN_OUTSET_1(0,4),
	GREEN_OUTSET_2(1,4),
	GREEN_OUTSET_3(2,4),
	GREEN_OUTSET_4(3,4),
	;
	
	public static Tile randomRedInset() {
		Tile[] tiles = new Tile[] { RED_INSET_1, RED_INSET_2, RED_INSET_3, RED_INSET_4 };
		return tiles[Utils.randInt(0, tiles.length-1)];
	}
	
	public static Tile randomBlueOutset() {
		Tile[] tiles = new Tile[] { BLUE_OUTSET_1, BLUE_OUTSET_2, BLUE_OUTSET_3, BLUE_OUTSET_4 };
		return tiles[Utils.randInt(0, tiles.length-1)];
	}
	
	public static Tile randomTanOutset() {
		Tile[] tiles = new Tile[] { TAN_OUTSET_1, TAN_OUTSET_2, TAN_OUTSET_3, TAN_OUTSET_4 };
		return tiles[Utils.randInt(0, tiles.length-1)];
	}
	
	public static Tile randomGreenOutset() {
		Tile[] tiles = new Tile[] { GREEN_OUTSET_1, GREEN_OUTSET_2, GREEN_OUTSET_3, GREEN_OUTSET_4 };
		return tiles[Utils.randInt(0, tiles.length-1)];
	}
	
	public static Tile randomBrick() {
		Tile[] tiles = new Tile[] { randomRedCheckerBrick(), randomBlueBrick(), randomGreyBrick() };
		return tiles[Utils.randInt(0, tiles.length-1)];
	}

	public static Tile randomLava() {
		Tile[] tiles = new Tile[] { LAVA_1, LAVA_2, LAVA_3 };
		return tiles[Utils.randInt(0, 2)];
	}
	public static Tile randomWater() {
		Tile[] tiles = new Tile[] { WATER_1, WATER_2, WATER_3 };
		return tiles[Utils.randInt(0, 2)];
	}
	public static Tile randomAcid() {
		Tile[] tiles = new Tile[] { ACID_1, ACID_2, ACID_3 };
		return tiles[Utils.randInt(0, 2)];
	}
	public static Tile randomRedCheckerBrick() {
		Tile[] tiles = new Tile[] { RED_CHECKER_BRICK_1, RED_CHECKER_BRICK_2, RED_CHECKER_BRICK_3 };
		
		return tiles [Utils.randInt(0, 2)];
	}
	
	public static Tile randomBlueBrick() {
		Tile[] tiles = new Tile[] { BLUE_BRICK_1, BLUE_BRICK_2, BLUE_BRICK_3, BLUE_BRICK_4, BLUE_BRICK_5, BLUE_BRICK_6 };
		return tiles[Utils.randInt(0, 5)];
	}
	public static Tile randomGreyBrick() {
		Tile[] tiles = new Tile[] { GREY_BRICK_1, GREY_BRICK_2, GREY_BRICK_3, GREY_BRICK_4, GREY_BRICK_5, GREY_BRICK_6 };
		return tiles[Utils.randInt(0, 5)];
	}
	private int tileX;
	private int tileY;
	private String name;
	private String tilesheet;
	private Tile(int tileX, int tileY, String tilesheet) {
		this.tileX = tileX;
		this.tileY = tileY;
		this.name = super.name().toLowerCase();
		this.tilesheet = "mistertiles/" + tilesheet + ".png";
	}
	private Tile(int tileX, int tileY) {
		this(tileX, tileY, "floor-tiles-32x32");
	}
	
	public String tilesheet() {
		return this.tilesheet;
	}
	
	public int x() {
		return this.tileX*32;
	}
	
	public int tileX() {
		return this.tileX;
	}
	
	public int tileY() {
		return this.tileY;
	}
	
	public int y() {
		return this.tileY*32;
	}
	
	public String tileName() {
		return this.name;
	}
	
	public boolean isSolid() {
		return !isLiquid();
	}
	public boolean isLiquid() {
		return isWater() || isLava() || isAcid();
	}
	public boolean isWater() {
		return this == Tile.WATER_1 || this == Tile.WATER_2 || this == Tile.WATER_3;
	}
	public boolean isLava() {
		return this == Tile.LAVA_1 || this == Tile.LAVA_2 || this == Tile.LAVA_3;
	}
	public boolean isAcid() {
		return this == Tile.ACID_1 || this == Tile.ACID_2 || this == Tile.ACID_3;
	}
}

package co.megadodo.mackycheese.game;

public class World {
	private long seed;
	private int w, h;
	private Tile[][] world;
	public World(Tile[][] world, long seed) {
		this.world = world;
		this.seed = seed;
		this.w = this.world.length;
		this.h = this.world[0].length;
	}
	
	public int width() { return w; }
	public int height() { return h; }
	public Tile tileAt(int x, int y) {
		if(x<0 || x >=w || y<0||y>=h) return Tile.EMPTY;
		return world[x][y];
	}
}

package co.megadodo.mackycheese.framework;

import co.megadodo.mackycheese.game.MyGame;

public class GameSettings {
	public static int windowW = MyGame.CELL_SIZE * MyGame.GRID_CELL_W;
	public static int windowH = MyGame.CELL_SIZE * MyGame.GRID_CELL_H;
	public static int gameW = MyGame.CELL_SIZE * MyGame.GRID_CELL_W;
	public static int gameH = MyGame.CELL_SIZE * MyGame.GRID_CELL_H;
	
	public static int millisPerFrame = 1;
	
	public static boolean debug = true;
}

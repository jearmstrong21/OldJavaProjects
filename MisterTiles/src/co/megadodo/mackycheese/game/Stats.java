package co.megadodo.mackycheese.game;

public class Stats {

	private Stats() {
		
	}
	
	public static int curLife = 1;
	public static int time = 0;
	public static int monstersKilled = 0;
	public static int calculateScore() {
		return Math.abs(monstersKilled * 100 - curLife * 20 - time/1000);
	}
	public static void reset() {
		curLife = 0;
		time = 0;
		monstersKilled = 0;
	}
}

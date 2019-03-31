package co.megadodo.mackycheese.game;

import co.megadodo.mackycheese.framework.GameRunner;

public class Main {
	public static void main(String[] args) {
		GameRunner gameRunner = new GameRunner("ImpactJV Development Test");
		
		gameRunner.run(new MyGame());
	}
}

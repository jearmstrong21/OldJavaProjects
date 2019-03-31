package co.megadodo.mackycheese.game;

import co.megadodo.mackycheese.framework.GameRunner;

public class Runner {

	public static void main(String[] args) {
		GameRunner g = new GameRunner("Title Here In Runner.java");
		g.run(new co.megadodo.mackycheese.game.Game());
	}
	
}

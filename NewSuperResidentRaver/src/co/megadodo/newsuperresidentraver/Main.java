package co.megadodo.newsuperresidentraver;

import co.megadodo.newsuperresidentraver.engine.GameFrame;
import co.megadodo.newsuperresidentraver.engine.RunGame;

public class Main {
	
	public static void main(String[] args) {
		MyGame game = new MyGame(1067, 600);
		GameFrame frame = RunGame.run(game, "Game", 1067, 600);
		frame.setVisible(true);
	}

}

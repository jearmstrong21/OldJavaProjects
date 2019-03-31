package co.megadodo.newsuperresidentraver.engine;

import javax.swing.Timer;

public class RunGame {
	
	public static GameFrame run(Game g, String title, int w, int h) {
		GameFrame frame = new GameFrame(title, g, w, h);
		Timer t = new Timer(Game.MILLIS_PER_FRAME, e-> {
			frame.repaint();
		});
		t.start();
		return frame;
	}

}

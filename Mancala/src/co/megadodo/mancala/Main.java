package co.megadodo.mancala;

import co.megadodo.consolewindow.ConsoleWindow;

public class Main {

	public static void main(String[] args) {

		MancalaBoard board = new MancalaBoard();
		board.setup();
		System.out.println("Board:");
		board.display();
		System.out.println("\nDo move:");
		board.move(1, 0);
		System.out.println("\nBoard after move;");
		board.display();
		String toStr = board.toString();
		System.out.println("\nBoard to string:\n" + toStr);
		System.out.println("\nParsed board:");
		MancalaBoard.parse(toStr).display();
		ConsoleWindow window = ConsoleWindow.create(500, 500, "Window");
		board.display(window);
//		MancalaPos pos = new MancalaPos(1,0);
//		for(int i = 0; i < 15; i++) {
//			System.out.println(pos.toString());
//			pos = pos.next(1);
//		}
	}

}

package co.megadodo.connect4;

import UDP.UDPClient;
import co.megadodo.consolewindow.ConsoleWindow;

public class UDPConnect4Client {
	
	public static void main(String[] args) {
		new UDPConnect4Client().threadClient();
	}
	UDPClient client;
	
	public UDPConnect4Client() {
		playerID = -1;
		moveToDo = -1;
		playerPiece = Piece.EMPTY;
		rejected = false;
		joined = false;
		window = new ConsoleWindow(500, 500, 12, "UDP Client", false);
		board = null;
	}
	
	public void createClient() {
		client = new UDPClient(UDP.HOST, UDP.PORT);
		window.console_println("UDP Client");
	}
	
	int moveToDo;
	int playerID;
	Piece playerPiece;
	boolean rejected;
	boolean joined;
	ConsoleWindow window;
	Connect4Board board;
	
	public void attemptJoin() {
		window.console_println("Attempting join");
		String response = client.send(UDP.JOIN_REQUEST);
		if(response.startsWith(UDP.JOIN_ACCEPT)) {
			playerID = Integer.parseInt(response.split(" ")[1]);
			playerPiece = Piece.fromPlayer(playerID);
			joined = true;
			window.console_println("Joined with id " + playerID);
		} else if(response.equals(UDP.JOIN_REJECT)) {
			window.console_println("Rejected");
		}
	}
	
	public boolean canMove() {
		if(board == null) return false;
		return board.playerMoving == playerPiece;
	}
	
	public void doClientLoop() {
		if(rejected) {
			window.console_println("Rejected, cannot do client loop");
			return;
		}
		if(!joined) {
			window.console_println("Not joined, cannot do client loop");
			return;
		}
		window.console_println("Starting client loop");
		while(true) {
			if(moveToDo != -1) {
				String response = client.send(UDP.MOVE_REQUEST + " " + moveToDo);
				window.console_println("Sent move " + moveToDo);
				moveToDo = -1;
			} else {
				String response = client.send(UDP.STATUS_REQUEST);
				board = Connect4Board.parse(response.split(" ")[1]);
				window.console_println("Status:");
				if(board != null) board.display(window);
			}
//			waitMillis(50);
		}
	}

	public void waitMillis(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void threadClient() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				createClient();
				attemptJoin();
				doClientLoop();
			}
		}).start();
	}

}

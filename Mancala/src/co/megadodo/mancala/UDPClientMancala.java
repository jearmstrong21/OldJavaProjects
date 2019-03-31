package co.megadodo.mancala;

import UDP.UDPClient;

public class UDPClientMancala {
	
	
	public static void main(String[] args) throws InterruptedException {
		new UDPClientMancala();
	}

	UDPClient client;
	int playerID = -1;
	MancalaBoard board = null;
	MancalaPos moveToDo = null;
	int playerMoving = -1;
	
	public boolean attemptingMove() {
		return moveToDo != null;
	}
	
	public void attemptMove(MancalaPos pos) {
		moveToDo = pos;
	}
	
	public UDPClientMancala() {
		
	}

	public void startClient() {
		client = new UDPClient(UDP.HOST, UDP.PORT);
	}
	
	public void attemptJoin() {
		System.out.println("Attempting join");
		String response = client.send(UDP.JOIN_REQUEST);
		if(response.startsWith(UDP.JOIN_ACCEPT)) {
			playerID = Integer.parseInt(response.split(" ")[1]);
			System.out.println("Joined with player id " + playerID);
		} else {
			playerID = -1;
			System.out.println("Rejected");
		}
	}
	
	public void threadClient() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				startClient();
				attemptJoin();
				doClientLoop();
			}
		}).start();
	}
	
	public void doClientLoop() {
		if(playerID == -1) {
			System.out.println("Cannot do client loop, not joined yet");
			return;
		}
		System.out.println("Start client loop");
		while(true) {
			if(moveToDo != null) {
				String response = client.send(UDP.MOVE_REQUEST + " " + moveToDo.player + " " + moveToDo.hole);
				moveToDo = null;
			} else {
				String response = client.send(UDP.STATUS_REQUEST);
				System.out.println("Status request: <" + response + ">");
				board = MancalaBoard.parse(response.split("SPLITTER")[1]);
				if(board == null) {
					System.out.println("Null board");
				} else {
					board.display();
				}
			}
			waitMillis(100);
		}
	}
	
	public void waitMillis(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

}

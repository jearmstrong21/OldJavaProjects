package co.megadodo.mancala;

import UDP.UDPClient;

public class UDPClientMancalaOld {
	
	
	public static void main(String[] args) throws InterruptedException {
		new UDPClientMancalaOld();
	}

	UDPClient client;
	int playerID = -1;
	MancalaBoard board;
	MancalaPos moveToDo = null;
	boolean gameStarted = false;
	int playerMoving = -1;
	
	public void attemptMove(MancalaPos pos) {
		moveToDo = pos;
	}
	
//	public void sendMove() {
//		if(moveToDo != null) return;
//		try {
//			Scanner sc = new Scanner(System.in);
//			synchronized(sc) {
//				int hole = sc.nextInt();
//				moveToDo = new MancalaPos(playerID, hole);
//				sc.close();
//			}
//		} catch(Throwable t) {
////			System.err.println("ERROR: " + t.getClass().getName() + ": " + t.getMessage());
//		}
//	}
	
	public UDPClientMancalaOld() {
		
	}
	
	public void attemptJoin() {
		System.out.println("Attempting to join");
		String response = client.send(UDPServerMancalaOld.PLAYER_JOIN);
		if(response.equals(UDPServerMancalaOld.PLAYER_REJECTED)) {
			System.out.println("Rejected");
			System.exit(0);
		}
		playerID = UDPServerMancalaOld.parsePlayerAccepted(response);
		System.out.println("Accepted: " + playerID);
	}
	
	public void doClientLoop() {
		String response;
		while(true) {
			waitMillis(100);
//			System.out.println("Send status request");
			try {
				response = client.send(UDPServerMancalaOld.STATUS_REQUEST + " " + playerID);
			} catch(Throwable t) {
				continue;
			}
			String[] strs = response.split(" SPLITTER ");
			if(strs.length > 2) {
//				System.out.println("Parsing mancala board: <" + strs[2] + ">");
				playerMoving = Integer.parseInt(strs[1]);
				System.out.println("playermoving: " + playerMoving);
				board = MancalaBoard.parse(strs[2]);
			}
			if(board == null) {
//				System.out.println("Game hasn't started yet");
			} else {
//				System.out.println("Game board:");
//				board.display();
			}
			if(moveToDo != null) {
				System.out.println("Prompting for move:");
				System.out.println("Got move: " + moveToDo.toString() + " - sending request");
				try {
					response = client.send(UDPServerMancalaOld.doMove(playerID, moveToDo.hole, playerID));
				} catch(Throwable t) {
					continue;
				}
				System.out.println("Move request response: <" + response + ">");
				moveToDo = null;
			}
		}
	}
	
	public void startClient() {
		client = new UDPClient(UDPServerMancalaOld.HOST, UDPServerMancalaOld.PORT);
	}
	
	public void waitMillis(long millis) {
		long curTime = System.currentTimeMillis();
		while(System.currentTimeMillis() < curTime + millis) {}
	}

}

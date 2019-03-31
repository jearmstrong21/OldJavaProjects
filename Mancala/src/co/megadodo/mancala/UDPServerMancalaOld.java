package co.megadodo.mancala;

import java.net.SocketException;

import UDP.UDPServer;

public class UDPServerMancalaOld {

	public static final String HOST = "localhost";
	public static final int PORT = 1234;
	public static final String PLAYER_JOIN = "PLAYER_JOIN";
	public static final String PLAYER_ACCEPTED = "PLAYER_ACCEPTED";
	public static final String PLAYER_REJECTED = "PLAYER_REJECTED";
	public static final String STATUS_REQUEST = "STATUS_REQUEST";
	public static final String STATUS_GOOD = "STATUS_GOOD";
	public static final String DO_MOVE = "DO_MOVE";
	public static final String doMove(int player, int hole, int playerFrom) {
		return DO_MOVE + " " + player + " " + hole + " " + playerFrom;
	}
	public static final MancalaPos parseMove(String str) {
		String[] strs = str.split(" ");
		return new MancalaPos(Integer.parseInt(strs[1]),Integer.parseInt(strs[2]));
	}
	public static final int idOfMove(String str) {
		return Integer.parseInt(str.split(" ")[3]);
	}
	public static boolean isPlayerAccepted(String msg) {
		return msg.startsWith(PLAYER_ACCEPTED);
	}
	public static final int parsePlayerAccepted(String msg) {
		return Integer.parseInt(msg.split(" ")[1]);
	}
	
	public static void main(String[] args) throws SocketException {
		UDPServer server = new UDPServer(HOST, PORT);
		MancalaBoard board = null;
		boolean player0 = false;
		boolean player1 = false;
		System.out.println("Mancala server");
		boolean keepGoing = true;
		boolean needToStart = false;
		int playerMoving = 0;
		while(keepGoing) {
			if(board != null) {
				playerMoving = board.playerMoving;
				System.out.println(playerMoving + " " + board.playerMoving);
			}
			String request = server.recieveRequest();
			if(request.equals(PLAYER_JOIN)) {
				if(player0 && !player1) {
					player1 = true;
					server.sendResponse(PLAYER_ACCEPTED + " 1");
					needToStart = true;
					System.out.println("Player 1 accepted");
				} else if(!player0 && !player1){
					player0 = true;
					server.sendResponse(PLAYER_ACCEPTED + " 0");
					System.out.println("Player 0 accepted");
				} else {
					server.sendResponse(PLAYER_REJECTED);
					System.out.println("Player rejected");
				}
			} else {
				if(needToStart) {
					needToStart = false;
					board = new MancalaBoard();
					board.setup();
					System.out.println("Start game:");
					board.display();
				}
				if(request.startsWith(STATUS_REQUEST)) {
					if(board != null) {
						server.sendResponse(STATUS_GOOD + " SPLITTER " + playerMoving + " SPLITTER " + board.toString());
					} else {
						server.sendResponse(STATUS_GOOD + " SPLITTER null");
					}
				}
				if(request.startsWith(DO_MOVE)) {
					if(board != null) {
						MancalaPos pos = parseMove(request);
						MancalaPos finish = board.move(pos.player, pos.hole);
						if(finish.hole != -1) {
							playerMoving = MancalaBoard.other(playerMoving);
						}
						System.out.println("Move done at " + pos.toString());
						board.display();
						server.sendResponse(STATUS_GOOD);
					}
				}
			}
		}
	}
	
	
}

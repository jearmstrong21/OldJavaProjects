package co.megadodo.mancala;

import java.net.SocketException;

import UDP.UDPServer;
import co.megadodo.consolewindow.ConsoleWindow;

public class UDPServerMancala {

	public static void main(String[] args) throws SocketException {
		UDPServer server = new UDPServer(UDP.HOST, UDP.PORT);
		String request;
		ConsoleWindow window = ConsoleWindow.create(500, 500, "Title");
		MancalaBoard board = null;
		boolean player0 = false;
		boolean player1 = false;
		System.out.println("Start server");
		window.console_println("UDP Mancala Server");
		while(true) {
			request = server.recieveRequest();
			if(request.equals(UDP.JOIN_REQUEST)) {
				if(player0 && !player1) {
					System.out.println("Player 1 join");
					window.console_println("Player 1 join");
					player1 = true;
					server.sendResponse(UDP.JOIN_ACCEPT + " 1");
					board = new MancalaBoard();
					board.setup();
					System.out.println("Start game");
					window.console_println("Start game");
				} else if(!player0 && !player1) {
					System.out.println("Player 0 join");
					window.console_println("Player 0 join");
					player0 = true;
					server.sendResponse(UDP.JOIN_ACCEPT + " 0");
				} else {
					server.sendResponse(UDP.JOIN_REJECT);
				}
				
		 	} else if(request.startsWith(UDP.STATUS_REQUEST)) {
		 		if(board == null) {
		 			server.sendResponse(UDP.STATUS_RESPONSE + "SPLITTERnull");
		 		} else {
		 			server.sendResponse(UDP.STATUS_RESPONSE + "SPLITTER" + board.toString());
		 		}
		 	} else if(request.startsWith(UDP.MOVE_REQUEST)) {
		 		if(board == null) {
		 			
		 		} else {
		 			String[] split = request.split(" ");
		 			int player = Integer.parseInt(split[1]);
		 			int hole = Integer.parseInt(split[2]);
		 			if(board.board[player][hole] == 0) {
		 				server.sendResponse("");
		 			} else {
		 				board.display();
		 				board.display(window);
		 				board.move(player, hole);
		 				board.display();
		 				board.display(window);
		 				System.out.println("Move: <" + request+ ">");
		 				window.console_println("Move: <" + request + ">");
		 				server.sendResponse("");
		 			}
		 		}
		 	}
		}
	}
	
	
}

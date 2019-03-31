package co.megadodo.connect4;

import java.net.SocketException;

import UDP.UDPServer;
import co.megadodo.consolewindow.ConsoleWindow;

public class UDPConnect4Server {
	
	public static void main(String[] args) throws SocketException {
		UDPServer server = new UDPServer(UDP.HOST,UDP.PORT);
		
		ConsoleWindow window = new ConsoleWindow(500, 500, 12, "UDP Server", false);
		String request;
		boolean player0 = false;
		boolean player1 = false;
		window.console_println("UDP Server");
		Connect4Board board = null;
		while(true) {
			request = server.recieveRequest();
			if(request.equals(UDP.JOIN_REQUEST)) {
				if(player0 && !player1) {
					window.console_println("Player 1 join");
					player1 = true;
					server.sendResponse(UDP.JOIN_ACCEPT + " 1");
				} else if(!player0 && !player1) {
					window.console_println("Player 0 join");
					player0 = true;
					server.sendResponse(UDP.JOIN_ACCEPT + " 0");
					board = new Connect4Board();
					window.console_println("Start game");
				} else {
					window.console_println("Player rejected");
					server.sendResponse(UDP.JOIN_REJECT);
				}
			} else if(request.equals(UDP.STATUS_REQUEST)) {
				server.sendResponse(UDP.STATUS_RESPONSE + " " + Connect4Board.toString(board));
			} else if(request.startsWith(UDP.MOVE_REQUEST)) {
				if(board != null) {
					int n = Integer.parseInt(request.split(" ")[1]);
					board.move(n);
					server.sendResponse(Connect4Board.toString(board));
				}
			}
		}
	}

}

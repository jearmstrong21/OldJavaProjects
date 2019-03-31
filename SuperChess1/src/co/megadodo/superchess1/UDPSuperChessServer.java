package co.megadodo.superchess1;

import java.util.Timer;
import java.util.TimerTask;

import UDP.UDPServer;

public class UDPSuperChessServer extends TimerTask {
	
	public static void main(String[] args) {
		new UDPSuperChessServer().startServer();
	}
	
	JWriterWindow console;
	GameSettings settings;
	Timer timer;
	Board board;
	UDPServer server;

	public void initServer() {
		console = new JWriterWindow();
		console.show();
		console.jprintln("");
		console.jprintln("UDP SuperChess V1 Server");
		settings = GameSettings.normal;
		board = new Board();
		board.setupBoard();
		timeLeft = new int[8][8];
		for(int x=0;x<8;x++){
			for(int y=0;y<8;y++){
				timeLeft[x][y] = settings.timeForPiece(board.getPiece(x, y));
			}
		}
		boolean err = false;
		try {
			server = new UDPServer(UDP.Host,UDP.Port);
		} catch(Throwable t) {
			t.printStackTrace();
			console.jprintln("Server error:");
			console.jprintln(t.getClass().getName() + ": " + t.getMessage());
			err = true;
		}
		if(!err)console.jprintln("Initialized server");
	}
	
	int[][] timeLeft;

	public void startServer() {
		initServer();
		timer = new Timer("UDP Server");
		timer.scheduleAtFixedRate(this, 1, 1);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(true) {
					doServerLoop();
				}
				
			}
		}).start();
	}
	
	boolean player1 = false;
	boolean player2 = false;
	
	public void doServerLoop() {
		String request = server.recieveRequest();
		if(request.equals(UDP.RequestJoin)) {
			if(!player1) {
				player1 = true;
				server.sendResponse(UDP.AcceptJoin1);
				console.jprintln("Player 1 joined");
			} else if(player1 && !player2){
				player2 = true;
				server.sendResponse(UDP.AcceptJoin2);
				console.jprintln("Player 2 joined");
			} else {
				server.sendResponse(UDP.RejectJoin);
				console.jprintln("Extra player rejected");
			}
		}
	}

	@Override
	public void run() {
		for(int x=0;x<8;x++){
			for(int y=0;y<8;y++){
				if(timeLeft[x][y] < settings.timeForPiece(board.getPiece(x, y))) {
					timeLeft[x][y]++;
				}
			}
		}
	}
	
}

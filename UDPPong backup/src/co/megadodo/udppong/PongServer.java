package co.megadodo.udppong;

import UDP.UDPServer;

public class PongServer {
	
	UDPServer server;
	
	public static void main(String[] args) throws Throwable {
		new PongServer();
	}
	
	public PongServer() throws Throwable {
		server = new UDPServer(Pong.HOST, Pong.PORT);
		System.out.println("Pong server");
		int id = 0;
		int pos0 = 0;
		int pos1 = 0;
		int ballX = Pong.W/2;
		int ballY = Pong.H/2;
		int vx = 1;
		int vy = 1;
		int speed = 1;
		
		String str = server.recieveRequest(); // TODO: exit request closes server
		while(!str.equals("exit")) {
//			ballX+=vx*speed;
//			ballY+=vy*speed;
			if(ballX<0||ballX>Pong.W) vx*=-1;
			if(ballY<0||ballY>Pong.H) vy*=-1;
			if(Pong.isReqExit(str)) {
				server.sendResponse(Pong.requestExit());
				server.close();
				System.out.println("Exiting based on request");
			}
			if(Pong.isReqID(str)) {
				server.sendResponse(Pong.encodeID(id));
				System.out.println("Registered ID " + id);
				id++;
			}
			if(Pong.isReqStatus(str)) {
				int[] result = Pong.decodeReqStatus(str);
				int paddleY = 0;
				if(result[0] == 0) {
					pos0 = result[1];
					paddleY = pos1;
				} else {
					pos1 = result[1];
					paddleY = pos0;
				}
				server.sendResponse(Pong.encodeStatus(paddleY, ballX, ballY));
			}
			str = server.recieveRequest();
			if(id > 2) // more than 2 players
			{
				server.sendResponse(Pong.requestExit());
				server.close();
				System.out.println("EXIT - " + id + " players");
				System.exit(0);
			}
		}
		
		server.close();
	}

}

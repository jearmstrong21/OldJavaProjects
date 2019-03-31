package co.megadodo.udppong;

import java.awt.Rectangle;

import UDP.UDPServer;

public class PongServer {
	
	UDPServer server;
	
	public static void main(String[] args) throws Throwable {
		new PongServer();
	}
	
	public static float randFloat() {
		return (float)Math.random();
	}
	
	public PongServer() throws Throwable {
		// 0-id means left side
		// 1-id means right side
		server = new UDPServer(Pong.HOST, Pong.PORT);
		System.out.println("Pong server");
		int id = 0;
		int pos0 = 0;
		int pos1 = 0;
		int lastp0 = 0;
		int lastp1 = 0;
		// ball vars are floats for more accuracy (sloped lines)
//		float ballX = randFloat() * Pong.W;
//		float ballY = randFloat() * Pong.H;
//		float ballX = Pong.W/2;
//		float ballY = Pong.H/2;
//		float vx = randFloat();
//		float vy = (float)Math.sqrt(vx*vx - 1);
//		float vy = randFloat();
		//sqrt(vx^2 + vy^2) = 1
		//vx^2 + vy^2 = 1
		//vx^2 - 1 = vy^2
		//vy = sqrt(vx^2 - 1)
		//float speed = 1;
		int speed = 1;
		int ballX = Pong.W/2;
		int ballY = Pong.H/2;
		int vx = 2;
		int vy = 1;
		
		String str = server.recieveRequest(); // TODO: exit request closes server
		Rectangle paddle0 = new Rectangle(Pong.PADDLE_X_L, 0, Pong.PADDLE_W, Pong.PADDLE_H);
		Rectangle paddle1 = new Rectangle(Pong.PADDLE_X_R, 0, Pong.PADDLE_W, Pong.PADDLE_H);
		Rectangle ball = new Rectangle(ballX, ballY, Pong.BALL_RAD, Pong.BALL_RAD);
		int frame = 0;
		while(!str.equals("exit")) {
			frame++;
			if(frame%1==0) {
				ballX+=vx*speed;
				ballY+=vy*speed;
			}
			ball.setLocation(ballX, ballY);
			if(vy == 0) vy += (int)(Math.random()>0.5?0:1);
			
			if(ballX<Pong.BALL_MARGIN||ballX>Pong.W-Pong.BALL_MARGIN*2) vx*=-1;
			if(ballY<Pong.BALL_MARGIN||ballY>Pong.H-Pong.BALL_MARGIN*2) vy*=-1;
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
					lastp0 = pos0;
					pos0 = result[1];
					paddle0.setLocation(Pong.PADDLE_X_L, pos0);
					paddleY = pos1;
				} else {
					lastp1 = pos1;
					pos1 = result[1];
					paddle1.setLocation(Pong.PADDLE_X_R, pos1);
					paddleY = pos0;
				}
				server.sendResponse(Pong.encodeStatus(paddleY, (int)ballX, (int)ballY));
			}
			int m = Pong.PADDLE_BOUNCE_MARG;
			if(paddle0.intersects(ball)) {
//				vx*=-((int)Math.abs(pos0-lastp0) + 1);
				vx*=-1;
				if( (ballY <= pos0 && ballY >= pos0 - m) || (ballY >= pos0+Pong.PADDLE_H-m && ballY <= pos0+Pong.PADDLE_H)) {
					vy*=-1;
					System.out.println(pos0 + " " + (pos0-m) + " " + ballY);
				}
			}
			if(paddle1.intersects(ball)) {
//				vx*=-((int)Math.abs(pos1-lastp1) + 1);
				vx*=-1;
				if( (ballY <= pos1 && ballY >= pos1 - m) || (ballY >= pos1+Pong.PADDLE_H-m && ballY <= pos1+Pong.PADDLE_H)) {
					vy*=-1;
					System.out.println(pos1 + " " + (pos1-m) + " " + ballY);
				}
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
	
	public static boolean inRange(int v, int a, int b) {
		return (v >= a && v <= b) || (v >= b && v <= a);
	}

}

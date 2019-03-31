package co.megadodo.udppong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import UDP.UDPClient;

@SuppressWarnings("serial")
public class PongClient implements MouseMotionListener {

	public static void main(String[] args) {
		new PongClient();
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				new PongClient();
//			}
//		}).start();
	}
	UDPClient client;
	
	JFrame frame;
	JPanel canvas;
	
	int y = Pong.H/2 - Pong.PADDLE_H/2;
//	int THIS_X = 50;
//	int OTHER_X = Pong.W-50;
	int targetY = y;
	int otherY = y;
	int id = 0;
	int ballX = Pong.W/2;
	int ballY = Pong.H/2;
	
	public PongClient() {
		client = new UDPClient(Pong.HOST, Pong.PORT);
		id = Pong.decodeID(client.send(Pong.requestID()));
		System.out.println("Client ID: " + id);
		
		frame = new JFrame();
		frame.setTitle("Pong client #" + id);
		frame.setSize(Pong.W,Pong.H);
		canvas = new JPanel() {
			public void paint(Graphics g) {
				draw((Graphics2D) g);
			}
		};
		Timer t = new Timer(Pong.MIL_PER_FRAME, e-> {
//			System.out.println("REPAINT");
			canvas.repaint();
		});
		t.start();
//		frame.addMouseMotionListener(this);
		canvas.addMouseMotionListener(this);
		canvas.requestFocus();
		frame.add(canvas);
		frame.setVisible(true);
		
//		Runtime.getRuntime().addShutdownHook(new Thread()
//		{
//		    @Override
//		    public void run() {
//		    	String str = client.send(Pong.EXIT_REQUEST);
//		    	if(!str.equals(Pong.EXIT_ACK)) {
//		    		System.out.println("Don't exit! AAaAAaaaaarrrrggghh");
//		    	}
//		    }
//		});
	}
	
	public void draw(Graphics2D g2d) {
		// DRAW
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, Pong.W, Pong.H);
		g2d.setColor(Color.WHITE);
		g2d.drawLine(Pong.W/2, 0, Pong.W/2, Pong.H);
		g2d.fillOval(ballX, ballY, Pong.BALL_RAD, Pong.BALL_RAD);
		g2d.drawRect(Pong.BALL_MARGIN, Pong.BALL_MARGIN, Pong.W-Pong.BALL_MARGIN*2, Pong.H-Pong.BALL_MARGIN*2);
		
		if(id == 0) {
			g2d.fillRect(Pong.PADDLE_X_L, y, Pong.PADDLE_W, Pong.PADDLE_H);
			g2d.fillRect(Pong.PADDLE_X_R, otherY, Pong.PADDLE_W, Pong.PADDLE_H);
			g2d.setColor(Color.RED);
			g2d.drawRect(Pong.PADDLE_X_L, y, Pong.PADDLE_W, Pong.PADDLE_BOUNCE_MARG);
			g2d.drawRect(Pong.PADDLE_X_L, y+Pong.PADDLE_H - Pong.PADDLE_BOUNCE_MARG, Pong.PADDLE_W, Pong.PADDLE_BOUNCE_MARG);
			g2d.drawRect(Pong.PADDLE_X_R, otherY, Pong.PADDLE_W, Pong.PADDLE_BOUNCE_MARG);
			g2d.drawRect(Pong.PADDLE_X_R, otherY+Pong.PADDLE_H - Pong.PADDLE_BOUNCE_MARG, Pong.PADDLE_W, Pong.PADDLE_BOUNCE_MARG);
		} else {
			g2d.fillRect(Pong.PADDLE_X_R, y, Pong.PADDLE_W, Pong.PADDLE_H);
			g2d.fillRect(Pong.PADDLE_X_L, otherY, Pong.PADDLE_W, Pong.PADDLE_H);
			g2d.setColor(Color.RED);
			g2d.drawRect(Pong.PADDLE_X_R, y, Pong.PADDLE_W, Pong.PADDLE_BOUNCE_MARG);
			g2d.drawRect(Pong.PADDLE_X_R, y+Pong.PADDLE_H - Pong.PADDLE_BOUNCE_MARG, Pong.PADDLE_W, Pong.PADDLE_BOUNCE_MARG);
			g2d.drawRect(Pong.PADDLE_X_L, otherY, Pong.PADDLE_W, Pong.PADDLE_BOUNCE_MARG);
			g2d.drawRect(Pong.PADDLE_X_L, otherY+Pong.PADDLE_H - Pong.PADDLE_BOUNCE_MARG, Pong.PADDLE_W, Pong.PADDLE_BOUNCE_MARG);
		}
		g2d.drawRect(ballX, ballY, Pong.BALL_RAD, Pong.BALL_RAD);
		// UPDATE
//		int oldY = y;
		y -= (y - targetY) / Pong.PADDLE_EASING;
		updateStats();
	}
	
	public void updateStats() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String str = client.send(Pong.requestStatus(y, id));
				int[] stats = Pong.decodeStatus(str);
				otherY = stats[0];
				ballX = stats[1];
				ballY = stats[2];
			}
		}).start();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
//		y = e.getY() - Pong.PADDLE_HEIGHT/2;
		targetY = e.getY() - Pong.PADDLE_H/2;
	}
}

package co.megadodo.polygondrawer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Main extends JFrame implements KeyListener {

	public static void main(String[] args) {

		new Main();

	}

	public static int W = 500;
	public static int H = 500;
	public static int FPS = 60;
	public static Color BACKGROUND_COLOR = Color.BLACK;
	public static int NUM_FRAMES = 0;

	public Main() {
		this.setTitle("Main");
		this.setVisible(true);
		this.setSize(W, H);

		calcPoints();
		
		this.addKeyListener(this);
		
		Main self = this;
		Timer t = new Timer(1000 / FPS, e -> {
			self.repaint();
		});
		t.start();
		
	}
	
	private void calcPoints() {
		for(int i = 0; i < sides; i++) {
			double x = 0, y = 0;
			x = Math.sin(Math.PI*2/sides*i) * W/2;
			y = Math.cos(Math.PI*2/sides*i) * H/2;
			x += W/2;
			y += H/2;
			points.add(new Point((int)x, (int)y));
		}
	}

	int sides = 2;
	boolean redraw = true;
	ArrayList<Point> points = new ArrayList<Point>();

	public void paint(Graphics g) {
		if(NUM_FRAMES < 10) {
			redraw = true;
		}
		if(!redraw)return;
		redraw = false;

		NUM_FRAMES++;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(BACKGROUND_COLOR);
		g2d.fillRect(0, 0, W, H);
		
		
		g2d.setColor(new Color(1f, 0f, 0f, 1f));
		for(int i = 0; i < points.size(); i++) {
			for(int j = 0; j < points.size(); j++) {
				if(i == j) continue;
				g2d.setColor(new Color(1f, 0f, 0f,
//						(float)(i+j)/(i+j+100)
						2f/sides
						)
						);
				g2d.drawLine(points.get(i).x, points.get(i).y, points.get(j).x, points.get(j).y);
			}
		}
		g2d.setColor(Color.CYAN);
		g2d.drawOval(0, 0, W, H);
		for(int i = 0; i < points.size(); i++) {
			g2d.setColor(Color.GREEN);
			int _w = 10;
			int _h = 10;
			g2d.fillRect(points.get(i).x - _w/2, points.get(i).y - _h/2, _w, _h);
		}
		g2d.setColor(Color.WHITE);
		g2d.drawString("" + sides, 100, 100);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(Character.isDigit(e.getKeyChar())) {
			boolean isShiftDown = e.isControlDown();
			
			int n = Integer.parseInt(String.valueOf(e.getKeyChar()));
			if(isShiftDown) sides-=n;
			else sides+=n;
			points.clear();
			redraw = true;
			if(sides<2)sides=2;
			calcPoints();
			
			return;
		}
		if(e.getKeyChar() == 'w') {
			redraw = true;
			sides++;
			points.clear();
			calcPoints();
		} else if(e.getKeyChar() == 's' && sides > 2) {
			redraw=true;
			sides--;
			points.clear();
			calcPoints();
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

package co.megadodo.fractions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Frame extends JFrame {
	public static void main(String[] args) {
		new Frame();
	}
	
	int total = 4;
	int W = 800;
	int H = 800;
	int a = 10;
	
	public Frame() {
		this.setTitle("Fractions");
		this.setSize(W+a,H+8);
		this.setVisible(true);
		Frame self = this;
		Timer t = new Timer(0, e-> {
			self.repaint();
		});
		t.start();
	}
	boolean painted=false;
	public void paint(Graphics g) {
		if(painted)return;
		painted=true;
		Graphics2D g2d = (Graphics2D) g;
		int n = 1000;
		int h = H/n;
		int inc = 1;
//		g2d.translate(a/2, -h/2);
		g2d.translate(a/2, 10);
		g2d.setBackground(Color.CYAN);
		g2d.setFont(new Font(Font.MONOSPACED, Font.PLAIN, H/n/2));
		
//		g2d.setColor(Color.BLACK);
//		g2d.setStroke(new BasicStroke(10));
//		g2d.drawLine(0, 0, 0, H);
		for(int j=1*inc;j<=n;j+=inc){
			for(int i = 0; i <= j; i++) {
				if(j==4) {
					g2d.setColor(Color.BLACK);
					g2d.setStroke(new BasicStroke(10));
//					g2d.drawLine(i*W/j, 0, i*W/j, H);
				}
				g2d.setColor(Color.GREEN);
				g2d.setStroke(new BasicStroke(1));
				g2d.drawLine(i*W/j, j*h-h, i*W/j, j*h);
			}
			g2d.setStroke(new BasicStroke());
			g2d.setColor(Color.GREEN);
			g2d.drawLine(0, j*h, W, j*h);
			g2d.setColor(Color.RED);
			g2d.drawString(j+"", 10, j*h);
		}
	}
}

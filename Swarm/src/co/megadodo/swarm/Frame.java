package co.megadodo.swarm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Frame extends JFrame implements MouseMotionListener, KeyListener {
	static ArrayList<Entity> list = new ArrayList<Entity>();
	
	public static void main(String[] args) {
		new Frame();
	}
	public static int N = 100;
	public static int W = 1400;
	public static int H = 800;
	
	public Frame() {
		this.setTitle("Swarm");
		this.setVisible(true);
		this.setSize(W,H);
		for(int i = 0; i < N; i++) {
			list.add(new Entity((int)(Math.random()*W), (int)(Math.random()*H), (double)(Math.random()*Math.PI)));
		}
		Frame self = this;
		Timer t = new Timer(17, e-> {
			self.repaint();
		});
		t.start();
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
	}
	
	int mx = 0, my = 0;
	
	public void collisions() {
		try {
			for(Entity a : list) {
				for(Entity b : list) {
					if(Entity.dist(a.x, a.y, b.x, b.y) > a.rad + b.rad-2) continue;
					if(a == b) continue;
//					System.out.println("COLLIDE " + a.id + " " + b.id);
					Entity c = a.collide(b);
					if(c == null) {
						list.remove(a);
					}
					c.x = c.lx;
					c.y = c.ly;
					if(c == Entity.NULLENT) continue;
					else list.add(c);
				}
			}
		} catch(Throwable t) {
			
//			collisions();
		}
	}
	
	boolean firstFrame=true;
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
//		g2d.setColor(new Color(0F,0F,0F,0.1F));
		if(firstFrame) {
			g2d.setColor(Color.BLACK);
			firstFrame=false;
		}
		else {
			g2d.setColor(new Color(0,0,0,1/300f));
		}
		g2d.fillRect(0, 0, W, H);
		collisions();
		for(Entity ent : list) {
			ent.update(list, mx, my, W, H);
			Color c = new Color(ent.r, ent.g, ent.b);
			g2d.setColor(c);
			g2d.fillOval(ent.x,ent.y,ent.rad,ent.rad);
			g2d.setColor(Color.GRAY);
			int length = ent.rad*2;
			int x1 = ent.x + ent.rad/2;
			int y1 = ent.y + ent.rad/2;
			
			int x2 = ent.x + (int)(Math.cos(ent.rotat)*length) + ent.rad/2;
			int y2 = ent.y + (int)(Math.sin(ent.rotat)*length) + ent.rad/2;
			
			g2d.drawLine(x1, y1, x2, y2);
		}
		g2d.setColor(Color.WHITE);
		int s = 20;
		g2d.setFont(new Font(Font.MONOSPACED, Font.PLAIN, s));
//		g2d.drawString("CHANGESPEED: " + Entity.changeSpeed, 0, s*3);
//		g2d.drawString("MARGIN: " + Entity.marg, 0, s*4);
//		g2d.drawString("Numinrange: "+ Entity.numInRange, 0, s*5);
//		g2d.drawString("Rad: " + Entity.rad, 0, s*6);
//		g2d.drawString("RandFactor: " + Entity.randFactor, 0, s*7);
//		g2d.drawString("Viewdist: " + Entity.viewDist, 0, s*8);
		
		g2d.drawString("NUM: " + list.size(), 0, s*3);
	}
	

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mx = e.getX();
		my = e.getY();
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
//		char c = e.getKeyChar();
//		switch(c) {
//			case '1': Entity.changeSpeed+=0.1; break;
//			case '2': Entity.changeSpeed-=0.1; break;
//			case '3': Entity.marg+=10; break;
//			case '4': Entity.marg-=10; break;
//			case '5': Entity.numInRange+=1; break;
//			case '6': Entity.numInRange-=1; break;
//			case '7': Entity.rad+=2; break;
//			case '8': Entity.rad-=2; break;
//			case '9': Entity.randFactor+=0.5; break;
//			case '0': Entity.randFactor-=0.5; break;
//			case 'q': Entity.viewDist+=10; break;
//			case 'w': Entity.viewDist-=10; break;
//			case 'e': if(list.size() > 0) list.remove(0); break;
//			case 'r': list.add(new Entity((int)(Math.random()*W), (int)(Math.random()*H), Math.random()*Math.PI));
//			case 't': int i = 0;
//					  while(i < 5 && list.size() > 0) { list.remove(0); i++; }
//					  break;
//			case 'y': for(i = 0; i < 5; i++) list.add(new Entity((int)(Math.random()*W), (int)(Math.random()*H), Math.random()*Math.PI)); break;
//			case 'u': list.clear(); break;
//			case 'i': list.clear();
//			for(i = 0; i < N; i++) {
//				list.add(new Entity((int)(Math.random()*W), (int)(Math.random()*H), (double)(Math.random()*Math.PI)));
//			}break;
//					  
//		}
		
		
		/*
		 * fields:
		 * SPEED
		 * RAD
		 * VIEWDIST
		 * NUMINRANGE
		 * CHANGESPEED
		 * RANDFACTOR
		 * MATECHANCE
		 * RADDISTFORMATE
		 * KILLCHANCE
		 */
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

package co.megadodo.astar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class AStarPanel extends JPanel {
	
	public static final int W = 40;
	public static final int H = 25;
	public static final int GRID_SIZE = 30;
	
	Pos[][] arr;
	
	public AStarPanel() {
		arr = new Pos[W][H];
		for(int x=0;x<W;x++) {
			for(int y=0;y<H;y++) {
				arr[x][y] = new Pos(x,y);
			}
		}
		repaint();
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		for(int x=0;x<W;x++){
			for(int y=0;y<H;y++){
				switch(arr[x][y].type) {
					case BARRIER: g2d.setColor(Color.BLACK); break;
					case EMPTY: g2d.setColor(Color.WHITE); break;
					case START: g2d.setColor(Color.GREEN); break;
					case END: g2d.setColor(Color.RED); break;
				}
			}
		}
	}
	
}

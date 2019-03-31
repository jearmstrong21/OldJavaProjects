package co.megadodo.expressioneval.source;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class DrawTree extends JFrame {
	Node2D node = null;
	int W = 1450;
	int H = 850;
	int LAYER_W = 100;
	public DrawTree(Node2D node) {
		this.node = node;
		this.setTitle("Draw Tree");
		this.setSize(W,H);
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
//		g2d.translate(100, 0);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, W*2, H*2);
		drawTree(node, g2d, 1, W, 0, W/2, 0, true);
	}
	
	public void drawTree(Node2D n, Graphics2D g2d, int layer, int w, int x, int px, int py, boolean left) {
		if(n == null) return;
		if(left) g2d.setColor(Color.RED);
		else g2d.setColor(Color.BLUE);
		g2d.drawLine(x+w/2, layer*LAYER_W-10, px, py);
		g2d.setColor(Color.WHITE);
		g2d.drawString(n.getData() + " : " + n.eval(), x+w/2, layer * LAYER_W);
		drawTree(n.getlNode(), g2d, layer+1, w/2, x, x+w/2, layer * LAYER_W, true);
		drawTree(n.getrNode(), g2d, layer+1, w/2, x + w/2, x+w/2, layer * LAYER_W, false);
	}
}

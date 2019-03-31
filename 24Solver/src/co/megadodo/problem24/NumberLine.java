package co.megadodo.problem24;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class NumberLine extends JPanel {
	
	public Problem24 prob = new Problem24(0, 0, 0, 0, false, false);

	public void repaint() {
		super.repaint();
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(255/2,255/2,255/2));
//		g2d.setColor(Color.BLACK);
		int arc = 0;
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
		g2d.setColor(Color.WHITE);
		g2d.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
		g2d.drawLine(getWidth()/2, getHeight()/2-10, getWidth()/2, getHeight()/2+10);
		
	}
	
}

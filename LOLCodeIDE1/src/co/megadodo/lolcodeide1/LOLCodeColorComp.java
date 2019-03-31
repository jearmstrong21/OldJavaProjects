package co.megadodo.lolcodeide1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JColorChooser;
import javax.swing.JComponent;

public class LOLCodeColorComp extends JComponent implements MouseListener {

	Color col;
	
	public LOLCodeColorComp(Color initCol) {
		this.col = initCol;
		this.addMouseListener(this);
	}
	
	public Color getCol() {
		return col;
	}
	
	public void popup() {
		Color c = JColorChooser.showDialog(this, "Pick color", this.col);
		if(c != null) col = c;
		repaint();
	}

	int rw = 20;
	int rh = rw;
	int marg = 2;
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(col);
		g2d.fillRoundRect(marg, marg, getWidth()-marg-marg, getHeight()-marg-marg, rw, rh);
		g2d.drawRoundRect(marg, marg, getWidth()-marg-marg, getHeight()-marg-marg, rw, rh);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		popup();
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		popup();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		popup();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
//		popup();
	}

	@Override
	public void mouseExited(MouseEvent e) {
//		popup();
	}
	
}

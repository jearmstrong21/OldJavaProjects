package co.megadodo.expressioneval.source;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;

// Nice class, but not used.
public class PopupColorChooser extends JButton implements ActionListener {

	Color col = Color.BLACK;
	String title;
	
	public PopupColorChooser(String title) {
		super();
		this.title = title;
		this.addActionListener(this);
		this.repaint();
		this.setPreferredSize(new Dimension(30,30));
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(col);
		g2d.fillRect(0, 0, 30, 30);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		col = JColorChooser.showDialog(this, title, col);
		this.repaint();
	}

}

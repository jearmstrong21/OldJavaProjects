package co.megadodo.udp;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class GUI {

	JFrame frame;
	JLabel lbl;
	JButton quit;
	
	public GUI() {
		frame = new JFrame("Server GUI");
		frame.setLayout(new BorderLayout());
		lbl = new JLabel();
		quit = new JButton("Quit");
		frame.add(new JScrollPane(lbl), BorderLayout.CENTER);
		frame.add(quit, BorderLayout.PAGE_END);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
	
	public void addText(String msg) {
		lbl.setText(lbl.getText() + "</br>" + msg);
	}
	
}

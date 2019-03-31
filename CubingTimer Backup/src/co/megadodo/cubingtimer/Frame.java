package co.megadodo.cubingtimer;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Frame extends JFrame {

	public static void main(String[] args) {
		new Frame();
	}
	
	public Frame() {
		setTitle("Title");
		setSize(500, 500);
		setVisible(true);
		String[] list = {"A", "B", "C"};
		JComboBox<String> jcb = new JComboBox<String>(list);
		JOptionPane.showMessageDialog( null, jcb, "select or type a value", JOptionPane.QUESTION_MESSAGE);
		System.out.println(jcb.getSelectedItem());
	}
	
}

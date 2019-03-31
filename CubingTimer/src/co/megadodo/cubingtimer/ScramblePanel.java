package co.megadodo.cubingtimer;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ScramblePanel extends JPanel {

	public static void main(String[] args) {
		System.out.println("Running ScramblePanel");
		new ScramblePanel(true);
	}
	
	public ScramblePanel(boolean start) {
		if(start) {
			ScramblePanel s = new ScramblePanel();
			JFrame frame = new JFrame("Title here");
			frame.add(s);
			frame.setSize(500, 500);
			frame.setVisible(true);
		}
	}
	
	JComboBox<String> cbCube;
	JLabel lblCube;
	JButton btnScramble;
	JLabel lblScramble;
	JLabel lblSeed;
	JTextField tfSeed;
	GridLayout layout;
	
	public ScramblePanel() {
		
		lblCube = new JLabel("Select cube:");
		cbCube = new JComboBox<String>();
		btnScramble = new JButton("Scramble!");
		lblScramble = new JLabel(" -- Scramble will appear here -- ");
		lblSeed = new JLabel("Seed (leave blank for random):");
		tfSeed = new JTextField(10);
		layout = new GridLayout(3,2);
		layout.setHgap(10);
		layout.setVgap(10);
		initCbCube();
		int margV = 10;
		int margH = 10;
		this.setBorder(BorderFactory.createEmptyBorder(margV, margH, margV, margH));
		this.setLayout(layout);
		this.add(lblCube);
		this.add(cbCube);
		this.add(lblSeed);
		this.add(tfSeed);
		this.add(btnScramble);
		this.add(lblScramble);
		
		btnScramble.addActionListener((e)->scramble());
	}
	
	public void scramble() {
		String str = tfSeed.getText().trim();
		long seed = 0;
		try {
			seed = Long.parseLong(str);
		} catch(NumberFormatException nfe) {
			seed = (long) (Math.random()*Long.MAX_VALUE);
		}
		lblSeed.setText("<html>Seed (leave blank for random):<br/>Last seed used: " + seed + "</html>");
		Cube c = Cube.parseCube((String)cbCube.getSelectedItem());
		String scramble = Scrambler.scramble(c, seed);
		lblScramble.setText(scramble);
	}
	
	public void initCbCube() {
		for(Cube c : Cube.values()) {
			if(c.scramble) {
				cbCube.addItem(c.toString());
			}
		}
	}
	
}

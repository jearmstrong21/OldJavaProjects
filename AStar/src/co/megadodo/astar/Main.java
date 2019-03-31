package co.megadodo.astar;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		JFrame frame = new JFrame("A*");
		frame.setSize(AStarPanel.W*AStarPanel.GRID_SIZE,AStarPanel.H*AStarPanel.GRID_SIZE);
		frame.add(new AStarPanel());
		frame.setVisible(true);
	}

}

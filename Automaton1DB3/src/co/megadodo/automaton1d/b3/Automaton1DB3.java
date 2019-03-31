package co.megadodo.automaton1d.b3;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Automaton1DB3 extends JFrame {

	public static void main(String[] args) {

		new Automaton1DB3();

	}

	public static int W = 700;
	public static int H = 700;
	public static int FPS = 1;
	public static Color BACKGROUND_COLOR = Color.BLACK;
	public static int NUM_FRAMES = 0;
	static int s = 10;
	static int CELL_NUM = W/s;
	static int ROW_NUM = H/s;
	static int CELL_W = W / CELL_NUM;
	static int ROW_H = H / ROW_NUM;
	int[][] cells = new int[CELL_NUM][ROW_NUM];
	static int[] rules;
	static int ruleNum = 27;
	
	void recalcRules() {
		for(int i = 0; i < 27; i++) rules[i] = (int)(Math.random()*3);
//		for(int i = 0; i < 27; i++) rules[i] = 0;
		ruleNum = 0;
		for(int i = 1; i <= 27; i++) {
			ruleNum += rules[27 - i]*Math.pow(rules[27-i], i);
		}
		System.out.print(ruleNum + " : ");
		for(int i = 0; i < 27; i++) System.out.print(rules[i]);
		System.out.println();
	}
	void recalcCells() {
		for(int x = 0; x < CELL_NUM; x++) {
			cells[x][0] = 0;
		}
		cells[CELL_NUM/2][0] = 2;
		
		for(int r = 1; r < ROW_NUM; r++) {
			for(int x = 1; x < CELL_NUM - 1; x++) {
				cells[r][x] = rules[26 - cells[r-1][x-1]*9 - cells[r-1][x]*3 - cells[r-1][x+1]];
			}
		}
	}

	public Automaton1DB3() {
		this.setTitle("A");
		this.setVisible(true);
		this.setSize(W, H);

		rules = new int[27];
		recalcRules();
		recalcCells();

		Automaton1DB3 self = this;
		Timer t = new Timer(1000 / FPS, e -> {
			self.repaint();
		});
		t.start();
	}
	//212002002201010002120102102 - think it was this
	//200220102200220002110010100

//	Color[] list = new Color[] {
//			Color.BLUE,
//			Color.BLACK,
//			Color.RED
//	};
	Color[] list = new Color[] {
			Color.WHITE,
			new Color(255,255,255,255/2),
			Color.BLACK
	};
	
	int n = 3;
	public void paint(Graphics g) {
		NUM_FRAMES++;
		System.out.println(NUM_FRAMES);
		if(NUM_FRAMES % n == 1) {
//		if(true) {
			System.out.println(NUM_FRAMES + " " + NUM_FRAMES);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(BACKGROUND_COLOR);
			g2d.fillRect(0, 0, W, H);
			for(int x = 0; x < CELL_NUM; x++) {
				for(int y = 0; y < ROW_NUM; y++) {
					int z = cells[x][y];
					if(z == 0) g2d.setColor(list[0]);
					if(z == 1) g2d.setColor(list[1]);
					if(z == 2) g2d.setColor(list[2]);
					g2d.fillRect(x*CELL_W, y*ROW_H, CELL_W, ROW_H);
				}
			}
			recalcRules();
			recalcCells();
		}
	}

}

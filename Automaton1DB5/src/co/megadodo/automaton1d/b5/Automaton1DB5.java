package co.megadodo.automaton1d.b5;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Automaton1DB5 extends JFrame {

	public static void main(String[] args) {

		new Automaton1DB5();

	}

	public static int W = 800;
	public static int H = 800;
	public static int FPS = 1;
	public static Color BACKGROUND_COLOR = Color.BLACK;
	public static int NUM_FRAMES = 0;
	static int s = 20;
	static int CELL_NUM = W/s;
	static int ROW_NUM = H/s;
	static int CELL_W = W / CELL_NUM;
	static int ROW_H = H / ROW_NUM;
	int[][] cells = new int[CELL_NUM][ROW_NUM];
	static int[] rules;
	static int ruleNum = 0;
	
	
	void recalcRules() {
		for(int i = 0; i < 3125; i++) rules[i] = (int)(Math.random()*5);
//		for(int i = 0; i < 3125; i++) rules[i] = 0;
		ruleNum = 0;
		for(int i = 1; i <= 3125; i++) {
			ruleNum += rules[3125 - i]*Math.pow(rules[3125-i], i);
		}
		System.out.print(ruleNum + " : ");
		for(int i = 0; i < 3125; i++) System.out.print(rules[i]);
		System.out.println();
	}
	void recalcCells() {
		for(int x = 0; x < CELL_NUM; x++) {
			cells[x][0] = 0;
		}
		cells[CELL_NUM/2][0] = 4;
		
		for(int r = 1; r < ROW_NUM; r++) {
			for(int x = 2; x < CELL_NUM - 2; x++) {
				cells[r][x] = rules[3124 - cells[r-1][x-2]*625 - cells[r-1][x-1]*125 - cells[r-1][x]*25 - cells[r-1][x+1]*5 - cells[r-1][x+2]];
			}
		}
	}

	public Automaton1DB5() {
		this.setTitle("A");
		this.setVisible(true);
		this.setSize(W, H);

		rules = new int[3125];
		recalcRules();
		recalcCells();

		Automaton1DB5 self = this;
		Timer t = new Timer(1000 / FPS, e -> {
			self.repaint();
		});
		t.start();
	}
	//212002002201010002120102102 - think it was this
	//200220102200220002110010100

//	Color[] list = new Color[] { new Color(255,255,255,255),
//							     new Color(255,255,255,204),
//							     new Color(255,255,255,153),
//							     new Color(255,255,255,102),
//							     new Color(255,255,255,51)
//							   };
	Color[] list = new Color[] { Color.WHITE,
								 Color.YELLOW,
								 Color.ORANGE,
								 Color.BLUE,
								 Color.BLACK
							   };
	
	int n = 2;
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
					if(z == 3) g2d.setColor(list[3]);
					if(z == 4) g2d.setColor(list[4]);
					g2d.fillRect(x*CELL_W, y*ROW_H, CELL_W, ROW_H);
				}
			}
			recalcRules();
			recalcCells();
		}
	}

}

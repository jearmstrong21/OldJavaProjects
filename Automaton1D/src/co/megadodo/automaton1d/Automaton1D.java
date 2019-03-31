package co.megadodo.automaton1d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Automaton1D extends JFrame {

	public static void main(String[] args) throws Throwable {

		new Automaton1D();

	}

	public static int W = 1400;
	public static int H = 725;
	public static int FPS = 1;
	public static Color BACKGROUND_COLOR = Color.BLACK;
	public static int NUM_FRAMES = 0;
	
	public static int n = 1;
	public static int CELL_NUM = W/n;
	public static int CELL_W = W / CELL_NUM;
	public static int ROW_NUM = H/n;
	public static int ROW_H = H / ROW_NUM;
	
	public static int ruleNum = 110;

	public static byte getRuleBit(int pos)
	{
//		String base3 = Integer.toString(ruleNum, 3);
//		return (byte) Integer.parseInt(base3.charAt(pos) + " ");
	   return (byte) ((ruleNum >> pos) & 1);
	}
	
	public static byte[] getRuleBits() {
		byte[] bits = new byte[8];
		for(int i = 0; i < 8; i++) {
			bits[i] = getRuleBit(7-i);
		}
		return bits;
	}
	
	public byte getCell(int x, int r) {
//		if(x < 0 || r < 0 || x >= CELL_NUM || r >= CELL_NUM) return 0;
		return cells[r][x];
	}
	
	byte[][] cells = new byte[ROW_NUM][CELL_NUM];
	
	public Automaton1D() throws Throwable {
		
		byte[] bits = getRuleBits();
		
		for(int i = 0; i < CELL_NUM; i++) {
			cells[0][i] = 0;
		}
		cells[0][CELL_NUM/2]=1;
		
		for(int row = 1; row < ROW_NUM; row++) {
			for(int x = 1; x < CELL_NUM-1; x++) {
				cells[row][x] = bits[7 - cells[row-1][x-1]*4 - cells[row-1][x]*2 - cells[row-1][x+1]];
			}
		}
		
		PrintWriter writer = new PrintWriter(new File("output.txt"));
		if(CELL_NUM > 30 || ROW_NUM>30) { writer.println("Too large of a data-set to write to file."); writer.close(); }
		else {
			for(int x = 0; x < CELL_NUM; x++) { 
				for(int y = 0; y < ROW_NUM; y++) {
					writer.print(cells[y][x] + " ");
				}
				writer.println();
			}
			writer.close();
		}
		
		this.setTitle("Automaton1D");
		this.setVisible(true);
		this.setSize(W, H);

		Automaton1D self = this;
		Timer t = new Timer(1000 / FPS, e -> {
			self.repaint();
		});
		t.start();
	}

	public void paint(Graphics g) {
		System.out.println(ruleNum);
		NUM_FRAMES++;
		
////		ruleNum = (int)(Math.random()*256);
////		ruleNum++;
//		
//		byte[] bits = getRuleBits();
//		
//		for(int i = 0; i < CELL_NUM; i++) {
//			cells[0][i] = 0;
//		}
//		cells[0][CELL_NUM/2]=1;
//		
//		for(int row = 1; row < ROW_NUM; row++) {
//			for(int x = 1; x < CELL_NUM-1; x++) {
//				cells[row][x] = bits[7 - cells[row-1][x-1]*4 - cells[row-1][x]*2 - cells[row-1][x+1]];
//			}
//		}
		if(NUM_FRAMES>3)return;
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(0,25);
		g2d.setColor(BACKGROUND_COLOR);
		g2d.fillRect(0, 0, W, H);
		for(int x = 0; x < CELL_NUM; x++) {
			for(int y = 0; y < ROW_NUM; y++) {
				byte cell = getCell(x,y);
				if(cell == 0) g2d.setColor(Color.WHITE);
				if(cell == 1) g2d.setColor(Color.BLACK);
				g2d.fillRect(x*CELL_W, y*ROW_H, CELL_W, ROW_H);
				if(n < 3) continue;
				g2d.setColor(Color.GRAY);
				g2d.drawRect(x*CELL_W,y*ROW_H,CELL_W,ROW_H);
			}
		}
		
//		ruleNum++;
//		byte[] bits = getRuleBits();
//		
//		for(int i = 0; i < CELL_NUM; i++) {
//			cells[0][i] = 0;
//		}
//		cells[0][CELL_NUM/2]=1;
//		
//		for(int row = 1; row < ROW_NUM; row++) {
//			for(int x = 1; x < CELL_NUM-1; x++) {
//				cells[row][x] = bits[7 - cells[row-1][x-1]*4 - cells[row-1][x]*2 - cells[row-1][x+1]];
//			}
//		}
		
	}

}

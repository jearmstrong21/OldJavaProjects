package co.megadodo.mackycheese.game;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import co.megadodo.mackycheese.framework.Entity;
import co.megadodo.mackycheese.framework.Game;
import co.megadodo.mackycheese.framework.GameRunner;
import co.megadodo.mackycheese.framework.GameSettings;
import co.megadodo.mackycheese.framework.Pair;
import co.megadodo.mackycheese.framework.Utils;

@SuppressWarnings("serial")
public class MyGame extends Game {
	// slows down noticably when GRID_CELL_W * GRID_CELL_H = around 3000
	// 50x50 is recommended
	public static int CELL_SIZE = 10;
	public static int GRID_CELL_W = 50;
	public static int GRID_CELL_H = 50;
	
	private Cell[][] cells;
	private int frameDiff = 1;
	private boolean paused = false;
	
	// UI
	private JFrame optionsFrame;
	
	public static void main(String[] args) {
		GameRunner runner = new GameRunner("Game");
		
		runner.run(new MyGame());
	}
	
	void initUI() {
		optionsFrame = new JFrame("Options");
		optionsFrame.setSize(200, 200);
		optionsFrame.setVisible(true);
	}

	public MyGame() {
		initUI();
		
		cells = new Cell[GRID_CELL_W][GRID_CELL_H];
		for(int x = 0; x < cells.length; x++) {
			for(int y = 0; y < cells[x].length; y++) {
				cells[x][y] = new Cell(this, x, y, x+" "+y);
				cells[x][y].randomOn();
				this.entities.add(cells[x][y]);
			}
		}
	}
	
	
	
	void print(String s)
	{
		System.out.print(s);
	}
	void println(String s)
	{
		print(s);
		print("\n");
	}
	
	Cell getCell(int x, int y) {
		if(x < 0 || x > cells.length-1 || y < 0 || y > cells[0].length-1) return new Cell(this, x, y, "undefined");
		return cells[x][y];
	}
	public static String shortUUID() {
		  UUID uuid = UUID.randomUUID();
		  long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
		  return Long.toString(l, Character.MAX_RADIX);
		}
	
	void updateCells() {
		for(int x = 0; x < cells.length; x++) {
			for(int y = 0; y < cells[x].length; y++) {
				Cell[] grid = new Cell[8];
				grid[7] = getCell(x-1,y-1);
				grid[6] = getCell(x  ,y-1);
				grid[5] = getCell(x+1,y-1);

				grid[4] = getCell(x-1,y);
				grid[3] = getCell(x+1,y);

				grid[2] = getCell(x-1,y+1);
				grid[1] = getCell(x  ,y+1);
				grid[0] = getCell(x+1,y+1);
				BitSet bits = new BitSet(8);
				for(int c=0;c<8;c++) {
					bits.set(c, grid[c].isOn());
				}
				byte neighbors = 0;
				for(int i = 0; i < bits.length(); i++) {
					if(bits.get(i)) {
						neighbors += Math.pow(2, i);
					}
				}
				cells[x][y].update(neighbors);
			}
		}
		for(int x = 0; x < cells.length; x++) {
			for(int y = 0; y < cells[x].length; y++) {
				cells[x][y].applyNextOn();
			}
		}
	}
	
	public void update() {
		super.update();
		if(paused) frames--;
		if(frames % frameDiff == 0 && !paused) {
			updateCells();
			if(frameDiff < 0) {
				for(int i = 0; i < Math.abs(frameDiff)-1; i++) {
					updateCells();
					println("extra update");
				}
			}
		}
	}
	
	public void keyPressed(KeyEvent evt)
	{
		int diff = 10;
		if(evt.getKeyCode() == KeyEvent.VK_UP  ) frameDiff -= diff;
		else if(evt.getKeyCode() == KeyEvent.VK_DOWN) frameDiff += diff;
		if(evt.getKeyChar() == 'R' || evt.getKeyChar() == 'r') {
			for(Cell[] ar : cells) {
				for(Cell c : ar) {
					c.randomOn();
				}
			}
			frames = 0;
		}
		if(evt.getKeyChar() == ' ' || evt.getKeyChar() == 'p' || evt.getKeyChar() == 'P') {
			paused = !paused;
		}
		if(evt.getKeyChar() == 'c' || evt.getKeyChar() == 'C') {
			for(Cell[] ar : cells) {
				for(Cell c : ar) {
					c.clear();
				}
			}
		}
		if(evt.getKeyChar() == 's' || evt.getKeyChar() == 'S') {
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage capture;
			try {
				capture = new Robot().createScreenCapture(screenRect);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				capture = null;
			}
			try {
				ImageIO.write(capture, "png", new File("screenshot " + shortUUID() + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		println(frameDiff+" ");
	}
	
	void activateCell(int x, int y) {
		if(x < 0 || x > GRID_CELL_W-1 || y < 0 || y > GRID_CELL_H-1) {
			return;
		}
		cells[x][y].activate();;
	}
	
	void deactivateCell(int x, int y) {
		if(x < 0 || x > GRID_CELL_W-1 || y < 0 || y > GRID_CELL_H-1) {
			return;
		}
		cells[x][y].deactivate();
	}
	
	public void mousePressed(MouseEvent evt)
	{
		super.mousePressed(evt);
		if(evt.getButton() == MouseEvent.BUTTON3 || evt.isShiftDown()) {
			int mbx = evt.getX() / CELL_SIZE; // mouse blip x
			int mby = evt.getY() / CELL_SIZE; // mouse blip y
			/*
			_X_
			__O
			OOO
			 */
			activateCell(mbx, mby);
			activateCell(mbx+1, mby+1);
			activateCell(mbx-1, mby+2);
			activateCell(mbx, mby+2);
			activateCell(mbx+1, mby+2);
			
		}
	}
	
	public void mouseDragged(MouseEvent evt)
	{
		mousePressed(evt);
	}
	
	public void mouseMoved(MouseEvent evt)
	{
				
		
	}
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
		g2d.setColor(Color.WHITE);
		int size = g2d.getFont().getSize();
		g2d.drawString("speed: " + frameDiff, 0, size*1);
		g2d.drawString("frame: " + frames   , 0, size*2);
	}
	
	
}

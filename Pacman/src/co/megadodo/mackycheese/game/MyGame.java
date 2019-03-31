package co.megadodo.mackycheese.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import co.megadodo.mackycheese.framework.Game;
import co.megadodo.mackycheese.framework.GameRunner;

@SuppressWarnings("serial")
public class MyGame extends Game {
	public static final int MOVE_TIME = 4;
	
	public static final Image maze = Toolkit.getDefaultToolkit().getImage("graphics/maze.png");
	
	public static void main(String[] args) {
		GameRunner runner = new GameRunner("Game");
		
		runner.run(new MyGame());
	}
	int[] x = { 1 , 6 , 12 ,  15 , 21 , 26 ,  1 , 6 , 9 , 12 , 15 , 18,21,26,  1,6,9,12,15,18,21,26,    9 , 12, 15, 18,    6 , 9 , 18, 21,  9 ,18,   1 , 6 ,9 ,12,15,18,21,26,    1 , 3 , 6 ,12,15,21,24,26,     1 ,3 ,6 ,9 ,12,15,18,21,24,26,    1 ,12,15,26};
	int[] y = { 1 , 1 , 1  ,  1  , 1  , 1  ,  5 , 5 , 5 , 5  , 5  , 5, 5 ,5 ,  8,8,8,8 ,8 ,8 ,8 ,8 ,    11, 11, 11, 11,    14, 14, 14, 14,  17,17,   20, 20,20,20,20,20,20,20,    23, 23, 23,23,23,23,23,23,     26,26,26,26,26,26,26,26,26,26,    29,29,29,29};

	
	
	public MyGame() {
		this.addEntity(new EntityPacman(this));
		
	}

	
	public void update() {
		super.update();
	}

	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(maze, 0, -16, this);
		for(int i = 0; i < x.length; i++) {
			g2d.setColor(Color.GREEN);
			g2d.fillRect(x[i]*16, y[i]*16, 16, 16);
		}
		super.draw(g2d);
	}
	
	public boolean isControlPoint(int entX, int entY) {
		for(int i = 0; i < x.length; i++) {
			if(Math.abs(x[i]*16 - entX) < 16 && Math.abs(y[i]*16 - entY) < 16) return true;
		}
		return false;
	}

	
	public void mousePressed(MouseEvent evt)
	{
		
	}
	
	public void mouseDragged(MouseEvent evt)
	{
		mousePressed(evt);
		mouseMoved(evt);
	}
	
	public void mouseMoved(MouseEvent evt)
	{
			
	}
	
	
	void print(String s)
	{
		System.out.println(s);
	}
}

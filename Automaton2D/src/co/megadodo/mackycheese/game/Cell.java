package co.megadodo.mackycheese.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.BitSet;

import co.megadodo.mackycheese.framework.Entity;
import co.megadodo.mackycheese.framework.Game;

public class Cell extends Entity {

	private boolean on = false;
	private boolean nextOn = false;
	private int cellX, cellY;
	
	public Cell(Game g, int cellX, int cellY, String name) {
		super(g, cellX * MyGame.CELL_SIZE, cellY * MyGame.CELL_SIZE, MyGame.CELL_SIZE, MyGame.CELL_SIZE, name);
		
		this.cellX = cellX;
		this.cellY = cellY;
	}
	
	public boolean isOn() {
		return on;
	}
	
	public void randomOn() {
//		this.setOn(Math.random() < 0.083);
//		this.setOn(Math.random() < 0.125);
		this.setOn(Math.random() < 0.5);
		this.nextOn = this.on;
	}
	
	public void clear() {
		on = false;
		nextOn = false;
	}
	
	public void setOn(boolean on) {
		this.on = on;
	}
	public static BitSet fromByte(byte b)
	{
	    BitSet bits = new BitSet(8);
	    for (int i = 0; i < 8; i++)
	    {
	        bits.set(i, (b & 1) == 1);
	        b >>= 1;
	    }
	    return bits;
	}
	public void update(byte neighbors) {
		super.update();
		BitSet fromByte = fromByte(neighbors);
		int onNeighbors = 0;
		for(int c = 0; c < fromByte.length(); c++) if(fromByte.get(c)) onNeighbors++;
		
		
		if(on) {
			if(onNeighbors < 2) {nextOn = false;} // under population
			if(onNeighbors > 3) {nextOn = false;} // over population
			// lives
		} else {
			if(onNeighbors == 3) nextOn = true; // rebirth
		}
	}
	
	public void applyNextOn() {
		this.on = this.nextOn;
	}
	
	public void update() {
		super.update();
	}
	
	public void draw(Graphics2D g2d) {
		super.draw(g2d);
		if(on) g2d.setColor(Color.GREEN);
		else   g2d.setColor(Color.BLACK);
		g2d.fillRect(posX, posY, MyGame.CELL_SIZE, MyGame.CELL_SIZE);
	}
	
	public void activate() {
		on = nextOn = true;
	}
	public void deactivate() {
		on = nextOn = false;
	}
	
	public void mouseClicked(MouseEvent evt) {
		super.mouseClicked(evt);
		if(evt.getButton() == MouseEvent.BUTTON1 && !evt.isShiftDown()) {
			int mx = evt.getX();
			int my = evt.getY();
			
			if(mx / MyGame.CELL_SIZE == cellX && my / MyGame.CELL_SIZE == cellY) {
				if(!this.on)
				{
					this.activate();
				}
				else {
					this.deactivate();
				}
			}
		}
	}

}

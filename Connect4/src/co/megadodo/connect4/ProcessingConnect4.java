package co.megadodo.connect4;

import processing.core.PApplet;

public class ProcessingConnect4 extends PApplet {
	
	public static void main(String[] args) {
		PApplet.runSketch(new String[] {"co.megadodo.connect4.ProcessingConnect4"}, new ProcessingConnect4());
	}
	
	public void settings() {
		size(750,500);
	}
	
	public void setup() {
		
	}
	
	public void draw() {
		background(255);
	}

}

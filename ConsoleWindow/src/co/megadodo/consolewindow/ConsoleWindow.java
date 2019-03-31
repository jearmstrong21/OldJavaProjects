package co.megadodo.consolewindow;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;

public class ConsoleWindow extends PApplet {
	
	int w;
	int h;
	int size;
	String title;
	boolean alwaysOnTop;
	int maxLines;
	
	int offsetX = 0;
	int offsetY = 0;
	
	public ConsoleWindow() {
		this(0,0,10,"Console",false);
	}
	
	public static ConsoleWindow create(int w, int h, String title) {
		return new ConsoleWindow(w,h,15,title,true);
	}
	
	public ConsoleWindow(int w, int h, int s, String title, boolean b) {
		this.w = w;
		this.h = h;
		this.size = s;
		this.title = title;
		this.alwaysOnTop = b;
		this.maxLines = (h - offsetY) / size;
		PApplet.runSketch(new String[] {"co.megadodo.consolewindow.ConsoleWindow"}, this);
	}
	
	public void settings() {
//		surface.setResizable(true);
//		surface.setSize(w, h);
//		surface.setTitle(title);
//		surface.setAlwaysOnTop(alwaysOnTop);
//		hide();
//		
//		PFont font = loadFont("Monospaced-50.vlw");
//		textFont(font);
//		textAlign(LEFT,TOP);
//		textSize(size);
	}
	
	public void setup() {
		surface.setResizable(true);
		surface.setSize(w, h);
		surface.setTitle(title);
		surface.setAlwaysOnTop(alwaysOnTop);
		
		textFont(createFont("Monospaced", size));
		textAlign(LEFT,TOP);
		textSize(size);
		lines.add("");
	}
	
	ArrayList<String> lines = new ArrayList<String>();
	
	public void draw() {
		fill(0);
		rect(-10,-10,width+10,height+10);
		fill(255);
		stroke(255);
		for(int i = 0; i < lines.size(); i++) {
			text(lines.get(i), offsetX, size*i+offsetY);
		}
	}
	
	public void console_clear() {
		lines = new ArrayList<String>();
		lines.add("");
	}
	
	public void console_print(String str) {
		if(lines.size() == 0) lines.add("");
		int size = lines.size();
		lines.set(size-1, lines.get(size-1) + str);
	}
	
	public void console_println() {
		console_println("");
	}
	
	public void console_println(String str) {
		console_print(str);
		lines.add("");
		if(lines.size() > maxLines) lines.remove(0);
	}
	
	public void show() {
		if(surface != null) surface.setVisible(true);
	}
	
	public void hide() {
		if(surface != null) surface.setVisible(false);
	}
}

package co.megadodo.superchess1;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;

public class JWriterWindow extends PApplet implements JWriter {

	public int textSize = 15;
	
	public JWriterWindow() {
		maxLines = 600 / textSize;
		System.out.println("Max lines: " + maxLines);
		PApplet.runSketch(new String[] {"co.megadodo.superchess1.JWriterWindow"}, this);
	}
	
	PFont font;
	
	public void settings() {
	}
	
	ArrayList<String> lines = null;
	
	public void setup() {
//		hide();
		font = loadFont("Monospaced-50.vlw");
		textFont(font);

		surface.setResizable(true);
		surface.setSize(600,600);
		surface.setTitle("Console");
		surface.setAlwaysOnTop(true);
	}
	
	public void clear() {
		lines.clear();
	}
	
	int maxLines;
	
	public void show() {
		surface.setVisible(true);
	}
	
	public void hide() {
		surface.setVisible(false);
	}
	
	public void draw() {
		background(0);
		stroke(255);
		fill(255);
		textAlign(LEFT,TOP);
		textSize(textSize);
		for(int i=0;i<lines.size();i++){
			text(lines.get(i),0,i*textSize);
		}
	}

	@Override
	public void jprintln() {
		if(lines == null) 
			lines = new ArrayList<String>();
		lines.add("");
		if(lines.size()>maxLines){
			lines.remove(0);
		}
	}
	
	@Override
	public void jprint(String str) {
		if(lines == null) {
			lines = new ArrayList<String>();
		}
		if(lines.size()==0)lines.add("");
		String s = lines.get(lines.size()-1);
		lines.remove(lines.size()-1);
		s+=str;
		lines.add(s);
	}

	@Override
	public void jprintln(String str) {
		if(lines == null) 
			lines = new ArrayList<String>();
		jprint(str);
		lines.add("");
		if(lines.size() > maxLines) {
			lines.remove(0);
		}
	}

}

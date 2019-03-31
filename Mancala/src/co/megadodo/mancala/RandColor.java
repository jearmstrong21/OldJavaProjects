package co.megadodo.mancala;

import processing.core.PApplet;

public class RandColor {

	public float r;
	public float g;
	public float b;
	public float randR;
	public float randG;
	public float randB;
	
	public void update(PApplet applet) {
		r+=applet.random(-randR,randR);
		g+=applet.random(-randG,randG);
		b+=applet.random(-randB,randB);
		r = PApplet.constrain(r,0,255);
		g = PApplet.constrain(g,0,255);
		b = PApplet.constrain(b,0,255);
	}
	
	public void draw(PApplet applet) {
		applet.fill(r,g,b);
	}
	
}

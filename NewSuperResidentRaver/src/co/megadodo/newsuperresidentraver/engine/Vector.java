package co.megadodo.newsuperresidentraver.engine;

public class Vector {

	public int x=0,y=0;
	public Vector(int x,int y) { this.x=x;this.y=y; }
	
	
	public static Vector plus(Vector a, Vector b) {
		return new Vector(a.x+b.x,a.y+b.y);
	}
	
	public static Vector zero = new Vector(0,0);
}

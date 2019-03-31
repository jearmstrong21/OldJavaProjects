package co.megadodo.astar;

public class Pos {

	public int f,g,h,x,y;
	public PosType type;
	public Pos() {
		this(-1,-1);
	}
	
	public Pos(int a, int b) {
		x=a;
		y=b;
		f=g=h=0;
		type=PosType.EMPTY;
	}
	
}

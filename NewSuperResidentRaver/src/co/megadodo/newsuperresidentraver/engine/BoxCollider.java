package co.megadodo.newsuperresidentraver.engine;

public class BoxCollider {
	public Vector pos;
	public Vector size;
	
	public BoxCollider(Vector p, Vector s) {
		pos = p;
		size = s;
	}
	
	public Vector topRight() {
		return new Vector(size.x,pos.y);
	}
	
	public Vector bottomRight() {
		return Vector.plus(pos,size);
	}
	
	public Vector bottomLeft() {
		return new Vector(pos.x,size.y);
	}
	public boolean pointInside(Vector point) {
		return point.x > pos.x && point.x < pos.x+size.x && point.y>pos.y && point.y<pos.y+size.y;
	}
	
	public boolean intersects(BoxCollider other) {
		return pointInside(other.pos) || pointInside(other.bottomLeft()) || pointInside(other.bottomRight()) || pointInside(other.topRight());
	}
}

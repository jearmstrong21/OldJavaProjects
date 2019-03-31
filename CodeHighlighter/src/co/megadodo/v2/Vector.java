package co.megadodo.v2;

/**
 * 
 * @author jackarmstrong
 *
 */
public class Vector implements Cloneable {
	
	float x;
	float y;
	float z;
	
	public Vector() {
		this( 0, 0, 0);
	}
	
	public Vector (float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void set (float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector copy() {
		return clone();
	}
	
	public Vector clone() {
		return new Vector(x, y, z);
	}
	
	public float dot (float _x, float _y, float _z) {
		return dot(new Vector(_x, _y, _z));
	}
	
	public float dot (Vector other) {
		return Vector.dot(this, other);
	}
	
	public static float dot (Vector a, Vector b) {
		return a.x * b.x + a.y * b.y + a.z * b.z;
	}
	
	@Override
	public String toString() {
		return "[" + x + ", " + y + ", " + z + "]" ;
	}
	
}
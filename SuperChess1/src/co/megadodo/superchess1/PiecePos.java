package co.megadodo.superchess1;

public class PiecePos {
	
	public int x,y;
	public PiecePos(int a,int b){
		x=a;
		y=b;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PiecePos other = (PiecePos) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PiecePos [x=" + x + ", y=" + y + "]";
	}
	
	

}

package co.megadodo.chess.framework;

public class PiecePos {
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


	public int x,y;
	
	public PiecePos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void assignLeft() {
		x--;
	}
	
	public void assignRight() {
		x++;
	}
	
	public void assignUp() {
		y--;
	}
	
	public void assignDown() {
		y++;
	}
	
	public void assignTopLeft() {
		x--;
		y--;
	}
	
	public void assignTopRight() {
		x--;
		y++;
	}
	
	public void assignBottomLeft() {
		x++;
		y--;
	}
	
	public void assignBottomRight() {
		x++;
		y++;
	}
	
	public PiecePos getLeft() {
		return new PiecePos(x-1,y);
	}
	
	public PiecePos getRight() {
		return new PiecePos(x+1,y);
	}
	
	public PiecePos getUp() {
		return new PiecePos(x,y-1);
	}
	
	public PiecePos getDown() {
		return new PiecePos(x,y+1);
	}
	
	public PiecePos getTopLeft() {
		return new PiecePos(x-1,y-1);
	}
	
	public PiecePos getTopRight() {
		return new PiecePos(x-1,y+1);
	}
	
	public PiecePos getBottomLeft() {
		return new PiecePos(x+1,y-1);
	}
	
	public PiecePos getBottomRight() {
		return new PiecePos(x+1,y+1);
	}
	
	
	public String toString() {
		char letter = 'a';
		switch(x){
			case 0: letter='a'; break;
			case 1: letter='b'; break;
			case 2: letter='c'; break;
			case 3: letter='d'; break;
			case 4: letter='e'; break;
			case 5: letter='f'; break;
			case 6: letter='g'; break;
			case 7: letter='h'; break;
		}
		return Character.toString(letter) + Integer.toString(8-y+1);
	}
	
	public boolean outBounds() {
		return !inBounds();
	}
	
	public boolean inBounds() {
		return x>=0 && y>=0 && x < 8 && y < 8;
	}
}

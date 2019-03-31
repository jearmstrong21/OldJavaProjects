package co.megadodo.connect4;

public enum Piece {
	R,Y,EMPTY;
	public String toString() {
		if(this == R) return "R";
		if(this == Y) return "Y";
		return "_";
	}
	public static Piece parse(String str) {
		if(str.equals("R")) return Piece.R;
		if(str.equals("Y")) return Piece.Y;
		return Piece.EMPTY;
	}
	public static Piece fromPlayer(int playerID) {
		if(playerID == 0) return Piece.Y;
		if(playerID == 1) return Piece.R;
		return Piece.EMPTY;
	}
}

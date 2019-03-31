package co.megadodo.superchess1;

public enum PieceType {

	Pawn,
	Knight,
	Queen,
	Bishop,
	King,
	Rook;
	
	public String toString() {
		if(this == Pawn) return "P";
		if(this == Knight) return "N";
		if(this == Queen) return "Q";
		if(this == Bishop) return "B";
		if(this == King) return "K";
		if(this == Rook) return "R";
		return "E";
	}
	
}

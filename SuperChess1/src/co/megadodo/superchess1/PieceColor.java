package co.megadodo.superchess1;

public enum PieceColor {
	
	White,
	Black;
	
	public String toString() {
		if(this == White) return "W";
		if(this == Black) return "B";
		return "E";
	}
	
	public PieceColor opp() {
		if(this == White) return Black;
		return White;
	}

}

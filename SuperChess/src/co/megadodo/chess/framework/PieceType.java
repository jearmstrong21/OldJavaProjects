package co.megadodo.chess.framework;

public enum PieceType {
	KING(0,"King",2000),
	QUEEN(1,"Queen",3500),
	BISHOP(2,"Bishop",1000),
	KNIGHT(3,"Knight",600),
	ROOK(4,"Rook",1500),
	PAWN(5,"Pawn",400);

	private int index;
	private String asStr;
	private int cooldownMillis;
	
	PieceType(int index, String asStr, int cooldown) {
		this.index = index;
		this.asStr = asStr;
		this.cooldownMillis = cooldown;
	}
	
	public int getIndex() {
		return index;
	}
	
	public String toString() {
		return asStr;
	}
	
	public int getCooldown() {
		return cooldownMillis;
	}
	
	public char getChar() {
		if(this == KNIGHT) return 'N';
		else return asStr.charAt(0);
	}
	
	public String getCharAsStr() {
		return Character.toString(getChar());
	}
}

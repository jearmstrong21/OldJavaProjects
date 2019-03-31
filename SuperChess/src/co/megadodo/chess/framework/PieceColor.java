package co.megadodo.chess.framework;

public enum PieceColor {
	WHITE(0,"White"),
	BLACK(1,"Black");
	private int index;
	private String asStr;
	
	PieceColor(int index, String asStr) {
		this.index = index;
		this.asStr = asStr;
	}
	
	public int getIndex() {
		return index;
	}
	
	public String toString() {
		return asStr;
	}
	
	public boolean isFriendly(PieceColor col) {
		if(col==null)return true;
		return this == col;
	}
	
	public boolean isEnemy(PieceColor col) {
		return !isFriendly(col);
	}
	
	public boolean isFriendlyNoNull(PieceColor col) {
		if(col == null) return false;
		return this == col;
	}
	
	public boolean isEnemyNoNull(PieceColor col) {
		if(col == null) return false;
		return !isFriendlyNoNull(col);
	}
}

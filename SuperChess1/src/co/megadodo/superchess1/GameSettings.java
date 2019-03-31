package co.megadodo.superchess1;

public class GameSettings {
	
	// times are in milliseconds
	public int timePawn;
	public int timeRook;
	public int timeBishop;
	public int timeKnight;
	public int timeKing;
	public int timeQueen;
	
	public static GameSettings normal = new GameSettings();
	
	static {
		normal.timePawn = 750;
		normal.timeRook = 6000;
		normal.timeBishop = 5000;
		normal.timeKnight = 7500;
		normal.timeKing = 1500;
		normal.timeQueen = 10000;
	}
	
	public GameSettings() {
		timePawn=timeRook=timeBishop=timeKnight=timeKing=timeQueen=0;
	}
	
	public int timeForPiece(Piece p) {
		if(p==null)return 0;
		if(p.isEmptyPiece())return 0;
		switch(p.type) {
			case Bishop: return timeBishop;
			case King: return timeKing;
			case Knight: return timeKnight;
			case Pawn: return timePawn;
			case Queen: return timeQueen;
			case Rook: return timeRook;
		}
		return 0;
	}

}

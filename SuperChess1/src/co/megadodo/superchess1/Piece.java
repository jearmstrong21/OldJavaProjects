package co.megadodo.superchess1;

public class Piece {

	public static Piece WP = new Piece(PieceType.Pawn,PieceColor.White);
	public static Piece WK = new Piece(PieceType.King,PieceColor.White);
	public static Piece WN = new Piece(PieceType.Knight,PieceColor.White);
	public static Piece WR = new Piece(PieceType.Rook,PieceColor.White);
	public static Piece WB = new Piece(PieceType.Bishop,PieceColor.White);
	public static Piece WQ = new Piece(PieceType.Queen,PieceColor.White);

	public static Piece BP = new Piece(PieceType.Pawn,PieceColor.Black);
	public static Piece BK = new Piece(PieceType.King,PieceColor.Black);
	public static Piece BN = new Piece(PieceType.Knight,PieceColor.Black);
	public static Piece BR = new Piece(PieceType.Rook,PieceColor.Black);
	public static Piece BB = new Piece(PieceType.Bishop,PieceColor.Black);
	public static Piece BQ = new Piece(PieceType.Queen,PieceColor.Black);
	
	public static Piece empty = new Piece(null, null);
	public boolean isEmptyPiece() {
		return type == null || col == null;
	}
	
	public PieceType type;
	public PieceColor col;
	
	public Piece(PieceType type, PieceColor col) {
		this.type = type;
		this.col = col;
	}
	
	public String toString() {
		if(isEmptyPiece()) return "  ";
		return col.toString() + type.toString();
	}

}

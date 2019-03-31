package co.megadodo.chess.framework;

public class Piece {
	PieceColor color;
	PieceType type;
	PiecePos pos;
	int timeTillMove;
	
	public static Piece WHITE_KING = new Piece(PieceColor.WHITE, PieceType.KING, null); 
	public static Piece WHITE_QUEEN = new Piece(PieceColor.WHITE, PieceType.QUEEN, null); 
	public static Piece WHITE_BISHOP = new Piece(PieceColor.WHITE, PieceType.BISHOP, null); 
	public static Piece WHITE_KNIGHT = new Piece(PieceColor.WHITE, PieceType.KNIGHT, null); 
	public static Piece WHITE_ROOK = new Piece(PieceColor.WHITE, PieceType.ROOK, null);
	public static Piece WHITE_PAWN = new Piece(PieceColor.WHITE, PieceType.PAWN, null); 
	
	public static Piece BLACK_KING = new Piece(PieceColor.BLACK, PieceType.KING, null); 
	public static Piece BLACK_QUEEN = new Piece(PieceColor.BLACK, PieceType.QUEEN, null); 
	public static Piece BLACK_BISHOP = new Piece(PieceColor.BLACK, PieceType.BISHOP, null); 
	public static Piece BLACK_KNIGHT = new Piece(PieceColor.BLACK, PieceType.KNIGHT, null); 
	public static Piece BLACK_ROOK = new Piece(PieceColor.BLACK, PieceType.ROOK, null);
	public static Piece BLACK_PAWN = new Piece(PieceColor.BLACK, PieceType.PAWN, null); 
	
	
	public String toString() {
		return color.toString().charAt(0) + type.getCharAsStr();
	}
	
	public Piece() {
		this(null,null,null);
	}
	
	public Piece(PieceColor color, PieceType type, PiecePos pos) {
		this.color = color;
		this.type = type;
		this.pos = pos;
		this.timeTillMove = type.getCooldown();
	}
	
	public void update() {
		timeTillMove--;
		if(timeTillMove<0)timeTillMove=0;
	}

	public int getTimeTillMove() {
		return timeTillMove;
	}
	
	public boolean canMove() {
		return timeTillMove <= 0;
	}
	
	public void move(PiecePos pos) {
		this.pos = pos;
		this.timeTillMove = type.getCooldown();
	}
	
	public PieceColor getColor() {
		return color;
	}

	public void setColor(PieceColor color) {
		this.color = color;
	}

	public PieceType getType() {
		return type;
	}

	public void setType(PieceType type) {
		this.type = type;
	}

	public PiecePos getPos() {
		return pos;
	}

	public void setPos(PiecePos pos) {
		this.pos = pos;
	}
	
	
	
}

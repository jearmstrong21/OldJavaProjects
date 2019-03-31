package co.megadodo.chess.framework;

import java.io.PrintStream;
import java.util.ArrayList;

public class Board {
	public static final int BOARD_W = 8;
	public static final int BOARD_H = 8;
	private static final int bw = BOARD_W;
	private static final int bh = BOARD_H;
	
	public Piece[][] arr;
	
	public void print(PrintStream out) {
		printAllMovesForPos(out, new PiecePos(-1,-1));
	}
	
	public void printAllMovesForPos(PrintStream out, PiecePos pos) {
		ArrayList<PiecePos> list = getAllMovesForPiece(pos);
		String prefix = " | ";
		for(int x = 0; x < bw; x++) {
//			for(int i=0;i<(prefix.length()+2)*8; i++) {
//				if( ( (i-1) % 5) == 0) out.print('+'); else out.print("-");
//			} 
			out.println("-+----+----+----+----+----+----+----+----+-");

			for(int y = 0; y < bh; y++) {
				String s = prefix + "  ";
				if(arr[x][y] != null) 
					s = prefix + arr[x][y].toString();
				else s = prefix + "  ";
				if(list.contains(new PiecePos(x,y))) s = prefix + "XX";
				out.print(s);
			}
			out.println(" |");
//			out.println();
		}
		
		out.println("-+----+----+----+----+----+----+----+----+-");
	}
	
	public Board() {
		clear();
	}
	
	public void setupNormal() {
		arr = new Piece[][] {
			{Piece.WHITE_ROOK, Piece.WHITE_KNIGHT, Piece.WHITE_BISHOP, Piece.WHITE_KING, Piece.WHITE_QUEEN, Piece.WHITE_BISHOP, Piece.WHITE_KNIGHT, Piece.WHITE_ROOK},
			{Piece.WHITE_PAWN, Piece.WHITE_PAWN  , Piece.WHITE_PAWN  , Piece.WHITE_PAWN, Piece.WHITE_PAWN , Piece.WHITE_PAWN  , Piece.WHITE_PAWN  , Piece.WHITE_PAWN},
			{null,null,null,null,null,null,null,null},
			{null,null,null,null,null,null,null,null},
			{null,null,null,null,null,null,null,null},
			{null,null,null,null,null,null,null,null},
			{Piece.BLACK_PAWN, Piece.BLACK_PAWN  , Piece.BLACK_PAWN  , Piece.BLACK_PAWN, Piece.BLACK_PAWN , Piece.BLACK_PAWN  , Piece.BLACK_PAWN  , Piece.BLACK_PAWN},
			{Piece.BLACK_ROOK, Piece.BLACK_KNIGHT, Piece.BLACK_BISHOP, Piece.BLACK_KING, Piece.BLACK_QUEEN, Piece.BLACK_BISHOP, Piece.BLACK_KNIGHT, Piece.BLACK_ROOK}
		};
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++) {
				if(arr[x][y] != null)
				arr[x][y].pos = new PiecePos(x,y);
			}
		}
	}
	
	public void clear() {
		arr = new Piece[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				arr[i][j] = null;
			}
		}
	}
	
	public void setPiece(PiecePos pos, PieceColor col, PieceType type) {
		if(pos.outBounds()) return;
		arr[pos.x][pos.y] = new Piece(col, type, pos);
	}
	
	public boolean canMove(PiecePos piece) {
		if(piece.outBounds()) return false;
		return arr[piece.x][piece.y].canMove();
	}
	
	public PieceType getPieceType(PiecePos pos) {
		if(pos.outBounds()) return null;
		Piece p = arr[pos.x][pos.y];
		return (p==null?null:p.getType());
	}
	
	public PieceColor getPieceColor(PiecePos pos) {
		if(pos.outBounds()) return null;
		Piece p = arr[pos.x][pos.y];
		return (p==null?null:p.getColor());
	}
	
	public ArrayList<PiecePos> getAllMovesForPiece(PiecePos pos) {
		ArrayList<PiecePos> list = new ArrayList<PiecePos>();
		if(pos.outBounds()) return list;
		Piece piece = arr[pos.x][pos.y];
		if(piece == null) return list;
		
		int x = piece.getPos().x;
		int y = piece.getPos().y;
		
		PiecePos curPos = pos;
		
		boolean hasPassedEnemyPiece = false; // HPEP?
		
		PieceColor col = piece.getColor();

		PieceColor nextCol = piece.getColor();
		
		switch(getPieceType(pos)) {
			case BISHOP:
				for(curPos = pos; curPos.inBounds() && !hasPassedEnemyPiece; curPos = curPos.getTopLeft()) {
					if(col.isFriendlyNoNull(getPieceColor(curPos.getTopLeft()))) break;
					if(col.isEnemy(getPieceColor(curPos.getTopLeft()))) hasPassedEnemyPiece = true;
					
					list.add(curPos.getTopLeft());
				}
				for(curPos = pos; curPos.inBounds() && !hasPassedEnemyPiece; curPos = curPos.getTopRight()) {
					if(col.isFriendlyNoNull(getPieceColor(curPos.getTopRight()))) break;
					if(col.isEnemy(getPieceColor(curPos.getTopRight()))) hasPassedEnemyPiece = true;
					
					list.add(curPos.getTopRight());
				}
				for(curPos = pos; curPos.inBounds() && !hasPassedEnemyPiece; curPos = curPos.getBottomLeft()) {
					if(col.isFriendlyNoNull(getPieceColor(curPos.getBottomLeft()))) break;
					if(col.isEnemy(getPieceColor(curPos.getBottomLeft()))) hasPassedEnemyPiece = true;
					
					list.add(curPos.getBottomLeft());
				}
				for(curPos = pos; curPos.inBounds() && !hasPassedEnemyPiece; curPos = curPos.getBottomRight()) {
					if(col.isFriendlyNoNull(getPieceColor(curPos.getBottomRight()))) break;
					if(col.isEnemy(getPieceColor(curPos.getBottomRight()))) hasPassedEnemyPiece = true;
					
					list.add(curPos.getBottomRight());
				}
				break;
			case KING:
				if(!col.isFriendlyNoNull(getPieceColor(curPos.getTopLeft()))) list.add(curPos.getTopLeft());
				if(!col.isFriendlyNoNull(getPieceColor(curPos.getTopRight()))) list.add(curPos.getTopRight());
				if(!col.isFriendlyNoNull(getPieceColor(curPos.getBottomLeft()))) list.add(curPos.getBottomLeft());
				if(!col.isFriendlyNoNull(getPieceColor(curPos.getBottomRight()))) list.add(curPos.getBottomRight());
				if(!col.isFriendlyNoNull(getPieceColor(curPos.getLeft()))) list.add(curPos.getLeft());
				if(!col.isFriendlyNoNull(getPieceColor(curPos.getRight()))) list.add(curPos.getRight());
				if(!col.isFriendlyNoNull(getPieceColor(curPos.getUp()))) list.add(curPos.getUp());
				if(!col.isFriendlyNoNull(getPieceColor(curPos.getDown()))) list.add(curPos.getDown());
				break;
			case KNIGHT:
				break;
			case PAWN:
				break;
			case QUEEN:
				for(curPos = pos; curPos.inBounds() && !hasPassedEnemyPiece; curPos = curPos.getLeft()) {
					if(col.isFriendlyNoNull(getPieceColor(curPos.getLeft()))) break;
					if(col.isEnemy(getPieceColor(curPos.getLeft()))) hasPassedEnemyPiece = true;
				
					list.add(curPos.getLeft());
				}
				for(curPos = pos; curPos.inBounds() && !hasPassedEnemyPiece; curPos = curPos.getRight()) {
					if(col.isFriendlyNoNull(getPieceColor(curPos.getRight()))) break;
					if(col.isEnemy(getPieceColor(curPos.getRight()))) hasPassedEnemyPiece = true;
					
					list.add(curPos.getRight());
				}
				for(curPos = pos; curPos.inBounds() && !hasPassedEnemyPiece; curPos = curPos.getUp()) {
					if(col.isFriendlyNoNull(getPieceColor(curPos.getUp()))) break;
					if(col.isEnemy(getPieceColor(curPos.getUp()))) hasPassedEnemyPiece = true;
					
					list.add(curPos.getUp());
				}
				for(curPos = pos; curPos.inBounds() && !hasPassedEnemyPiece; curPos = curPos.getDown()) {
					if(col.isFriendlyNoNull(getPieceColor(curPos.getDown()))) break;
					if(col.isEnemy(getPieceColor(curPos.getDown()))) hasPassedEnemyPiece = true;
					
					list.add(curPos.getDown());
				}
				

				
				
				curPos = pos;
				nextCol = piece.color;
				hasPassedEnemyPiece = false;
				
				
				
				for(curPos = pos; curPos.inBounds() && !hasPassedEnemyPiece; curPos = curPos.getTopLeft()) {
					if(col.isFriendlyNoNull(getPieceColor(curPos.getTopLeft()))) break;
					if(col.isEnemy(getPieceColor(curPos.getTopLeft()))) hasPassedEnemyPiece = true;
					
					list.add(curPos.getTopLeft());
				}
				for(curPos = pos; curPos.inBounds() && !hasPassedEnemyPiece; curPos = curPos.getTopRight()) {
					if(col.isFriendlyNoNull(getPieceColor(curPos.getTopRight()))) break;
					if(col.isEnemy(getPieceColor(curPos.getTopRight()))) hasPassedEnemyPiece = true;
					
					list.add(curPos.getTopRight());
				}
				for(curPos = pos; curPos.inBounds() && !hasPassedEnemyPiece; curPos = curPos.getBottomLeft()) {
					if(col.isFriendlyNoNull(getPieceColor(curPos.getBottomLeft()))) break;
					if(col.isEnemy(getPieceColor(curPos.getBottomLeft()))) hasPassedEnemyPiece = true;
					
					list.add(curPos.getBottomLeft());
				}
				for(curPos = pos; curPos.inBounds() && !hasPassedEnemyPiece; curPos = curPos.getBottomRight()) {
					if(col.isFriendlyNoNull(getPieceColor(curPos.getBottomRight()))) break;
					if(col.isEnemy(getPieceColor(curPos.getBottomRight()))) hasPassedEnemyPiece = true;
					
					list.add(curPos.getBottomRight());
				}
				break;
			case ROOK:
				for(curPos = pos; curPos.inBounds() && !hasPassedEnemyPiece; curPos = curPos.getLeft()) {
					if(col.isFriendlyNoNull(getPieceColor(curPos.getLeft()))) break;
					if(col.isEnemy(getPieceColor(curPos.getLeft()))) hasPassedEnemyPiece = true;
					
					list.add(curPos.getLeft());
				}
				for(curPos = pos; curPos.inBounds() && !hasPassedEnemyPiece; curPos = curPos.getRight()) {
					if(col.isFriendlyNoNull(getPieceColor(curPos.getRight()))) break;
					if(col.isEnemy(getPieceColor(curPos.getRight()))) hasPassedEnemyPiece = true;
					
					list.add(curPos.getRight());
				}
				for(curPos = pos; curPos.inBounds() && !hasPassedEnemyPiece; curPos = curPos.getUp()) {
					if(col.isFriendlyNoNull(getPieceColor(curPos.getUp()))) break;
					if(col.isEnemy(getPieceColor(curPos.getUp()))) hasPassedEnemyPiece = true;
					
					list.add(curPos.getUp());
				}
				for(curPos = pos; curPos.inBounds() && !hasPassedEnemyPiece; curPos = curPos.getDown()) {
					if(col.isFriendlyNoNull(getPieceColor(curPos.getDown()))) break;
					if(col.isEnemy(getPieceColor(curPos.getDown()))) hasPassedEnemyPiece = true;
					
					list.add(curPos.getDown());
				}
				break;
			default:
				break;
			
		}
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).outBounds()) list.remove(i);
		}
		
		return list;
	}
}

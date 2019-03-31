package co.megadodo.superchess1;

import java.util.ArrayList;

// white goes down
// black goes up
public class Board {
	
	public Piece[][] board;
	
	public void place(Piece piece, PiecePos pos) {
		place(piece,pos.x,pos.y);
	}
	
	public void place(Piece piece, int x, int y) {
		board[x][y] = piece;
	}
	
	public void setPiece(int x, int y, Piece p) {
		board[x][y] = p;
	}
	
	public void setupBoard() {
		setPiece(0,0,Piece.WR);
		setPiece(1,0,Piece.WN);
		setPiece(2,0,Piece.WB);
		setPiece(3,0,Piece.WQ);
		setPiece(4,0,Piece.WK);
		setPiece(5,0,Piece.WB);
		setPiece(6,0,Piece.WN);
		setPiece(7,0,Piece.WR);
		
		for(int x=0;x<8;x++){
			setPiece(x,1,Piece.WP);
			setPiece(x,6,Piece.BP);
		}
		
		setPiece(0,7,Piece.BR);
		setPiece(1,7,Piece.BN);
		setPiece(2,7,Piece.BB);
		setPiece(3,7,Piece.BQ);
		setPiece(4,7,Piece.BK);
		setPiece(5,7,Piece.BB);
		setPiece(6,7,Piece.BN);
		setPiece(7,7,Piece.BR);
	}
	
	public void printMovesForPiece(JWriter out, PiecePos p) {
		printMovesForPiece(out,movesForPiece(p));
	}
	
	public void printMovesForPiece(JWriter out, int x, int y) {
		printMovesForPiece(out, movesForPiece(new PiecePos(x,y)));
	}
	
	public void printMovesForPiece(JWriter out, ArrayList<PiecePos> moves) {
		out.jprint("  ");
		for(int x=0;x<8;x++){
			out.jprint(" " + (x+1) + " ");
		}
		out.jprintln();
		for(int y=0;y<8;y++){
			out.jprint(" " + (y+1) + " ");
			for(int x=0;x<8;x++){				
				if(moves.contains(new PiecePos(x,y)))out.jprint("XX ");
				else out.jprint(board[x][y] + " ");
			}
			out.jprintln("|");
		}
	}
	
	public static void main(String[] args) {
		Board board = new Board();
		board.setupBoard();
		JWriterWindow writer = new JWriterWindow();
		writer.show();
		writer.jprintln("Board console program");
		writer.jprintln();
		board.printBoard(writer);
		writer.jprintln();
		board.printMovesForPiece(writer, 6, 7);
	}
	
	public void swap(PiecePos a, PiecePos b) {
		swap(a.x,a.y,b.x,b.y);
	}
	
	public Piece getPiece(int x, int y) {
		return board[x][y];
	}
	
	public void swap(int ax, int ay, int bx, int by) {
		Piece temp = board[ax][ay];
		board[ax][ay] = board[bx][by];
		board[bx][by] = temp;
	}
	
	public Board() {
		board = new Piece[8][8];
		for(int x=0;x<8;x++){
			for(int y=0;y<8;y++){
				board[x][y]=Piece.empty;
			}
		}
	}
	
	public void printBoard(JWriter out) {
		for(int y=0;y<8;y++){
			out.jprint((y+1) + ": ");
			for(int x=0;x<8;x++){
				out.jprint(board[x][y].toString() + " ");
			}
			out.jprintln("|");
		}
	}
	
//	public ArrayList<PiecePos> movesForPiece(PiecePos pos) {
//		return movesForPiece(pos.x,pos.y);
//	}
	
	public boolean outBounds(PiecePos pos) {
		return outBounds(pos.x,pos.y);
	}
	
	public boolean outBounds(int x, int y) {
		return x<0||y<0||x>=8||y>=8;
	}
	
	public ArrayList<PiecePos> movesForDir(PiecePos start, PiecePos dir, PieceColor col) {
		ArrayList<PiecePos> moves = new ArrayList<PiecePos>();
		PiecePos cur = new PiecePos(start.x,start.y);
		cur.x+=dir.x;
		cur.y+=dir.y;
		boolean done = false;
		while(!outBounds(cur) && !done){
			if(board[cur.x][cur.y].col == col) {
				done = true;
				continue;
			}
			moves.add(new PiecePos(cur.x,cur.y));
			if(board[cur.x][cur.y].col == col.opp())done=true;
			cur.x+=dir.x;
			cur.y+=dir.y;
		}
		return moves;
	}
	
	PiecePos[] knightMoves = new PiecePos[] {
			new PiecePos(2,1),
			new PiecePos(-2,1),
			new PiecePos(2,-1),
			new PiecePos(-2,-1),
			
			new PiecePos(1,2),
			new PiecePos(-1,2),
			new PiecePos(1,-2),
			new PiecePos(-1,-2)
	};
	
	PiecePos[] kingMoves = new PiecePos[] {
			new PiecePos(-1,0),
			new PiecePos(1,0),
			new PiecePos(0,-1),
			new PiecePos(0,1)
	};
	
	public ArrayList<PiecePos> movesForPiece(PiecePos piecePos) {
		PieceType type = board[piecePos.x][piecePos.y].type;
		PieceColor col = board[piecePos.x][piecePos.y].col;
		ArrayList<PiecePos> list = new ArrayList<PiecePos>();
		if(type == PieceType.Bishop || type == PieceType.Queen) {
			list.addAll(movesForDir(piecePos,new PiecePos(-1,-1),col));
			list.addAll(movesForDir(piecePos,new PiecePos(1,-1),col));
			list.addAll(movesForDir(piecePos,new PiecePos(-1,1),col));
			list.addAll(movesForDir(piecePos,new PiecePos(1,1),col));
		}
		if(type == PieceType.Rook || type == PieceType.Queen) {
			list.addAll(movesForDir(piecePos,new PiecePos(1,0),col));
			list.addAll(movesForDir(piecePos,new PiecePos(-1,0),col));
			list.addAll(movesForDir(piecePos,new PiecePos(0,1),col));
			list.addAll(movesForDir(piecePos,new PiecePos(0,-1),col));
		}
		if(type == PieceType.Knight) {
			for(PiecePos p : knightMoves) {
				PiecePos actual = new PiecePos(p.x+piecePos.x,p.y+piecePos.y);
				if(outBounds(actual))continue;
				if(board[actual.x][actual.y].col != col) list.add(actual);
			}
		}
		if(type == PieceType.King) {
			for(PiecePos p : kingMoves) {
				PiecePos actual = new PiecePos(p.x+piecePos.x,p.y+piecePos.y);
				if(outBounds(actual))continue;
				if(board[actual.x][actual.y].col != col) list.add(actual);
			}
		}
		if(type == PieceType.Pawn) {
			int x = piecePos.x;
			int y = piecePos.y;
			if(col == PieceColor.White) {
				// going down
				if(!outBounds(x,y+1)){
					if(board[x][y+1].isEmptyPiece()){
						list.add(new PiecePos(x,y+1));
						if(y==1){
							if(!outBounds(x,y+2)){
								if(board[x][y+2].isEmptyPiece()){
									list.add(new PiecePos(x,y+2));
								}
							}
						}
					}
				}
				if(!outBounds(x-1,y+1)){
					if(board[x-1][y+1].col == PieceColor.Black){
						list.add(new PiecePos(x-1,y+1));
					}
				}
				if(!outBounds(x+1,y+1)){
					if(board[x+1][y+1].col == PieceColor.Black){
						list.add(new PiecePos(x+1,y+1));
					}
				}
//				if(board[x][y-1].isEmptyPiece()) {
//					list.add(new PiecePos(x,y-1));
//					if(board][x])
//				}
			}
			
			if(col == PieceColor.Black) {
				if(!outBounds(x,y-1)){
					if(board[x][y-1].isEmptyPiece()){
						list.add(new PiecePos(x,y-1));
						if(y==6){
							if(!outBounds(x,y-2)){
								if(board[x][y-2].isEmptyPiece()){
									list.add(new PiecePos(x,y-2));
								}
							}
						}
					}
				}
				if(!outBounds(x-1,y-1)){
					if(board[x-1][y-1].col == PieceColor.White){
						list.add(new PiecePos(x-1,y-1));
					}
				}
				if(!outBounds(x+1,y-1)){
					if(board[x+1][y-1].col == PieceColor.White){
						list.add(new PiecePos(x+1,y-1));
					}
				}
			}
		}
//		list.add(piecePos);
		return list;
	}
	
}


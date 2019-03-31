package co.megadodo.chess;

import java.util.ArrayList;

import co.megadodo.chess.framework.*;

public class Main {

	public static void main(String[] args) {
		int x = 5,y = 4;
		Board b = new Board();
//		b.setupNormal();
		
		b.setPiece(new PiecePos(x,y), PieceColor.WHITE, PieceType.QUEEN);
		
		ArrayList<PiecePos> list = b.getAllMovesForPiece(new PiecePos(x,y));

		System.out.println("NORMAL PRINT");
		
		b.print(System.out);
		
		
		System.out.println("MOVE PRINT");
		
		b.printAllMovesForPos(System.out, new PiecePos(x,y));

		System.out.println("----  " + list.size());
		
		for(PiecePos p : list) {
			System.out.println(p.x + "," + p.y);
		}
	}

}

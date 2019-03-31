package co.megadodo.connect4;

import java.util.Scanner;

import co.megadodo.consolewindow.ConsoleWindow;

public class Connect4Board {

	Piece[][] pieces; // 7x6: 7 columns, 6 rows
	Piece playerMoving;
	int turnNum;
	int lastMoveCol;
	int lastMoveRow;
	
	public static void main(String[] args) {
		Connect4Board board = new Connect4Board();
		Scanner sc = new Scanner(System.in);
		while(board.winning() == Piece.EMPTY) {
			board.display();
			String str = Connect4Board.toString(board);
			System.out.println(str);
			Connect4Board.parse(str).display();
			System.out.print("Move: ");
			board.move(sc.nextInt());
		}
		board.display();
		System.out.println("Winner: " + board.winning().toString());
		sc.close();

	}
	
	public static Connect4Board parse(String str) {
		if(str.equals("null")) return null;
		Connect4Board board = new Connect4Board();
		board.playerMoving = Piece.parse(str.charAt(0) + "");
		str = str.substring(1, str.length());
		for(int col = 0; col < 7; col++) {
			for(int row = 0; row < 6; row++) {
				board.pieces[col][row] = Piece.parse(str.charAt(0)+"");
				str = str.substring(1, str.length());
			}
		}
		return board;
	}
	
	public static String toString(Connect4Board board) {
		if(board == null) return "null";
		String str = board.playerMoving.toString();
		for(int col = 0; col < 7; col++) {
			for(int row = 0; row < 6; row++) {
				str+=board.pieces[col][row].toString();
			}
		}
		return str;
	}
	
	public Connect4Board() {
		playerMoving = Piece.Y;
		turnNum = 0;
		lastMoveCol = -1;
		lastMoveRow = -1;
		pieces = new Piece[7][6];
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 6; j++) {
				pieces[i][j] = Piece.EMPTY;
			}
		}
	}
	
	public boolean canMove(int col) {
		return pieces[col][0] == Piece.EMPTY;
	}
	
	public Piece winning() {
		if(playerMoving == Piece.Y) if(checkForWin(Piece.R)) return Piece.R;
		if(playerMoving == Piece.R) if(checkForWin(Piece.Y)) return Piece.Y;
		return Piece.EMPTY;
	}
	
	public boolean checkForWin(Piece p) {
		int c = lastMoveCol;
		int r = lastMoveRow;
		if(c == -1 || r == -1) return false;
		if(pieces[c][r] != p) return false;
		
		return 	   equal(c,r,c,r+1,c,r+2,c,r+3)
				|| equal(c,r-1,c,r,c,r+1,c,r+2)
				|| equal(c,r-2,c,r-1,c,r,c,r+1)
				|| equal(c,r-3,c,r-2,c,r-1,c,r)
				
				|| equal(c,r,c+1,r,c+2,r,c+3,r)
				|| equal(c-1,r,c,r,c+1,r,c+2,r)
				|| equal(c-2,r,c-1,r,c,r,c+1,r)
				|| equal(c-3,r,c-2,r,c-1,r,c,r)
				
				|| equal(c,r,c+1,r+1,c+2,r+2,c+3,r+3)
				|| equal(c-1,r-1,c,r,c+1,r+1,c+2,r+2)
				|| equal(c-2,r-2,c-1,r-1,c,r,c+1,r+1)
				|| equal(c-3,r-3,c-2,r-2,c-1,r-1,c,r)
				
				|| equal(c,r,c+1,r-1,c+2,r-2,c+3,r-3)
				|| equal(c-1,r+1,c,r,c+1,r-1,c+3,r-2)
				|| equal(c-2,r+2,c-1,r+1,c,r,c+1,r-1)
				|| equal(c-3,r+3,c-2,r+2,c-1,r+1,c,r)
				;
	}
	
	public boolean equal(int a, int b, int c, int d, int e, int f, int g, int h) {
		if(b<0||b>5)return false;
		if(d<0||d>5)return false;
		if(f<0||f>5)return false;
		if(h<0||h>5)return false;

		if(a<0||a>6)return false;
		if(c<0||c>6)return false;
		if(e<0||e>6)return false;
		if(g<0||g>6)return false;
		
		return pieces[a][b] == pieces[c][d] && pieces[c][d] == pieces[e][f] && pieces[e][f] == pieces[g][h];
	}
	
	public int move(int col) {
		for(int row = 5; row >= 0; row--) {
			if(pieces[col][row] == Piece.EMPTY) {
				pieces[col][row] = playerMoving;
				if(playerMoving == Piece.R) playerMoving = Piece.Y;
				else if(playerMoving == Piece.Y) playerMoving = Piece.R;
				lastMoveCol = col;
				lastMoveRow = row;
				return row;
			}
		}
		return -1;
	}
	
	public void display(ConsoleWindow w) {
		w.console_println("Player moving: " + playerMoving.toString());
		w.console_println("Player winning: " + winning().toString());
		for(int row = 0; row < 6; row++) {
			w.console_print("Row " + (row+1) + ": ");
			for(int col = 0; col < 7; col++) {
				w.console_print(pieces[col][row].toString());
			}
			w.console_println();
		}
	}
	
	public void display() {
		System.out.println("Player moving: " + playerMoving.toString());
		System.out.println("Player winning: " + winning().toString());
		for(int row = 0; row < 6; row++) {
			System.out.print("Row " + (row+1) + ": ");
			for(int col = 0; col < 7; col++) {
				System.out.print(pieces[col][row].toString());
			}
			System.out.println();
		}
	}
	
}

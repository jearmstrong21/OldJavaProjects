package co.megadodo.mancala;

import co.megadodo.consolewindow.ConsoleWindow;

public class MancalaBoard {
//	int player0Home;
//	int player1Home;
	int[] homes;
	public final static int PLAYERS = 2;
	public final static int HOLES = 6;
	public final static int START_NUM = 4;
	int[][] board; // = new int[2][6] = new int[player][hole]
	int playerMoving = 0;
	
	public String toString() {
		String str = playerMoving + " " + homes[0] + " " + homes[1] + " ";
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 6; j++) {
				str += board[i][j] + " ";
			}
		}
		return str.trim();
	}
	
	public static MancalaBoard parse(String str) {
		if(str.trim().equals("null")) return null;
		MancalaBoard board = new MancalaBoard();
		String[] strs = str.split(" ");
		board.playerMoving = Integer.parseInt(strs[0]);
		board.homes[0] = Integer.parseInt(strs[1]);
		board.homes[1] = Integer.parseInt(strs[2]);
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 6; j++) {
				board.board[i][j] = Integer.parseInt(strs[1 + 2+i*6+j]);
			}
		}
//		board.playerMoving = Integer.parseInt(strs[14]);
		return board;
	}

	public final static int other(int player) {
		if(player == 0) return 1;
		else return 0;
	}
	
	// -1 if not over, 0 if over and player 0 side is empty, 1 if over and player 1 side is empty
	public int isGameOver() {
		if(isZeroArray(board[0])) return 0;
		if(isZeroArray(board[1])) return 1;
		return -1;
	}
	
	public boolean isZeroArray(int[] arr) {
		for(int i : arr) if(i != 0) return false;
		return true;
	}
	
	// -1 if tie
	public int winningPlayer() {
		if(homes[0] == homes[1]) return -1;
		if(homes[0] > homes[1]) return 0;
		return 1;
	}
	
	public MancalaBoard() {
		board = new int[PLAYERS][HOLES];
		homes = new int[PLAYERS];
	}
	
	public void setup() {
		homes[0] = 0;
		homes[1] = 0;
		for(int player = 0; player < PLAYERS; player++) {
			for(int hole = 0; hole < HOLES; hole++) {
				board[player][hole] = START_NUM;
			}
		}
	}
	
	public String format(int i) {
		if(i < 10) return "  " + i;
		if(i < 100) return " " + i;
				     return "" + i;
	}
	
	public MancalaPos move(int player, int hole) {
		if(board[player][hole] == 0) {
			System.out.println("zero hole");
			return null;
		}
		int num = board[player][hole];
		MancalaPos pos = new MancalaPos(player,hole);
		board[player][hole] = 0;
		while(num > 0) {
			pos = pos.next(playerMoving);
			System.out.println(pos);
			num--;
			if(pos.hole == -1) {
				homes[pos.player]++;
			} else {
				board[pos.player][pos.hole]++;
			}
		}
		
		// if land in empty hole on your own side, take opposite marbles to your home
		if(pos.hole != -1) {
			if(pos.player == playerMoving) {
				if(board[pos.player][pos.hole] == 1) {
					homes[pos.player]+=board[other(pos.player)][pos.hole];
					board[other(pos.player)][pos.hole] = 0;
				}
			}
		}

		System.out.print(playerMoving);
//		if(playerMoving == 0) playerMoving = 1;
//		else if(playerMoving == 1) playerMoving = 0;
		if(pos.hole != -1) playerMoving = other(playerMoving);
		System.out.println(playerMoving);
		return pos;
	}
	
	public void display(ConsoleWindow window) {
		window.console_println("Player moving: " + playerMoving);
		window.console_print(format(homes[0]) + " ");
		for(int i = 0; i < HOLES; i++) {
			window.console_print(format(board[0][i]) + " ");
		}
		window.console_println(format(homes[1]));
		window.console_print("    ");
		for(int i = 0; i < HOLES; i++) {
			window.console_print(format(board[1][i]) + " ");
		}
		window.console_println();
	}
	
	public void display(
			//int player
			) {
//		if(player == 1) {
			System.out.println("Player moving: " + playerMoving);
			System.out.print(format(homes[0]) + " ");
			for(int i = 0; i < HOLES; i++) {
				System.out.print(format(board[0][i])+" ");
			}
			System.out.println(format(homes[1]));
			System.out.print("    ");
			for(int i =0 ; i < HOLES; i++) {
				System.out.print(format(board[1][i])+" ");
			}
			System.out.println();
//		} else {
//			System.out.print(format(homes[1])+" ");
//			for(int i = 0; i < HOLES; i++) {
//				System.out.print(format(board[1][i])+" ");
//			}
//			System.out.println(format(homes[0]));
//			System.out.print("    ");
//			for(int i=0;i<HOLES;i++){
//				System.out.print(format(board[0][i])+" ");
//			}
//			System.out.println();
//		}
	}
	
}

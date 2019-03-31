package co.megadodo.mancala;

import processing.core.PApplet;
import processing.core.PVector;

public class MancalaProcessing extends PApplet {
	
	public static void main(String[] args) {
		PApplet.main("co.megadodo.mancala.MancalaProcessing");
	}
	Button[] btnHomes;
	Button[][] btnHoles;
	int W = 750;
	int H = 500;
	public void settings() {
		size(W, H);
	}
	
	public Button createButton(float x, float y, boolean disabled) {
		Button btn = new Button();
		btn.x = x+OFFSET_X;
		btn.y = y+OFFSET_Y;
		btn.text = "";
		btn.rad = RAD;
		btn.disabled = disabled;
		return btn;
	}
	
	final static int RAD = 50;
	final static int SPACING = 20;
	final static int OFFSET_X = (RAD+SPACING)/2;
	final static int OFFSET_Y = -RAD-SPACING;
	
	MancalaBoard board;
	public void setup() {
		startGame();
	}
	
	public void startGame() {
		board = new MancalaBoard();
		board.setup();
		btnHomes = new Button[MancalaBoard.PLAYERS];
		btnHoles = new Button[MancalaBoard.PLAYERS][MancalaBoard.HOLES];
		btnHomes[0] = createButton(width/2 - (RAD+SPACING)*4,height/2,true);
		btnHomes[1] = createButton(width/2 + (RAD+SPACING)*3,height/2,true);
		for(int i = 0; i < MancalaBoard.PLAYERS; i++) {
			for(int j = 0; j < MancalaBoard.HOLES; j++) {
				float h = height/2;
				if(i==1)h+=RAD+SPACING;
				btnHoles[i][j] = createButton(width/2+(RAD+SPACING)*(j-3),h,false);
			}
		}
	}
	
	int playerMoving = 1;
	class Button {
		boolean disabled;
		float rad;
		int btnState;
		float x;
		float y;
		String text;
		void draw(boolean home) {
			if(new PVector(mouseX,mouseY).dist(new PVector(x,y)) <= rad/2) {
				if(mousePressed) {
					btnState = BTN_CLICK;
				} else {
					btnState = BTN_HOVER;
				}
			} else {
				btnState = BTN_NORMAL;
			}
			if(disabled) btnState = BTN_NORMAL;
			stroke(0);
			ellipseMode(CENTER);
//			if(disabled) {
			if(btnState == BTN_NORMAL) fill(255/4,0,0);
			if(btnState == BTN_HOVER) fill(255/2,0,0);
			if(btnState == BTN_CLICK) fill(255,0,0);
			if(disabled) fill(255);
//			}
			if(home) {
				ellipse(x, y+rad-SPACING/2, rad, rad*2+SPACING);
			} else {
				ellipse(x, y, rad, rad);
			}
			// text
			if(btnState == BTN_NORMAL) fill(255);
			if(btnState == BTN_HOVER) fill(255);
			if(btnState == BTN_CLICK) fill(0);
			if(disabled) fill(0);
			noStroke();
			textAlign(CENTER,CENTER);
			if(home) {
				text(text,x,y+rad-SPACING/2);
			} else {
				text(text,x,y);
			}
		}
	}
	int BTN_NORMAL = 0;
	int BTN_HOVER = 1;
	int BTN_CLICK = 2;

	public void draw() {
		background(255);
		playerMoving = board.playerMoving;
		for(int player = 0; player < MancalaBoard.PLAYERS; player++) {
			btnHomes[player].text = ""+board.homes[player];
			btnHomes[player].draw(true);
		}
		for(int player = 0; player < MancalaBoard.PLAYERS; player++) {
			for(int hole = 0; hole < MancalaBoard.HOLES; hole++) {
				btnHoles[player][hole].text = ""+board.board[player][hole];
				btnHoles[player][hole].draw(false);
			}
		}
		for(int player = 0; player < MancalaBoard.PLAYERS; player++) {
			for(int hole = 0; hole < MancalaBoard.HOLES; hole++) {
				if(player != playerMoving || board.board[player][hole] == 0) btnHoles[player][hole].disabled = true;
				else btnHoles[player][hole].disabled = false;
				if(btnHoles[player][hole].btnState == BTN_CLICK) {
					if(board.board[player][hole] != 0) {
						if(player == playerMoving) {
							System.out.println("BEFORE:");
							board.display();
							MancalaPos pos = board.move(player, hole);
							System.out.println("AFTER:");
							board.display();
//							if(pos.hole != -1) playerMoving = MancalaBoard.other(playerMoving);
						}
					}
				}
			}
		}
		textAlign(LEFT,BASELINE);
		fill(0);
		text("Winning player (-1 for tie): " + board.winningPlayer(), 0, height-5);
		text("Current player: " + playerMoving, 0, height-25);
		text("Game over: (-1 for not over): " + board.isGameOver(), 0, height-45);
		textAlign(CENTER,CENTER);
		text("Player 1 side", width/2,height/2+RAD+SPACING);
		text("Player 0 side", width/2,height/2-RAD*2-SPACING*2);
		text("Player 1 home", width/2+RAD*4+SPACING*4,height/2-RAD*2-SPACING*2);
		text("Player 0 home", width/2-RAD*4-SPACING*4,height/2-RAD*2-SPACING*2);
		pushStyle();
		textSize(50);
		text("Mancala", width/2, 50);
		popStyle();
	}
	
	public void mousePressed() {
	}
	
	public void keyPressed() {
		
	}

}

package co.megadodo.connect4;

import processing.core.PApplet;

public class UDPConnect4ClientProcessing extends PApplet {

	public static void main(String[] args) {
		PApplet.runSketch(new String[] {"co.megadodo.connect4.UDPConnect4ClientProcessing"}, new UDPConnect4ClientProcessing());
	}
	
	int red = color(255,0,0);
	int yellow = color(255,255,0);
	int boardBG = color(255/4,255/4,255);
	UDPConnect4Client client;
	class RandCol {
		float rR,rG,rB;
		float r,g,b;
		RandCol(float _r, float initial) {
			rR=rG=rB=_r;
			r=g=b=initial;
			
		}
		void update() {
			r+=random(-rR,rR);
			g+=random(-rG,rG);
			b+=random(-rB,rB);
			r=constrain(r,0,255);
			g=constrain(g,0,255);
			b=constrain(b,0,255);
		}
		void set() {
			fill(r,g,b);
		}
	}
	RandCol title;
	RandCol bg;
	int boardX;
	int boardY;
	int boardW;
	int boardH;
	int cellW;
	int cellH;
	int paddingX;
	int paddingY;
	public void settings() {
		size(750,750);
		cellW = 75;
		cellH = 75;
		paddingX = 10;
		paddingY = 10;
		boardW = cellW*7+paddingX*6;
		boardH = cellH*6+paddingY*5;;
		boardX = (width-boardW)/2;
		boardY = (height-boardH)/2;
		title = new RandCol(5,0);
		bg = new RandCol(0.5f,255);
		client = new UDPConnect4Client();
		client.threadClient();
	}
	public void drawCircle(Piece p, int x, int y) {
		if(p == Piece.Y) fill(yellow);
		else if(p == Piece.R) fill(red);
		else fill(255);
//		ellipseMode(CENTER);
		ellipse(x,y,cellW,cellH);

	}
	public void draw() {
		background(bg.r,bg.g,bg.b);
		bg.update();
		title.set();
		textAlign(CENTER,CENTER);
//		textSize(40);
//		text("Connect 4",width/2,50);
		if(client.board != null) {
			drawCircle(client.board.playerMoving,0,0);
			drawCircle(client.playerPiece,0,500);
			fill(boardBG);
			rect(boardX,boardY,boardW,boardH);
			for(int col = 0; col < 7; col++) {
				for(int row = 0; row < 6; row++) {
					drawCircle(client.board.pieces[col][row],boardX+cellW*col+paddingX*col+cellW/2,boardY+cellH*row+paddingY*row+cellH/2);
				}
			}
		}
		if(keyPressed && client.moveToDo == -1 && client.canMove()) {
			System.out.println("Move");
//			for(int col = 0; col < 7; col++) {
//				if(mouseX > boardX+cellW*col+paddingX*col+cellW/2 && mouseX < boardX+cellW*col+paddingX*col+cellW/2+cellW) {
//					client.moveToDo = col;
//				}
//			}
			if(key == '1') client.moveToDo = 0;
			if(key == '2') client.moveToDo = 1;
			if(key == '3') client.moveToDo = 2;
			if(key == '4') client.moveToDo = 3;
			if(key == '5') client.moveToDo = 4;
			if(key == '6') client.moveToDo = 5;
			if(key == '7') client.moveToDo = 6;
		}
	}
	
}

package co.megadodo.superchess1;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

// processing applet class
public class SuperChessV1 extends PApplet {
	
	public static void main(String[] args) {
		PApplet.runSketch(new String[] {"SuperChessV1"}, new SuperChessV1());
	}
	
	public void settings() {
		size(1000,1000);
	}
	
	PImage iBB;
	PImage iBK;
	PImage iBN;
	PImage iBP;
	PImage iBQ;
	PImage iBR;
	
	PImage iWB;
	PImage iWK;
	PImage iWN;
	PImage iWP;
	PImage iWQ;
	PImage iWR;
	
	
	Board board;
	
	int boardX;
	int boardY;
	
	int boardW;
	int boardH;
	
	int pieceW;
	int pieceH;

	int rechargeBarHeight;
	int rechargeBarWidth;
	
	public PImage imageForPiece(Piece p) {
		if(p == Piece.BB) return iBB;
		if(p == Piece.BK) return iBK;
		if(p == Piece.BN) return iBN;
		if(p == Piece.BP) return iBP;
		if(p == Piece.BQ) return iBQ;
		if(p == Piece.BR) return iBR;

		if(p == Piece.WB) return iWB;
		if(p == Piece.WK) return iWK;
		if(p == Piece.WN) return iWN;
		if(p == Piece.WP) return iWP;
		if(p == Piece.WQ) return iWQ;
		if(p == Piece.WR) return iWR;
		
		return new PImage();
	}
	
	public void loadImages() {
		iBB = loadImage("BB.png");
		iBK = loadImage("BK.png");
		iBN = loadImage("BN.png");
		iBP = loadImage("BP.png");
		iBQ = loadImage("BQ.png");
		iBR = loadImage("BR.png");

		iWB = loadImage("WB.png");
		iWK = loadImage("WK.png");
		iWN = loadImage("WN.png");
		iWP = loadImage("WP.png");
		iWQ = loadImage("WQ.png");
		iWR = loadImage("WR.png");
	}
	
	public void setup() {
		board = new Board();
		board.setupBoard();
		
		loadImages();
		
		boardX = 200;
		boardY = 300;
		
		boardW = 600;
		boardH = 600;
		
		pieceW = boardW/8;
		pieceH = boardH/8;
		
		rechargeBarWidth = 70;
		rechargeBarHeight = 5;
	}
	
	public void drawTitle() {
		textSize(100);
		textAlign(CENTER,CENTER);
		fill(0);
		stroke(0);
		text("SUPERMEGACHESSV1",width/2,100);
	}
	
	public void mousePressed() {
		if(mouseX>boardX
				&&mouseY>boardY
				&&mouseX<boardX+boardW&&mouseY<boardY+boardH) {
			int x=mouseX;
			int y=mouseY;
			x-=boardX;
			y-=boardY;
			x/=pieceW;
			y/=pieceH;
			highlightedPosses = board.movesForPiece(new PiecePos(x,y));
		}
	}
	
	ArrayList<PiecePos> highlightedPosses = new ArrayList<PiecePos>();
	
	public void draw() {
		background(200);
		
		drawTitle();
		
		for(int x=0;x<8;x++){
			for(int y=0;y<8;y++){
				noStroke();
				if((x+y)%2==0){
					fill(149,79,16);
				} else {
					fill(195,147,21);
				}
				rect(x*pieceW+boardX,y*pieceH+boardY,pieceW,pieceH);
				image(imageForPiece(board.getPiece(x, y)),x*pieceW+boardX,y*pieceH+boardY,pieceW,pieceH);
//				if(board.getPiece(x, y).col == PieceColor.White)fill(255);
//				else 
				fill(0);
//				stroke(0);
				if(!board.getPiece(x, y).isEmptyPiece())rect(x*pieceW+boardX+(pieceW-rechargeBarWidth)/2,y*pieceH+boardY-rechargeBarHeight+pieceH,rechargeBarWidth,rechargeBarHeight);
				if(highlightedPosses.contains(new PiecePos(x,y))){
					pushStyle();
					stroke(255);
					strokeWeight(2);
					line(x*pieceW+boardX,y*pieceH+boardY,x*pieceW+boardX+pieceW,y*pieceH+boardY+pieceH);
					line(x*pieceW+boardX+pieceW,y*pieceH+boardY,x*pieceW+boardX,y*pieceH+boardY+pieceH);
					popStyle();
				}
			}
		}
	}
	
}

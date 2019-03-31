package co.megadodo.mancala;


import processing.core.PApplet;
import processing.core.PVector;

public class NewMancalaProcessing extends PApplet {
	
	public static void main(String[] args) {
		PApplet.main("co.megadodo.mancala.NewMancalaProcessing");
	}
	
	public void settings() {
		size(750,500);
	}
	
//	MancalaBoard board;
	Button[] btnHomes;
	Button[][] btnHoles;
	RandColor titleColor;
	RandColor backgroundColor;
	UDPClientMancala client;
	
	public void setup() {
		client = new UDPClientMancala();
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				client.startClient();
//				client.attemptJoin();
//				client.doClientLoop();
//			}
//		}).start();
		client.threadClient();
		setupGame();
		setupColors();
	}
	
	public void setupGame() {
//		board = new MancalaBoard();
//		board.setup();
		setupButtons();
	}
	public void setupColors() {
		backgroundColor = new RandColor();
		backgroundColor.r = 255;
		backgroundColor.g = 255;
		backgroundColor.b = 255;
		backgroundColor.randR = 0.5f;
		backgroundColor.randG = 0.5f;
		backgroundColor.randB = 0.5f;
		
		titleColor = new RandColor();
		titleColor.r = 0;
		titleColor.g = 0;
		titleColor.b = 0;
		titleColor.randR = 5;
		titleColor.randG = 5;
		titleColor.randB = 5;
	}
	public void setupButtons() {
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
	public void drawButtons() {
		for(Button button : btnHomes) button.draw(true);
		for(Button[] btns : btnHoles) for(Button button : btns) button.draw(false);
	}
	public void clickButton(int player, int hole) {
		if(!btnHoles[player][hole].disabled) {
//			System.out.println("BEFORE:");
//			board.display();
			if(!client.attemptingMove()) client.attemptMove(new MancalaPos(player,hole));
//			System.out.println("AFTER:");
//			board.display();
//			System.out.println("\n\n\n");
		}
	}
	public void updateButtons() {
		for(int player = 0; player < 2; player++) {
			btnHomes[player].text = "" + client.board.homes[player];
			for(int hole = 0; hole < 6; hole++) {
				btnHoles[player][hole].text = "" + client.board.board[player][hole];
				btnHoles[player][hole].disabled = false;
				if(client.playerID != client.board.playerMoving || client.playerID != player) {
					btnHoles[player][hole].disabled = true;
				} else {
					if(client.board.board[player][hole] == 0) btnHoles[player][hole].disabled = true;
				}
				if(!btnHoles[player][hole].disabled && btnHoles[player][hole].btnState == BTN_CLICK) {
					clickButton(player,hole);
				}
			}
		}
	}
	//TODO: playerMoving bug is either in server of client, probably client
	public void draw() {
		backgroundColor.update(this);
		titleColor.update(this);
		background(backgroundColor.r,backgroundColor.g,backgroundColor.b);
		if(client.board == null) {
			pushStyle();
//			textSize(50);
			fill(0);
			textAlign(CENTER,CENTER);
			if(client.playerID == -1) {
				text("Attempting to join", width/2, height/2);
			} else {
				text("Waiting for other player", width/2, height/2);
			}
			popStyle();
		} else {
			drawButtons();
			updateButtons();
			drawGameStats();
			drawGameTitle();
			drawGameHelpers();
			client.board.display();
		}
	}
	
	public void drawGameHelpers() {
		textAlign(CENTER,CENTER);
		text("Player 1 side", width/2,height/2+RAD+SPACING);
		text("Player 0 side", width/2,height/2-RAD*2-SPACING*2);
		text("Player 1 home", width/2+RAD*4+SPACING*4,height/2-RAD*2-SPACING*2);
		text("Player 0 home", width/2-RAD*4-SPACING*4,height/2-RAD*2-SPACING*2);
	}
	public void drawGameTitle() {
		pushStyle();
		textSize(50);
		textAlign(CENTER,CENTER);
		noStroke();
		titleColor.draw(this);
		text("Mancala", width/2, 50);
		popStyle();
	}
	public void drawGameStats() {
		textAlign(LEFT,BASELINE);
		fill(0);
		text("Winning player (-1 for tie): " + client.board.winningPlayer(), 0, height-5);
		text("Current player: " + client.board.playerMoving, 0, height-25);
		text("Game over: (-1 for not over): " + client.board.isGameOver(), 0, height-45);
		text("Client #: " + client.playerID, 0, height-65);
	}

	int BTN_NORMAL = 0;
	int BTN_HOVER = 1;
	int BTN_CLICK = 2;
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
}

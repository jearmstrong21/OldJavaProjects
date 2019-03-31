package co.megadodo.newsuperresidentraver.engine;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements KeyListener {

	public Game game;
	public GameFrame(String title, Game g, int w, int h) {
		setTitle(title);
		setSize(w,h);
		setGame(g);
		this.addKeyListener(this);
	}
	
	public void paint(Graphics g) {
		game.paint(g);
	}
	
	public void setGame(Game g) {
		this.game = g;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(!game.keys.contains(e.getKeyChar()))game.keys.add((Character)e.getKeyChar());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(!game.keys.contains(e.getKeyChar()))game.keys.add((Character)e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(game.keys.contains(e.getKeyChar()))game.keys.remove((Character)e.getKeyChar());
	}
	
}

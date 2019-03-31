package co.megadodo.primedrawer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class PrimeDrawer extends JFrame {

	public static void main(String[] args) {
		new PrimeDrawer();
	}
	
	public PrimeDrawer() {
		this.setTitle("PrimeDrawer");
		this.setSize(600, 600);
		this.setVisible(true);
	}
	
	public boolean isPrime(int n) {
		if(n <= 1) return false;
		for(int i = 2; i <= n/2; i++) {
			if(n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int n = 1;
		int s = 5;
		// no even numbers, and every 2nd group of 3 lines has no prime
		// numbers
		int numPrimes = 0;
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		g2d.setColor(Color.BLACK);
		g2d.translate(0, 23);
		for(int x = 0; x < getWidth(); x+=s) {
			for(int y = 0; y < getHeight(); y+=s) {
				if(isPrime(n)) {
					g2d.setColor(new Color(0,0,0,255/2));
					g2d.fillRect(y, x, s, s);
					numPrimes++;
				}
//				g2d.setColor(Color.BLUE);
//				g2d.drawString(n + "", x+s/2, y+s/2);
				n++;
			}
		}
		g2d.setColor(Color.BLUE);
		g2d.setColor(new Color(0, 0, 255, 255/3));
		for(int i = 0; i <= getWidth()/s; i+=3) {
//			g2d.drawLine(0, i*s, getWidth(), i*s);
//			g2d.drawLine(i*s,0,i*s,gextWidth());
			if(i%2==0){
//				g2d.fillRect(0,i*s,getWidth(),s);
//				g2d.fillRect(0,i*s+s+s,getWidth(),s);
//				g2d.fillRect(i*s-s,0,s,getHeight());
//				g2d.fillRect(i*s+s,0,s,getHeight());
				g2d.fillRect(i*s, 0, s,getHeight());
				g2d.fillRect(i*s-s-s,0,s,getHeight());
			}
		}
		System.out.println((100*(numPrimes / (double) (getWidth()*getHeight()/s/s))));
	}
	
}

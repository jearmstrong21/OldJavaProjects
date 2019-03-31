package co.megadodo.cubingtimer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TimingFrame extends JFrame {
	
	String curTime = "12 : 34 : 56 : 789";
	
	JPanel pnl;
	
	public static void main(String[] args) {
//		new TimingFrame().setVisible(true);
		CubingTimer.main(args);
		System.out.println("TimingFrame:main(args[]) -> CubingTimer:main(args[])");
	}
	
	Cube cb = null;
	
	public TimingFrame() {
		this.setSize(550, 200);
		this.setTime(this.curTime);
		this.repaint();
		pnl = new JPanel() {

			public void paint(Graphics g) {
				Graphics2D g2d = (Graphics2D) g;
				
				g2d.setColor(Color.WHITE);
				g2d.fillRect(-1, -1, getWidth()+1, getHeight()+1);
				
				g2d.setColor(Color.BLACK);
				
				int size = 10;
				g2d.setFont(new Font("Monospace", Font.PLAIN, size));
				g2d.drawString("Press any key to pause/unpause", 10, size*2);
				g2d.drawString("Click anywhere on this window to finish", 10, size*3+5);
				
				drawTime(g2d);
			}
			
		};
		this.add(pnl);
	}
	
	public void setTime(String str) {
		this.setTitle(String.valueOf(cb) + " : " + str);
		this.curTime = str;
		this.repaint();
	}
	
	int unit = 20;
	
	
	public void drawTime(Graphics2D g2d) {
		int x = unit*2;
		String str = curTime.replace(" ", "");
		for(int i = 0; i < str.length(); i++) {
			drawDigit(g2d, str.charAt(i), x, getHeight()/2);
			if(str.charAt(i) == ':') {
				x+=unit*2/3;
			} else {
				x+=unit*3/2;
			}
		}
	}
	
//	public void drawEight(Graphics2D g2d, int x, int y) {
//		g2d.setColor(Color.BLACK);
//		g2d.drawLine(x, y, x+unit, y);
//		g2d.drawLine(x, y, x, y+unit);
//		g2d.drawLine(x,y+unit,x,y+unit*2);
//		g2d.drawLine(x,y+unit*2,x+unit,y+unit*2);
//		g2d.drawLine(x+unit,y,x+unit,y+unit);
//		g2d.drawLine(x+unit,y+unit,x+unit,y+unit*2);
//		g2d.drawLine(x,y+unit,x+unit,y+unit);
//	}
	
	public void drawDigit(Graphics2D g2d, char dig, int x, int y) {
//		g2d.setColor(Color.RED);
//		g2d.drawString(Character.toString(dig), x, y);
		if(dig == '0') {
			g2d.setColor(Color.BLACK);
			g2d.drawLine(x, y, x+unit, y);
			g2d.drawLine(x, y, x, y+unit);
			g2d.drawLine(x,y+unit,x,y+unit*2);
			g2d.drawLine(x,y+unit*2,x+unit,y+unit*2);
			g2d.drawLine(x+unit,y,x+unit,y+unit);
			g2d.drawLine(x+unit,y+unit,x+unit,y+unit*2);
		} else if(dig == '1') {
			g2d.setColor(Color.BLACK);
			g2d.drawLine(x, y, x, y+unit);
			g2d.drawLine(x, y+unit, x, y+unit*2);
		} else if(dig == '2') {
			g2d.drawLine(x, y, x+unit, y);
			g2d.drawLine(x,y+unit,x,y+unit*2);
			g2d.drawLine(x,y+unit*2,x+unit,y+unit*2);
			g2d.drawLine(x+unit,y,x+unit,y+unit);
			g2d.drawLine(x,y+unit,x+unit,y+unit);
		} else if(dig == '3') {
			g2d.drawLine(x, y, x+unit, y);
			g2d.drawLine(x,y+unit*2,x+unit,y+unit*2);
			g2d.drawLine(x+unit,y,x+unit,y+unit);
			g2d.drawLine(x+unit,y+unit,x+unit,y+unit*2);
			g2d.drawLine(x,y+unit,x+unit,y+unit);
		} else if(dig == '4') {
			g2d.drawLine(x, y, x, y+unit);
			g2d.drawLine(x+unit,y,x+unit,y+unit);
			g2d.drawLine(x+unit,y+unit,x+unit,y+unit*2);
			g2d.drawLine(x,y+unit,x+unit,y+unit);
		} else if(dig == '5') {
			g2d.drawLine(x, y, x+unit, y);
			g2d.drawLine(x, y, x, y+unit);
			g2d.drawLine(x,y+unit*2,x+unit,y+unit*2);
			g2d.drawLine(x+unit,y+unit,x+unit,y+unit*2);
			g2d.drawLine(x,y+unit,x+unit,y+unit);
		} else if(dig == '6') {
			g2d.drawLine(x, y, x+unit, y);
			g2d.drawLine(x, y, x, y+unit);
			g2d.drawLine(x,y+unit,x,y+unit*2);
			g2d.drawLine(x,y+unit*2,x+unit,y+unit*2);
			g2d.drawLine(x+unit,y+unit,x+unit,y+unit*2);
			g2d.drawLine(x,y+unit,x+unit,y+unit);
		} else if(dig == '7') {
			g2d.drawLine(x, y, x+unit, y);
			g2d.drawLine(x+unit,y,x+unit,y+unit);
			g2d.drawLine(x+unit,y+unit,x+unit,y+unit*2);
		} else if(dig == '8') {
			g2d.drawLine(x, y, x+unit, y);
			g2d.drawLine(x, y, x, y+unit);
			g2d.drawLine(x,y+unit,x,y+unit*2);
			g2d.drawLine(x,y+unit*2,x+unit,y+unit*2);
			g2d.drawLine(x+unit,y,x+unit,y+unit);
			g2d.drawLine(x+unit,y+unit,x+unit,y+unit*2);
			g2d.drawLine(x,y+unit,x+unit,y+unit);
		} else if(dig == '9') {
			g2d.drawLine(x, y, x+unit, y);
			g2d.drawLine(x, y, x, y+unit);
			g2d.drawLine(x,y+unit*2,x+unit,y+unit*2);
			g2d.drawLine(x+unit,y,x+unit,y+unit);
			g2d.drawLine(x+unit,y+unit,x+unit,y+unit*2);
			g2d.drawLine(x,y+unit,x+unit,y+unit);
		}
		
	}

}

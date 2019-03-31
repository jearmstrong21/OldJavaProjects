package co.megadodo.expressioneval.source;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	String equation = "";

	int W = 1100;
	int H = 750;
	
	int SX = 0;
	int SY = 0;
	int EX = 0;
	int EY = 0;
	double DELTA = 0;
	int S = 5;
	int GRID_LINE = 1;

	public Canvas(int W, int H) {
		this.W = W;
		this.H = H;
	}


	public void updateEquation() {
		repaint();
	}

	public void background(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, W, H);
	}

	public void drawAxes(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		g2d.drawLine(W / 2, 0, W / 2, H);
		g2d.drawLine(0, H / 2, W, H / 2);
	}

	public void drawEquation(Graphics2D g2d, Color col) {
		g2d.setColor(col);
		boolean debug = false;
		String[] split = equation.split("=");
		List<String> list = Arrays.asList(split);
//		System.out.println("equation: <" + equation + ">");
		Variables vars = new Variables();
		int a = S;
		int lastX = 0;
		int lastY = 0;
		for (int x = SX; x < EX; x += 1) {
			vars.setVar('x', x);
			for (int y = SY; y < EY; y += 1) {
				vars.setVar('y', y);
				double val = ExpressionEvalStacks.evalExpr(split[0], vars, debug);
				boolean valid = true;
				for (int i = 1; i < split.length; i++) {
					if (Math.abs(ExpressionEvalStacks.evalExpr(split[i], vars, debug) - val) > DELTA) {
						valid = false;
					}
				}
				if (valid)
					if (lastX != 0)
						if (lastY != 0)
							g2d.drawLine(lastX * S, lastY * S, x * S, y * S);
				if (valid) {
					lastX = x;
					lastY = y;
				}
				if (valid)
					g2d.fillRect((int) (x * S) - a / 2, (int) (y * S) - a / 2, S, S);
//				if (valid)
//					System.out.println("VALID: " + (x) + " " + y);
			}
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		background(g2d);
		drawAxes(g2d);
		g2d.translate(W / 2, H / 2);
		// g2d.scale(S, S);
		
		g2d.setColor(Color.GRAY);
		for(int x = SX; x < EX; x+=GRID_LINE) {
			for(int y = SY; y < EY; y+=GRID_LINE) {
				g2d.drawRect(x*S, y*S, GRID_LINE*S, GRID_LINE*S);
			}
		}

		drawEquation(g2d, Color.BLACK);
	}

}

package co.megadodo.problem24;

import java.awt.Color;

public abstract class ColorFilter {

	Problem24 prob;
	
	public ColorFilter(Problem24 prob) {
		// maybe generate colors
		this.prob = prob;
	}
	
	public abstract Color getCol(double num);
	
}

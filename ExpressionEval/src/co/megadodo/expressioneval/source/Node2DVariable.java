package co.megadodo.expressioneval.source;

public class Node2DVariable extends Node2D {
	public Node2DVariable(char var) {
		super();
		this.var = var;
	}

	@Override
	public double eval() {
		return Variables_Static.var(var);
	}
}

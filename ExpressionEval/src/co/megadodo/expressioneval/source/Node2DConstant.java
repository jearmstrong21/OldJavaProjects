package co.megadodo.expressioneval.source;

public class Node2DConstant extends Node2D{
	public Node2DConstant(double cons) {
		super();
		this.cons = cons;
	}
	
	public double eval() {
		return cons;
	}

}

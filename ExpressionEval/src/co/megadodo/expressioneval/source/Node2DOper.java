package co.megadodo.expressioneval.source;

public class Node2DOper extends Node2D {
	public Node2DOper(Operator oper, Node2D l, Node2D r) {
		super();
		this.o = oper;
		this.lNode = l;
		this.rNode = r;
	}

	@Override
	public double eval() {
		double l = lNode.eval();
		double r = rNode.eval();
		
		switch(o) {
			case DIVIDES: return l / r;
			case EXPONENT: return Math.pow(l, r);
			case MINUS: return l - r;
			case MODULO: return l % r;
			case PLUS: return l + r;
			case TIMES: return l * r;
		}
		
		return Double.NaN;
	}
}

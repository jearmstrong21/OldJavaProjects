package co.megadodo.expressioneval.source;

public abstract class Node2D {
	protected Node2D lNode = null;
	protected Node2D rNode = null;
	protected Operator o = null;
	protected Character var = null;
	protected Double cons = null;
	
	public Node2D getlNode() {
		return lNode;
	}

	public Node2D getrNode() {
		return rNode;
	}

	public Operator getO() {
		return o;
	}

	public Character getVar() {
		return var;
	}

	public Double getCons() {
		return cons;
	}

	public String toString() {
		return "Node2D |" + (o != null ? "O" + o.toString() : "") + (var != null ? "V" + var.toString() : "") + (cons != null ? "C" + cons.toString() : "") + "|";
	}
	
	public String getData() {
		if(o!=null)return o.toString();
		if(var!=null)return var.toString();
		if(cons!=null)return cons.toString();
		return "EMPTY NODE";
	}

	public abstract double eval();

}

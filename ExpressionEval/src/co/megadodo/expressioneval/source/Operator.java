package co.megadodo.expressioneval.source;

import java.util.ArrayList;

public enum Operator {
	PLUS('+',1),
	MINUS('-',1),
	TIMES('*',2),
	DIVIDES('/',2),
	MODULO('%',3),
	EXPONENT('^',3),
	L_PAREN('(',0),
	R_PAREN(')',5);
	
	char oper;
	int preced;
	Operator(Character oper, int preced) {
		this.oper = oper;
		this.preced = preced;
	}
	
	public String toString() {
		return "" + oper;
	}
	
	public static ArrayList<Character> opersList() {
		Operator[] vals = Operator.values();
		ArrayList<Character> list = new ArrayList<Character>();
		for(Operator o : vals) {
			list.add(o.oper);
		}
		return list;
	}
	// returns true if b should be done before a, or b has same preced as a.

	public static boolean higher(Operator b, Operator a) {
		return a.preced >= b.preced;
	}
}

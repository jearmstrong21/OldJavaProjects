package co.megadodo.expressioneval.source;

public class Test {

	public static void main(String[] args) {
		
		Variables_Static.addVar('x', 3);
		Variables_Static.addVar('y', 2);
		
		Node2D node = ExpressionEval.assembleExpression("3", "", true);

//		new DrawTree(node);
		System.out.println(node.eval());
		
	}
	
	public static double evalExpression(String exp) { return assembleExpression(exp, "").eval(); }
	
	public static Node2D assembleExpression(String exp, String indent) {
		/* Parentheses
		 * Assemble(expr):
		 * 	All top-level parentheses must be evaluated using Assemble(expr) and 
		 * 	  then replaced with the evaluated numbers
		 *  Evaluate what is left - DONE - Already done, just add first step to the beginning
		 */
		Node2D node = null;
//		exp = exp.replaceAll("\\s", "");
		System.out.println(indent + " assemble <" + exp + ">");
		if(isStringConstant(exp)) return new Node2DConstant(Double.parseDouble(exp));
//		if(isStringVariable(exp)) return new Node2DVariable(exp.charAt(0));
//		if(isBasicExpression(exp)) {
			String[] split = exp.split("[=]");
			if(split.length < 2) split = exp.split("[\\+]");
			if(split.length < 2) split = exp.split("[\\-]");
			if(split.length < 2) split = exp.split("[\\*]");
			if(split.length < 2) split = exp.split("[/]");
			if(split.length < 2) split = exp.split("[\\^]");
			if(split.length < 2) split = exp.split("[%]");
			char oper = exp.charAt(split[0].length());
			System.out.println(indent + " Split: <" + split[0] + "> " + oper + " <" + split[1] + ">");
			Node2D l = assembleExpression(split[0], indent + "   ");
			Node2D r = assembleExpression(split[1], indent + "   ");
			switch(oper) {
				case '=': return new Node2DConstant((l.eval()==r.eval()?0:1));
				case '+': return new Node2DOper(Operator.PLUS, l, r);
				case '-': return new Node2DOper(Operator.MINUS, l, r);
				case '*': return new Node2DOper(Operator.TIMES, l, r);
				case '/': return new Node2DOper(Operator.DIVIDES, l, r);
				case '^': return new Node2DOper(Operator.EXPONENT, l, r);
				case '%': return new Node2DOper(Operator.MODULO, l, r);
			}
//		}
//		String[] split = exp.split("[\\+\\-\\*/]");
//		char oper = exp.charAt(split[0].length());
//		
		return node;
	}
	
	public static boolean isStringConstant(String exp) {
		return exp.matches("^-??\\d+$");
	}
	
	public static boolean isStringVariable(String exp) {
		if(exp.length() > 1 || exp.length() == 0) return false;
		return Variables_Static.var(exp.charAt(0)) != Double.NaN;
	}
	
	public static Node2D getX() {
		return Variables_Static.varNode('x');
	}
	
	public static Node2D getY() {
		return Variables_Static.varNode('y');
	}

}

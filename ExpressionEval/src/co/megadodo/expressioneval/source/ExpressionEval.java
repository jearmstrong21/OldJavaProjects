package co.megadodo.expressioneval.source;

public class ExpressionEval {
	
	public static boolean isExprTrue(String expr) {
		return evalExpr(expr) == 0;
	}
	

	public static double evalExpr(String expr) {
		return assembleExpression(expr).eval();
	}
	
	public static double evalExpr(String expr, double x, double y) {
		double _x = Variables_Static.var('x');
		double _y = Variables_Static.var('y');
		Variables_Static.setVar('x', x);
		Variables_Static.setVar('y', y);
		
		double result = assembleExpression(expr).eval();
		Variables_Static.setVar('x', _x);
		Variables_Static.setVar('y', _y);
		
		return result;
	}
	
	public static boolean isExprTrue(String expr, double x, double y) {
		return evalExpr(expr, x, y) == 0;
	}
	
	public static Node2D assembleExpression(String exp) {
		return assembleExpression(exp, "", false);
	}

	public static Node2D assembleExpression(String exp, String indent, boolean debug) {
		/* Parentheses
		 * Assemble(expr):
		 * 	All top-level parentheses must be evaluated using Assemble(expr) and 
		 * 	  then replaced with the evaluated numbers
		 *  Evaluate what is left - DONE - Already done, just add first step to the beginning
		 */
		
		while(exp.contains("(")) {
			String copyExp = String.copyValueOf(exp.toCharArray());
			copyExp = copyExp.substring(copyExp.indexOf("(") + 1);
			copyExp = copyExp.substring(0, copyExp.indexOf(")"));

			System.out.println(copyExp);
//			copyExp = copyExp.replaceAll("\\.", "\\\\.");
			
			Double eval = assembleExpression(copyExp, indent + "     ", debug).eval();
			exp = exp.replaceAll("[(]" + copyExp + "[)]", Double.toString(eval));
		}
		
		if(debug) System.out.println(indent + "EXP AFTER PARENTHESES: <" + exp + ">");
		
		Node2D node = null;
		if(debug) System.out.println(indent + "assemble <" + exp + ">");
		exp = exp.replaceAll("\\s", "");
		if(isStringConstant(exp)) {
			node = new Node2DConstant(Double.parseDouble(exp));
			if(debug) System.out.println(indent + "OUTPUT CONST: " + node.eval());
			return node;
		}
		if(isStringVariable(exp)) {
			node = new Node2DVariable(exp.charAt(0));
			if(debug) System.out.println(indent + "OUTPUT VAR: " + node.eval());
			return node;
		}

		String[] split = exp.split("[=]");
		if(split.length < 2) split = exp.split("[\\+]");
		if(split.length < 2) split = exp.split("[\\-]");
		if(split.length < 2) split = exp.split("[\\*]");
		if(split.length < 2) split = exp.split("[/]");
		if(split.length < 2) split = exp.split("[\\^]");
		if(split.length < 2) split = exp.split("[%]");
		char oper = exp.charAt(split[0].length());
		if(debug) System.out.println(indent + "Split <" + exp + ">");
		if(debug) System.out.println(indent + "Split <" + split[0] + "> <" + oper + "> <" + split[1] + ">");
		if(debug) System.out.println();
		Node2D l = assembleExpression(split[0], indent + "|    ", debug);
		if(debug) System.out.println();
		Node2D r = assembleExpression(split[1], indent + "|    ", debug);
		if(debug) System.out.println(indent + "L: " + l.eval());
		if(debug) System.out.println(indent + "R: " + r.eval());
		switch(oper) {
			case '=': node = new Node2DConstant(   Math.abs(l.eval()-r.eval()) <= 10 ? 0 : 1 );
			case '+': node = new Node2DOper(Operator.PLUS, l, r);
			case '-': node = new Node2DOper(Operator.MINUS, l, r);
			case '*': node = new Node2DOper(Operator.TIMES, l, r);
			case '/': node = new Node2DOper(Operator.DIVIDES, l, r);
			case '^': node = new Node2DOper(Operator.EXPONENT, l, r);
			case '%': node = new Node2DOper(Operator.MODULO, l, r);
		}
		if(debug) System.out.println(indent + "OUTPUT OPER: " + node.eval());
		return node;
	}

	public static boolean isStringConstant(String exp) {
		return exp.matches("^-??\\d+\\.?\\d+$");
	}
	
	public static boolean isStringVariable(String exp) {
		if(exp.length() > 1 || exp.length() == 0) return false;
		return Variables_Static.var(exp.charAt(0)) != Double.NaN;
	}
}


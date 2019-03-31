package co.megadodo.problem24;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

public class ExpressionEvalStacks {
	
	static void print(String s) {
		System.out.println(s);
	}
	
	public static void main(String[] args) {
		Variables vars = new Variables();
		vars.setVar('x', 2);
		vars.setVar('y', 5);
		print(evalExpr("8/", vars, true) + "");
	}
	
	public static String spaceExpr(String expr) {
		expr = expr.replaceAll(Pattern.quote("+"), " + ");
		expr = expr.replaceAll(Pattern.quote("-"), " - ");
		expr = expr.replaceAll(Pattern.quote("*"), " * ");
		expr = expr.replaceAll(Pattern.quote("/"), " / ");
		expr = expr.replaceAll(Pattern.quote("%"), " % ");
		expr = expr.replaceAll(Pattern.quote("^"), " ^ ");
		expr = expr.replaceAll(Pattern.quote("("), " ( ");
		expr = expr.replaceAll(Pattern.quote(")"), " ) ");
		return expr;
	}
	
	public static double evalExpr(String expr, Variables vars, boolean debug) {
		Stack<Double> vals = new Stack<>();
		Stack<Operator> opers = new Stack<>();
		expr = spaceExpr(expr);
		Scanner sc = new Scanner(expr);
		if(debug) System.out.println("EXPR: <" + expr + ">");
		
		while(sc.hasNext()) {
			String nextToken = sc.next();
			if(isNumber(nextToken)) vals.push(number(nextToken));
			if(isVariable(nextToken, vars)) vals.push(variable(nextToken, vars));
			
			if(nextToken.equals("(")) opers.push(Operator.L_PAREN);
			if(nextToken.equals(")")) {
				while(opers.peek() != Operator.L_PAREN) {
					Operator popped = opers.pop();
					double operand2 = (vals.empty() ? 0 : vals.pop());
					double operand1 = (vals.empty() ? 0 : vals.pop());
					double result = applyOperator(operand1, operand2, popped);
					vals.push(result);
				}
				opers.pop();
			}
			
			if(isOperator(nextToken)) {
				Operator thisOp = oper(nextToken);

				while(!opers.isEmpty() && Operator.higher(thisOp, opers.peek())) {
					Operator popped = opers.pop();
					double operand2 = (vals.empty() ? 0 : vals.pop());
					double operand1 = (vals.empty() ? 0 : vals.pop());
					double result = applyOperator(operand1, operand2, popped);
					vals.push(result);
				}
				opers.push(thisOp);
				
			}
		}
		
		while(!opers.isEmpty()) {
			Operator o = opers.pop();
			double operand2 = (vals.empty() ? 0 : vals.pop());
			double operand1 = (vals.empty() ? 0 : vals.pop());
			double result = applyOperator(operand1, operand2, o);
			vals.push(result);
		}
		
		sc.close();
		
		return vals.pop();
	}
	
	public static double applyOperator(double a, double b, Operator o) {
		switch(o) {
			case DIVIDES: return a / b;
			case EXPONENT: return Math.pow(a, b);
			case MINUS: return a - b;
			case MODULO: return a % b;
			case PLUS: return a + b;
			case TIMES: return a * b;
			case L_PAREN: break;
			case R_PAREN: break;
		}
		return Double.NaN;
	}
	
	static double combo(double a, double b) {
		int n = 0;
		if(a == 0) n = (int)Math.round(b);
		if(b == 0) n = (int)Math.round(a);
		return comboInt(n);
	}
	
	static int comboInt(int n) {
		if(n <= 1) return 1;
		return comboInt(n-1) * n;
	}
	
	public static double number(String str) {
		return Double.parseDouble(str);
	}
	
	public static double variable(String str, Variables vars) {
		return vars.var(str.charAt(0));
	}
	
	public static Operator oper(String str) {
		char c = str.charAt(0);
		switch(c) {
			case '+': return Operator.PLUS;
			case '-': return Operator.MINUS;
			case '*': return Operator.TIMES;
			case '/': return Operator.DIVIDES;
			case '%': return Operator.MODULO;
			case '^': return Operator.EXPONENT;
			case '(': return Operator.L_PAREN;
			case ')': return Operator.R_PAREN;
		}
		return null;
	}
	
	public static boolean isVariable(String str, Variables vars) {
		return str.length() == 1 ? vars.hasVar(str.charAt(0)) : false;
	}
	
	public static boolean isNumber(String str) {
		try {
			Double.parseDouble(str);
		} catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	public static boolean isOperator(String str) {
		if(str.equals("(") || str.equals(")")) return false;
		return str.length() == 1 ? Operator.opersList().contains(str.charAt(0)) : false;
	}
}

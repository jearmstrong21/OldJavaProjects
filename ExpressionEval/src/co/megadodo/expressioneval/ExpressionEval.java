package co.megadodo.expressioneval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

public class ExpressionEval {

	public static final int EVAL_T = 1;
	public static final int EVAL_F = 1;
	
	
	public static void main(String[] args) {
		Variables vars = new Variables();
		vars.setVar("a", 30);
		Scanner sc = new Scanner(System.in);
		String expr = sc.nextLine();
		while(!expr.equalsIgnoreCase("done")) {
			System.out.println(evalInfix(expr, vars, true));
			expr = sc.nextLine();
		}
		
		sc.close();
	}
	
	private ExpressionEval() {} // dont instantiate
	
	public static String spaceExpr(String expr) {
		Operator[] opers = Operator.values();
		for(Operator oper : opers) {
			expr = expr.replaceAll(Pattern.quote(oper.oper + ""), " " + oper.oper + " ");
		}
		return expr;
	}
	
	public static boolean isNumber(String expr) {
		try {
			Double.parseDouble(expr);
		} catch(Throwable t) {
			return false;
		}
		return true;
	}
	
	public static double number(String expr) {
		return Double.parseDouble(expr);
	}
	
	public static boolean isOperator(String operator) {
		Operator[] opers = Operator.values();
		for(Operator o : opers) if(o.oper == operator.charAt(0)) return true;
		return false;
	}
	
	public static Operator operator(String str) {
		Operator[] opers = Operator.values();
		for(Operator o : opers) if(o.oper == str.charAt(0)) return o;
		return null;
	}
	
	public static double applyOperator(double _1, double _2, Operator o) {
		double result = Double.NaN;
		System.out.println("OPERATOR: " + o);
		switch(o) {
			case DIVIDES: result = _1 / _2; break;
			case EQUALS: result = (_1 == _2) ? EVAL_T : EVAL_F; break;
			case EXPONENT: result = Math.pow(_1, _2); break;
			case L_PAREN: result = Double.NaN; break;
			case L_THAN: result = (_1 < _2) ? EVAL_T : EVAL_F; break;
			case MINUS: result = _1 - _2; break;
			case MODULO: result = _1 % _1; break;
			case M_THAN: result = (_1 > _2) ? EVAL_T : EVAL_F; break;
			case PLUS: result = _1 + _2; break;
			case R_PAREN: result = Double.NaN; break;
			case TIMES: result = _1 * _2; break;
		}
		return result;
	}
	
	public static void evalStacks(Stack<Double> vals, Stack<Operator> opers) {
		double _2 = vals.pop();
		double _1 = vals.pop();
		Operator operator = opers.pop();
		vals.push(applyOperator(_1, _2, operator));
	}
	
	public static double evalPostfix(String expr, Variables vars) {
		Stack<Double> vals;
		Stack<Operator> opers;
		 vals = new Stack<>();
		opers = new Stack<>();
		
		Scanner sc = new Scanner(expr);
		
		while(sc.hasNext()) {
			String token = sc.next();
			System.out.println(token);
			if(isNumber(token)) {
				vals.push(number(token));
			} else if(vars.hasVar(token)) {
				vals.push(vars.var(token));
			} else if(token.equals("pi")) {
				vals.push(Math.PI);
			} else if(token.equals("e")) {
				vals.push(Math.E);
			} else if(isOperator(token)) {
				opers.push(operator(token));
				evalStacks(vals, opers);
			}
		}
		
		sc.close();
		
		return vals.pop();
	}

	public static boolean isVariable(String tok, Variables vars) {
		return vars.hasVar(tok);
	}
	
	public static double variable(String tok, Variables vars) {
		return vars.var(tok);
	}
	
	public static double evalInfix(String expr, Variables vars, boolean debug) {
		
		expr = spaceExpr(expr);
		Scanner sc = new Scanner(expr);
		
		if(debug) System.out.println("SPACED EXPR: " + expr);
		
		Stack<Double> vals = new Stack<>();
		Stack<Operator> opers = new Stack<>();
		while(sc.hasNext()) {
			String nextToken = sc.next();
			System.out.println("TOKEN: <" + nextToken + ">");
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
				Operator thisOp = operator(nextToken);

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
	
	public static void printStck(String str, Stack stck) {
		System.out.println(str + " " + Arrays.toString(stck.toArray()));
	}

}















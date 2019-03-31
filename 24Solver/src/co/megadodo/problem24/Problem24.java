package co.megadodo.problem24;

import static java.lang.System.in;
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Scanner;

public class Problem24 {

	private static Scanner sc;
	
	static {
		sc = new Scanner(in);
	
	}
	
	public static final float map(float value, float start1, float stop1, float start2, float stop2) {
		return start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1));
	}
	
	public static int map(int v, int a, int b, int c, int d) {
		return(int)map(v,a,b,c,d);
	}
	
	public static void Main(String[] args) {
		
		int a;
		int b;
		int c;
		int d;
		boolean exp;
		boolean mod;
		
		out.println("Welcome to the 24 problem solver!");
		out.println("Enter the 4 numbers:");
		
		out.print("A = ");
		a = sc.nextInt();
		
		out.print("B = ");
		b = sc.nextInt();
		
		out.print("C = ");
		c = sc.nextInt();
		
		out.print("D = ");
		d = sc.nextInt();
		
		out.print("Exp? ");
		exp = sc.next().equalsIgnoreCase("Y");
		
		out.print("Mod? ");
		mod = sc.next().equalsIgnoreCase("Y");
		
		sc.close();
		
		Problem24 instance = new Problem24(a, b, c, d, exp, mod);
		
		instance.solve();
		instance.printAnswers();
	}
	
	int a;
	int b;
	int c;
	int d;
	boolean exp;
	boolean mod;
	Variables vars;
	ArrayList<String> validExprs;
	ArrayList<String> exprs;
	
	public Problem24(int a, int b, int c, int d, boolean exp, boolean mod) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.vars = new Variables();
		this.vars.setVar('a', a);
		this.vars.setVar('b', b);
		this.vars.setVar('c', c);
		this.vars.setVar('d', d);
		this.exp = exp;
		this.mod = mod;
	}
	
	public boolean isExprCorrect(String str) {
		return 24 == ExpressionEvalStacks.evalExpr(str, this.vars, false);
	}
	
	int counter = 0;
	
	public void findExprs() {
		validExprs = new ArrayList<String>();
		exprs = new ArrayList<String>();
		// expr
		// = vars[var1] + opers[oper1] 
		// + vars[var2] + opers[oper2] 
		// + vars[var3] + opers[oper3] + vars[var4]
		String[] vars = new String[] { Integer.toString(a), Integer.toString(b), Integer.toString(c), Integer.toString(d) };
		String[] opers = new String[4 + (exp ? 1 : 0) + (mod ? 1 : 0)];
		opers[0] = "+";
		opers[1] = "-";
		opers[2] = "*";
		opers[3] = "/";
		if(exp) {
			opers[4] = "^";
		}
		if(mod) {
			if(exp) opers[5] = "%";
			else opers[4] = "%";
		}
		for(int var1 = 0; var1 < 4; var1++) {
			for(int oper1 = 0; oper1 < opers.length; oper1++) {
				for(int var2 = 0; var2 < 4; var2++) {
					if(var1 == var2) continue;
					for(int oper2 = 0; oper2 < opers.length; oper2++) {
						for(int var3 = 0; var3 < 4; var3++) {
							if(var1 == var3 || var2 == var3) continue;
							for(int oper3 = 0; oper3 < opers.length; oper3++) {
								for(int var4 = 0; var4 < 4; var4++) {
									if(var1 == var4 || var2 == var4 || var3 == var4) continue;
									String expr = ExpressionEvalStacks.spaceExpr(
											
											vars[var1] + opers[oper1] + vars[var2]
													+ opers[oper2] + vars[var3]
															+ opers[oper3]
																	+ vars[var4]
											
											);
									if(isExprCorrect(expr)) validExprs.add(expr);
									exprs.add(expr);
									counter++;
								}
							}
						}
					}
				}
			}
		}
		out.println(counter + " iterations to find solutions.");
	}
	
	public void solve() {
		findExprs();    // populate validExprs list
	}
	
	public void printAnswers() {
		out.println("Answers to 24 problem:");
		for(String s : validExprs) {
			out.println("24 = " + s);
		}
		out.println(validExprs.size() + " valid solutions.");
	}

}

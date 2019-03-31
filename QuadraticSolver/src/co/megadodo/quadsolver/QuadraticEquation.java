package co.megadodo.quadsolver;

public class QuadraticEquation {

	double a;
	double b;
	double c;
	
	public void setA(double a) { this.a = a; }
	public void setB(double b) { this.b = b; }
	public void setC(double c) { this.c = c; }
	
	public double determ() {
		return this.b*this.b - 4*this.a*this.c;
	}
	
	public int numDistinctRoots() {
		return (root1()==root2()) ? 1 : 2;
	}
	// TODO vertexes, maxima / minima
	
	public boolean isUpward() {
		return a > 0;
	}
	
	public boolean isDownard() {
		return a < 0;
	}
	
	public double vertex() {
		return -b / (2*a);
	}
	
	public double evalVertex() {
		return forValue(vertex());
	}
	
	public String vertexPoint() {
		return "(" + vertex() + ", " + evalVertex() + ")";
	}
	
	// end TODO
	
	public String toString() {
		String str = a + "x^2 ";
		if(b < 0) str += "- " + (-b) + "x ";
		else if(b == 0) str += "";
		else /* b>0 */ str += "+ " + b + "x ";
		
		if(c < 0) str += "- " + (-c);
		else if(c == 0) str += "";
		else /* c>0 */ str += "+ " + c;
		return str;
	}
	
	public double smallestRoot() {
		double r1 = root1();
		double r2 = root2();
		return (r1 < r2 ? r1 : r2);
	}
	
	public double largestRoot() {
		double r1 = root1();
		double r2 = root2();
		return (r1 > r2 ? r1 : r2);
	}
	
	public double root1Numerator() {
		return -b - sqrtDeterm();
	}
	
	public double root2Numerator() {
		return -b + sqrtDeterm();
	}
	
	public String root1Str() {
		return root1Numerator() + " / " + (2*a);
	}
	
	public String root2Str() {
		return root2Numerator() + " / " + (2*a);
	}
	
	public String asFunction(char funcName) {
		return funcName + "(x) = " + toString();
	}
	
	public String asFunction() {
		return asFunction('f');
	}
	
	public String strFactor(int rootNum) {
		double root = (rootNum == 1 ? root1() : root2());
		if(root < 0) return "(x + " + (root*-1) + ")";
		if(root == 0) return "(x)";
		if(root > 0) return "(x - " + root + ")";
		return "()";
	}
	
	public String toStringFactored() {
		return strFactor(1) + strFactor(2);
	}
	
	public String asFactoredFunction(char funcName) {
		return funcName + "(x) = " + toStringFactored();
	}
	
	public String asFactoredFunction() {
		return asFactoredFunction('f');
	}
	
	public double sqrtDeterm() {
		return Math.sqrt(this.determ());
	}
	
	public double sol1() {
		return (-b - sqrtDeterm()) / (2*a);
	}
	
	public double sol2() {
		return (-b + sqrtDeterm()) / (2*a);
	}
	
	public double root1() {
		return sol1();
	}
	
	public double root2() {
		return sol2();
	}
	
	public double sumRoots() {
		return -b / a;
	}
	
	public double forValue(double x) {
		return a*x*x + b*x + c;
	}
	
}

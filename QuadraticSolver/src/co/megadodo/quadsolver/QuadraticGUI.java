package co.megadodo.quadsolver;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class QuadraticGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		QuadraticGUI gui = new QuadraticGUI();
	}
	
	JLabel lblA;
	JLabel lblB;
	JLabel lblC;
	JTextField tfA;
	JTextField tfB;
	JTextField tfC;
	JPanel pnlA;
	JPanel pnlB;
	JPanel pnlC;
	JPanel pnlVars;
	GridLayout layoutPnlVars;
	JLabel lblOutput;
	
	public double parseDouble(String str) {
		try {
			return Double.parseDouble(str);
		} catch(Throwable t) {
			return Double.NaN;
		}
	}
	
	public void recalc() {
		double a = parseDouble(tfA.getText());
		double b = parseDouble(tfB.getText());
		double c = parseDouble(tfC.getText());
		QuadraticEquation eq = new QuadraticEquation();
		eq.setA(a);
		eq.setB(b);
		eq.setC(c);
		String txt = "<html><body><pre>";
		txt+="  Equation: " + eq.toString() + "</br>\n";
		txt+="Factored form: " + eq.toStringFactored() + "</br>\n";
		txt+="Equation as function" + eq.asFunction() + "</br>\n";
		txt+="Factored form as function: " + eq.asFactoredFunction() + "</br>\n";
		txt+="Root #1: " + eq.root1() + "</br>\n";
		txt+="Root #2: " + eq.root2() + "</br>\n";
		txt+="Root #1 Numerator: " + eq.root1Numerator() + "</br>\n";
		txt+="Root #2 Numerator: " + eq.root2Numerator() + "</br>\n";
		txt+="Root #1 Fraction: " + eq.root1Str() + "</br>\n";
		txt+="Root #2 Fraction: " + eq.root2Str() + "</br>\n";
		txt+="Determinant: " + eq.determ() + "</br>\n";
		txt+="Square root of determinant: " + eq.sqrtDeterm() + "</br>\n";
		txt+="Sum of roots: " + eq.sumRoots() + "</br>\n";
		txt+="Largest root: " + eq.largestRoot() + "</br>\n";
		txt+="Smallest root: " + eq.smallestRoot() + "</br>\n";
		txt+="# of distinct roots: " + eq.numDistinctRoots() + "</br>\n";
		txt+="Vertex: " + eq.vertex() + "</br>\n";
		txt+="Evaluate at vertex: " + eq.evalVertex() + "</br>\n";
		txt+="Vertex point: " + eq.vertexPoint() + "</br>\n";
		txt+="Upwards parabola: " + eq.isUpward() + "</br>\n";
		txt+="Downwards parabola: " + eq.isDownard() +"</br>\n";
		txt+="</body></pre>	</html>";
		txt = txt.replace("\n", "\n  ");
		lblOutput.setText(txt);
		System.out.println(txt.replace("</br>", "\n").replace("<html>", "").replace("</html>",""));
		this.invalidate();
	}
	
	public QuadraticGUI() {
		this.setSize(500, 600);
		this.setTitle("Quadratic Solver");
		pnlA = new JPanel();
		lblA = new JLabel("A: ");
		tfA = new JTextField();
		tfA.setColumns(20);
		pnlA.add(lblA); 
		pnlA.add(tfA);
		
		pnlB = new JPanel();
		lblB = new JLabel("B: "); 
		tfB = new JTextField();
		tfB.setColumns(20);
pnlB.add(lblB);
		pnlB.add(tfB);
		
		pnlC = new JPanel();
		lblC = new JLabel("C: ");
		tfC = new JTextField();
		tfC.setColumns(20);
pnlC.add(lblC);
		pnlC.add(tfC);
		
		pnlVars = new JPanel();
		layoutPnlVars = new GridLayout(3, 1);
		pnlVars.setLayout(layoutPnlVars);
		pnlVars.add(pnlA);
		pnlVars.add(pnlB);
		pnlVars.add(pnlC);
		
		lblOutput = new JLabel();
		
		tfA.addActionListener((e) -> recalc());
		tfB.addActionListener((e) -> recalc());
		tfC.addActionListener((e) -> recalc());
		JScrollPane pane = new JScrollPane(lblOutput);
		System.out.println(this);
		this.setLayout(new GridLayout(2, 1));
		this.add(pnlVars);
		this.add(pane);
		this.setVisible(true);
	}
}
//
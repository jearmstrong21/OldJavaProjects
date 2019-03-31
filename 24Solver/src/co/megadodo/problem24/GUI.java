package co.megadodo.problem24;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class GUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new GUI();
	}

	GridLayout mainLayout;
	GridLayout varsLayout;
	GridLayout cboxLayout;
	GridLayout srpnLayout; // scrollpane layout
	GridLayout layout;
	
	JPanel panel;
	
	NumberLine nmbrLine;
	
	JPanel pnlVars;
	
	JPanel pnlCboxes;
	
	JScrollPane scrpnSols;		// scrpnsSols -> pnlSols -> {lstSols, lblSols}
	JPanel pnlSols;
	JLabel lblSols;
	JList<String> lstSols;
	
	JButton btSolve;
	
	JPanel pnlVarA;
	JPanel pnlVarB;
	JPanel pnlVarC;
	JPanel pnlVarD;
	
	JLabel lblVarA;
	JLabel lblVarB;
	JLabel lblVarC;
	JLabel lblVarD;
	
	JTextField txtVarA;
	JTextField txtVarB;
	JTextField txtVarC;
	JTextField txtVarD;
	
	JCheckBox cboxExp;
	JCheckBox cboxMod;
	
	public int parseInt(String str) { // Exception-safe
		str = str.trim();
		try {
			return Integer.parseInt(str);
		} catch(NumberFormatException nfe) {
			return 0;
		}
	}
	
	public int getA() {
		return parseInt(txtVarA.getText());
	}
	
	public int getB() {
		return parseInt(txtVarB.getText());
	}
	
	public int getC() {
		return parseInt(txtVarC.getText());
	}
	
	public int getD() {
		return parseInt(txtVarD.getText());
	}
	
	public boolean getExp() {
		return cboxExp.isSelected();
	}
	
	public boolean getMod() {
		return cboxMod.isSelected();
	}
	
	public ArrayList<String> calcList(ArrayList<String> exprs) {
		int numPerLine = 1;
		ArrayList<String> list = new ArrayList<String>();
		
		int perLine = 0;
		String cur = "";
		for(int i = 0; i < exprs.size(); i++) {
			if(perLine >= numPerLine) {
				list.add(cur);
				cur = "";
				perLine = 0;
			}
			cur += String.format("% 5d", i+1) + ": " + exprs.get(i);
			perLine++;
		}
		list.add(cur);
		
		return list;
	}
	
	public void solve() {
		nmbrLine.prob = new Problem24(getA(), getB(), getC(), getD(), getExp(), getMod());
		nmbrLine.prob.solve();
		nmbrLine.repaint();
		lstSols.setListData(new Vector<String>(calcList(nmbrLine.prob.validExprs)));
		lblSols.setText("<html>" + "Number of iterations to calculate solutions: " + nmbrLine.prob.counter + "<br>Number of solutions: " + nmbrLine.prob.validExprs.size() + "<br>Solutions:" + "</html>");
	}
	
	public GUI() {
		nmbrLine = new NumberLine();
		scrpnSols = new JScrollPane();
		srpnLayout = new GridLayout(2, 1);
			pnlSols = new JPanel();
				lblSols = new JLabel("Solutions:");
				lstSols = new JList<String>();
				lstSols.setLayoutOrientation(JList.HORIZONTAL_WRAP);
				lstSols.setLayoutOrientation(JList.VERTICAL_WRAP);
				lstSols.setCellRenderer(new MyListCellRenderer());
			pnlSols.setLayout(srpnLayout);
			pnlSols.add(lblSols);
			pnlSols.add(lstSols);
		scrpnSols.setViewportView(pnlSols);
		
		pnlCboxes = new JPanel();
			cboxExp = new JCheckBox("Use exponent (^) operator", false);
			cboxMod = new JCheckBox("Use modulo (%) operator", false);
			cboxLayout = new GridLayout(2, 1);
		pnlCboxes.setLayout(cboxLayout);
		pnlCboxes.add(cboxExp);
		pnlCboxes.add(cboxMod);
		
		pnlVars = new JPanel();
			btSolve = new JButton("Solve 24 Problem");
			btSolve.addActionListener( event -> solve() );
			pnlVarA = new JPanel();
				lblVarA = new JLabel("A: ");
				txtVarA = new JTextField();
				txtVarA.setText("0");
				txtVarA.setColumns(10);
			pnlVarA.add(lblVarA);
			pnlVarA.add(txtVarA);
			pnlVarB = new JPanel();
				lblVarB = new JLabel("B: ");
				txtVarB = new JTextField();
				txtVarB.setText("0");
				txtVarB.setColumns(10);
			pnlVarB.add(lblVarB);
			pnlVarB.add(txtVarB);
			pnlVarC = new JPanel();
				lblVarC = new JLabel("C: ");
				txtVarC = new JTextField();
				txtVarC.setText("0");
				txtVarC.setColumns(10);
			pnlVarC.add(lblVarC);
			pnlVarC.add(txtVarC);
			pnlVarD = new JPanel();
				lblVarD = new JLabel("D: ");
				txtVarD = new JTextField();
				txtVarD.setText("0");
				txtVarD.setColumns(10);
			pnlVarD.add(lblVarD);
			pnlVarD.add(txtVarD);
			varsLayout = new GridLayout(5,1);
		pnlVars.setLayout(varsLayout);
		pnlVars.add(pnlVarA);
		pnlVars.add(pnlVarB);
		pnlVars.add(pnlVarC);
		pnlVars.add(pnlVarD);
		pnlVars.add(btSolve);
		layout = new GridLayout(1, 3);
		panel = new JPanel();
		panel.setLayout(layout);
		panel.add(pnlVars);
		panel.add(pnlCboxes);
		
		mainLayout = new GridLayout(2, 1);
		lstSols.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 10));
		this.setLayout(mainLayout);
		this.add(panel);
		this.add(scrpnSols);
		panel.add(nmbrLine);
		
		this.setTitle("24 Problem solver");
		this.setSize(750, 500);
		this.setVisible(true);
//		this.pack();
	}
	
}

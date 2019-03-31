package co.megadodo.cpcalculator;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Frame {

	JFrame frame;
	JLabel lblA;
	JLabel lblB;
	JTextField tfA;
	JTextField tfB;
	JPanel pnlA;
	JPanel pnlB;
	JPanel pnlInput;
	JButton btnCalc;
	GridLayout inputLayout;
	GridLayout frameLayout;
	GridLayout outputLayout;
	JPanel pnlOutput;
	JLabel lblOutputL;
	JLabel lblOutputR;
	
	public Frame() {
		frame = new JFrame("Combination and Permutation calculator");
			frameLayout = new GridLayout(3,1);
			pnlInput = new JPanel();
				inputLayout = new GridLayout(1,2);
				pnlA = new JPanel();
					lblA = new JLabel("A:");
					tfA = new JTextField("0");
					tfA.setColumns(20);
				pnlA.add(lblA);
				pnlA.add(tfA);
				pnlB = new JPanel();
					lblB = new JLabel("B:");
					tfB = new JTextField("0");
					tfB.setColumns(20);
				pnlB.add(lblB);
				pnlB.add(tfB);
			pnlInput.setLayout(inputLayout);
			pnlInput.add(pnlA);
			pnlInput.add(pnlB);
			btnCalc = new JButton("Calculate");
			pnlOutput = new JPanel();
				outputLayout = new GridLayout(1, 2);
				lblOutputL = new JLabel();
				lblOutputR = new JLabel();
			pnlOutput.setLayout(outputLayout);
			pnlOutput.add(lblOutputL);
			pnlOutput.add(lblOutputR);
			
		frame.setLayout(frameLayout);
		frame.add(pnlInput);
		frame.add(btnCalc);
		frame.add(pnlOutput);
		
		frame.setSize(500, 500);
		frame.setVisible(true);
		
		ActionListener actionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				long a = Long.parseLong(tfA.getText());
				long b = Long.parseLong(tfB.getText());
				lblOutputL.setText("Perm: " + perm(a,b));
				lblOutputR.setText("Combo: " + combo(a,b));
			}
		};

		tfA.addActionListener(actionListener);
		tfB.addActionListener(actionListener);
		
		btnCalc.addActionListener(actionListener);
		
	}
	
	public static long perm(long a, long b) {
//		return facto(a)/facto(a-b);
		long result = 1;
		for(long i = a; i > a-b; i--) result*=i;
		return result;
	}
	
	public static long combo(long a, long b) {
//		return facto(a)/(facto(b) * facto(a-b));
		return perm(a,b)/facto(b);
	}
	
	public static long facto(long n) {
		long total = 1;
		for(int i = 1; i <= n; i++) {
			total*=i;
		}
		return total;
	}
	
	public static void main(String[] args) {
		new Frame();
	}
	
}

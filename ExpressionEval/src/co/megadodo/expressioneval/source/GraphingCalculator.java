package co.megadodo.expressioneval.source;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class GraphingCalculator extends JFrame {
	
	int W = 1400;
	int H = 800;
	
	public static void main(String[] args) {
		new GraphingCalculator();
	}
	
	JPanel  equatPanel;
	JPanel settingsPanel;
	JLabel  topLabel;
	JButton addEquation;
	GridLayout equatPanelLayout;
	GridLayout settingsPanelLayout;
	JPanel equat;
	JTextField text;
	
	Canvas canvas;

	JLabel lbDelta;
	JTextField tfDelta;
	
	JLabel lbS;
	JTextField tfS;
	
	JLabel lbSX;
	JTextField tfSX;
	
	JLabel lbSY;
	JTextField tfSY;
	
	JLabel lbEX;
	JTextField tfEX;
	
	JLabel lbEY;
	JTextField tfEY;
	
	JLabel lbGL;
	JTextField tfGL;
	
//	int min = -100;
//	int max = 100;
	
	public void initGUI() {
		canvas = new Canvas(1100, 750);
		
		lbDelta = new JLabel("Delta");
		tfDelta = new JTextField();
		
		lbS = new JLabel("S");
		tfS = new JTextField();
		
		lbSX = new JLabel("SX");
		tfSX = new JTextField();
		
		lbSY = new JLabel("SY");
		tfSY = new JTextField();
		
		lbEX = new JLabel("EX");
		tfEX = new JTextField();
		
		lbEY = new JLabel("EY");
		tfEY = new JTextField();
		
		lbGL = new JLabel("GL");
		tfGL = new JTextField();
		

		equat = new JPanel();
		
		text = new JTextField();
		
		equatPanel = new JPanel();
		settingsPanel = new JPanel();
		
		equatPanelLayout = new GridLayout(2, 1);
		settingsPanelLayout = new GridLayout(7, 2);
		
	}
	public void addGUI() {
		settingsPanel.add(lbDelta);
		settingsPanel.add(tfDelta);
		
		settingsPanel.add(lbS);
		settingsPanel.add(tfS);
		
		settingsPanel.add(lbSX);
		settingsPanel.add(tfSX);
		
		settingsPanel.add(lbSY);
		settingsPanel.add(tfSY);
		
		settingsPanel.add(lbEX);
		settingsPanel.add(tfEX);
		
		settingsPanel.add(lbEY);
		settingsPanel.add(tfEY);
		
		settingsPanel.add(lbGL);
		settingsPanel.add(tfGL);

		equat.add(text);
		
		equatPanel.add(equat);

		equatPanel.add(settingsPanel);

		this.add(topLabel, BorderLayout.PAGE_START);
		this.add(equatPanel, BorderLayout.LINE_START);
		this.add(canvas, BorderLayout.CENTER);
		
	}
	public void initGUISettings() {
		canvas.equation = "x = y";
		int a = 100;
		canvas.S = 1;
		canvas.GRID_LINE = 20;
		canvas.DELTA = 1;
		canvas.SX = -a;
		canvas.SY = -a;
		canvas.EX = a;
		canvas.EY = a;

		lbDelta.setHorizontalAlignment(JLabel.RIGHT);
		lbS.setHorizontalAlignment(JLabel.RIGHT);
		lbSX.setHorizontalAlignment(JLabel.RIGHT);
		lbSY.setHorizontalAlignment(JLabel.RIGHT);
		lbEX.setHorizontalAlignment(JLabel.RIGHT);
		lbEY.setHorizontalAlignment(JLabel.RIGHT);
		lbGL.setHorizontalAlignment(JLabel.RIGHT);
		
		equatPanel.setPreferredSize(new Dimension(300, H));
		equatPanel.setLayout(equatPanelLayout);
		settingsPanel.setLayout(settingsPanelLayout);
		
		text.setPreferredSize(new Dimension(250, 20));
		
		topLabel = new JLabel("Graphing Calculator");
		topLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40));
		topLabel.setHorizontalAlignment(JLabel.CENTER);
		topLabel.setVerticalAlignment(JLabel.CENTER);
		topLabel.setPreferredSize(new Dimension(W, 50));
	}
	public void initActions() {
		text.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.equation = text.getText();
				canvas.updateEquation();
			}
		});
		tfDelta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.DELTA = Integer.parseInt(tfDelta.getText());
				canvas.updateEquation();
			}
		});
		tfS.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.S = Integer.parseInt(tfS.getText());
				canvas.updateEquation();
			}
		});
		tfSX.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.SX = Integer.parseInt(tfSX.getText());
				canvas.updateEquation();
			}
		});
		tfSY.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.SY = Integer.parseInt(tfSY.getText());
				canvas.updateEquation();
			}
		});
		tfEX.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.EX = Integer.parseInt(tfEX.getText());
				canvas.updateEquation();
			}
		});
		tfEY.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.EY = Integer.parseInt(tfEY.getText());
				canvas.updateEquation();
			}
		});
		tfGL.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.GRID_LINE = Integer.parseInt(tfGL.getText());
				canvas.updateEquation();
			}
		});
	
	}
	
	public GraphingCalculator() {
		initGUI();
		initGUISettings();
		initActions();
		addGUI();
		
		tfDelta.setText(canvas.DELTA + "");
		tfS.setText(canvas.S + "");
		tfSX.setText(canvas.SX + "");
		tfSY.setText(canvas.SY + "");
		tfEX.setText(canvas.EX + "");
		tfEY.setText(canvas.EY + "");
		tfGL.setText(canvas.GRID_LINE + "");
		
		this.setTitle("Graphing Calculator");
		this.setSize(W, H);
		this.setVisible(true);
	}
}

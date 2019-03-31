package co.megadodo.lolcodeide1;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;

public class LOLCodePrefs {
	
	public static int TEXTSIZE_PROJ_MAN = 10;
	public static int TEXTSIZE_CONSOLE = 10;
	public static int TEXTSIZE_EDITOR = 10;
	public static Color EDITOR_HIGHLIGHT = new Color(255, 109, 106,255);
	public static Color TEXT_COLOR = new Color(0,0,0);
	public static String ROOT_FILE = "/Users/jackarmstrong/LOLCode/";	
	
	JFrame frame;
	LayoutManager frameLayout;
	
//	JLabel lblTxtSizeProjMan;
//	JSpinner inpTxtSizeProjMan;
//	JPanel pnlTxtSizeProjMan;
//	LayoutManager lytTxtSizeProjMan;
	
//	JLabel lblTxtSizeCnsl;
//	JSpinner inpTxtSizeCnsl;
//	JPanel pnlTxtSizeCnsl;
//	LayoutManager lytTxtSizeCnsl;
	
//	JLabel lblTxtSizeEdtr;
//	JSpinner inpTxtSizeEdtr;
//	JPanel pnlTxtSizeEdtr;
//	LayoutManager lytTxtSizeEdtr;
	
	JLabel lblEdtrHilit;
	LOLCodeColorComp clcEdtrHilit;
	JPanel pnlEdtrHilit;
	LayoutManager lytEdtrHilit;
	
	JLabel lblTxtCol;
	LOLCodeColorComp clcTxtCol;
	JPanel pnlTxtCol;
	LayoutManager lytTxtCol;
	
	JLabel lblRootFile;
	JButton btnRootFile;
	JFileChooser flcRootFile;
	JPanel pnlRootFile;
	LayoutManager lytRootFile;
	
	JButton btnClose;
	JButton btnReset;
	JButton btnApply;
	
	int WIDTH = 500;
	int HEIGHT = 500;
	
	public static void main(String[] args) {
		LOLCodePrefs prefs = new LOLCodePrefs();
		prefs.show();
		System.out.println("co.megadodo.lolcodeide1.LOLCodePrefs.main(String[] args)");
	}
	
	public LayoutManager genInputLayout() {
		return genInputLayout(2);
	}
	
	public LayoutManager genInputLayout(int i) {
		return new GridLayout(1, i);
	}
	
	public LOLCodePrefs() {
		frame = new JFrame("Preferences");
		frame.setSize(WIDTH,HEIGHT);
		frameLayout = new GridLayout(9,2);
		
//		lblTxtSizeProjMan = new JLabel("Project Manager text size:");
//		inpTxtSizeProjMan = new JSpinner();
//		inpTxtSizeProjMan.setValue(TEXTSIZE_PROJ_MAN);
//		lytTxtSizeProjMan = genInputLayout();
//		pnlTxtSizeProjMan = new JPanel();
//		pnlTxtSizeProjMan.setLayout(lytTxtSizeProjMan);
//		pnlTxtSizeProjMan.add(lblTxtSizeProjMan);
//		pnlTxtSizeProjMan.add(inpTxtSizeProjMan);
		
//		lblTxtSizeCnsl = new JLabel("Console text size:");
//		inpTxtSizeCnsl = new JSpinner();
//		inpTxtSizeCnsl.setValue(TEXTSIZE_CONSOLE);
//		lytTxtSizeCnsl = genInputLayout();
//		pnlTxtSizeCnsl = new JPanel();
//		pnlTxtSizeCnsl.setLayout(lytTxtSizeCnsl);
//		pnlTxtSizeCnsl.add(lblTxtSizeCnsl);
//		pnlTxtSizeCnsl.add(inpTxtSizeCnsl);
		
//		lblTxtSizeEdtr = new JLabel("Editor text size:");
//		inpTxtSizeEdtr = new JSpinner();
//		inpTxtSizeEdtr.setValue(TEXTSIZE_EDITOR);
//		lytTxtSizeEdtr = genInputLayout();
//		pnlTxtSizeEdtr = new JPanel();
//		pnlTxtSizeEdtr.setLayout(lytTxtSizeEdtr);
//		pnlTxtSizeEdtr.add(lblTxtSizeEdtr);
//		pnlTxtSizeEdtr.add(inpTxtSizeEdtr);
		
		lblEdtrHilit = new JLabel("Editor highlight color:");
		lytEdtrHilit = genInputLayout();
		clcEdtrHilit = new LOLCodeColorComp(EDITOR_HIGHLIGHT);
		pnlEdtrHilit = new JPanel();
		pnlEdtrHilit.setLayout(lytEdtrHilit);
		pnlEdtrHilit.add(lblEdtrHilit);
		pnlEdtrHilit.add(clcEdtrHilit);
		
		lblTxtCol = new JLabel("Text color:");
		lytTxtCol = genInputLayout();
		clcTxtCol = new LOLCodeColorComp(TEXT_COLOR);
		pnlTxtCol = new JPanel();
		pnlTxtCol.setLayout(lytTxtCol);
		pnlTxtCol.add(lblTxtCol);
		pnlTxtCol.add(clcTxtCol);
		
		lblRootFile = new JLabel("Root file:");
		lytRootFile = genInputLayout();
		btnRootFile = new JButton(ROOT_FILE);
		flcRootFile = new JFileChooser();
		flcRootFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		pnlRootFile = new JPanel();
		pnlRootFile.setLayout(lytRootFile);
		pnlRootFile.add(lblRootFile);
		pnlRootFile.add(btnRootFile);
		
		btnReset = new JButton("Reset preferences");
		btnClose = new JButton("Close");
		btnApply = new JButton("Apply preferences");
		
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TEXTSIZE_PROJ_MAN = TEXTSIZE_EDITOR = TEXTSIZE_CONSOLE = 10;
				EDITOR_HIGHLIGHT = new Color(255, 109, 106,255);
				TEXT_COLOR = new Color(0,0,0);
				ROOT_FILE = "/Users/jackarmstrong/LOLCode/";
//				inpTxtSizeProjMan.setValue(TEXTSIZE_PROJ_MAN);
//				inpTxtSizeEdtr.setValue(TEXTSIZE_EDITOR);
//				inpTxtSizeCnsl.setValue(TEXTSIZE_CONSOLE);
				clcEdtrHilit.col = EDITOR_HIGHLIGHT;
				clcTxtCol.col = TEXT_COLOR;
				btnRootFile.setText(ROOT_FILE);
				flcRootFile.setCurrentDirectory(new File(ROOT_FILE));
				frame.repaint();
			}
		});
		
		btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hide();
			}
		});
		
		btnApply.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Apply preferences");
//				TEXTSIZE_PROJ_MAN = (Integer)inpTxtSizeProjMan.getValue();
				LOLCodeIDE1.inst.reset();
			}
		});
		
		btnRootFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int res = flcRootFile.showOpenDialog(null);
				if(res == JFileChooser.APPROVE_OPTION) {
					ROOT_FILE = flcRootFile.getCurrentDirectory().getAbsolutePath();
					btnRootFile.setText(flcRootFile.getSelectedFile().getAbsolutePath());
				}
			}
		});
		
		frame.setLayout(frameLayout);
		
//		frame.add(pnlTxtSizeProjMan);
//		frame.add(pnlTxtSizeCnsl);
//		frame.add(pnlTxtSizeEdtr);
		frame.add(pnlEdtrHilit);
		frame.add(pnlTxtCol);
		frame.add(pnlRootFile);
		frame.add(btnReset);
		frame.add(btnApply);
		frame.add(btnClose);
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	public void hide() {
		frame.setVisible(false);
	}
	
}

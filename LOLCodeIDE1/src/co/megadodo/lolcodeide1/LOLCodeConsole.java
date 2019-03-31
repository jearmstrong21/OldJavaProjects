package co.megadodo.lolcodeide1;

import java.awt.Dimension;
import java.awt.Font;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class LOLCodeConsole extends JPanel {
	
	JTextArea area;
	Font font;

	public LOLCodeConsole(int w, int h) {
		this.setPreferredSize(new Dimension(w,h));
		this.font = new Font(Font.MONOSPACED, Font.PLAIN, 20);
		this.area = new JTextArea();
		this.area.setPreferredSize(new Dimension(w,h));
		this.area.setEditable(false);
		this.area.setFont(font);
		this.add(this.area);
	}
	
	public void printLine(String str) {
		print(str);
		print("\n");
	}
	
	public void print(String str) {
		area.setText(area.getText()+str);
	}
	
	public void runFile(File f) {
		printLine("Running file: " + f.getName());
	}
	
}

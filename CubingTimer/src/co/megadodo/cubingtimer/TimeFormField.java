package co.megadodo.cubingtimer;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TimeFormField extends JComponent {
	
	JTextField tfHr;
	JTextField tfMn;
	JTextField tfSe;
	JTextField tfMl;
	JPanel pnl;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("TimeFormField");
		TimeFormField tff = new TimeFormField();
		tff.init();
		frame.add(tff);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}
	
	public TimeFormField() {
		
	}
	
	public void init() {
		pnl = new JPanel();
		tfHr = new JTextField("0");
		tfMn = new JTextField("0");
		tfSe = new JTextField("0");
		tfMl = new JTextField("0");
		pnl.add(tfHr);
		pnl.add(new JLabel(" : "));
		pnl.add(tfMn);
		pnl.add(new JLabel(" : "));
		pnl.add(tfSe);
		pnl.add(new JLabel(" : "));
		pnl.add(tfMl);
		add(pnl);
	}
	
	public long totalMillis() {
		return millis() + 1000*second() + 60*1000*minute() + 60*60*1000*hour();
	}
	
	public int hour() {
		return Integer.parseInt(tfHr.getText());
	}
	
	public int minute() {
		return Integer.parseInt(tfMn.getText());
	}
	
	public int second() {
		return Integer.parseInt(tfSe.getText());
	}
	
	public int millis() {
		return Integer.parseInt(tfMl.getText());
	}

}

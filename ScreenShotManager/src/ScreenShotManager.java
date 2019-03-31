import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScreenShotManager extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new ScreenShotManager();
	}
	
	JPanel empt() { return new JPanel(); }
	JLabel output;
	public ScreenShotManager() {
		this.setTitle("Screenshot Manager");
		this.setSize(200,200);
		
		JButton screenshot = new JButton("SCREENSHOT");
		screenshot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
				int minute = Calendar.getInstance().get(Calendar.MINUTE);
				int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
				String activity = "MISC";
				if( (hour == 9) || (hour == 8 && minute > 50) || (hour == 10 && minute < 30) ) {
					if(day == Calendar.TUESDAY || day == Calendar.THURSDAY) activity = "TRIG";// 9:00 - 10:00
				} else if (  (hour == 10 && minute > 30) ||  (hour == 11) ) {
					if(day == Calendar.TUESDAY || day == Calendar.THURSDAY) activity = "WRIT";// 10:45 - 11:45
				} else if ( hour == 9 || (hour == 8 && minute > 50) || (hour == 11) ) {
					if(day == Calendar.SATURDAY) activity = "LIN ALG"; // 9-10
				} else if ( (hour == 4+12 && minute > 10) || (hour == 5+12) || (hour == 6+12 && minute < 10)) {
					if(day == Calendar.WEDNESDAY) activity = "INTERM ALG";
				}
				
				System.out.println(activity);
				
				String dayStr = "";
				switch(day) {
					case 1: dayStr = "SUN"; break;
					case 2: dayStr = "MON"; break;
					case 3: dayStr = "TUE"; break;
					case 4: dayStr = "WED"; break;
					case 5: dayStr = "THU"; break;
					case 6: dayStr = "FRI"; break;
					case 7: dayStr = "SAT"; break;
				}
				// DDD HH MM
				String hourStr = (hour < 10 ? "0" : "") + (hour>12?hour-12:hour);
				String minuteStr = (minute < 10 ? "0": "") + minute;
				String s = "/Users/jackarmstrong/Desktop/Screenshots/" + activity + "/" + dayStr + " " + hourStr + ":" + minuteStr + " " + (hour>12?"PM":"AM") + ".png";
				System.out.println(s);
				output.setText("SCREENSHOT: " + activity);
				try {
					Robot robot = new Robot();
					BufferedImage screenFullImage = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
					ImageIO.write(screenFullImage, "png", new File(s));
				} catch(Throwable t) {
					t.printStackTrace();
					output.setText(output.getText() + " ERR: " + t.getMessage());
				}
			}
		});
		output = new JLabel("");
		GridLayout layout = new GridLayout(3,1,0,0);
		this.add(screenshot);
		
		JButton delScreenshot = new JButton("DELETE SCREENSHOT");

		delScreenshot.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		this.add(delScreenshot);
		this.add(output);
		
		this.setLayout(layout);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMaxX() - this.getWidth();
        int y = 0;
        this.setLocation(x, y);
        setIconImage(new ImageIcon("icon.png").getImage());
        


		this.setVisible(true);
	}

}

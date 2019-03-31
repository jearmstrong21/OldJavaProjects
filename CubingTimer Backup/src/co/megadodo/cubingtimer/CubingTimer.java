package co.megadodo.cubingtimer;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//TODO: add button for saving records, everything except GUI and
//TODO: 	impl for record saving/adding/deleting/viewing is done

@SuppressWarnings("serial")
public class CubingTimer extends JFrame implements KeyListener, MouseListener {

	ArrayList<Solve> solves;
	Calendar calendar;
	String FILE_NAME = "Cubes.txt";
	
	int WIDTH = 800;
	int HEIGHT = 800;
	
	Timer timer;
	
	JButton btTiming;
	JButton btSelectCube;
	JButton btTimes;
	JButton btSaveRecords;
	JPanel  pnButtons;
	
	boolean timing;
	
	JLabel  lbCurTime;
	JPanel  pnCurTime;
	
	Cube cube;
	
	GridLayout pnButtonsLayout;
	GridLayout mainLayout;
	
	public static void main(String[] args) {
		new CubingTimer();
	}
	
	public CubingTimer() {
		calendar = Calendar.getInstance();
		timing = false;
		timer = new Timer();
		this.setTitle("Cubing Timer");
		this.setSize(WIDTH, HEIGHT);
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int millisPerFrame = 1;
		javax.swing.Timer t = new javax.swing.Timer(millisPerFrame, e-> {
			repaint();
		}); 
		t.start();
		
		// INIT GUI
		btTiming      = new JButton("Timing");
		btSelectCube  = new JButton("Select Cube");
		btTimes       = new JButton("Times");
		btSaveRecords = new JButton("Save Records");
		pnButtons     = new JPanel();
		
		btTiming.setEnabled(false);

		pnButtonsLayout = new GridLayout(10, 1);
		pnButtons.setLayout(pnButtonsLayout);
		
		pnButtons.add(btTiming);
		pnButtons.add(btSelectCube);
		pnButtons.add(btTimes);
		pnButtons.add(btSaveRecords);
		
		lbCurTime = new JLabel("ERROR");
		pnCurTime = new JPanel();
		
		pnCurTime.add(lbCurTime);

		btSaveRecords.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Solve.writeSolves(FILE_NAME, solves);
			}
		});
		
		btSelectCube.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cube = Cube.promptForCube();
				if(cube != null) btTiming.setEnabled(true);
			}
		});
		
		btTiming.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("BTTIMING");
				btTiming.setEnabled(false);
				btSelectCube.setEnabled(false);
				btTimes.setEnabled(false);
				btSaveRecords.setEnabled(false);
				timer = new Timer();
				timer.start();
				timing = true;
			}
		});
		
		mainLayout = new GridLayout(1, 2);
		setLayout(mainLayout);
		
		add(pnButtons, BorderLayout.LINE_START);
		add(pnCurTime, BorderLayout.LINE_END);

//		System.out.println(Cube.promptForCube());
//		System.out.println(Cube.promptForCube());
//		System.out.println(Cube.promptForCube());
		
//		ArrayList<Solve> solves = Solve.parseSolves("Cubes.txt");
//		for(Solve s : solves) {
//			System.out.println(s.shortToString());
//		}
	
		solves = Solve.parseSolves(FILE_NAME);
		
		this.setVisible(true);

//		ArrayList<Cube> list = Cube.findShape("Cube");
//		for(Cube c : list) System.out.println(c.getShape() + " " + c.getSize());
	}
	
	public void paint(Graphics g) {
		super.paint(g);
//		Graphics2D g2d = (Graphics2D) g;
		if(timing) {
//			System.out.println("TIMING PAINT");
			timer.update();
			lbCurTime.setText(timer.getStrTime());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("CLICKED");
		if(timing) {
			if(timer.started) {
				long timeLong = timer.passedTime();
				String str = timer.strTime;
				timer.stop();
				timing = false;
				// addRecord();
				boolean doAddRecord = JOptionPane.showConfirmDialog(null, "Add Record " + str + "?", cube.toString(), JOptionPane.YES_NO_OPTION) == 0;
				System.out.println(doAddRecord);
				btSelectCube.setEnabled(true);
				btTimes.setEnabled(true);
				btSaveRecords.setEnabled(true);
				lbCurTime.setText("");
				if(doAddRecord) {
					Solve s = new Solve();
					s.cube = cube;
					s.solveDay = calendar.get(Calendar.DAY_OF_MONTH);
					s.solveMonth = calendar.get(Calendar.MONTH);
					s.solveTime = timeLong;
					s.solveType = SolveType.promptForSolveType();
					s.solveYear = calendar.get(Calendar.YEAR);
					s.strTime = str;
					solves.add(s);
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
}

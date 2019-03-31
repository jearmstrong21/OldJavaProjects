package co.megadodo.cubingtimer;

import java.awt.Component;
import java.awt.Dimension;
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

// ComboBox for sorting the table

@SuppressWarnings("serial")
public class CubingTimer extends JFrame implements KeyListener, MouseListener {
	
	public static String YEAR;
	public static String MONTH;
	public static String DAY;
	public static String DATE;
	
	public static void main(String[] args) {
		
		new CubingTimer();
		
		int y = Calendar.getInstance().get(Calendar.YEAR 				);
		int m = Calendar.getInstance().get(Calendar.MONTH				) + 1;
		int d = Calendar.getInstance().get(Calendar.DAY_OF_MONTH		);
		System.out.println(y + " " + m + " " + d);
		YEAR = "" + y;
		if(m < 10) MONTH = "0" + m;
		else MONTH = "" + m;
		if(d < 10) DAY = "0" + d;
		else DAY = "" + d;
		DATE = YEAR + " / " + MONTH + " / " + DAY;
		System.out.println(DATE);
	}
	
	SolveComparator sorter;
	
	ArrayList<Solve> solves;
	public static String FILE_NAME = "Cubes.txt";
	
	int WIDTH = 800;
	int HEIGHT = 800;
	
	Timer timer;
	
	JPanel pnTab1;
	JPanel pnTab2;
	JPanel pnBtns;
	JPanel pnSortBy;
	
	JButton btTiming;
	JButton btSelectCube;
	
	TimingFrame timerFrame;
	
	JPanel pnRecords;
	JTable tbRecords;
	JScrollPane spTbRecords;
	JButton btDelRecord;
	
	JLabel lblSort;
	JComboBox<String> cbTbSort;
	
	JButton btMakeTime;
	JButton btEditTime;
	
	JTabbedPane tbMainPane;
	
	boolean timing;
	
	Cube cube;
	
	GridLayout pnTab1Layout;
	GridLayout pnTab2Layout;
	
	// populates table with all records
	public void doTable() {
		solves.sort(sorter);
		String[] columnNames = new String[] {
			"Cube",
			"Solve Type",
			"Time",
			"Millis",
			"Date",
			"Brand"
		};
		Object[][] data = new Object[solves.size()][6];
		for(int i = 0; i < solves.size(); i++) {
			Solve s = solves.get(i);
			data[i][0] = s.cube;
			data[i][1] = s.solveType;
			data[i][2] = s.strTime;
			data[i][3] = s.solveTime;
			data[i][4] = s.solveDate;
			data[i][5] = s.brand;
		}
		if(tbRecords != null) {
			pnTab2.remove(spTbRecords);
		}
//		pnTab2.remove(cbTbSort);
		tbRecords = new JTable(data, columnNames) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		spTbRecords = new JScrollPane(tbRecords);
		spTbRecords.setPreferredSize(new Dimension(600, 600));
		pnTab2.add(spTbRecords);
//		pnTab2.add(cbTbSort);
		tbRecords.setSize(tbRecords.getSize());
		resizeColumnWidth(tbRecords);
	}
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 15; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        if(width > 300)
	            width=300;
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
	
	public CubingTimer() {
		sorter = new SolveComparator();
		
		solves = Solve.parseSolves(FILE_NAME);
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
		
		pnTab2 = new JPanel();
		pnTab2Layout = new GridLayout(3,1);
		
		btDelRecord = new JButton("Delete Record");
		btDelRecord.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = tbRecords.getSelectedRow();
				if(index == -1) {
					JOptionPane.showMessageDialog(null, "No row selected", "No row selected", JOptionPane.ERROR_MESSAGE);
					return;
				}
				boolean confirm = 0 == JOptionPane.showConfirmDialog(null, "Delete record?", "Delete Record?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(!confirm) return;
				Solve s = solves.remove(index);
//				solves.remove(index);
				Solve.writeSolves(FILE_NAME, solves);
				doTable();
				JOptionPane.showMessageDialog(null, s.shortToString(), "Deleted Record", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		pnTab2.setLayout(pnTab2Layout);
//		pnTab2.add(spTbRecords);
		btDelRecord.setPreferredSize(new Dimension(100, 600));
		cbTbSort = new JComboBox<String>(SortField.strValues);
		cbTbSort.setPreferredSize(new Dimension(700, 100));
		
		tbMainPane = new JTabbedPane();
		lblSort = new JLabel("Sort By:");
		pnTab2.add(lblSort);
		//TODO
		pnTab2.add(cbTbSort);
		cbTbSort.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SortField field = SortField.parseSortField((String)cbTbSort.getSelectedItem());
				sorter.field = field;
				doTable();
			}
		});
		btMakeTime = new JButton("Make Time");
		btMakeTime.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				makeTime();
			}
		});
		btEditTime = new JButton("Edit Time");
		btEditTime.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editTime();
			}
		});
		pnBtns = new JPanel();
		pnBtns.setLayout(new GridLayout(1,3));
		pnBtns.add(btMakeTime);
		pnBtns.add(btEditTime);
		pnBtns.add(btDelRecord);
		pnSortBy = new JPanel();
		pnSortBy.setLayout(new GridLayout(1,2));
		pnSortBy.add(lblSort);
		pnSortBy.add(cbTbSort);
		pnTab2.add(pnBtns);
		pnTab2.add(pnSortBy);
		doTable();

		btTiming      = new JButton("Timing");
		btSelectCube  = new JButton("Select Cube");

		btTiming.setEnabled(false);

		pnTab1Layout = new GridLayout(2, 1);
		pnTab1 = new JPanel();
		pnTab1.setLayout(pnTab1Layout);
		
		pnTab1.add(btTiming);
		pnTab1.add(btSelectCube);

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
				startTiming();
			}
		});
		
		tbMainPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		tbMainPane.add("Timing", pnTab1);
		tbMainPane.add("Records", pnTab2);
		
		this.add(tbMainPane);

		SortField field = SortField.parseSortField((String)cbTbSort.getSelectedItem());
		sorter.field = field;
		doTable();
		this.setVisible(true);
	}
	public void startTiming() {
		btTiming.setEnabled(false);
		btSelectCube.setEnabled(false);
		timer = new Timer();
		timer.start();
		timer.pause();
		timing = true;
		timerFrame = new TimingFrame();
		timerFrame.cb = cube;
		// TODO
		// IF extra time is needed - uncomment this and replace numbers
		// with extra time needed
//		boolean doExtraTime = JOptionPane.showConfirmDialog(null, "Add 31:01:920 to the time?", "Add 31:01:920 to the time?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
//		if(doExtraTime) {
//			timer.timeStart-=(920 + 1*1000 + 31*60*1000);
//		}
		timerFrame.setTime("00 : 00 : 00 : 000");
		timerFrame.setVisible(true);
		timerFrame.requestFocus();
		timerFrame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				pauseUnpauseTiming();
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		timerFrame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				stopTiming();
			}
		});
	}
	public void pauseUnpauseTiming() {
		if(timer.paused) timer.unpause();
		else timer.pause();
	}
	public void stopTiming() {
		boolean wasPaused = timer.paused;
		if(!timer.paused) timer.pause();
		if(JOptionPane.showConfirmDialog(null, "Stop timing?", "Stop timing?", JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
			if(!wasPaused) timer.unpause();
			return;
		}
		if(timer.paused) timer.unpause();
		else timer.pause();
		if(timing) {
			if(timer.started) {
				if(timer.paused) timer.unpause();
				long timeLong = timer.passedTime();
				String str = timer.strTime;
				timer.stop();
				timing = false;
				timerFrame.setVisible(false);
				
				boolean doAddRecord = JOptionPane.showConfirmDialog(null, "Add Record " + str + "?", cube.toString(), JOptionPane.YES_NO_OPTION) == 0;
				btSelectCube.setEnabled(true);
				timerFrame.setTime("");
				if(doAddRecord) {
					Solve s = new Solve();
					s.cube = cube;
					s.solveTime = timeLong;
					s.solveType = SolveType.promptForSolveType();
					s.solveDate = DATE;
					s.strTime = str;
					s.brand = CubeBrand.promptForBrand(cube);
					solves.add(s);
					Solve.writeSolves(FILE_NAME, solves);
					doTable();
				}
			}
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(timing) {
			if(!timer.paused) timer.update();
			timerFrame.repaint();
//			timerFrame.setSize(timerFrame.getSize());
			timerFrame.setTime(timer.getStrTime());
			timerFrame.getContentPane().validate();
			timerFrame.getContentPane().repaint();
			timerFrame.validate();
			timerFrame.repaint();
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
	
	public void makeTime() {
		
		Solve solve = new Solve();
		
		boolean doIt = JOptionPane.showConfirmDialog(this, "Make new time?", "Make new time?", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION;
		if(!doIt) return;
		solve.cube = Cube.promptForCube();
		solve.solveDate = JOptionPane.showInputDialog(this, "Date:", "Date", JOptionPane.PLAIN_MESSAGE);
		int hours = Integer.parseInt(JOptionPane.showInputDialog(this, "# of hours:", "# of hours:", JOptionPane.PLAIN_MESSAGE));
		int mins = Integer.parseInt(JOptionPane.showInputDialog(this, "# of minutes:", "# of minutes:", JOptionPane.PLAIN_MESSAGE));
		int secs = Integer.parseInt(JOptionPane.showInputDialog(this, "# of seconds:", "# of seconds:", JOptionPane.PLAIN_MESSAGE));
		int mils = Integer.parseInt(JOptionPane.showInputDialog(this, "# of milliseconds:", "# of milliseconds:", JOptionPane.PLAIN_MESSAGE));
		
		solve.solveTime = mils + secs*1000 + mins*60*1000 + hours*60*60*1000;
		solve.strTime = format(hours) + " : " + format(mins) + " : " + format(secs) + " : " + format(mils);
		solve.brand = CubeBrand.promptForBrand(solve.cube);
		solve.solveType = SolveType.promptForSolveType();
		solves.add(solve);

		Solve.writeSolves(FILE_NAME, solves);
		doTable();
	}
	
	public void editTime() {
		int index = tbRecords.getSelectedRow();
		if(index == -1) {
			JOptionPane.showMessageDialog(null, "No row selected", "No row selected", JOptionPane.ERROR_MESSAGE);
			return;
		}
		boolean confirm = JOptionPane.showConfirmDialog(this, "Edit row?", "Edit row?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
		if(!confirm) {
			return;
		}
		Solve solve = solves.get(index);
		
		solve.cube = Cube.promptForCube();
		solve.solveDate = JOptionPane.showInputDialog(this, "Date:", "Date", JOptionPane.PLAIN_MESSAGE);
		int hours = Integer.parseInt(JOptionPane.showInputDialog(this, "# of hours:", "# of hours:", JOptionPane.PLAIN_MESSAGE));
		int mins = Integer.parseInt(JOptionPane.showInputDialog(this, "# of minutes:", "# of minutes:", JOptionPane.PLAIN_MESSAGE));
		int secs = Integer.parseInt(JOptionPane.showInputDialog(this, "# of seconds:", "# of seconds:", JOptionPane.PLAIN_MESSAGE));
		int mils = Integer.parseInt(JOptionPane.showInputDialog(this, "# of milliseconds:", "# of milliseconds:", JOptionPane.PLAIN_MESSAGE));
		
		solve.solveTime = mils + secs*1000 + mins*60*1000 + hours*60*60*1000;
		solve.strTime = format(hours) + " : " + format(mins) + " : " + format(secs) + " : " + format(mils);
		solve.brand = CubeBrand.promptForBrand(solve.cube);
		solve.solveType = SolveType.promptForSolveType();
		
		solves.set(index, solve);
		Solve.writeSolves(FILE_NAME, solves);
		doTable();
	}
	
	public String format(int i) {
		if(i < 10) return "0" + i;
		return "" + i;
	}
	
}

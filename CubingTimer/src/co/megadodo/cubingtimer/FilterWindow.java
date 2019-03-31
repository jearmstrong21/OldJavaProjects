package co.megadodo.cubingtimer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

// WIP (Work-In-Progress) Does nothing but load solves, probably should be deleted.
public class FilterWindow {
	
	public static enum FilterType {
		TIME_UNDER,
		TIME_OVER,
		SIZE,
		SHAPE,
		BRAND,
		CUBE
	}
	
	// TIME_UNDER: obj1 is TimeFormField
	// TIME_OVER: obj1 is TimerFormField
	// SIZE: obj1 is String
	// SHAPE: obj1 is String
	// BRAND: obj1 is String
	// CUBE: obj1 is String, obj2 is String
	
	public static ArrayList<Solve> filterSolves(ArrayList<Solve> solves, FilterType type, Object obj1, Object obj2) {
		long millis;
		switch(type) {
			case BRAND:
				break;
			case CUBE:
				break;
			case SHAPE:
				break;
			case SIZE:
				break;
			case TIME_OVER:
				millis = ((TimeFormField)obj1).totalMillis();
				for(Solve s : solves) if(s.solveTime < millis) solves.remove(s);
				break;
			case TIME_UNDER:
				millis = ((TimeFormField)obj1).totalMillis();
				for(Solve s : solves) if(s.solveTime > millis) solves.remove(s);
				break;
		}
		
		return solves;
	}
	
	public static void main(String[] args) {
		new FilterWindow().show(Solve.parseSolves(CubingTimer.FILE_NAME));
	}
	
	JFrame frame;
	BorderLayout frameLayout;
	JPanel pnlInputs;
	GridLayout pnlInputsLayout;
	JPanel pnlResults;
	JScrollPane spnResults;
	JTable tblResults;
	
	JLabel lblFilterTimeOver;
	TimeFormField tffTimeOver;
	JLabel lblFilterTimeUnder;
	TimeFormField tffTimeUnder;
	
	public FilterWindow() {
		frame = new JFrame("Filter Solves");
		frame.setSize(800,500);
			pnlInputs = new JPanel();
			pnlInputsLayout = new GridLayout(1,4);
				lblFilterTimeOver = new JLabel("Time over:");
				tffTimeOver = new TimeFormField();
				lblFilterTimeUnder = new JLabel("Time under:");
				tffTimeUnder = new TimeFormField();
			pnlInputs.add(lblFilterTimeOver);
			pnlInputs.add(tffTimeOver);
			pnlInputs.add(lblFilterTimeUnder);
			pnlInputs.add(tffTimeUnder);
			pnlResults = new JPanel();
				spnResults = new JScrollPane();
					tblResults = new JTable();
//					tblResults.setSize(frame.getWidth(),frame.getHeight()/2);
				spnResults.setViewportView(tblResults);
//				spnResults.setSize(frame.getWidth(),frame.getHeight()/2);
			pnlResults.add(spnResults);
//			pnlResults.setSize(frame.getWidth(),frame.getHeight()/2);
		frameLayout = new BorderLayout();
		frame.setLayout(frameLayout);
		frame.add(pnlResults, BorderLayout.CENTER);
		frame.add(pnlInputs, BorderLayout.SOUTH);
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
	
	public void show(ArrayList<Solve> solves) {
		frame.setVisible(true);
		
		DefaultTableModel mdl = new DefaultTableModel();
		mdl.addColumn("Cube");
		mdl.addColumn("Solve Type");
		mdl.addColumn("Time");
		mdl.addColumn("Millis");
		mdl.addColumn("Date");
		mdl.addColumn("Brand");
		
		for(int i = 0; i < solves.size(); i++) {
			Solve s = solves.get(i);
			mdl.addRow(new Object[] {s.cube,s.solveType,s.strTime,s.solveTime,s.solveDate,s.brand});
//			mdl.setValueAt(s.cube, i, 0);
//			mdl.setValueAt(s.solveType, i, 1);
//			mdl.setValueAt(s.strTime, i, 2);
//			mdl.setValueAt(s.solveTime, i, 3);
//			mdl.setValueAt(s.solveDate, i, 4);
//			mdl.setValueAt(s.brand, i, 5);
		}
		tblResults.setModel(mdl);
		resizeColumnWidth(tblResults);
//		tblResults.setColumnModel(mdlCols);
		
		//load solves
	}

}

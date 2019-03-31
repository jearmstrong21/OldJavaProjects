package co.megadodo.cubingtimer;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public enum SolveType {
	SPEED,
	SPEED_NORMAL,
	NORMAL,
	NORMAL_EASY,
	EASY;
	
	public String toString() {
		switch(this) {
			case SPEED: return "Speed";
			case SPEED_NORMAL: return "Speed-Normal";
			case NORMAL: return "Normal";
			case NORMAL_EASY: return "Normal-Easy";
			case EASY: return "Easy";
		}
		return "Null";
	}
	
	public static SolveType parseSolveType(String str) {
		if(str.equals("Speed")) return SPEED;
		if(str.equals("Speed-Normal")) return SPEED_NORMAL;
		if(str.equals("Normal")) return NORMAL;
		if(str.equals("Normal-Easy")) return NORMAL_EASY;
		if(str.equals("Easy")) return EASY;
		return null;
	}
	
	public static SolveType promptForSolveType() {
		String[] list = new String[SolveType.values().length];
		SolveType[] cubes = SolveType.values();
		for(int i = 0; i < cubes.length; i++) {
			list[i] = cubes[i].toString();
		}
		JComboBox<String> jcb = new JComboBox<String>(list);
		jcb.setSelectedIndex(0);
		JOptionPane.showMessageDialog(null, jcb, "Select Solve Type", JOptionPane.PLAIN_MESSAGE);
		return parseSolveType((String)jcb.getSelectedItem());
		
	}
}

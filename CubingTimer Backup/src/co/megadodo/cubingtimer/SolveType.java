package co.megadodo.cubingtimer;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public enum SolveType {
	SPEED,
	NORMAL;
	
	public static SolveType parseSolveType(String str) {
		if(str.equals("SPEED")) return SPEED;
		if(str.equals("NORMAL")) return NORMAL;
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
		JOptionPane.showMessageDialog(null, jcb, "Select Cube", JOptionPane.PLAIN_MESSAGE);;
		return parseSolveType((String)jcb.getSelectedItem());
		
	}
}

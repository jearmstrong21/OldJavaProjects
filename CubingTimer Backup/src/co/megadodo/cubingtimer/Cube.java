package co.megadodo.cubingtimer;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public enum Cube {
	CUBE_2x2,
	CUBE_3x3,
	CUBE_4x4,
	CUBE_5x5,
	CUBE_6x6,
	CUBE_7x7,
	CUBE_8x8,
	CUBE_9x9,
	CUBE_10x10,
	
	PENTAGON_2x2,
	PENTAGON_3x3,
	PENTAGON_4x4,
	PENTAGON_5x5,
	PENTAGON_6x6,
	PENTAGON_7x7,
	PENTAGON_8x8,
	PENTAGON_9x9,
	
	TRIANGLE_3x3,
	TRIANGLE_4x4,
	TRIANGLE_5x5;
	
	String shape;
	String size;
	
	private Cube() {
		String[] split = this.toString().split("_");
		shape = split[0];
		shape = shape.toLowerCase();
		char[] shapeChars = shape.toCharArray();
		shapeChars[0] = Character.toUpperCase(shapeChars[0]);
		shape = String.valueOf(shapeChars);
		size = split[1];
	}
	
	public String getShape() {
		return shape;
	}
	
	public String getSize() {
		return size;
	}
	
	public static ArrayList<Cube> findShape(String s, ArrayList<Cube> cubes) {
		ArrayList<Cube> list = new ArrayList<Cube>();
		s = s.trim();
		if(s.equals("")) return cubes;
		
		for(Cube cube : cubes) {
			String shape = cube.getShape();
			if(shape.contains(s)) {
				list.add(cube);
			}
		}
		
		return list;
	}
	
	public static ArrayList<Cube> findShape(String s) {
		ArrayList<Cube> list = new ArrayList<Cube>();
		Cube[] cubes = Cube.values();
		for(Cube c : cubes) list.add(c);
		return findShape(s, list);
	}
	
	public static ArrayList<Cube> findSize(String s, ArrayList<Cube> cubes) {
		ArrayList<Cube> list = new ArrayList<Cube>();
		s = s.trim();
		if(s.equals("")) return cubes;
		
		for(Cube cube : cubes) {
			String shape = cube.getSize();
			if(shape.contains(s)) {
				list.add(cube);
			}
		}
		
		return list;
	}
	
	public static ArrayList<Cube> findSize(String s) {
		ArrayList<Cube> list = new ArrayList<Cube>();
		Cube[] cubes = Cube.values();
		for(Cube c : cubes) list.add(c);
		return findSize(s, list);
	}
	
	public static Cube parseCube(String s) {
		
		Cube[] list = Cube.values();
		for(Cube c : list) {
			if(c.toString().equals(s)) return c;
		}
		
		return null;
	}
	
	public static Cube promptForCube() {
		
		String[] list = new String[Cube.values().length + 1];
		list[0] = "None";
		Cube[] cubes = Cube.values();
		for(int i = 1; i <= cubes.length; i++) {
			list[i] = cubes[i-1].toString();
		}
		JComboBox<String> jcb = new JComboBox<String>(list);
		jcb.setSelectedIndex(0);
		JOptionPane.showMessageDialog(null, jcb, "Select Cube", JOptionPane.PLAIN_MESSAGE);;
		return parseCube((String)jcb.getSelectedItem());
		
	}
	
}

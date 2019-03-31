package co.megadodo.cubingtimer;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
// NOTE: Pyraminx/Triangle solves tips can be chosen to be done before timing.
public enum Cube {
	CUBE_2x2(CubeBrand.DFANTIXCYCLONEBOYS,true,10),
	CUBE_3x3(CubeBrand.DFANTIXCYCLONEBOYS,true,20),
	CUBE_4x4(CubeBrand.DFANTIXCYCLONEBOYS,true,25),
	CUBE_5x5(CubeBrand.DFANTIXCYCLONEBOYS,true,30),
	CUBE_6x6(CubeBrand.DFANTIXCYCLONEBOYS,true,35),
	CUBE_7x7(CubeBrand.MOYU_AOFU,true,0),
	CUBE_8x8,
	CUBE_9x9,
	CUBE_10x10(CubeBrand.SHENGSHOU),
	V_SPHERE_ORIGINAL(CubeBrand.VCUBE),
	
	SQUARE1_3x3(CubeBrand.DFANTIXXMANVOLT),
	
	CUBOID_3x3x5(CubeBrand.CALVINS,true),
	CUBOID_3x3x9(CubeBrand.WITEDEN),
	CUBOID_1x3x3(CubeBrand.LANLAN,true),
	CUBOID_2x2x3(CubeBrand.SHENGSHOU,true),
	CUBOID_2x3x3(CubeBrand.OTHER,true),
	CUBOID_4x4x5(CubeBrand.AYI,true),
	
	FISHER_3x3(CubeBrand.DFANTIXYONGJUNYILENG,true),
	
	SKEWB_2x2(CubeBrand.DFANTIXQIYI),
	
	MASTERMORPHIX_2x2(CubeBrand.SHENGSHOU,true),
	MASTERMORPHIX_3x3(CubeBrand.SHENGSHOU,true),
	MASTERMORPHIX_4x4(CubeBrand.SHENGSHOU,true),
	MASTERMORPHIX_5x5(CubeBrand.SHENGSHOU,true),
	
	GHOST_2x2,
	GHOST_3x3,
	GHOST_4x4,
	GHOST_5x5,
	
	MIRROR_2x2,
	MIRROR_3x3(CubeBrand.SHENGSHOU,true),
	MIRROR_4x4,
	
	PENTAGON_2x2,
	PENTAGON_3x3(CubeBrand.SHENGSHOU),
	PENTAGON_4x4,
	PENTAGON_5x5(CubeBrand.OTHER),
	PENTAGON_6x6,
	PENTAGON_7x7(CubeBrand.MF8),
	PENTAGON_8x8,
	PENTAGON_9x9,
	
	TRIANGLE_3x3(CubeBrand.SHENGSHOU),
	TRIANGLE_4x4(CubeBrand.SHENGSHOU),
	TRIANGLE_5x5(CubeBrand.MEFFERTS),
	
	REDI_3x3(CubeBrand.OTHER),
	
	IVY_3x3(CubeBrand.DFANTIXQIYI);
	
	String shape="";
	String size="";
	CubeBrand brand=CubeBrand.OTHER;
	boolean scramble=false;
	int moves=0;
	
	private Cube() {
		this(CubeBrand.OTHER);
	}
	
	private Cube(CubeBrand b, boolean scramble) {
		this(b);
//		this.scramble = scramble;
	}
	
	private Cube(CubeBrand b, boolean scramble, int moves) {
		this(b, scramble);
//		this.moves = moves;
	}
	
	private Cube(CubeBrand b) {
//		this.scramble = false;
		brand = b;
		String[] split = super.toString().split("_");
		shape = split[0];
		shape = shape.toLowerCase();
		char[] shapeChars = shape.toCharArray();
		shapeChars[0] = Character.toUpperCase(shapeChars[0]);
		shape = String.valueOf(shapeChars);
		size = split[1];
	}
	
	public String toString() {
		if(this == V_SPHERE_ORIGINAL) return "V-Sphere Original";
		return size + " " + shape;
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
		JOptionPane.showMessageDialog(null, jcb, "Select Cube", JOptionPane.PLAIN_MESSAGE);
		return parseCube((String)jcb.getSelectedItem());
		
	}
	
}

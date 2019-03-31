package co.megadodo.expressioneval.source;

import java.util.HashMap;
import java.util.Map;

public class Variables_Static {
	
	public static Map<Character, Double> map = new HashMap<Character, Double>();
	
	public static void addVar(char name, double val) {
		map.put(name, val);
	}
	
	public static double delVar(char name) {
		Double rm = map.remove(name);
		if(rm == null) return Double.NaN;
		return rm;
	}
	
	public static Node2D varNode(char name) {
		return new Node2DVariable(name);
	}
	
	public static double setVar(char name, double val) {
		Double d = var(name);
		map.put(name, val);
		return d;
	}
	
	public static double var(Character name) {
		try {
			return map.get(name);
		} catch(NullPointerException npe) {
			System.out.println("***********************\nNot valid variable: " + name + "\n***********************");
		}
		return Double.NaN;
	}
}

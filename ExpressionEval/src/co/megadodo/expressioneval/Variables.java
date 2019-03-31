package co.megadodo.expressioneval;

import java.util.HashMap;
import java.util.Map;

public class Variables {
	private Map<Character, Double> map;
	
	public Variables() {
		map = new HashMap<>();
	}
	
	public void setVar(char name, double val) {
		map.put(name, val);
	}
	
	public double delVar(char name) {
		return map.remove(name);
	}
	
	public double var(char name) {
		return map.get(name);
	}
	
	public boolean hasVar(char name) {
		return map.containsKey(name);
	}
	
	public void setVar(CharSequence seq, double val) {
		if(seq.length() != 1) return;
		setVar(seq.charAt(0), val);
	}
	
	public double delVar(CharSequence seq) {
		if(seq.length() != 1) return Double.NaN;
		return delVar(seq.charAt(0));
	}
	
	public double var(CharSequence seq) {
		if(seq.length() != 1) return Double.NaN;
		return var(seq.charAt(0));
	}
	
	public boolean hasVar(CharSequence seq) {
		if(seq.length() != 1) return false;
		return hasVar(seq.charAt(0));
	}
}

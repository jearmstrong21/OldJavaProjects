package co.megadodo.expressioneval.source;

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
}

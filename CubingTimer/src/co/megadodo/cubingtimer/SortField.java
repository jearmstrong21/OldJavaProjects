package co.megadodo.cubingtimer;

public enum SortField {
	CUBE,
	SOLVETYPE,
	STRTIME,
	SOLVETIME,
	SOLVEDATE,
	BRAND;
	
	public static String[] strValues = new String[] { "Cube", "Solvetype", "Time", "Millis", "Date", "Brand" };
	
	public String toString() {
		return strValues[this.ordinal()];
	}
	
	public static SortField parseSortField(String s) {
		if(s.equals("Cube")) return CUBE;
		if(s.equals("Solvetype")) return SOLVETYPE;
		if(s.equals("Time")) return STRTIME;
		if(s.equals("Millis")) return SOLVETIME;
		if(s.equals("Date")) return SOLVEDATE;
		if(s.equals("Brand")) return BRAND;
		return null;
	}
}

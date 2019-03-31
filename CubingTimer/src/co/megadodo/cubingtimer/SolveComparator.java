package co.megadodo.cubingtimer;

import java.util.Comparator;

public class SolveComparator implements Comparator<Solve> {

	public SolveComparator() {
		
	}
	
	public SolveComparator(SortField f) {
		field = f;
	}
	
	public SortField field;
	
	@Override
	public int compare(Solve o1, Solve o2) {
		if(field == null) return 0;
		switch(field) {
			case CUBE: return o1.cube.toString().compareTo(o2.cube.toString());
			case SOLVEDATE: return o1.solveDate.compareTo(o2.solveDate);
			case SOLVETIME: return new Long(o1.solveTime).compareTo(o2.solveTime);
			case SOLVETYPE: return o1.solveType.toString().compareTo(o2.solveType.toString());
			case STRTIME: return o1.strTime.compareTo(o2.strTime);
			case BRAND: return o1.brand.toString().compareTo(o2.brand.toString());
		}
		return 0;
	}

}

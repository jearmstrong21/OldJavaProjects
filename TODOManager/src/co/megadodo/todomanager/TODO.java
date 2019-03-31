package co.megadodo.todomanager;

public class TODO {
	
	int number;
	int parentNumber;
	String descr;
	
	public TODO(String descr) {
		this();
		this.descr = descr;
	}
	
	public TODO() {
		number=-1;
		parentNumber=-1;
		descr=null;
	}
	
	public boolean equals(Object o) {
		if(o instanceof TODO) {
			TODO t = (TODO)o;
			return t.descr.equals(descr) && number == t.number && parentNumber == t.parentNumber;
		}
		return false;
	}
	
}

package co.megadodo.superchess1;

public interface JWriter {
	
	public abstract void jprint(String str);
	public abstract void jprintln(String str);
	public default void jprintln() {
		jprintln("");
	}
	
}

package co.megadodo.superchess1;

public class JWriterConsole implements JWriter {

	@Override
	public void jprint(String str) {
		System.out.print(str);
	}
	
	@Override
	public void jprintln(String str) {
		System.out.println(str);
	}
}

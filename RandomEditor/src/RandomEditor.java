import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class RandomEditor {

	public static void main(String[] args) throws Throwable {
		Process pro;
		boolean keepOn = true;
		int i = 0;
		file = new File("JavaCode.java");
		dummyfile = new File("dummyfile.txt");
		sc = new Scanner(dummyfile);
		writer = new PrintWriter(file);
		for(int x = 0; x < 100; x++) {
			for(int y = 0; y < 20; y++) {
				writer.print(" ");
			}
			writer.println();
		}
		writer.close();
		int res = 0;
		while(keepOn) {
			pro = Runtime.getRuntime().exec("javac JavaCode.java");
			pro.waitFor();
			res = pro.exitValue();
			System.out.println(i + " " + res);
			randEdit(100);
			i++;
//			if(i >= 100) {
//				keepOn = false;
//			}
			if(res == 0 && i > 10) keepOn = false;
		}
//		System.out.println(randChar() + " " + randChar());
	}
	
	public static File dummyfile;
	public static Scanner sc;
	public static File file;
	public static PrintWriter writer;

	public static int randInt(int i) {
		return (int) (Math.random() * i);
	}
	
	public static char randChar() {
		int i = randInt(53);
		if(i < 26) return (char)('a'+i);
		if(i == 26) return ' ';
		if(i > 26) return (char)('A' + (i-26));
		return '0';
	}
	
	public static void randEdit(int num) throws Throwable {
		sc = new Scanner(file);
		String txt = "";
		while(sc.hasNextLine()) txt+=sc.nextLine()+"\n";
		char[] arr = txt.toCharArray();
		for(int i = 0; i < num; i++) arr[randInt(arr.length-1)] = randChar();
		txt = new String(arr);
		sc.close();
		writer = new PrintWriter(file);
		writer.print(txt);
		writer.close();
	}
	
}

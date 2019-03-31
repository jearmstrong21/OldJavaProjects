package co.megadodo.euler;

import java.util.Scanner;

public class Euler0017 extends Euler {

	public void run() {
		Scanner sc = new Scanner(System.in);
		String str = "";
		for(int i = 1; i <= 1000; i++) str += asStringN(i);
		str = str.replace(" ", "");
		str = str.replace("-", "");
		println(str);
		println(str.length());
	}
	
}

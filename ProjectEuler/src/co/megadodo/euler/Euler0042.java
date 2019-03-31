package co.megadodo.euler;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Euler0042 extends Euler {
	
	public void run() throws Throwable {
		
		ArrayList<String> names = new ArrayList<String>();
		Scanner sc = new Scanner(new File("Euler0042.txt"));
		sc.useDelimiter(Pattern.quote(","));
		while(sc.hasNext()) {
			String next = sc.next().replace("\"", "");
			names.add(next);
		}
		sc.close();
		
		int counter = 0;
		println("READ NAMES");
		for(String s : names) {
			if(isTriNumber(alphaScore(s), 20)) {
				counter++;
			}
		}
		
		println("RESULT: " + counter);
		println("NUM WORDS: " + names.size());

	}

}

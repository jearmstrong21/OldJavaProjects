package co.megadodo.euler;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Euler0022 extends Euler {

	public void run() throws Throwable {
		ArrayList<String> names = new ArrayList<String>();
		Scanner sc = new Scanner(new File("Euler0022.txt"));
		sc.useDelimiter(Pattern.quote(","));
		while(sc.hasNext()) {
			String next = sc.next().replace("\"", "");
			names.add(next);
		}
		sc.close();
		String[] arr = EulerUtils.<String>asArray(names, String.class);
		Arrays.sort(arr);
		int total = 0;
		for(int i = 0; i < arr.length; i++) {
			total += alphaScore(arr[i]) * (i+1);
		}
		println(total);
	}
	
	
}

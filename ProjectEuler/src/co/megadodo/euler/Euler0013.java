package co.megadodo.euler;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Euler0013 extends Euler {
	

	public void run() throws Throwable {
		ArrayList<BigInteger> list = new ArrayList<BigInteger>();
		Scanner sc = new Scanner(new File("Euler0013.txt"));
		while(sc.hasNextLine()) {
			list.add(new BigInteger(sc.nextLine()));
		}
		sc.close();
		BigInteger num = BigInteger.ONE;
		for(BigInteger big : list) {
			num = num.add(big);
		}
		System.out.println(num);
		System.out.println(num.toString().substring(0, 10));
	}
	
}

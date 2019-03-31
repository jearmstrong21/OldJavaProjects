package co.megadodo.euler;

import java.math.BigInteger;

public class Euler0016 extends Euler {

	public void run() {
		BigInteger bigInt = new BigInteger("2");
		bigInt = bigInt.pow(1000);
		String str = bigInt.toString();
		int total = 0;
		char[] charArray = str.toCharArray();
		println(bigInt);
		for(char c : charArray) {
			total += parseInt(c + "");
		}
		println(total);
	}
	
}

package co.megadodo.euler;

import java.math.BigInteger;

public class Euler0048 extends Euler {

	public void run() {
		
		BigInteger num = BigInteger.ZERO;
		for(int i = 1; i <= 1000; i++) {
			num = num.add(BigInteger.valueOf(i).pow(i));
		}
		println(num);
		println(num.toString().substring(num.toString().length()-10));
		
	}
	
}

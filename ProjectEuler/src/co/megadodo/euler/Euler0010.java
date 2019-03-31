package co.megadodo.euler;

import java.math.BigInteger;

public class Euler0010 extends Euler {
	
	public void run() {
		BigInteger sum = BigInteger.ZERO;
		int primeCounter = 0;
		int prime = nthPrime(primeCounter);
		while(prime < TWO_MIL) {
			primeCounter++;
			println(primeCounter);
			prime = nthPrime(primeCounter);
			sum = sum.add(BigInteger.valueOf(prime));
		}
		println(sum);
		println(sum.subtract(BigInteger.valueOf(nthPrime(primeCounter-1))));
	}
	
}

package co.megadodo.euler;

import java.math.BigInteger;

public class Euler0055 extends Euler {
	
	public void run() {
		
		int counter = 0;
		
		for(int i = 1; i <= 10000; i++) {
			if(!isLychrel(i)) counter++;
		}
		
		println(counter);
		
	}
	
	// under 10,000 - not generalizable, therefore not
	// in EulerUtils
	public boolean isLychrel(int number) {
		return lychrel(number) != -1;
	}
	
	public int lychrel(int number) {
		
		BigInteger current = BigInteger.valueOf(number);
		for(int counter = 0; counter < 50; counter++) {
			current = current.add( new BigInteger(reverseStr(current.toString())));
			if(current.toString().equals(reverseStr(current.toString()))) return counter+1;
		}
		return -1;
		
	}
	
	public String reverseStr(String str) {
		return new StringBuilder(str).reverse().toString();
	}

}

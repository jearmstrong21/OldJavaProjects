package co.megadodo.euler;

import java.math.BigInteger;

public class Euler0053 extends Euler {

	public void run() {
		
		int counter = 0;
		
		// BigInteger
		for(int n = 1; n <= 70; n++) {
			for(int r = 1; r < n; r++) {
				try {
//					if(facto(n-r)==0) println(n,r,n-r,facto(n-r));
					if(comboBig(BigInteger.valueOf(n), BigInteger.valueOf(r)).compareTo(BigInteger.valueOf(MIL)) > 0) {
						counter++;
					}
				} catch(ArithmeticException arme) {
					println(n, r, arme.getMessage());
				}
			}
		}
		println(factoI(35-1));
		println(factoI(34));
		
		println(counter);
		
	}
	
}

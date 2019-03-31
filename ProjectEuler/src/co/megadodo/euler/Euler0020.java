package co.megadodo.euler;

import java.math.BigInteger;

public class Euler0020 extends Euler {

	public void run() {
		BigInteger combo = comboBig(BigInteger.valueOf(100));
		println(combo);
		String str = combo.toString();
		println(sumDigits(str));
	}
	
}

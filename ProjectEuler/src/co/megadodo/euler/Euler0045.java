package co.megadodo.euler;

public class Euler0045 extends Euler {
	
	public void run() {
		boolean found = false;
		int n = 285;
		int max = 40;
		for(long i = 100000; !found; i++) {
			if(isTriNumber(i, max) && isHexNumber(i, max) && isPentNumber(i, max)) {
				found = true;
				println(i);
				exit();
			}
		}
	}
	
}

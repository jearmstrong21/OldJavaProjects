package co.megadodo.euler;

public class Euler0007 extends Euler {
	
	public void run() {
		
		int primeCounter = 0;
		int num = 1;
		
		while(primeCounter < 10001) {
			boolean prime = isPrime(num);
			if(prime) {
				primeCounter++;
				println(format("% 4d", primeCounter) + ": " + num);
			}
			num++;
		}
	}
	
	
}

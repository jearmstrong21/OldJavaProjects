package co.megadodo.euler;

public class Euler0009 extends Euler {

	public void run() {
		int a=0;
		int b=0;
		int c=0;
		int goodA = 0;
		int goodB = 0;
		int goodC = 0;
		for(a = 0; a < 1000; a++) {
			for(b = a; b < 1000; b++) {
				for(c = b; c < 1000; c++) {
					if(a*a + b*b == c*c && a + b + c == 1000) {
						println("Triplet: " + a + " " + b + " " + c);
						goodA = a;
						goodB = b;
						goodC = c;
					}
				}
			}
		}
		println("Product abc: " + (goodA * goodB * goodC));
		
	}
	
}

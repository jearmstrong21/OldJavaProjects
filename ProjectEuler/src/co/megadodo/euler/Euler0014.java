package co.megadodo.euler;

public class Euler0014 extends Euler {

	public void run() {
		int maxInd = 0;
		int maxVal = 0;
		for(int i = 1; i <= MIL; i++) {
			int num = collatzNum(i);
			if(num >= maxVal) {
				maxVal = num;
				maxInd = i;
			}
		}
		println(maxVal + " " + maxInd);
	}
	
}

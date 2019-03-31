package co.megadodo.euler;

public class Euler0012 extends Euler {

	public void run() {
		int index = 1;
		int tri = 0;
		// numFactors > 5
		boolean found = false;
		for(index = 2000; index < 10000; index++) {
			tri = triNumber(index);
			println(String.format("% 4d", index) + " : " + numFactors(tri, false) + " " + tri);
			if(numFactors(triNumber(index)) > 500) {
				found = true;
			}
		}
		println("RESULT: #" + index + " " + tri);
	}
	
}

package co.megadodo.euler;

public class Euler0040 extends Euler {
	
	public void run() {
		String str = "";
		int i = 1;
		while(str.length() <= 1000000) {
			str += i + "";
			i++;
		}
		println(str.charAt(-1+1));
		println(str.charAt(-1+10));
		println(str.charAt(-1+100));
		println(str.charAt(-1+1000));
		println(str.charAt(-1+10000));
		println(str.charAt(-1+100000));
		println(str.charAt(-1+1000000));
	}

}

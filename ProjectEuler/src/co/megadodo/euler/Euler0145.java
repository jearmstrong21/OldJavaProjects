package co.megadodo.euler;

public class Euler0145 extends Euler {

	@Override
	public void run() throws Throwable {
		
		long num = 0;
		for(long i = 1; i < Math.pow(10, 9); i+=1) {
//			if(oddDigits(i+reverseL(i))){
//				num++;
//				println(num + " " + i + " " +reverseL(i) + " " + (i+reverseL(i)));
//			}
			if(i % 2 == 0 && i % 10 != 0) num++;
		}
		System.out.println(num);

	}
	
	public boolean oddDigits(long i) {
		String str = String.valueOf(i);
		for(char c : str.toCharArray()) {
			if(c == '0' || c == '2' || c=='4'||c=='6'||c=='8') return false;
		}
		return true;
	}

}

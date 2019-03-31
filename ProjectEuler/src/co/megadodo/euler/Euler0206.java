package co.megadodo.euler;

public class Euler0206 extends Euler {

	public void run() {
		
		boolean found = false;
		long number;
		println(matches("1a2a3a4a5a6a7a8a9a0"));
		println("1a2a3a4a5a6a7a8a9a0".length());
		for(number = 1000000000; !found; number++) {
			if(matches(Long.toString(number*number))) found = true;
			if(number % 1000 == 0) println(number + " " + (number*number));
			if(number < 0 || ((number*number) + "").length() > 19) {
				println("EXIT " + number + " " + number*number);
				exit();
			}
		}
		number--;
		println(number + " " + number*number);
		
		
	}
	
	public boolean matches(String str) {
		String pattern = "1*2*3*4*5*6*7*8*9*0";
		if(str.length() != pattern.length()) return false;
		for(int i = 0; i < pattern.length(); i++) {
			if(pattern.charAt(i) == '_') continue;
			if(pattern.charAt(i) != str.charAt(i)) return false;
		}
		return true;
	}
	
}

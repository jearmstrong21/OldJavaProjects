package co.megadodo.euler;

import java.util.Arrays;

public class Euler0052 extends Euler {

	public void run() throws Throwable {

		boolean found = false;
		
		int i = 0;
		for(i = 0; i < 1000000; i++) {
			
			if(matches(i)) found = true;
			
		}
		
		i--;
		println(found);
		println(i);
		
	}
	
	public boolean matches(int a) {
		//abcdef
		return matches(a, a*2) && matches(a*2, a*3) && matches(a*3, a*4) && matches(a*4, a*5) && matches(a*5, a*6);
	}

	public boolean matches(int a, int b) {
		String s1 = String.valueOf(a);
		String s2 = String.valueOf(2);
		char[] arr1 = s1.toCharArray();
		char[] arr2 = s2.toCharArray();
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		return Arrays.equals(arr1, arr2);
	}
	
}

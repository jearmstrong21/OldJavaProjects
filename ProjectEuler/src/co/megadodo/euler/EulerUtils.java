package co.megadodo.euler;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.List;

public interface EulerUtils {
	
	public static int MIL = 1000000;
	public static int TWO_MIL = 2000000;
	
	public default int length(int n) {
		return length(n, 10);
	}
	
	public default long reverseL(long l) {
		StringBuffer bfr = new StringBuffer(String.valueOf(l));
		return Long.parseLong(bfr.reverse().toString());
	}
	
	public default int length(int n, int radix) {
		return Integer.toString(n, radix).length();
	}
	
	public default int factoI(int n) {
		int total = 1;
		for(int i = 1; i <= n; i++) 
			total*=i;
		return total;
	}
	
	public default long factoL(long n) {
		long total = 1;
		for(long i = 1; i <= n; i++)
			total*=i;
		return total;
	}
	
	public default BigInteger factoBig(BigInteger n) {
		BigInteger total = BigInteger.ONE;
		for(BigInteger i = BigInteger.ONE; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
			total = total.multiply(i);
		}
		return total;
	}
	
	public default int comboI(int n, int r) {
		return factoI(n) / ( factoI(r) * factoI(n-r)   );
	}
	
	public default long comboL(long n, long r) {
		return factoL(n) / ( factoL(r) * factoL(n-r)   );
	}
	
	public default BigInteger comboBig(BigInteger n, BigInteger r) {
		
		return factoBig(n).divide(  factoBig(r).multiply( factoBig(n.subtract(r))  )  );
		
	}
	
	public default boolean isPalindrome(double d) {
		return reverse(d) == d;
	}
	
	public default boolean isPalindromeF(float f) {
		return reverseF(f) == f;
	}
	
	public default boolean isPalindromeI(int i) {
		return reverseI(i) == i;
	}
	
	public default double reverse(double d) {
		StringBuilder builder = new StringBuilder(Double.toString(d));
		builder.reverse();
		return parseDouble(builder.toString());
	}
	
	public default float reverseF(float f) {
		StringBuilder builder = new StringBuilder(Float.toString(f));
		builder.reverse();
		return parseFloat(builder.toString());
	}
	
	public default int reverseI(int i) {
		StringBuilder builder = new StringBuilder(Integer.toString(i));
		builder.reverse();
		return parseInt(builder.toString());
	}
	
	public default double round(double d) {
		return Math.round(d);
	}
	
	public default float roundF(float f) {
		return (float) round(f);
	}
	
	public default int roundI(int i) {
		return (int) round(i);
	}
	
	public default double floor(double d) {
		return Math.floor(d);
	}
	
	public default float floorF(float f) {
		return (float) floor(f);
	}
	
	public default int floorI(int i) {
		return (int) floor(i);
	}
	
	public default double ceil(double d) {
		return Math.ceil(d);
	}
	
	public default float ceilF(float d) {
		return (float) ceil(d);
	}
	
	public default int ceilI(int i) {
		return (int) ceil(i);
	}
	
	public default double sqrt(double d) {
		return Math.sqrt(d);
	}
	
	public default float sqrtF(float f) {
		return (float) sqrt(f);
	}
	
	// I = int
	public default int sqrtI(int i) {
		return (int) sqrtF(i);
	}
	
	public default int whichTriNumber(int number) {
		return (sqrtI(8*number+1) - 1)/2;
	}
	
	public default int whichPentNumber(int number) {
		return (sqrtI(24*number+1)+1)/6;
	}
	
	public default int whichHexNumber(int number) {
		return (sqrtI(8*number+1)+1)/4;
	}
	
	public default int hexNumber(int ind) {
		return ind * (2*ind - 1);
	}
	
	public default boolean isHexNumber(long number, int maxHex) {
		for(int i = 1; i <= maxHex; i++) {
			if(hexNumber(i) == number) return true;
		}
		return false;
	}
	
	public default int pentNumber(int ind) {
		return ind * (3*ind-1)/2;
	}
	
	public default boolean isPentNumber(long number, int maxPent) {
		for(int i = 1; i <= maxPent; i++) {
			if(pentNumber(i) == number) return true;
		}
		return false;
	}

	public default boolean isTriNumber(long number, int maxTri) {
		for(int i = 1; i <= maxTri; i++) {
			if(triNumber(i) == number) return true;
		}
		return false;
	}
	
	public default int alphaScore(String str) {
		if(!str.equals(str.toLowerCase())) {
			return alphaScore(str.toLowerCase());
		}
		int total = 0;
		for(char c : str.toCharArray()) {
			total += (c - 'a') + 1;
		}
		return total;
	}
	
	public default boolean containsRepeats(int... ints) {
		for(int i = 0; i < ints.length; i++) {
			int num = ints[i];
			for(int j = 0; j < ints.length; j++) {
				if(i==j)continue;
				int num2 = ints[j];
				if(num == num2) return true;
			}
		}
		return false;
	}
	
	public static <T>T[] asArray(List<T> list, Class<T> clazz) {
		@SuppressWarnings("unchecked")
		T[] arr = (T[]) Array.newInstance(clazz, list.size());
		for(int i=0;i<list.size();i++)arr[i]=list.get(i);
		return arr;
	}
	
	public default int sumDigits(String str) {
		char[] charArray = str.toCharArray();
		int total = 0;
		for(char c : charArray) total += parseInt(c + "");
		return total;
	}
	
	public default BigInteger comboBig(BigInteger n) {
		if(n.compareTo(BigInteger.ONE) <= 0) return BigInteger.ONE;
		return n.multiply(comboBig(n.subtract(BigInteger.ONE)));
	}
	

	// 1 - 3 digits
	public default String asStringN(int number) {
		if(number < 1 || number > 1000) return "X";
		if(number == 1000) return "one thousand";
		if(number < 10) return asString1Dig(number);
		if(number < 100) return asString2(number);
		if(number < 1000) return asString3(number);
		
		return "X";
	}
	
	
	public default String asString2(int number) {
		int secondDigit = (number - number%10)/10;
		int firstDigit = number%10;
		String tens = asStringTens(secondDigit);
		String ones = asString1Dig(firstDigit);
		if(secondDigit == 1) {
			return asStringTeens(number);
		}
		if(ones.equals("")) return tens;
		return tens + "-" + ones;
	}
	
	public default String asString3(int number) {
		int digHundreds = (number - number%100) / 100;
		int digTens		= (number - digHundreds*100) % 10;
		int digOnes     = (number - digHundreds * 100 - digTens * 10);
		String strHundreds = asStringHundred(digHundreds);
		if(digTens == 0 && digOnes == 0) return strHundreds;
		String strTensOnes = asString2(digTens*10 + digOnes);
		return strHundreds + " and " + strTensOnes;
	}
	
	public default String asStringHundred(int digit) {
		return asString1Dig(digit) + " hundred";
	}
	
	public default String asStringTeens(int number) {
		switch(number) {
			case 10: return "ten";
			case 11: return "eleven";
			case 12: return "twelve";
			case 13: return "thirteen";
			case 14: return "fourteen";
			case 15: return "fifteen";
			case 16: return "sixteen";
			case 17: return "seventeen";
			case 18: return "eighteen";
			case 19: return "nineteen";
		}
		return "X";
	}
	
	// second digit, 0-9
	public default String asStringTens(int digit) {
		switch(digit) {
			case 0: return "";
			case 1: return "ten";
			case 2: return "twenty";
			case 3: return "thirty";
			case 4: return "forty";
			case 5: return "fifty";
			case 6: return "sixty";
			case 7: return "seventy";
			case 8: return "eighty";
			case 9: return "ninety";
		}
		return "X";
	}
	
	// 1 digit, 0-9
	public default String asString1Dig(int digit) {
		switch(digit) {
			case 0: return "";
			case 1: return "one";
			case 2: return "two";
			case 3: return "three";
			case 4: return "four";
			case 5: return "five";
			case 6: return "six";
			case 7: return "seven";
			case 8: return "eight";
			case 9: return "nine";
		}
		return "X";
	}
	
	
	public default int collatzNum(int start) {
		int num = 1;
		while(start > 1) {
			if(even(start)) start/=2;
			else start = start*3 + 1;
			num++;
		}
		return num;
	}
	
	
	public default boolean even(int n) {
		return n % 2 == 0;
	}
	
	public default boolean odd(int n) {
		return !even(n);
	}
	
	public default void exit() {
		exit(0);
	}
	
	public default void exit(int status) {
		System.exit(status);
	}
	
	public default int triNumberRecursive(int n) {
		if(n <= 1) return 1;
		return triNumberRecursive(n - 1) + n;
	}
	
	public default int triNumber(int n) {
		return n * (n + 1) / 2;
	}
	
	
	public default int numFactors(int n) {
		return numFactors(n, false);
	}
	
	public default int numFactors(int n, boolean debug) {
		int num = 0;
		for(int i = 1; i <= n; i++) {
			if(n % i == 0) {
				if(debug) print(i + " ");
				num++;
			}
		}
		if(debug) println();
		return num;
	}
	
	public default String format(String frmt, Object... args) {
		return String.format(frmt, args);
	}
	
	public default int nthPrime(int n) {
		int primeCounter = 0;
		int i = 0;
		while(primeCounter <= n) {
			i++;
			if(isPrime(i)) {
				primeCounter++;
			}
		}
		return i;
	}

	public default boolean isPrime(int n) {
		if(n <= 1) return false;
		for(int i = 2; i <= n/2; i++) {
			if(n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public default boolean isPrimeLong(long n) {
		for(long i = 2; i < n/2; i++) {
			if(n/i > i) {
				return false;
			}
			if(n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public default int parseInt(String str) {
		return Integer.parseInt(str);
	}
	
	
	public default float parseFloat(String str) {
		return Float.parseFloat(str);
	}
	
	public default double parseDouble(String str) {
		return Double.parseDouble(str);
	}
	
	public default byte parseByte(String str) {
		return Byte.parseByte(str);
	}
	
	public default short parseShort(String str) {
		return Short.parseShort(str);
	}
	
	public default long parseLong(String str) {
		return Long.parseLong(str);
	}

	public default void print(byte what) {
		System.out.print(what);
		System.out.flush();
	}

	public default void print(boolean what) {
		System.out.print(what);
		System.out.flush();
	}

	public default void print(char what) {
		System.out.print(what);
		System.out.flush();
	}

	public default void print(int what) {
		System.out.print(what);
		System.out.flush();
	}

	public default void print(long what) {
		System.out.print(what);
		System.out.flush();
	}

	public default void print(float what) {
		System.out.print(what);
		System.out.flush();
	}

	public default void print(double what) {
		System.out.print(what);
		System.out.flush();
	}

	public default void print(String what) {
		System.out.print(what);
		System.out.flush();
	}

	public default void print(Object... variables) {
		StringBuilder sb = new StringBuilder();
		for (Object o : variables) {
			if (sb.length() != 0) {
				sb.append(" ");
			}
			if (o == null) {
				sb.append("null");
			} else {
				sb.append(o.toString());
			}
		}
		System.out.print(sb.toString());
	}

	public default void println() {
		System.out.println();
	}

	public default void println(byte what) {
		System.out.println(what);
		System.out.flush();
	}

	public default void println(boolean what) {
		System.out.println(what);
		System.out.flush();
	}

	public default void println(char what) {
		System.out.println(what);
		System.out.flush();
	}

	public default void println(int what) {
		System.out.println(what);
		System.out.flush();
	}

	public default void println(long what) {
		System.out.println(what);
		System.out.flush();
	}

	public default void println(float what) {
		System.out.println(what);
		System.out.flush();
	}

	public default void println(double what) {
		System.out.println(what);
		System.out.flush();
	}

	public default void println(String what) {
		System.out.println(what);
		System.out.flush();
	}

	public default void println(Object... variables) {
		print(variables);
		println();
	}

}

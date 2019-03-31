/*
ID: melissa11
LANG: JAVA
TASK: beads
 */
import java.io.PrintStream;
import java.util.Scanner;
	

public class beads {
	
	static int R = 0;
	static int B = 1;
	static int W = 2;
	static int N = 0;
	static int[] ar;
	public static void main(String[] args) throws Throwable {
//		Scanner sc = new Scanner(new File("friday.in"));
//		PrintWriter out = new PrintWriter("friday.out");
		Scanner sc = new Scanner(System.in);
		PrintStream out = System.out;

//		N = Integer.parseInt(sc.nextLine());
		N = 29;
		ar = new int[N];
//		String s = sc.nextLine();
//		String s = "rrbbwwrrbb"; // 0011220011
		String s = "wwwbbrwrbrbrrbrbrwrwwrbwrwrrb";
		for(int i = 0; i < N; i++) {
			char c = s.charAt(i);
			if(c == 'r') ar[i] = R;
			if(c == 'w') ar[i] = W;
			if(c == 'b') ar[i] = B;
		}
		
		int maxsplit = 0;
		for(int i = 0; i < N; i++) {
			if(numOfBeadsOnSplit(maxsplit) < numOfBeadsOnSplit(i)) {
				maxsplit = i;
			}
		}
		out.println(maxsplit);
		out.println(numOfBeadsOnSplit(maxsplit));
		sc.close();
		out.close();
		
	}
	
	
	static int numOfBeadsOnSplit(int split) {
		System.out.print("SPLIT: " + split + ": LEFT: ");
		int total = 0;
		int i = 0;
		int j = 0;
		int first = ar[split];
		System.out.print(ar[split] + " ");
		while(ar[i] == first && j < N) {
			i--;
			if(i < 0) i = N-1;
			total++;
			System.out.print(ar[i] + " ");
			j++;
		}
		
		System.out.print("TOTAL: " + total + " RIGHT: ");
		
		i = 0;
		j = 0;
		if(split != N-1) first = ar[split+1];
		else first = ar[0];

		System.out.print(ar[first] + " ");
		while(ar[i] == first && j < N) {
			i++;
			if(i >= N) {
				i = 0;
			}
			total++;
			System.out.print(ar[i] + " ");
			j++;
		}
		System.out.println();
		
		return total;
		
		
	}
	
}


/*
ID: melissa11
LANG: JAVA
TASK: gift1
*/

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class gift1 {
	public static void main(String[] args) throws Throwable {
		Scanner sc = new Scanner(new File("gift1.in"));
		PrintWriter out = new PrintWriter("gift1.out");
		
		int NP = sc.nextInt();
		sc.nextLine();
		String[] list = new String[NP];
		int[] bal = new int[NP];
		

		/*
5
dave
laura
owen
vick
amr
dave
200 3
laura
owen
vick
owen
500 1
dave
amr
150 2
vick
owen
laura
0 2
amr
vick
vick
0 0
		 */
		for(int i = 0; i < NP; i++) {
			list[i] = sc.nextLine();
			bal[i] = 0;
		}
		for(int i = 0; i < NP; i++) {
			String name = sc.nextLine();

			int _bal = sc.nextInt();
			int split = sc.nextInt();
			sc.nextLine();

			int index = -1;
			int payout = getsplit(_bal,split);
			for(int j = 0; j < NP; j++) { if(list[j].equals(name)) index = j; }
			
			for(int j = 0; j < split; j++) {
				bal[index]-=payout;
				String _name = sc.nextLine();
				for(int k = 0; k < NP; k++) { if(list[k].equals(_name)) bal[k]+=payout; }
			}
		}
		
		for(int i = 0; i < NP; i++) {
			out.println(list[i] + " " + bal[i]);
		}
		
		sc.close();
		out.close();
	}
	
	static int getsplit(int a, int b) {
		if(b == 0) return 0;
		return a/b;
	}
	
}

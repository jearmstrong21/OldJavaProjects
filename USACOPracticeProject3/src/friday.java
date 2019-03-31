/*
ID: melissa11
LANG: JAVA
TASK: friday
*/
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
	

public class friday {
	public static void main(String[] args) throws Throwable {
		Scanner sc = new Scanner(new File("friday.in"));
		PrintWriter out = new PrintWriter("friday.out");
//		Scanner sc = new Scanner(System.in);
//		PrintStream out = System.out;
		
		int[] days = new int[8]; // 0index is not used, 1-7 = Sat,Sun,Mon,Tue,Wed,Thu,Fri
		int[] daysInMonth = new int[] {0,31,28,31,30,31,30,31,31,30,31,30,31}; // 0index is not used
		int N = sc.nextInt();
//		int N = 20;
		int Y = 1900;
		int M = 1;
		int D = 1;
		int W_D = 3;
		
		while(Y < 1900+N) {
			W_D++;
			D++;
			if(W_D > 7) W_D = 1;
			int daysinmonth = daysInMonth[M];
			if(isleapyear(Y) && M == 2) daysinmonth++;
			if(D > daysinmonth) {
				M++;
				D = 1;
			}
			if(M > 12) {
				Y++;
				M = 1;
			}
			
			if(D == 13) {
				days[W_D]++;
			}
			
		}

		for(int i = 1; i<= 7; i++) {
			if(i!=7)
			out.print(days[i] + " ");
			else out.print(days[i]);
		}
		out.println();

		
		sc.close();
		out.close();
		
	}
	
	static boolean isleapyear(int y) {
		return ((y % 4 == 0) && (y % 100 != 0) || (y % 400 == 0));
	}
	
	
}

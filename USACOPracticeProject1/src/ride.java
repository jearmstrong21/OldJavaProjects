/*
ID: melissa11
LANG: JAVA
TASK: ride
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ride {
	public static void main(String[] args) throws Throwable {
		//ride.in
		//ride.out
		Scanner sc = new Scanner(new File("ride.in"));
		String a = sc.nextLine();
		String b = sc.nextLine();
		PrintWriter out = new PrintWriter("ride.out");
		out.println( (numOfStr(a)%47 == numOfStr(b)%47 ? "GO" : "STAY" )  );
		out.close();
		sc.close();
		
	}
	
	static int numOfStr(String s) {
		int a = 1;
		for(int i=0;i<s.length();i++){
			a*=chartonum(s.charAt(i));
		}
		return a;
	}
	
	static int chartonum(char c) {
		c = Character.toLowerCase(c);
		switch(c){
			case 'a':return  1;
			case 'b':return  2;
			case 'c':return  3;
			case 'd':return  4;
			case 'e':return  5;
			case 'f':return  6;
			case 'g':return  7;
			case 'h':return  8;
			case 'i':return  9;
			case 'j':return 10;
			case 'k':return 11;
			case 'l':return 12;
			case 'm':return 13;
			case 'n':return 14;
			case 'o':return 15;
			case 'p':return 16;
			case 'q':return 17;
			case 'r':return 18;
			case 's':return 19;
			case 't':return 20;
			case 'u':return 21;
			case 'v':return 22;
			case 'w':return 23;
			case 'x':return 24;
			case 'y':return 25;
			case 'z':return 26;
		}
		System.out.println(c);
		return 0;
	}
}

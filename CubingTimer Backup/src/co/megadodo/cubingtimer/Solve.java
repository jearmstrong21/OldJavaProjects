package co.megadodo.cubingtimer;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Solve {

	Cube cube;
	SolveType solveType;
	String strTime;
	long solveTime;
	long solveYear;
	long solveMonth;
	long solveDay;
	
	public String shortToString() {
		return cube + " " + solveType + " " + strTime + " " + solveTime + " " + solveYear + " " + solveMonth + " " + solveDay;
	}
	
	public static void writeSolves(String fileName, ArrayList<Solve> list) {
		PrintWriter writer;
		
		try { writer = new PrintWriter(new File(fileName)); } catch(Throwable t) { return; }
		
		for(Solve s : list) {
			writer.println(s.cube);
			writer.println(s.solveType);
			writer.println(s.strTime);
			writer.println(s.solveTime);
			writer.println(s.solveYear);
			writer.println(s.solveMonth);
			writer.println(s.solveDay);
		}
		
		writer.flush();
		writer.close();
	}
	
	public static ArrayList<Solve> parseSolves(String fileName) {
		ArrayList<Solve> list = new ArrayList<Solve>();
		
		Scanner sc;
		try { sc = new Scanner(new File(fileName)); } catch(Throwable t) { return list; }
		
		while(sc.hasNextLine()) {
			
			Solve s = new Solve();
			s.cube = Cube.parseCube(sc.nextLine());
			s.solveType = SolveType.parseSolveType(sc.nextLine());
			s.strTime = sc.nextLine();
			s.solveTime = Long.parseLong(sc.nextLine());
			s.solveYear = Long.parseLong(sc.nextLine());
			s.solveMonth = Long.parseLong(sc.nextLine());
			s.solveDay = Long.parseLong(sc.nextLine());
			list.add(s);
		}
		
		sc.close();

		return list;
	}
	
}

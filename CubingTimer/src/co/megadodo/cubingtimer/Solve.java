package co.megadodo.cubingtimer;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Solve {

	public Cube cube;
	public SolveType solveType;
	public String strTime;
	public long solveTime;
	public String solveDate;
	public CubeBrand brand = CubeBrand.OTHER;
	
	public String shortToString() {
		return cube + " " + solveType + " " + strTime + " " + solveTime + " " + solveDate + " " + brand;
	}
	public static final String PRCSING_DIR = "~/Documents/Processing/CubeGrapher/Cubes_Eclipse.txt";
	public static void writeSolves(String fileName, ArrayList<Solve> list) {
		PrintWriter writer;
		
		try { writer = new PrintWriter(new File(fileName)); } catch(Throwable t) { return; }
		
		for(Solve s : list) {
			writer.println(s.cube);
			writer.println(s.solveType);
			writer.println(s.strTime);
			writer.println(s.solveTime);
			writer.println(s.solveDate);
			writer.println(s.brand);
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
			s.solveDate = sc.nextLine();
			s.brand = CubeBrand.parseCubeBrand(sc.nextLine());
			list.add(s);
		}
		
		sc.close();

		return list;
	}
	
}

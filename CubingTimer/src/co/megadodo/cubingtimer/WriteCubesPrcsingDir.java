package co.megadodo.cubingtimer;

import java.util.ArrayList;

public class WriteCubesPrcsingDir {

	public static void main(String[] args) {
		System.out.println("Writing solves to Processing directory");
		System.out.println("Retreiving solves");
		ArrayList<Solve> solves = Solve.parseSolves(CubingTimer.FILE_NAME);
		System.out.println("# of solves: " + solves.size());
		System.out.println("Writing solves to <" + Solve.PRCSING_DIR + ">");
		Solve.writeSolves(Solve.PRCSING_DIR, solves);
		System.out.println("Terminating");
		System.exit(0);
	}
	
}

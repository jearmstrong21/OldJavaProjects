package co.megadodo.cubingtimer;

import java.util.ArrayList;

public class BackupCubes {

	public static void main(String[] args) {
		System.out.println("Backing up cubes");
		ArrayList<Solve> solves = Solve.parseSolves(CubingTimer.FILE_NAME);
		System.out.println("Loading solves");
		Solve.writeSolves("Cubes_backup.txt", solves);
		System.out.println("Backup complete");
		System.exit(0);
	}
	
}

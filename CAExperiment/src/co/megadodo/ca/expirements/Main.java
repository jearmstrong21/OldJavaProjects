package co.megadodo.ca.expirements;

import co.megadodo.ca.expirements.terrain_ca.TerrainCA;

public class Main {
	public static enum Expirement {
		TERRAIN_CA
	}
	public static void main(String[] args) {
		Expirement run = Expirement.TERRAIN_CA;
		switch(run) {
			case TERRAIN_CA: TerrainCA.main(args);
		}
	}
}

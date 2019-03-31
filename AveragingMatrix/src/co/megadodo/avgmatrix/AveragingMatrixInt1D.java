package co.megadodo.avgmatrix;

import java.util.ArrayList;
import java.util.Random;

public class AveragingMatrixInt1D {
	AveragingMatrixInt1DParams params;
	Random rand;
	ArrayList<int[]> gens;
	
	public AveragingMatrixInt1D(AveragingMatrixInt1DParams params) {
		this.params = params;
		this.rand = new Random(params.getSeed());
		this.gens = new ArrayList<int[]>();
		int[] firstGen = new int[params.getWidth()];
	}
	
	public AveragingMatrixInt1D(long seed, int min, int max, int w) {
		this(new AveragingMatrixInt1DParams() {
			
			@Override
			public long getSeed() {
				return seed;
			}
			
			@Override
			public int getMin(Random rand, int x) {
				return min;
			}
			
			@Override
			public int getMax(Random rand, int x) {
				return max;
			}

			@Override
			public int getRand(int generated, int x) {
				return generated;
			}
			
			@Override
			public int getWidth() {
				return w;
			}
		});
	}
}

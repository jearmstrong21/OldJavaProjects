package co.megadodo.avgmatrix;

import java.util.Random;

public interface AveragingMatrixInt1DParams {
	public int  getMin(Random rand, int x);
	public int  getMax(Random rand, int x);
	public long getSeed();
	public int  getRand(int generated, int x);
	public int  getWidth();
}

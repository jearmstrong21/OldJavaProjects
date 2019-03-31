package co.megadodo.avgmatrix;

import java.util.Random;

public interface AveragingMatrixInt2DParams {
	public int  getMin(Random rand, int x, int y);
	public int  getMax(Random rand, int x, int y);
	public long getSeed();
	public int  getRand(int generated, int x, int y);
	public int  getWidth();
	public int  getHeight();
}

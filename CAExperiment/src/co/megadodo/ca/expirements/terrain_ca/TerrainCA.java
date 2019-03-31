package co.megadodo.ca.expirements.terrain_ca;


public class TerrainCA {
	
	private static class Cell {
		private int state = 0, nextState = 0;
		
		public Cell(int state) {
			this.state = this.nextState = state;
		}
		
		public void update(int left, int right) {
			nextState = state - left + state - right;
			
		}
		
		public void applyNextState() {
			state = Math.abs(nextState);
		}
		
		public int getState() {
			return this.state;
		}
	}
	private static int getCell(int index, Cell[] cells) {
		if(index < 0) return cells[0].getState();
		if(index >= cells.length) return cells[cells.length-1].getState();
		return cells[index].getState();
	}
	public static void main(String[] args) {
		Cell[] cells = new Cell[20];
		int number = (int)(Math.random() * 10);
		for(int i = 0; i < cells.length; i++) {
			cells[i] = new Cell(number);
			int diff = 0;
			diff = (int) (Math.random() * 4) - 2;
			//if(min != -1 || max != 1)
			{
				if(diff == -1 || diff == 1)
				{
					//diff = 0;
				}
			}
			number+=diff;
		}
		
		for(int i = 1; i <= 20; i++) {
			printCells(cells);
			cells = update(cells);
		}
		
	}
	
	static Cell[] update(Cell[] cells) {
		for(int c = 0; c < cells.length; c++) {
			cells[c].update(getCell(c-1, cells), getCell(c+1, cells));
		}
		for(int c = 0; c < cells.length; c++) {
			cells[c].applyNextState();
		}
		return cells;
	}
	
	static void printCells(Cell[] cells) {
		for(Cell c : cells) {
			System.out.print(c.getState() + "\t");
		}
		System.out.println();
	}

}

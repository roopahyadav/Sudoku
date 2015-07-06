package sodokuassignment;

public class Sudoku {
	/**
	 * class to solve sudoku
	 */

	static int grid1[][] = { { 0, 0, 6, 5, 0, 8, 4, 0, 0 },
			{ 5, 2, 0, 0, 0, 0, 0, 0, 0 }, 
			{ 0, 8, 7, 0, 0, 0, 0, 3, 1 },
			{ 0, 0, 3, 0, 1, 0, 0, 8, 0 }, 
			{ 9, 0, 0, 8, 6, 3, 0, 0, 5 },
			{ 0, 5, 0, 0, 9, 0, 6, 0, 0 }, 
			{ 1, 3, 0, 0, 0, 0, 2, 5, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 7, 4 }, 
			{ 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
	
	/**
	 * checking valid input
	 */
	boolean isValid(SingleGrid cell, int value) {

	

		/**
		 * checking valid input in rowise
		 * */
		for (int c = 0; c < 9; c++) {
			if (grid1[cell.row][c] == value)
				return false;
		}

		/**
		 * checking valid input in columnwise
		 * 
		 */
		for (int r = 0; r < 9; r++) {
			if (grid1[r][cell.col] == value)
				return false;
		}

		/**
		 * checking valid input in gridwise
		 * 
		 */
		int x1 = 3 * (cell.row / 3);
		int y1 = 3 * (cell.col / 3);
		int x2 = x1 + 2;
		int y2 = y1 + 2;

		for (int x = x1; x <= x2; x++)
			for (int y = y1; y <= y2; y++)
				if (grid1[x][y] == value)
					return false;

		return true;
	}

	/**
	 * To get the new cell
	 * 
	 * @param cur
	 * @return
	 */
	SingleGrid getNextCell(SingleGrid cur) {

		int row = cur.row;
		int col = cur.col;

		col++;

		if (col > 8) {

			col = 0;
			row++;
		}

		if (row > 8)
			return null;

		SingleGrid next = new SingleGrid(row, col);
		return next;
	}

	/**
	 * 
	 * solve the sudoku
	 * 
	 * @param cur
	 * @return
	 */
	boolean solve(SingleGrid cur) {

		if (cur == null)
			return true;

		if (grid1[cur.row][cur.col] != 0) {

			return solve(getNextCell(cur));
		}

		for (int i = 1; i <= 9; i++) {

			boolean valid = isValid(cur, i);

			if (!valid)
				continue;

			grid1[cur.row][cur.col] = i;

			boolean solved = solve(getNextCell(cur));

			if (solved)
				return true;
			else
				grid1[cur.row][cur.col] = 0;

		}

		return false;
	}

	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku();
		SingleGrid cell = new SingleGrid(0, 0);
		boolean solved = sudoku.solve(cell);

		if (!solved) {
			System.out.println("SUDOKU cannot be solved.");
			return;
		}
		System.out.println("Solution to sudoku is:");

		printgrid1(grid1);
	}

	/**
	 * 
	 * print the grid
	 * 
	 * @param grid1
	 */
	static void printgrid1(int grid1[][]) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++)
				System.out.print(grid1[row][col] + "\t");
			System.out.println();
		}
	}

}

class SingleGrid {

	int row, col;

	public SingleGrid(int row, int col) {

		this.row = row;
		this.col = col;
	}
}

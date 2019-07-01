package com.thistestuser.solver;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.IntStream;

/**
 * Adapted from https://github.com/eugenp/tutorials/blob/master/algorithms-miscellaneous-2/
 * src/main/java/com/baeldung/algorithms/sudoku/BacktrackingAlgorithm.java
 */
public class SudokuSolver
{
	private static final int BOARD_SIZE = 9;
	private static final int SUBSECTION_SIZE = 3;
	private static final int BOARD_START_INDEX = 0;
	
	private static final int NO_VALUE = 0;
	private static final int MIN_VALUE = 1;
	private static final int MAX_VALUE = 9;
	
    private static int[][] board = {
    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
    	{0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    private SudokuConstraints constraints = new SudokuConstraints();
	
	public static void main(String[] args)
	{
		SudokuSolver solver = new SudokuSolver();
		solver.setupConstraints();
		if(solver.solve(board))
			solver.printBoard();
		else
			System.out.println("Impossible board");
	}
	
	/**
	 * Setup specific constraints for this sudoku. A cell's row is horizontal, while its column is vertical.
	 * The digits also range from 0-8.
	 */
	private void setupConstraints()
	{
		constraints = new SudokuConstraints();
	}
	
	private void printBoard()
	{
		for(int row = BOARD_START_INDEX; row < BOARD_SIZE; row++)
		{
			for(int column = BOARD_START_INDEX; column < BOARD_SIZE; column++)
				System.out.print(board[row][column] + " ");
			System.out.println();
		}
	}
	
	private boolean solve(int[][] board)
	{
		for(int row = BOARD_START_INDEX; row < BOARD_SIZE; row++)
			for(int column = BOARD_START_INDEX; column < BOARD_SIZE; column++)
				if(board[row][column] == NO_VALUE)
				{
					for(int k = MIN_VALUE; k <= MAX_VALUE; k++)
					{
						board[row][column] = k;
						if(isValid(board, row, column) && solve(board))
							return true;
						board[row][column] = NO_VALUE;
					}
					return false;
				}
		return true;
	}
	
	private boolean isValid(int[][] board, int row, int column)
	{
		if(!rowConstraint(board, row) || !columnConstraint(board, column))
			return false;
		if(constraints.isSubsections() && !subsectionConstraint(board, row, column))
			return false;
		if(constraints.isDiagonal() && (row == column || 8 - row == column) && !diagonalConstraint(board, row == column))
			return false;
		if(constraints.isCenterDot() && row % 3 == 1 && column % 3 == 1
			&& !centerDotConstraint(board))
			return false;
		if(constraints.isNonConsecutive() && !nonConsecutiveConstraint(board, row, column))
			return false;
		if(constraints.isNonSum5() && !nonSumConstraint(board, row, column, true))
			return false;
		if(constraints.isNonSum10() && !nonSumConstraint(board, row, column, false))
			return false;
		if(constraints.getRegions() != null)
			for(List<Cell> cells : constraints.getRegions())
				if(cells.contains(new Cell(row, column)) && !regionConstraint(board, cells))
					return false;
		if(constraints.getKillerSums() != null)
			for(Entry<List<Cell>, Integer> entry : constraints.getKillerSums().entrySet())
				if(entry.getKey().contains(new Cell(row, column)) && !killerConstraint(board, entry.getKey(), entry.getValue()))
					return false;
		if(constraints.getGreaterThans() != null)
			for(Entry<Cell, Cell> entry : constraints.getGreaterThans())
				if((entry.getKey().equals(new Cell(row, column)) || entry.getValue().equals(new Cell(row, column)))
					&& !greaterThanConstraint(board, entry.getKey(), entry.getValue()))
					return false;
		if(constraints.getArrows() != null)
			for(Entry<Cell, List<Cell>> entry : constraints.getArrows().entrySet())
				if((entry.getKey().equals(new Cell(row, column)) || entry.getValue().contains(new Cell(row, column)))
					&& !arrowConstraint(board, entry.getValue(), entry.getKey()))
					return false;
		if(constraints.getConsecutive() != null)
			for(Entry<Cell, Cell> entry : constraints.getConsecutive())
				if((entry.getKey().equals(new Cell(row, column)) || entry.getValue().equals(new Cell(row, column)))
					&& !consecutiveConstraint(board, entry.getKey(), entry.getValue()))
					return false;
		if(constraints.getOddCells() != null && constraints.getOddCells().contains(new Cell(row, column)) && board[row][column] % 2 == 0)
			return false;
		if(constraints.getEvenCells() != null && constraints.getEvenCells().contains(new Cell(row, column)) && board[row][column] % 2 == 1)
			return false;
		if(constraints.getSum5() != null)
			for(Entry<Cell, Cell> entry : constraints.getSum5())
				if((entry.getKey().equals(new Cell(row, column)) || entry.getValue().equals(new Cell(row, column)))
					&& !sumConstraint(board, entry.getKey(), entry.getValue(), row, column, true))
					return false;
		if(constraints.getSum10() != null)
			for(Entry<Cell, Cell> entry : constraints.getSum10())
				if((entry.getKey().equals(new Cell(row, column)) || entry.getValue().equals(new Cell(row, column)))
					&& !sumConstraint(board, entry.getKey(), entry.getValue(), row, column, false))
					return false;
		return true;
	}
	
	private boolean sumConstraint(int[][] board, Cell cell1, Cell cell2, int row, int column, boolean sum5)
	{
		int value1 = board[cell1.getRow()][cell1.getColumn()];
		int value2 = board[cell2.getRow()][cell2.getColumn()];
		if(value1 != NO_VALUE && value2 != NO_VALUE && value1 + value2 != (sum5 ? 5 : 10))
			return false;
		if(sum5 && board[row][column] >= 5)
			return false;
		if(!sum5 && board[row][column] == 5)
			return false;
		if((value1 == NO_VALUE && value2 != NO_VALUE) || (value1 != NO_VALUE && value2 == NO_VALUE))
		{
			int known = value1 != NO_VALUE ? value1 : value2;
			int otherRow = value1 != NO_VALUE ? cell2.getRow() : cell1.getRow();
			int otherCol = value1 != NO_VALUE ? cell2.getColumn() : cell1.getColumn();
			board[otherRow][otherCol] = (sum5 ? 5 : 10) - known;
			if(isValid(board, otherRow, otherCol))
			{
				board[otherRow][otherCol] = NO_VALUE;
				return true;
			}
			board[otherRow][otherCol] = NO_VALUE;
			return false;
		}
		return true;
	}
	
	private boolean consecutiveConstraint(int[][] board, Cell cell1, Cell cell2)
	{
		int value1 = board[cell1.getRow()][cell1.getColumn()];
		int value2 = board[cell2.getRow()][cell2.getColumn()];
		if(value1 != NO_VALUE && value2 != NO_VALUE && Math.abs(value1 - value2) != 1)
			return false;
		if((value1 == NO_VALUE && value2 != NO_VALUE) || (value1 != NO_VALUE && value2 == NO_VALUE))
		{
			int known = value1 != NO_VALUE ? value1 : value2;
			int otherRow = value1 != NO_VALUE ? cell2.getRow() : cell1.getRow();
			int otherCol = value1 != NO_VALUE ? cell2.getColumn() : cell1.getColumn();
			if(known > 1)
			{
				board[otherRow][otherCol] = known - 1;
				if(isValid(board, otherRow, otherCol))
				{
					board[otherRow][otherCol] = NO_VALUE;
					return true;
				}
			}
			if(known < 9)
			{
				board[otherRow][otherCol] = known + 1;
				if(isValid(board, otherRow, otherCol))
				{
					board[otherRow][otherCol] = NO_VALUE;
					return true;
				}
			}
			board[otherRow][otherCol] = NO_VALUE;
			return false;
		}
		return true;
	}
	
	private boolean arrowConstraint(int[][] board, List<Cell> cells, Cell sumCell)
	{
		int realSum = 0;
		int zeroes = 0;
		for(Cell cell : cells)
		{
			if(board[cell.getRow()][cell.getColumn()] == NO_VALUE)
				zeroes++;
			realSum += board[cell.getRow()][cell.getColumn()];
		}
		int sum = board[sumCell.getRow()][sumCell.getColumn()]; 
		if(sum != NO_VALUE)
		{
			//All other zeroes gridded in as "1"
			if(realSum + zeroes * 1 > sum)
				return false;
			//Too low
			if(realSum + zeroes * 9 < sum)
				return false;
		}
		if(realSum + zeroes * 1 > 9)
			return false;
		return true;
	}
	
	private boolean greaterThanConstraint(int[][] board, Cell cell1, Cell cell2)
	{
		int value1 = board[cell1.getRow()][cell1.getColumn()];
		int value2 = board[cell2.getRow()][cell2.getColumn()];
		if(value1 == 1)
			return false;
		if(value2 == 9)
			return false;
		if(value1 != NO_VALUE && value2 != NO_VALUE && value1 <= value2)
			return false;
		return true;
	}
	
	private boolean killerConstraint(int[][] board, List<Cell> cells, int sum)
	{
		int realSum = 0;
		int zeroes = 0;
		boolean[] constraint = new boolean[BOARD_SIZE];
		for(Cell cell : cells)
		{
			if(!checkConstraint(board, cell.getRow(), constraint, cell.getColumn()))
				return false;
			if(board[cell.getRow()][cell.getColumn()] == NO_VALUE)
				zeroes++;
			realSum += board[cell.getRow()][cell.getColumn()];
		}
		//All other zeroes gridded in as "1"
		if(realSum + zeroes * 1 > sum)
			return false;
		//Too low
		if(realSum + zeroes * 9 < sum)
			return false;
		return true;
	}
	
	private boolean regionConstraint(int[][] board, List<Cell> cells)
	{
		boolean[] constraint = new boolean[BOARD_SIZE];
		
		for(Cell cell : cells)
			if(!checkConstraint(board, cell.getRow(), constraint, cell.getColumn()))
				return false;
		return true;
	}
	
	private boolean nonSumConstraint(int[][] board, int row, int column, boolean sum5)
	{
		int value = board[row][column];
		if(row < 8 && board[row + 1][column] != NO_VALUE && board[row + 1][column] + value == (sum5 ? 5 : 10))
		{
			boolean canSum = false;
			if((sum5 ? constraints.getSum5() : constraints.getSum10()) != null)
				for(Entry<Cell, Cell> entry : (sum5 ? constraints.getSum5() : constraints.getSum10()))
					if((entry.getKey().equals(new Cell(row, column)) || entry.getValue().equals(new Cell(row + 1, column)))
						|| (entry.getKey().equals(new Cell(row + 1, column)) || entry.getValue().equals(new Cell(row, column))))
					{
						canSum = true;
						break;
					}
			if(!canSum)
				return false;
		}
		if(row > 0 && board[row - 1][column] != NO_VALUE && board[row - 1][column] + value == (sum5 ? 5 : 10))
		{
			boolean canSum = false;
			if((sum5 ? constraints.getSum5() : constraints.getSum10()) != null)
				for(Entry<Cell, Cell> entry : (sum5 ? constraints.getSum5() : constraints.getSum10()))
					if((entry.getKey().equals(new Cell(row, column)) || entry.getValue().equals(new Cell(row - 1, column)))
						|| (entry.getKey().equals(new Cell(row - 1, column)) || entry.getValue().equals(new Cell(row, column))))
					{
						canSum = true;
						break;
					}
			if(!canSum)
				return false;
		}
		if(column < 8 && board[row][column + 1] != NO_VALUE && board[row][column + 1] + value == (sum5 ? 5 : 10))
		{
			boolean canSum = false;
			if((sum5 ? constraints.getSum5() : constraints.getSum10()) != null)
				for(Entry<Cell, Cell> entry : (sum5 ? constraints.getSum5() : constraints.getSum10()))
					if((entry.getKey().equals(new Cell(row, column)) || entry.getValue().equals(new Cell(row, column + 1)))
						|| (entry.getKey().equals(new Cell(row, column + 1)) || entry.getValue().equals(new Cell(row, column))))
					{
						canSum = true;
						break;
					}
			if(!canSum)
				return false;
		}
		if(column > 0 && board[row][column - 1] != NO_VALUE && board[row][column - 1] + value == (sum5 ? 5 : 10))
		{
			boolean canSum = false;
			if((sum5 ? constraints.getSum5() : constraints.getSum10()) != null)
				for(Entry<Cell, Cell> entry : (sum5 ? constraints.getSum5() : constraints.getSum10()))
					if((entry.getKey().equals(new Cell(row, column)) || entry.getValue().equals(new Cell(row, column - 1)))
						|| (entry.getKey().equals(new Cell(row, column - 1)) || entry.getValue().equals(new Cell(row, column))))
					{
						canSum = true;
						break;
					}
			if(!canSum)
				return false;
		}
		return true;
	}
	
	private boolean nonConsecutiveConstraint(int[][] board, int row, int column)
	{
		int value = board[row][column];
		if(row < 8 && board[row + 1][column] != NO_VALUE && Math.abs(board[row + 1][column] - value) == 1)
		{
			boolean canCons = false;
			if(constraints.getConsecutive() != null)
				for(Entry<Cell, Cell> entry : constraints.getConsecutive())
					if((entry.getKey().equals(new Cell(row, column)) || entry.getValue().equals(new Cell(row + 1, column)))
						|| (entry.getKey().equals(new Cell(row + 1, column)) || entry.getValue().equals(new Cell(row, column))))
					{
						canCons = true;
						break;
					}
			if(!canCons)
				return false;
		}
		if(row > 0 && board[row - 1][column] != NO_VALUE && Math.abs(board[row - 1][column] - value) == 1)
		{
			boolean canCons = false;
			if(constraints.getConsecutive() != null)
				for(Entry<Cell, Cell> entry : constraints.getConsecutive())
					if((entry.getKey().equals(new Cell(row, column)) || entry.getValue().equals(new Cell(row - 1, column)))
						|| (entry.getKey().equals(new Cell(row - 1, column)) || entry.getValue().equals(new Cell(row, column))))
					{
						canCons = true;
						break;
					}
			if(!canCons)
				return false;
		}
		if(column < 8 && board[row][column + 1] != NO_VALUE && Math.abs(board[row][column + 1] - value) == 1)
		{
			boolean canCons = false;
			if(constraints.getConsecutive() != null)
				for(Entry<Cell, Cell> entry : constraints.getConsecutive())
					if((entry.getKey().equals(new Cell(row, column)) || entry.getValue().equals(new Cell(row, column + 1)))
						|| (entry.getKey().equals(new Cell(row, column + 1)) || entry.getValue().equals(new Cell(row, column))))
					{
						canCons = true;
						break;
					}
			if(!canCons)
				return false;
		}
		if(column > 0 && board[row][column - 1] != NO_VALUE && Math.abs(board[row][column - 1] - value) == 1)
		{
			boolean canCons = false;
			if(constraints.getConsecutive() != null)
				for(Entry<Cell, Cell> entry : constraints.getConsecutive())
					if((entry.getKey().equals(new Cell(row, column)) || entry.getValue().equals(new Cell(row, column - 1)))
						|| (entry.getKey().equals(new Cell(row, column - 1)) || entry.getValue().equals(new Cell(row, column))))
					{
						canCons = true;
						break;
					}
			if(!canCons)
				return false;
		}
		return true;
	}
	
	private boolean centerDotConstraint(int[][] board)
	{
		boolean[] constraint = new boolean[BOARD_SIZE];
		
		for(int r = 1; r < 9; r += 3)
			for(int c = 1; c < 9; c += 3)
				if(!checkConstraint(board, r, constraint, c))
					return false;
		return true;
	}
	
	private boolean diagonalConstraint(int[][] board, boolean leftToRight)
	{
		boolean[] constraint = new boolean[BOARD_SIZE];
		
		if(leftToRight)
		{
			for(int d = 0; d < BOARD_SIZE; d++)
				if(!checkConstraint(board, d, constraint, d))
					return false;
		}else
		{
			for(int d = 0; d < BOARD_SIZE; d++)
				if(!checkConstraint(board, d, constraint, 8 - d))
					return false;
		}
		return true;
	}
	
	private boolean subsectionConstraint(int[][] board, int row, int column)
	{
		boolean[] constraint = new boolean[BOARD_SIZE];
		int subsectionRowStart = row / SUBSECTION_SIZE * SUBSECTION_SIZE;
		int subsectionRowEnd = subsectionRowStart + SUBSECTION_SIZE;
		
		int subsectionColumnStart = column / SUBSECTION_SIZE * SUBSECTION_SIZE;
		int subsectionColumnEnd = subsectionColumnStart + SUBSECTION_SIZE;
		
		for(int r = subsectionRowStart; r < subsectionRowEnd; r++)
			for(int c = subsectionColumnStart; c < subsectionColumnEnd; c++)
				if(!checkConstraint(board, r, constraint, c))
					return false;
		return true;
	}
	
	private boolean columnConstraint(int[][] board, int column)
	{
		boolean[] constraint = new boolean[BOARD_SIZE];
		return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
			.allMatch(row -> checkConstraint(board, row, constraint, column));
	}
	
	private boolean rowConstraint(int[][] board, int row)
	{
		boolean[] constraint = new boolean[BOARD_SIZE];
		return IntStream.range(BOARD_START_INDEX, BOARD_SIZE).allMatch(
			column -> checkConstraint(board, row, constraint, column));
	}
	
	private boolean checkConstraint(int[][] board, int row,
		boolean[] constraint, int column)
	{
		if(board[row][column] != NO_VALUE)
			if(!constraint[board[row][column] - 1])
				constraint[board[row][column] - 1] = true;
			else
				return false;
		return true;
	}
}

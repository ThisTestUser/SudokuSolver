package com.thistestuser.solver;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Adapted from https://github.com/eugenp/tutorials/blob/master/algorithms-miscellaneous-2/
 * src/main/java/com/baeldung/algorithms/sudoku/BacktrackingAlgorithm.java
 */
public class SudokuSolver
{
	private static final int NO_VALUE = 0;
	private final SudokuConstraints constraints;
	
	private final Map<Cell, Integer> gtUpChainCache = new HashMap<>();
	private final Map<Cell, Integer> gtDownChainCache = new HashMap<>();
	private final Map<Cell, Integer> gtUpGroupCache = new HashMap<>();
	private final Map<Cell, Integer> gtDownGroupCache = new HashMap<>();
	
	public SudokuSolver(SudokuConstraints constraints)
	{
		this.constraints = constraints;
	}
	
	public boolean solve(int[][] board)
	{
		for(int row = 0; row < constraints.getBoardSize(); row++)
			for(int column = 0; column < constraints.getBoardSize(); column++)
				if(board[row][column] == NO_VALUE)
				{
					for(int k = 1; k <= constraints.getBoardSize(); k++)
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
		if(constraints.isDiagonal() && (row == column || row + column == constraints.getBoardSize() - 1)
			&& !diagonalConstraint(board, row == column))
			return false;
		if(constraints.isCenterDot() && row % constraints.getSubsecHeight() == (constraints.getSubsecHeight() / 2)
			&& column % constraints.getSubsecWidth() == (constraints.getSubsecWidth() / 2) && !centerDotConstraint(board))
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
		if(constraints.getConsecutives() != null)
			for(Entry<Cell, Cell> entry : constraints.getConsecutives())
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
			if(known < constraints.getBoardSize())
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
		int curSum = 0;
		int unknowns = 0;
		for(Cell cell : cells)
		{
			if(board[cell.getRow()][cell.getColumn()] == NO_VALUE)
				unknowns++;
			curSum += board[cell.getRow()][cell.getColumn()];
		}
		int sum = board[sumCell.getRow()][sumCell.getColumn()]; 
		if(sum != NO_VALUE)
		{
			//All other zeroes gridded in as "1"
			if(curSum + unknowns * 1 > sum)
				return false;

			//Sum is too low
			if(unknowns == 0 && curSum < sum)
				return false;
		}
		if(curSum + unknowns * 1 > constraints.getBoardSize())
			return false;
		return true;
	}
	
	private boolean greaterThanConstraint(int[][] board, Cell cell1, Cell cell2)
	{
		int value1 = board[cell1.getRow()][cell1.getColumn()];
		int value2 = board[cell2.getRow()][cell2.getColumn()];
		if(value1 == 1)
			return false;
		if(value2 == constraints.getBoardSize())
			return false;
		if(value1 != NO_VALUE && value2 != NO_VALUE && value1 <= value2)
			return false;
		
		if(value1 != NO_VALUE)
		{
			//Find longest downward chain starting from cell2
			Set<Cell> passed = new HashSet<>();
			int chain = gtDownChainCache.getOrDefault(cell2, 0);
			if(chain == 0)
			{
				Queue<Cell> queue = new LinkedList<>();
				queue.add(cell2);
				while(!queue.isEmpty())
				{
				    int size = queue.size();
				    while(size-- != 0)
				    {
				        Cell next = queue.poll();
				        if(passed.contains(next))
				        	throw new IllegalArgumentException("Cyclic relation found in greater-than constraint!");
						passed.add(next);
				        for(Entry<Cell, Cell> entry : constraints.getGreaterThans())
				        	if(entry.getKey().equals(next))
				        		queue.add(entry.getValue());
				    }    
				    chain++;
				}
				gtDownChainCache.put(cell2, chain);
			}
			if(value1 - chain < 0)
				return false;
			
			//Get the largest group which must have unique numbers
			int largest = gtDownGroupCache.getOrDefault(cell2, 0);
			if(largest == 0)
			{
				largest = largestGroup(passed);
				gtDownGroupCache.put(cell2, largest);
			}
			if(value1 - largest < 0)
				return false;
		}
		if(value2 != NO_VALUE)
		{
			//Find longest upward chain starting from cell1
			Set<Cell> passed = new HashSet<>();
			int chain = gtUpChainCache.getOrDefault(cell2, 0);
			if(chain == 0)
			{
				Queue<Cell> queue = new LinkedList<>();
				queue.add(cell1);
				while(!queue.isEmpty())
				{
				    int size = queue.size();
				    while(size-- != 0)
				    {
				        Cell next = queue.poll();
				        if(passed.contains(next))
				        	throw new IllegalArgumentException("Cyclic relation found in greater-than constraint!");
						passed.add(next);
				        for(Entry<Cell, Cell> entry : constraints.getGreaterThans())
				        {
				        	if(entry.getValue().equals(next))
				        		queue.add(entry.getKey());
				        }
				    }    
				    chain++;
				}
				gtUpChainCache.put(cell2, chain);
			}
			if(value2 + chain > constraints.getBoardSize())
				return false;
			
			//Get the largest group which must have unique numbers
			int largest = gtUpGroupCache.getOrDefault(cell2, 0);
			if(largest == 0)
			{
				largest = largestGroup(passed);
				gtUpGroupCache.put(cell2, largest);
			}
			if(largest + value2 > constraints.getBoardSize())
				return false;
		}
		return true;
	}
	
	/**
	 * Find the largest group (by row, column, or subsection) that the cells are part of
	 */
	private int largestGroup(Set<Cell> cells)
	{
		Map<Integer, AtomicInteger> rows = new HashMap<>();
		Map<Integer, AtomicInteger> columns = new HashMap<>();
		Map<Integer, AtomicInteger> subsections = new HashMap<>();
		Map<List<Cell>, AtomicInteger> customRegions = new HashMap<>();
		
		for(Cell cell : cells)
		{
			rows.putIfAbsent(cell.getRow(), new AtomicInteger());
			rows.get(cell.getRow()).incrementAndGet();
			columns.putIfAbsent(cell.getColumn(), new AtomicInteger());
			columns.get(cell.getColumn()).incrementAndGet();
			if(constraints.isSubsections())
			{
				int index = cell.getRow() / constraints.getSubsecHeight() * constraints.getSubsecHeight()
					+ cell.getColumn() / constraints.getSubsecWidth();
				subsections.putIfAbsent(index, new AtomicInteger());
				subsections.get(index).incrementAndGet();
			}
			if(constraints.getRegions() != null)
				for(List<Cell> list : constraints.getRegions())
					if(list.contains(cell))
					{
						customRegions.putIfAbsent(list, new AtomicInteger());
						customRegions.get(list).incrementAndGet();
					}
		}
		TreeSet<Integer> results = new TreeSet<>();
		results.add(0);
		rows.values().forEach(a -> results.add(a.get()));
		columns.values().forEach(a -> results.add(a.get()));
		subsections.values().forEach(a -> results.add(a.get()));
		customRegions.values().forEach(a -> results.add(a.get()));
		return results.last();
	}
	
	private boolean killerConstraint(int[][] board, List<Cell> cells, int sum)
	{
		int curSum = 0;
		int unknowns = 0;
		boolean[] passed = new boolean[constraints.getBoardSize()];
		for(Cell cell : cells)
		{
			//Convention: No duplicate numbers in cages
			if(!duplicatesCheck(board, cell.getRow(), passed, cell.getColumn()))
				return false;
			
			if(board[cell.getRow()][cell.getColumn()] == NO_VALUE)
				unknowns++;
			curSum += board[cell.getRow()][cell.getColumn()];
		}
		int unknownsFilled = 0;
		int theoreticalSum = 0;
		for(int i = 0; i < passed.length; i++)
		{
			if(unknownsFilled == unknowns)
				break;
			
			if(!passed[i])
			{
				theoreticalSum += i + 1;
				unknownsFilled++;
			}
		}
		//Too high
		if(curSum + theoreticalSum > sum)
			return false;
	
		unknownsFilled = 0;
		theoreticalSum = 0;
		for(int i = passed.length - 1; i >= 0; i--)
		{
			if(unknownsFilled == unknowns)
				break;
			
			if(!passed[i])
			{
				theoreticalSum += i + 1;
				unknownsFilled++;
			}
		}
		//Too low
		if(curSum + theoreticalSum < sum)
			return false;
		return true;
	}
	
	private boolean regionConstraint(int[][] board, List<Cell> cells)
	{
		boolean[] passed = new boolean[constraints.getBoardSize()];
		for(Cell cell : cells)
			if(!duplicatesCheck(board, cell.getRow(), passed, cell.getColumn()))
				return false;
		return true;
	}
	
	private boolean nonSumConstraint(int[][] board, int row, int column, boolean sum5)
	{
		int value = board[row][column];
		if(row < constraints.getBoardSize() - 1 && board[row + 1][column] != NO_VALUE && board[row + 1][column] + value == (sum5 ? 5 : 10))
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
		if(column < constraints.getBoardSize() - 1 && board[row][column + 1] != NO_VALUE && board[row][column + 1] + value == (sum5 ? 5 : 10))
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
		if(row < constraints.getBoardSize() - 1 && board[row + 1][column] != NO_VALUE && Math.abs(board[row + 1][column] - value) == 1)
		{
			boolean canCons = false;
			if(constraints.getConsecutives() != null)
				for(Entry<Cell, Cell> entry : constraints.getConsecutives())
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
			if(constraints.getConsecutives() != null)
				for(Entry<Cell, Cell> entry : constraints.getConsecutives())
					if((entry.getKey().equals(new Cell(row, column)) || entry.getValue().equals(new Cell(row - 1, column)))
						|| (entry.getKey().equals(new Cell(row - 1, column)) || entry.getValue().equals(new Cell(row, column))))
					{
						canCons = true;
						break;
					}
			if(!canCons)
				return false;
		}
		if(column < constraints.getBoardSize() - 1 && board[row][column + 1] != NO_VALUE && Math.abs(board[row][column + 1] - value) == 1)
		{
			boolean canCons = false;
			if(constraints.getConsecutives() != null)
				for(Entry<Cell, Cell> entry : constraints.getConsecutives())
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
			if(constraints.getConsecutives() != null)
				for(Entry<Cell, Cell> entry : constraints.getConsecutives())
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
		boolean[] passed = new boolean[constraints.getBoardSize()];
		
		for(int r = constraints.getSubsecHeight() / 2; r < constraints.getBoardSize(); r += constraints.getSubsecHeight())
			for(int c = constraints.getSubsecWidth() / 2; c < constraints.getBoardSize(); c += constraints.getSubsecWidth())
				if(!duplicatesCheck(board, r, passed, c))
					return false;
		return true;
	}
	
	private boolean diagonalConstraint(int[][] board, boolean leftToRight)
	{
		boolean[] passed = new boolean[constraints.getBoardSize()];
		
		if(leftToRight)
		{
			for(int d = 0; d < constraints.getBoardSize(); d++)
				if(!duplicatesCheck(board, d, passed, d))
					return false;
		}else
		{
			for(int d = 0; d < constraints.getBoardSize(); d++)
				if(!duplicatesCheck(board, d, passed, constraints.getBoardSize() - 1 - d))
					return false;
		}
		return true;
	}
	
	private boolean subsectionConstraint(int[][] board, int row, int column)
	{
		boolean[] passed = new boolean[constraints.getBoardSize()];
		int subsectionRowStart = row / constraints.getSubsecHeight() * constraints.getSubsecHeight();
		int subsectionRowEnd = subsectionRowStart + constraints.getSubsecHeight();
		
		int subsectionColumnStart = column / constraints.getSubsecWidth() * constraints.getSubsecWidth();
		int subsectionColumnEnd = subsectionColumnStart + constraints.getSubsecWidth();
		
		for(int r = subsectionRowStart; r < subsectionRowEnd; r++)
			for(int c = subsectionColumnStart; c < subsectionColumnEnd; c++)
				if(!duplicatesCheck(board, r, passed, c))
					return false;
		return true;
	}
	
	private boolean columnConstraint(int[][] board, int column)
	{
		boolean[] passed = new boolean[constraints.getBoardSize()];
		return IntStream.range(0, constraints.getBoardSize()).allMatch(
			row -> duplicatesCheck(board, row, passed, column));
	}
	
	private boolean rowConstraint(int[][] board, int row)
	{
		boolean[] passed = new boolean[constraints.getBoardSize()];
		return IntStream.range(0, constraints.getBoardSize()).allMatch(
			column -> duplicatesCheck(board, row, passed, column));
	}
	
	private boolean duplicatesCheck(int[][] board, int row,
		boolean[] passed, int column)
	{
		if(board[row][column] != NO_VALUE)
			if(!passed[board[row][column] - 1])
				passed[board[row][column] - 1] = true;
			else
				return false;
		return true;
	}
}

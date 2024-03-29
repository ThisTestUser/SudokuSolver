package com.thistestuser.solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Special constraints for the sudoku. Unique numbers means that in a region,
 * there can't be more than one of each digit.
 */
public class SudokuConstraints
{
	/**
	 * Should the grid be divided into subsections with unique numbers?
	 */
	private boolean subsections = true;

	/**
	 * Should diagonals of the sudoku have unique numbers?
	 */
	private boolean diagonal = false;

	/**
	 * Should the center of each of the section of the square have unique numbers?
	 * The dimensions of the subsection must be odd for this to work.
	 */
	private boolean centerDot = false;

	/**
	 * Is the sudoku non-consecutive (numbers next to each other can't be
	 * consecutive) unless marked as consecutive in {@link #consecutives}?
	 */
	private boolean nonConsecutive = false;

	/**
	 * Can numbers next to each other in a sudoku add up to 5 (if true, only cells marked in {@link #sum5}
	 * can add up to 5)?
	 */
	private boolean nonSum5 = false;

	/**
	 * Can numbers next to each other in a sudoku add up to 10 (if true, only cells marked in {@link #sum10}
	 * can add up to 10)?
	 */
	private boolean nonSum10 = false;

	/**
	 * Specific regions of the sudoku that must have unique numbers.
	 */
	private List<List<Cell>> regions = new ArrayList<>();

	/**
	 * Specific regions of the sudoku that must sum up to the integer provided and
	 * have unique numbers.
	 */
	private Map<List<Cell>, Integer> killerSums = new HashMap<>();

	/**
	 * Pairs of two cells where the first cell has a greater value than the second cell.
	 */
	private List<Entry<Cell, Cell>> greaterThans = new ArrayList<>();

	/**
	 * Specific regions of the sudoku that must sum up to a specific cell.
	 * Regions can have repeating numbers.
	 */
	private Map<Cell, List<Cell>> arrows = new HashMap<>();

	/**
	 * Pairs of two cells that contain consecutive numbers.
	 */
	private List<Entry<Cell, Cell>> consecutives = new ArrayList<>();

	/**
	 * These cells must be even.
	 */
	private List<Cell> evenCells = new ArrayList<>();

	/**
	 * These cells must be odd.
	 */
	private List<Cell> oddCells = new ArrayList<>();

	/**
	 * Pairs of two cells that sum to 5.
	 */
	private List<Entry<Cell, Cell>> sum5 = new ArrayList<>();

	/**
	 * Pairs of two cells that sum to 10.
	 */
	private List<Entry<Cell, Cell>> sum10 = new ArrayList<>();
	
	private final int boardSize;
	private final int subsecWidth;
	private final int subsecHeight;
	
	public SudokuConstraints(int boardSize)
	{
		this(boardSize, boardSize, boardSize);
	}
	
	public SudokuConstraints(int boardSize, int subsecWidth, int subsecHeight)
	{
		if(boardSize % subsecWidth != 0 || boardSize % subsecHeight != 0)
			throw new IllegalArgumentException("The board must be exactly divisble into subsections");
		this.boardSize = boardSize;
		this.subsecWidth = subsecWidth;
		this.subsecHeight = subsecHeight;
	}

	public boolean isSubsections()
	{
		return subsections;
	}
	
	public void setSubsections(boolean subsections)
	{
		this.subsections = subsections;
	}
	
	public boolean isDiagonal()
	{
		return diagonal;
	}
	
	public void setDiagonal(boolean diagonal)
	{
		this.diagonal = diagonal;
	}
	
	public boolean isCenterDot()
	{
		return centerDot;
	}
	
	public void setCenterDot(boolean centerDot)
	{
		if(subsecWidth % 2 == 0 || subsecHeight % 2 == 0)
			centerDot = false;
		this.centerDot = centerDot;
	}
	
	public boolean isNonConsecutive()
	{
		return nonConsecutive;
	}
	
	public void setNonConsecutive(boolean nonConsecutive)
	{
		this.nonConsecutive = nonConsecutive;
	}

	public boolean isNonSum5()
	{
		return nonSum5;
	}

	public void setNonSum5(boolean nonSum5)
	{
		this.nonSum5 = nonSum5;
	}

	public boolean isNonSum10()
	{
		return nonSum10;
	}

	public void setNonSum10(boolean nonSum10)
	{
		this.nonSum10 = nonSum10;
	}

	public List<List<Cell>> getRegions()
	{
		return regions;
	}
	
	public void setRegions(List<List<Cell>> regions)
	{
		this.regions = regions;
	}
	
	public Map<List<Cell>, Integer> getKillerSums()
	{
		return killerSums;
	}
	
	public void setKillerSums(Map<List<Cell>, Integer> killerSums)
	{
		this.killerSums = killerSums;
	}
	
	public List<Entry<Cell, Cell>> getGreaterThans()
	{
		return greaterThans;
	}
	
	public void setGreaterThans(List<Entry<Cell, Cell>> greaterThans)
	{
		this.greaterThans = greaterThans;
	}
	
	public Map<Cell, List<Cell>> getArrows()
	{
		return arrows;
	}
	
	public void setArrows(Map<Cell, List<Cell>> arrows)
	{
		this.arrows = arrows;
	}
	
	public List<Entry<Cell, Cell>> getConsecutives()
	{
		return consecutives;
	}
	
	public void setConsecutives(List<Entry<Cell, Cell>> consecutives)
	{
		this.consecutives = consecutives;
	}
	
	public List<Cell> getEvenCells()
	{
		return evenCells;
	}
	
	public void setEvenCells(List<Cell> evenCells)
	{
		this.evenCells = evenCells;
	}
	
	public List<Cell> getOddCells()
	{
		return oddCells;
	}
	
	public void setOddCells(List<Cell> oddCells)
	{
		this.oddCells = oddCells;
	}
	
	public List<Entry<Cell, Cell>> getSum5()
	{
		return sum5;
	}
	
	public void setSum5(List<Entry<Cell, Cell>> sum5)
	{
		this.sum5 = sum5;
	}
	
	public List<Entry<Cell, Cell>> getSum10()
	{
		return sum10;
	}
	
	public void setSum10(List<Entry<Cell, Cell>> sum10)
	{
		this.sum10 = sum10;
	}
	
	public int getBoardSize()
	{
		return boardSize;
	}
	
	public int getSubsecWidth()
	{
		return subsecWidth;
	}

	public int getSubsecHeight()
	{
		return subsecHeight;
	}
}

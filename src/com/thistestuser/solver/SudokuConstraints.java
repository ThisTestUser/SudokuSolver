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
	 * Should the grid be divided into 3x3 sections (default sudokus have this)
	 */
	private boolean subsections = true;

	/**
	 * Should diagonals of the sudoku have unique numbers
	 */
	private boolean diagonal = false;

	/**
	 * Should the center of each of the 3x3 section of the square have unique
	 * numbers
	 */
	private boolean centerDot = false;

	/**
	 * Is the sudoku non-consecutive (numbers next to each other can't be
	 * consecutive) unless marked as consecutive in {@link #consecutive}
	 */
	private boolean nonConsecutive = false;

	/**
	 * Can numbers next to each other in a sudoku add up to 5 (if true, only cells marked in {@link #sum5}
	 * can add up to 5)
	 */
	private boolean nonSum5 = false;

	/**
	 * Can numbers next to each other  in a sudoku add up to 10 (if true, only cells marked in {@link #sum10}
	 * can add up to 10)
	 */
	private boolean nonSum10 = false;

	/**
	 * Special regions of the sudoku that must have unique numbers
	 */
	private List<List<Cell>> regions = new ArrayList<>();

	/**
	 * Specific regions of the sudoku that must sum up the integer provided and
	 * have unique numbers
	 */
	private Map<List<Cell>, Integer> killerSums = new HashMap<>();

	/**
	 * Two cells where the first cell has a greater value than the second cell
	 */
	private List<Entry<Cell, Cell>> greaterThans = new ArrayList<>();

	/**
	 * The sum of numbers in the region must equal the number in the cell.
	 * Region can have repeating numbers
	 */
	private Map<Cell, List<Cell>> arrows = new HashMap<>();

	/**
	 * The two cells must have consecutive numbers
	 */
	private List<Entry<Cell, Cell>> consecutive = new ArrayList<>();

	/**
	 * The cells must be even
	 */
	private List<Cell> evenCells = new ArrayList<>();

	/**
	 * The cells must be odd
	 */
	private List<Cell> oddCells = new ArrayList<>();

	/**
	 * The cells must have a sum of 5
	 */
	private List<Entry<Cell, Cell>> sum5 = new ArrayList<>();

	/**
	 * The cells must have a sum of 10
	 */
	private List<Entry<Cell, Cell>> sum10 = new ArrayList<>();

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
	
	public List<Entry<Cell, Cell>> getConsecutive()
	{
		return consecutive;
	}
	
	public void setConsecutive(List<Entry<Cell, Cell>> consecutive)
	{
		this.consecutive = consecutive;
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
}

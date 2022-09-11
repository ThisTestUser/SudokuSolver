package com.thistestuser.solver;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main
{
	public static final Map<String, SudokuProblem> SUDOKU_PROBLEMS = new HashMap<>();

	static
	{
		//Initialize template problems
		int[][] board;
		SudokuConstraints constraints;
		
		board = new int[][]{
			{0, 0, 0, 0},
			{2, 0, 0, 0},
			{0, 0, 3, 0},
			{0, 4, 0, 0},
		};
		constraints = new SudokuConstraints(4, 2, 2);
		constraints.setDiagonal(true);
		SUDOKU_PROBLEMS.put("4x4_diag", new SudokuProblem(board, constraints));
		
		board = new int[][]{
			{6, 4, 3, 0, 0, 0},
			{0, 0, 4, 0, 0, 1},
			{1, 0, 6, 0, 0, 0},
			{0, 0, 1, 0, 4, 0},
			{0, 0, 0, 0, 0, 3},
			{0, 5, 0, 0, 0, 0},
		};
		constraints = new SudokuConstraints(6, 2, 3);
		SUDOKU_PROBLEMS.put("6x6", new SudokuProblem(board, constraints));
		
		board = new int[][]{
			{0, 0, 0, 0, 0, 0, 2, 3, 0},
			{2, 0, 0, 0, 0, 7, 0, 0, 0},
			{0, 0, 0, 0, 4, 0, 0, 7, 9},
			{0, 0, 9, 7, 8, 0, 0, 6, 2},
			{0, 0, 0, 2, 0, 0, 0, 0, 3},
			{0, 0, 4, 0, 0, 0, 5, 0, 0},
			{0, 0, 0, 1, 0, 6, 0, 0, 7},
			{0, 1, 0, 0, 7, 0, 0, 0, 0},
			{9, 8, 0, 0, 0, 0, 0, 0, 4}
		};
		constraints = new SudokuConstraints(9, 3, 3);
		SUDOKU_PROBLEMS.put("9x9", new SudokuProblem(board, constraints));
		
		board = new int[][]{
			{8, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 3, 6, 0, 0, 0, 0, 0},
			{0, 7, 0, 0, 9, 0, 2, 0, 0},
			{0, 5, 0, 0, 0, 7, 0, 0, 0},
			{0, 0, 0, 0, 4, 5, 7, 0, 0},
			{0, 0, 0, 1, 0, 0, 0, 3, 0},
			{0, 0, 1, 0, 0, 0, 0, 6, 8},
			{0, 0, 8, 5, 0, 0, 0, 1, 0},
			{0, 9, 0, 0, 0, 0, 4, 0, 0}
		};
		constraints = new SudokuConstraints(9, 3, 3);
		SUDOKU_PROBLEMS.put("9x9_hardest", new SudokuProblem(board, constraints));
		
		board = new int[][]{
	    	{0, 0, 0, 0, 0, 3, 0, 0, 6},
	    	{0, 0, 0, 0, 0, 8, 0, 2, 7},
	    	{0, 7, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 9, 0, 0, 0, 0, 0, 1},
	    	{1, 0, 0, 2, 5, 0, 0, 0, 4},
	    	{0, 8, 0, 0, 0, 0, 9, 0, 0},
	    	{0, 1, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 6, 7, 5, 2, 0, 0}
		};
		constraints = new SudokuConstraints(9, 3, 3);
		constraints.setDiagonal(true);
		SUDOKU_PROBLEMS.put("9x9_diag", new SudokuProblem(board, constraints));
		
		board = new int[][]{
	    	{6, 0, 0, 0, 0, 0, 0, 3, 0},
	    	{0, 0, 8, 0, 1, 2, 0, 4, 0},
	    	{9, 0, 1, 0, 0, 0, 0, 0, 3},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 9, 0, 0, 0, 6, 0},
	    	{0, 0, 0, 0, 0, 0, 7, 0, 0},
	    	{0, 0, 7, 2, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 5, 2, 0, 0, 0, 0},
	    	{0, 9, 0, 0, 7, 0, 0, 0, 0}
		};
		constraints = new SudokuConstraints(9);
		constraints.setSubsections(false);
		List<List<Cell>> regions = new ArrayList<>();
		regions.add(Arrays.asList(new Cell(0, 0), new Cell(0, 1), new Cell(0, 2), new Cell(0, 3), new Cell(0, 4),
			new Cell(1, 1), new Cell(1, 4), new Cell(1, 5), new Cell(2, 5)));
		regions.add(Arrays.asList(new Cell(0, 5), new Cell(0, 6), new Cell(0, 7), new Cell(0, 8), new Cell(1, 6),
			new Cell(1, 7), new Cell(2, 6), new Cell(3, 5), new Cell(3, 6)));
		regions.add(Arrays.asList(new Cell(1, 0), new Cell(1, 2), new Cell(2, 0), new Cell(2, 1), new Cell(2, 2),
			new Cell(3, 0), new Cell(3, 1), new Cell(4, 0), new Cell(4, 1)));
		regions.add(Arrays.asList(new Cell(1, 3), new Cell(2, 3), new Cell(2, 4), new Cell(3, 2), new Cell(3, 3),
			new Cell(4, 2), new Cell(4, 3), new Cell(5, 1), new Cell(5, 2)));
		regions.add(Arrays.asList(new Cell(3, 4), new Cell(4, 4), new Cell(4, 5), new Cell(5, 3), new Cell(5, 4),
			new Cell(5, 5), new Cell(5, 6), new Cell(6, 3), new Cell(6, 6)));
		regions.add(Arrays.asList(new Cell(1, 8), new Cell(2, 7), new Cell(2, 8), new Cell(3, 7), new Cell(3, 8),
			new Cell(4, 6), new Cell(4, 7), new Cell(4, 8), new Cell(5, 7)));
		regions.add(Arrays.asList(new Cell(5, 0), new Cell(6, 0), new Cell(6, 1), new Cell(6, 2), new Cell(6, 4),
			new Cell(6, 5), new Cell(7, 2), new Cell(7, 3), new Cell(7, 4)));
		regions.add(Arrays.asList(new Cell(7, 0), new Cell(7, 1), new Cell(8, 0), new Cell(8, 1), new Cell(8, 2),
			new Cell(8, 3), new Cell(8, 4), new Cell(8, 5), new Cell(8, 6)));
		regions.add(Arrays.asList(new Cell(5, 8), new Cell(6, 7), new Cell(6, 8), new Cell(7, 5), new Cell(7, 6),
			new Cell(7, 7), new Cell(7, 8), new Cell(8, 7), new Cell(8, 8)));
		constraints.setRegions(regions);
		SUDOKU_PROBLEMS.put("9x9_jigsaw", new SudokuProblem(board, constraints));
		
		board = new int[][]{
	    	{0, 0, 0, 0, 0, 0, 7, 0, 0},
	    	{0, 3, 0, 0, 0, 0, 0, 5, 0},
	    	{7, 0, 0, 1, 0, 0, 4, 0, 0},
	    	{2, 9, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 8, 0, 0, 0, 0},
	    	{0, 8, 5, 0, 0, 6, 0, 0, 0},
	    	{0, 0, 7, 0, 0, 1, 0, 0, 0},
	    	{6, 0, 0, 4, 0, 5, 0, 0, 0},
	    	{0, 0, 3, 0, 0, 0, 0, 6, 0}
		};
		constraints = new SudokuConstraints(9, 3, 3);
		regions = new ArrayList<>();
		regions.add(Arrays.asList(new Cell(1, 1), new Cell(1, 2), new Cell(1, 3), new Cell(2, 1), new Cell(2, 2),
			new Cell(2, 3), new Cell(3, 1), new Cell(3, 2), new Cell(3, 3)));
		regions.add(Arrays.asList(new Cell(5, 1), new Cell(5, 2), new Cell(5, 3), new Cell(6, 1), new Cell(6, 2),
			new Cell(6, 3), new Cell(7, 1), new Cell(7, 2), new Cell(7, 3)));
		regions.add(Arrays.asList(new Cell(1, 5), new Cell(1, 6), new Cell(1, 7), new Cell(2, 5), new Cell(2, 6),
			new Cell(2, 7), new Cell(3, 5), new Cell(3, 6), new Cell(3, 7)));
		regions.add(Arrays.asList(new Cell(5, 5), new Cell(5, 6), new Cell(5, 7), new Cell(6, 5), new Cell(6, 6),
			new Cell(6, 7), new Cell(7, 5), new Cell(7, 6), new Cell(7, 7)));
		constraints.setRegions(regions);
		SUDOKU_PROBLEMS.put("9x9_hyper", new SudokuProblem(board, constraints));
		
		board = new int[][]{
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
		constraints = new SudokuConstraints(9, 3, 3);
		Map<List<Cell>, Integer> killerSums = new HashMap<>();
		killerSums.put(Arrays.asList(new Cell(0, 0), new Cell(0, 1), new Cell(0, 2)), 11);
		killerSums.put(Arrays.asList(new Cell(0, 3), new Cell(0, 4), new Cell(1, 3)), 19);
		killerSums.put(Arrays.asList(new Cell(0, 5), new Cell(0, 6), new Cell(0, 7)), 13);
		killerSums.put(Arrays.asList(new Cell(0, 8), new Cell(1, 8), new Cell(2, 8)), 18);
		killerSums.put(Arrays.asList(new Cell(1, 0), new Cell(2, 0), new Cell(3, 0)), 16);
		killerSums.put(Arrays.asList(new Cell(1, 1), new Cell(1, 2), new Cell(2, 1)), 24);
		killerSums.put(Arrays.asList(new Cell(1, 4), new Cell(1, 5), new Cell(1, 6)), 15);
		killerSums.put(Arrays.asList(new Cell(1, 7), new Cell(2, 6), new Cell(2, 7)), 13);
		killerSums.put(Arrays.asList(new Cell(2, 2), new Cell(2, 3), new Cell(2, 4)), 10);
		killerSums.put(Arrays.asList(new Cell(2, 5), new Cell(3, 5)), 11);
		killerSums.put(Arrays.asList(new Cell(3, 1), new Cell(4, 0), new Cell(4, 1)), 12);
		killerSums.put(Arrays.asList(new Cell(3, 2), new Cell(3, 3), new Cell(3, 4)), 14);
		killerSums.put(Arrays.asList(new Cell(3, 6), new Cell(3, 7), new Cell(3, 8)), 15);
		killerSums.put(Arrays.asList(new Cell(4, 2), new Cell(4, 3), new Cell(4, 4), new Cell(4, 5), new Cell(4, 6)), 30);
		killerSums.put(Arrays.asList(new Cell(4, 7), new Cell(4, 8), new Cell(5, 7)), 12);
		killerSums.put(Arrays.asList(new Cell(5, 0), new Cell(5, 1), new Cell(5, 2)), 15);
		killerSums.put(Arrays.asList(new Cell(5, 3), new Cell(6, 3)), 4);
		killerSums.put(Arrays.asList(new Cell(5, 4), new Cell(5, 5), new Cell(5, 6)), 19);
		killerSums.put(Arrays.asList(new Cell(5, 8), new Cell(6, 8), new Cell(7, 8)), 14);
		killerSums.put(Arrays.asList(new Cell(6, 0), new Cell(7, 0), new Cell(8, 0)), 12);
		killerSums.put(Arrays.asList(new Cell(6, 1), new Cell(6, 2), new Cell(7, 1)), 15);
		killerSums.put(Arrays.asList(new Cell(6, 4), new Cell(6, 5), new Cell(6, 6)), 22);
		killerSums.put(Arrays.asList(new Cell(6, 7), new Cell(7, 6), new Cell(7, 7)), 9);
		killerSums.put(Arrays.asList(new Cell(7, 2), new Cell(7, 3), new Cell(7, 4)), 19);
		killerSums.put(Arrays.asList(new Cell(7, 5), new Cell(8, 4), new Cell(8, 5)), 9);
		killerSums.put(Arrays.asList(new Cell(8, 1), new Cell(8, 2), new Cell(8, 3)), 18);
		killerSums.put(Arrays.asList(new Cell(8, 6), new Cell(8, 7), new Cell(8, 8)), 16);
		constraints.setKillerSums(killerSums);
		SUDOKU_PROBLEMS.put("9x9_killer", new SudokuProblem(board, constraints));
		
		board = new int[][]{
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
		constraints = new SudokuConstraints(9, 3, 3);
		List<Entry<Cell, Cell>> greaterThans = new ArrayList<>();
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(0, 2), new Cell(1, 2)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(0, 4), new Cell(0, 3)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(0, 7), new Cell(1, 7)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(0, 8), new Cell(1, 8)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(1, 0), new Cell(1, 1)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(1, 1), new Cell(2, 1)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(1, 6), new Cell(1, 7)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(1, 7), new Cell(1, 8)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(1, 8), new Cell(2, 8)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(2, 1), new Cell(2, 0)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(2, 2), new Cell(2, 1)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(2, 4), new Cell(2, 3)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(2, 5), new Cell(2, 4)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(2, 7), new Cell(2, 6)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(3, 2), new Cell(3, 1)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(3, 4), new Cell(3, 3)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(3, 4), new Cell(4, 4)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(5, 0), new Cell(4, 0)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(4, 0), new Cell(4, 1)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(4, 2), new Cell(4, 1)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(4, 4), new Cell(5, 4)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(4, 5), new Cell(4, 4)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(5, 5), new Cell(4, 5)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(4, 7), new Cell(5, 7)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(5, 1), new Cell(5, 0)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(5, 4), new Cell(5, 3)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(5, 6), new Cell(5, 7)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(5, 7), new Cell(5, 8)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(6, 1), new Cell(6, 2)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(6, 2), new Cell(7, 2)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(6, 4), new Cell(6, 3)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(7, 3), new Cell(6, 3)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(6, 5), new Cell(7, 5)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(6, 7), new Cell(6, 8)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(7, 0), new Cell(7, 1)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(7, 2), new Cell(7, 1)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(8, 1), new Cell(7, 1)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(7, 4), new Cell(7, 3)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(8, 4), new Cell(7, 4)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(7, 5), new Cell(8, 5)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(8, 2), new Cell(8, 1)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(8, 4), new Cell(8, 3)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(8, 7), new Cell(8, 8)));
		constraints.setGreaterThans(greaterThans);
		//Warning: This will take DAYS to finish
		//SUDOKU_PROBLEMS.put("9x9_greaterthan", new SudokuProblem(board, constraints));
		
		board = new int[][]{
	    	{0, 0, 0, 0, 0, 1, 0, 0, 0},
	    	{0, 1, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 1, 0},
	    	{0, 0, 0, 1, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 1, 0, 0},
	    	{1, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 1, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 1},
	    	{0, 0, 0, 0, 1, 0, 0, 0, 0}
		};
		constraints = new SudokuConstraints(9, 3, 3);
		greaterThans = new ArrayList<>();
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(0, 0), new Cell(0, 1)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(0, 1), new Cell(0, 2)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(0, 2), new Cell(1, 2)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(0, 3), new Cell(1, 3)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(0, 4), new Cell(1, 4)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(0, 7), new Cell(1, 7)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(0, 8), new Cell(0, 7)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(2, 1), new Cell(1, 1)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(2, 3), new Cell(1, 3)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(2, 4), new Cell(1, 4)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(1, 5), new Cell(2, 5)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(2, 5), new Cell(2, 4)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(2, 6), new Cell(2, 7)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(3, 1), new Cell(4, 1)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(4, 2), new Cell(3, 2)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(3, 4), new Cell(3, 5)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(3, 5), new Cell(4, 5)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(3, 7), new Cell(3, 8)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(3, 8), new Cell(4, 8)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(4, 1), new Cell(4, 0)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(4, 2), new Cell(5, 2)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(4, 3), new Cell(4, 4)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(5, 5), new Cell(4, 5)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(5, 7), new Cell(4, 7)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(4, 8), new Cell(4, 7)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(5, 8), new Cell(4, 8)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(5, 1), new Cell(5, 0)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(5, 2), new Cell(5, 1)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(5, 3), new Cell(5, 4)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(5, 6), new Cell(5, 7)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(6, 1), new Cell(7, 1)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(7, 2), new Cell(6, 2)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(6, 3), new Cell(6, 4)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(7, 7), new Cell(6, 7)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(8, 1), new Cell(7, 1)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(7, 1), new Cell(7, 2)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(7, 3), new Cell(8, 3)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(7, 4), new Cell(7, 3)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(7, 6), new Cell(8, 6)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(7, 7), new Cell(7, 6)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(8, 0), new Cell(8, 1)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(8, 2), new Cell(8, 1)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(8, 3), new Cell(8, 4)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(8, 6), new Cell(8, 7)));
		greaterThans.add(new AbstractMap.SimpleEntry<>(new Cell(8, 8), new Cell(8, 7)));
		constraints.setGreaterThans(greaterThans);
		SUDOKU_PROBLEMS.put("9x9_greaterthan_2", new SudokuProblem(board, constraints));
		
		board = new int[][]{
	    	{0, 7, 4, 0, 0, 5, 0, 0, 0},
	    	{2, 0, 0, 0, 0, 0, 7, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 8, 0, 0, 0, 0},
	    	{0, 0, 7, 1, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0}
		};
		constraints = new SudokuConstraints(9, 3, 3);
		Map<Cell, List<Cell>> arrows = new HashMap<>();
		arrows.put(new Cell(1, 1), Arrays.asList(new Cell(0, 2), new Cell(1, 3), new Cell(0, 4)));
		arrows.put(new Cell(0, 5), Arrays.asList(new Cell(0, 6), new Cell(1, 7)));
		arrows.put(new Cell(1, 6), Arrays.asList(new Cell(1, 5), new Cell(1, 4)));
		arrows.put(new Cell(1, 8), Arrays.asList(new Cell(2, 7), new Cell(2, 6), new Cell(3, 7)));
		arrows.put(new Cell(2, 5), Arrays.asList(new Cell(2, 4), new Cell(3, 3), new Cell(4, 2)));
		arrows.put(new Cell(3, 0), Arrays.asList(new Cell(2, 0), new Cell(1, 0)));
		arrows.put(new Cell(3, 1), Arrays.asList(new Cell(3, 2), new Cell(2, 1)));
		arrows.put(new Cell(3, 6), Arrays.asList(new Cell(4, 5), new Cell(5, 6)));
		arrows.put(new Cell(5, 1), Arrays.asList(new Cell(4, 1), new Cell(4, 0)));
		arrows.put(new Cell(4, 4), Arrays.asList(new Cell(5, 3), new Cell(5, 4), new Cell(6, 5)));
		arrows.put(new Cell(4, 7), Arrays.asList(new Cell(4, 8), new Cell(5, 8), new Cell(6, 8)));
		arrows.put(new Cell(7, 0), Arrays.asList(new Cell(6, 0), new Cell(7, 1)));
		arrows.put(new Cell(6, 2), Arrays.asList(new Cell(7, 2), new Cell(6, 1)));
		arrows.put(new Cell(6, 4), Arrays.asList(new Cell(7, 3), new Cell(8, 4)));
		arrows.put(new Cell(8, 5), Arrays.asList(new Cell(8, 6), new Cell(8, 7)));
		arrows.put(new Cell(7, 6), Arrays.asList(new Cell(6, 6), new Cell(5, 7)));
		constraints.setArrows(arrows);
		SUDOKU_PROBLEMS.put("9x9_arrow", new SudokuProblem(board, constraints));
		
		board = new int[][]{
	    	{0, 0, 7, 0, 0, 3, 0, 8, 9},
	    	{0, 0, 9, 0, 0, 5, 0, 0, 7},
	    	{8, 0, 2, 0, 0, 0, 1, 0, 0},
	    	{0, 0, 1, 0, 2, 0, 0, 0, 6},
	    	{0, 0, 0, 0, 8, 0, 0, 7, 0},
	    	{0, 0, 0, 1, 0, 0, 0, 9, 0},
	    	{0, 0, 0, 7, 0, 0, 3, 5, 0},
	    	{0, 0, 0, 9, 0, 8, 0, 0, 0},
	    	{0, 8, 0, 0, 0, 0, 0, 0, 4}
		};
		constraints = new SudokuConstraints(9, 3, 3);
		constraints.setCenterDot(true);
		SUDOKU_PROBLEMS.put("9x9_centerdot", new SudokuProblem(board, constraints));
		
		//Add your problem here
	}
	
	public static void main(String[] args)
	{
		if(args.length == 1 && args[0].equalsIgnoreCase("--all"))
		{
			for(Entry<String, SudokuProblem> entry : SUDOKU_PROBLEMS.entrySet())
				solveProblem(entry.getKey(), entry.getValue());
			System.out.println("Done");
			return;
		}else if(args.length == 2 && args[0].equalsIgnoreCase("--problem"))
		{
			if(!SUDOKU_PROBLEMS.containsKey(args[1]))
			{
				System.out.println("Invalid problem specified");
				return;
			}
			solveProblem(args[1], SUDOKU_PROBLEMS.get(args[1]));
			System.out.println("Done");
			return;
		}
		
		System.out.println("Invalid argument. Below are the possible arguments:");
		System.out.println("To solve a specific problem: --problem PROBLEM_NAME");
		System.out.println("To solve all problems: --all");
	}
	
	private static void solveProblem(String name, SudokuProblem problem)
	{
		System.out.println("Solving problem \"" + name + "\"...");
		if(problem.solve())
		{
			System.out.println("Success:");
			problem.printSolvedBoard();
		}else
		{
			System.out.println("No solution was found. Original board:");
			problem.printBoard();
		}
	}
}

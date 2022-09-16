package com.thistestuser.solver;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SudokuTemplates
{
	public static void initProblems()
	{
		final int A = 10;
		final int B = 11;
		final int C = 12;
		final int D = 13;
		final int E = 14;
		final int F = 15;
		final int G = 16;
		
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
		Main.SUDOKU_PROBLEMS.put("4x4_diag", new SudokuProblem(board, constraints));
		
		board = new int[][]{
			{6, 4, 3, 0, 0, 0},
			{0, 0, 4, 0, 0, 1},
			{1, 0, 6, 0, 0, 0},
			{0, 0, 1, 0, 4, 0},
			{0, 0, 0, 0, 0, 3},
			{0, 5, 0, 0, 0, 0},
		};
		constraints = new SudokuConstraints(6, 2, 3);
		Main.SUDOKU_PROBLEMS.put("6x6", new SudokuProblem(board, constraints));
		
		board = new int[][]{
			{0, 4, 0, 0, 0, 0, 0, 0, 3},
			{8, 0, 0, 9, 0, 0, 2, 0, 4},
			{0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 7, 2, 0, 0, 8, 0, 3, 0},
			{3, 8, 0, 6, 0, 0, 9, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 6, 0},
			{0, 0, 0, 0, 7, 9, 0, 0, 0},
			{0, 0, 6, 0, 5, 0, 1, 0, 0},
			{2, 0, 0, 0, 3, 0, 0, 7, 0}
		};
		constraints = new SudokuConstraints(9, 3, 3);
		Main.SUDOKU_PROBLEMS.put("9x9", new SudokuProblem(board, constraints));
		
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
		Main.SUDOKU_PROBLEMS.put("9x9_hardest", new SudokuProblem(board, constraints));
		
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
		Main.SUDOKU_PROBLEMS.put("9x9_diag", new SudokuProblem(board, constraints));
		
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
		Main.SUDOKU_PROBLEMS.put("9x9_jigsaw", new SudokuProblem(board, constraints));
		
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
		Main.SUDOKU_PROBLEMS.put("9x9_hyper", new SudokuProblem(board, constraints));
		
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
		Main.SUDOKU_PROBLEMS.put("9x9_killer", new SudokuProblem(board, constraints));
		
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
		//Main.SUDOKU_PROBLEMS.put("9x9_greaterthan", new SudokuProblem(board, constraints));
		
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
		//This takes about 2.5 minutes to solve
		Main.SUDOKU_PROBLEMS.put("9x9_greaterthan_2", new SudokuProblem(board, constraints));
		
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
		Main.SUDOKU_PROBLEMS.put("9x9_arrow", new SudokuProblem(board, constraints));
		
		board = new int[][]{
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 7, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 8, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 8, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0}
		};
		constraints = new SudokuConstraints(9, 3, 3);
		arrows = new HashMap<>();
		arrows.put(new Cell(0, 2), Arrays.asList(new Cell(1, 3), new Cell(2, 4)));
		arrows.put(new Cell(1, 4), Arrays.asList(new Cell(0, 4), new Cell(0, 5)));
		arrows.put(new Cell(1, 8), Arrays.asList(new Cell(1, 7), new Cell(0, 7)));
		arrows.put(new Cell(2, 1), Arrays.asList(new Cell(2, 2), new Cell(1, 2)));
		arrows.put(new Cell(2, 6), Arrays.asList(new Cell(2, 5), new Cell(1, 5)));
		arrows.put(new Cell(3, 1), Arrays.asList(new Cell(3, 2), new Cell(2, 3)));
		arrows.put(new Cell(3, 5), Arrays.asList(new Cell(4, 5), new Cell(3, 4)));
		arrows.put(new Cell(4, 1), Arrays.asList(new Cell(3, 0), new Cell(2, 0), new Cell(1, 0)));
		arrows.put(new Cell(4, 2), Arrays.asList(new Cell(5, 1), new Cell(5, 0)));
		arrows.put(new Cell(4, 6), Arrays.asList(new Cell(5, 6), new Cell(4, 7), new Cell(3, 6)));
		arrows.put(new Cell(5, 4), Arrays.asList(new Cell(4, 4), new Cell(4, 3), new Cell(5, 2), new Cell(6, 1)));
		arrows.put(new Cell(5, 5), Arrays.asList(new Cell(6, 5), new Cell(7, 5)));
		arrows.put(new Cell(5, 7), Arrays.asList(new Cell(4, 8), new Cell(3, 8)));
		arrows.put(new Cell(6, 6), Arrays.asList(new Cell(7, 7), new Cell(7, 6)));
		arrows.put(new Cell(7, 2), Arrays.asList(new Cell(7, 1), new Cell(6, 0)));
		arrows.put(new Cell(7, 3), Arrays.asList(new Cell(7, 4), new Cell(8, 5)));
		arrows.put(new Cell(8, 0), Arrays.asList(new Cell(7, 0), new Cell(8, 1)));
		arrows.put(new Cell(8, 6), Arrays.asList(new Cell(8, 7), new Cell(8, 8)));
		constraints.setArrows(arrows);
		//This takes about a minute to solve
		Main.SUDOKU_PROBLEMS.put("9x9_arrow_2", new SudokuProblem(board, constraints));
		
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
		Main.SUDOKU_PROBLEMS.put("9x9_centerdot", new SudokuProblem(board, constraints));
		
		board = new int[][]{
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 9, 0, 0, 0, 0, 3, 0, 6},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 3, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 3, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 7, 0},
	    	{0, 0, 9, 5, 0, 0, 0, 0, 0}
		};
		constraints = new SudokuConstraints(9, 3, 3);
		List<Entry<Cell, Cell>> consecutive = new ArrayList<>();
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(0, 2), new Cell(1, 2)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(0, 4), new Cell(1, 4)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(0, 5), new Cell(0, 6)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(1, 1), new Cell(1, 2)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(1, 7), new Cell(1, 8)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(2, 0), new Cell(3, 0)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(2, 0), new Cell(2, 1)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(2, 2), new Cell(3, 2)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(2, 3), new Cell(3, 3)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(2, 7), new Cell(2, 8)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(3, 2), new Cell(4, 2)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(3, 3), new Cell(3, 4)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(3, 5), new Cell(3, 6)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(3, 7), new Cell(4, 7)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(3, 7), new Cell(3, 8)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(3, 8), new Cell(4, 8)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(4, 1), new Cell(4, 2)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(4, 4), new Cell(4, 5)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(4, 8), new Cell(5, 8)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(5, 1), new Cell(5, 2)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(5, 5), new Cell(6, 5)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(5, 6), new Cell(6, 6)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(6, 0), new Cell(7, 0)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(6, 1), new Cell(7, 1)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(6, 2), new Cell(7, 2)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(6, 4), new Cell(6, 5)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(6, 6), new Cell(6, 7)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(7, 2), new Cell(7, 3)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(7, 4), new Cell(7, 5)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(7, 6), new Cell(8, 6)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(7, 7), new Cell(8, 7)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(7, 8), new Cell(8, 8)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(8, 1), new Cell(8, 2)));
		constraints.setConsecutive(consecutive);
		constraints.setNonConsecutive(true);
		Main.SUDOKU_PROBLEMS.put("9x9_consecutive", new SudokuProblem(board, constraints));
		
		board = new int[][]{
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 8, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 8, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 2, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 9, 0, 0, 0}
		};
		constraints = new SudokuConstraints(9, 3, 3);
		consecutive = new ArrayList<>();
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(0, 0), new Cell(1, 0)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(0, 1), new Cell(1, 1)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(0, 2), new Cell(1, 2)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(0, 2), new Cell(0, 3)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(0, 5), new Cell(0, 6)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(1, 0), new Cell(2, 0)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(1, 4), new Cell(1, 5)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(1, 6), new Cell(2, 6)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(1, 7), new Cell(2, 7)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(2, 0), new Cell(2, 1)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(2, 2), new Cell(2, 3)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(2, 4), new Cell(3, 4)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(2, 8), new Cell(3, 8)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(3, 0), new Cell(4, 0)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(3, 1), new Cell(4, 1)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(3, 2), new Cell(4, 2)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(3, 6), new Cell(4, 6)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(3, 7), new Cell(4, 7)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(4, 5), new Cell(4, 6)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(5, 0), new Cell(6, 0)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(5, 1), new Cell(6, 1)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(5, 1), new Cell(5, 2)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(5, 2), new Cell(6, 2)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(5, 5), new Cell(6, 5)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(5, 6), new Cell(6, 6)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(5, 7), new Cell(5, 8)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(5, 8), new Cell(6, 8)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(6, 1), new Cell(7, 1)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(6, 2), new Cell(6, 3)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(6, 3), new Cell(7, 3)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(6, 5), new Cell(7, 5)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(7, 2), new Cell(8, 2)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(7, 2), new Cell(7, 3)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(7, 3), new Cell(8, 3)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(7, 5), new Cell(7, 6)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(7, 6), new Cell(8, 6)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(7, 7), new Cell(8, 7)));
		consecutive.add(new AbstractMap.SimpleEntry<>(new Cell(8, 2), new Cell(8, 3)));
		constraints.setConsecutive(consecutive);
		constraints.setNonConsecutive(true);
		Main.SUDOKU_PROBLEMS.put("9x9_consecutive_2", new SudokuProblem(board, constraints));
		
		board = new int[][]{
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{9, 0, 0, 0, 0, 3, 4, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 1, 0, 8},
	    	{0, 0, 0, 2, 0, 0, 0, 0, 9},
	    	{7, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 4, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 8, 0, 0},
	    	{0, 5, 0, 0, 0, 0, 0, 9, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 3, 0}
		};
		constraints = new SudokuConstraints(9, 3, 3);
		List<Cell> evenCells = new ArrayList<>();
		evenCells.add(new Cell(0, 0));
		evenCells.add(new Cell(0, 1));
		evenCells.add(new Cell(1, 1));
		evenCells.add(new Cell(2, 2));
		evenCells.add(new Cell(0, 3));
		evenCells.add(new Cell(0, 5));
		evenCells.add(new Cell(1, 3));
		evenCells.add(new Cell(2, 4));
		evenCells.add(new Cell(1, 6));
		evenCells.add(new Cell(1, 7));
		evenCells.add(new Cell(2, 7));
		evenCells.add(new Cell(2, 8));
		evenCells.add(new Cell(3, 1));
		evenCells.add(new Cell(3, 2));
		evenCells.add(new Cell(5, 0));
		evenCells.add(new Cell(5, 2));
		evenCells.add(new Cell(3, 3));
		evenCells.add(new Cell(3, 5));
		evenCells.add(new Cell(4, 4));
		evenCells.add(new Cell(4, 5));
		evenCells.add(new Cell(4, 6));
		evenCells.add(new Cell(4, 7));
		evenCells.add(new Cell(5, 7));
		evenCells.add(new Cell(5, 8));
		evenCells.add(new Cell(6, 0));
		evenCells.add(new Cell(6, 2));
		evenCells.add(new Cell(8, 0));
		evenCells.add(new Cell(8, 1));
		evenCells.add(new Cell(6, 5));
		evenCells.add(new Cell(7, 3));
		evenCells.add(new Cell(7, 4));
		evenCells.add(new Cell(8, 4));
		evenCells.add(new Cell(6, 6));
		evenCells.add(new Cell(7, 6));
		evenCells.add(new Cell(7, 8));
		evenCells.add(new Cell(8, 8));
		List<Cell> oddCells = new ArrayList<>();
		for(int r =  0; r < 9; r++)
			for(int c = 0; c < 9; c++)
			{
				if(!evenCells.contains(new Cell(r, c)))
					oddCells.add(new Cell(r, c));
			}
		constraints.setEvenCells(evenCells);
		constraints.setOddCells(oddCells);
		Main.SUDOKU_PROBLEMS.put("9x9_oddeven", new SudokuProblem(board, constraints));
		
		board = new int[][]{
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 6, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 3, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0},
	    	{0, 0, 0, 0, 0, 0, 0, 0, 0}
		};
		constraints = new SudokuConstraints(9, 3, 3);
		List<Entry<Cell, Cell>> sum5 = new ArrayList<>();
		List<Entry<Cell, Cell>> sum10 = new ArrayList<>();
		sum5.add(new AbstractMap.SimpleEntry<>(new Cell(0, 1), new Cell(0, 2)));
		sum10.add(new AbstractMap.SimpleEntry<>(new Cell(0, 6), new Cell(1, 6)));
		sum10.add(new AbstractMap.SimpleEntry<>(new Cell(0, 1), new Cell(1, 1)));
		sum5.add(new AbstractMap.SimpleEntry<>(new Cell(1, 2), new Cell(2, 2)));
		sum10.add(new AbstractMap.SimpleEntry<>(new Cell(1, 5), new Cell(1, 6)));
		sum5.add(new AbstractMap.SimpleEntry<>(new Cell(1, 7), new Cell(1, 8)));
		sum10.add(new AbstractMap.SimpleEntry<>(new Cell(2, 4), new Cell(3, 4)));
		sum10.add(new AbstractMap.SimpleEntry<>(new Cell(2, 7), new Cell(3, 7)));
		sum10.add(new AbstractMap.SimpleEntry<>(new Cell(3, 0), new Cell(4, 0)));
		sum10.add(new AbstractMap.SimpleEntry<>(new Cell(3, 2), new Cell(4, 2)));
		sum5.add(new AbstractMap.SimpleEntry<>(new Cell(3, 7), new Cell(4, 7)));
		sum10.add(new AbstractMap.SimpleEntry<>(new Cell(4, 1), new Cell(5, 1)));
		sum5.add(new AbstractMap.SimpleEntry<>(new Cell(4, 3), new Cell(4, 4)));
		sum10.add(new AbstractMap.SimpleEntry<>(new Cell(4, 4), new Cell(5, 4)));
		sum10.add(new AbstractMap.SimpleEntry<>(new Cell(4, 7), new Cell(4, 8)));
		sum10.add(new AbstractMap.SimpleEntry<>(new Cell(5, 5), new Cell(5, 6)));
		sum10.add(new AbstractMap.SimpleEntry<>(new Cell(5, 6), new Cell(6, 6)));
		sum10.add(new AbstractMap.SimpleEntry<>(new Cell(6, 0), new Cell(7, 0)));
		sum10.add(new AbstractMap.SimpleEntry<>(new Cell(6, 1), new Cell(7, 1)));
		sum5.add(new AbstractMap.SimpleEntry<>(new Cell(6, 4), new Cell(6, 5)));
		sum10.add(new AbstractMap.SimpleEntry<>(new Cell(6, 6), new Cell(6, 7)));
		sum10.add(new AbstractMap.SimpleEntry<>(new Cell(7, 4), new Cell(8, 4)));
		sum5.add(new AbstractMap.SimpleEntry<>(new Cell(7, 4), new Cell(7, 5)));
		sum10.add(new AbstractMap.SimpleEntry<>(new Cell(8, 6), new Cell(8, 7)));
		constraints.setSum5(sum5);
		constraints.setSum10(sum10);
		constraints.setNonSum5(true);
		constraints.setNonSum10(true);
		Main.SUDOKU_PROBLEMS.put("9x9_xv", new SudokuProblem(board, constraints));
		
		board = new int[][]{
			{0, B, 0, 0, 0, 0, 4, 0, 0, 9, 2, 0},
			{0, 0, 0, 4, 0, 0, 0, 0, 1, 0, 0, 7},
			{8, 0, 0, 0, B, 0, C, 0, 6, 0, 0, 0},
			{2, C, 0, 0, 0, 0, 3, 0, A, 0, 0, 0},
			{A, 1, 0, 0, 0, 0, 0, 6, 8, 0, 0, 4},
			{0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 9, 0},
			{0, 0, 0, 0, 7, B, 0, 0, 0, 0, 0, 1},
			{0, 0, 0, 0, 0, 0, 6, 0, 4, 0, A, 0},
			{0, 0, 0, 1, 4, 0, A, 0, 5, 0, 0, 0},
			{0, 9, 0, 6, 0, 2, 0, 0, C, 8, 0, 0},
			{3, 0, 0, 0, 0, 8, 7, 0, 0, 0, 5, 0},
			{0, 5, 0, A, 1, C, 0, 0, B, 6, 0, 0}
		};
		constraints = new SudokuConstraints(12, 4, 3);
		//Warning: This will take HOURS to finish
		//Main.SUDOKU_PROBLEMS.put("12x12", new SudokuProblem(board, constraints));
		
		board = new int[][]{
			{0, 5, 0, 0, 6, 0, F, 0, D, B, 0, 0, 0, C, G, 0},
			{0, 2, 6, 0, 0, 0, 0, 9, 0, 0, 0, G, 0, E, 0, 0},
			{0, 0, 8, 0, 0, 0, C, 2, 0, 0, 7, 6, 3, 0, B, 0},
			{0, 1, 7, 0, 0, 0, 4, 3, 0, F, 0, 0, 0, 5, 0, 0},
			{0, 8, 0, 0, 0, 0, 0, 0, 3, 0, 0, E, 0, 0, 2, 4},
			{0, 0, 0, 0, 0, 0, 0, A, 0, C, D, 0, 0, 0, 0, B},
			{0, G, 0, 3, 0, 0, 0, 0, 0, 2, 8, 5, C, 0, 0, E},
			{0, E, 0, 0, 0, 9, 0, 0, 4, 0, 6, 0, 5, D, 0, 1},
			{0, C, 3, 0, F, 0, 0, 0, 0, 0, 0, 0, 9, 0, 6, 7},
			{0, 0, 0, 7, D, 0, A, 0, 6, 8, 2, 0, 0, 0, 3, 0},
			{2, 0, 0, E, 0, B, 0, 7, G, 0, 3, 0, 0, 0, 0, 0},
			{0, F, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, A},
			{0, 0, 1, C, 0, 0, 9, 0, 0, 3, 0, 0, F, 0, 0, 0},
			{G, 0, 5, 0, 1, C, 0, 0, B, 0, 0, 0, 4, 0, 0, 2},
			{7, A, E, 0, 0, 8, 0, G, 0, 0, 5, 0, 0, 9, 0, 0},
			{0, 0, 9, 0, 0, F, 0, 0, 0, 0, A, 1, 7, 0, 0, G}
		};
		constraints = new SudokuConstraints(16, 4, 4);
		//Warning: Not viable currently
		//Main.SUDOKU_PROBLEMS.put("16x16", new SudokuProblem(board, constraints));
	}
}

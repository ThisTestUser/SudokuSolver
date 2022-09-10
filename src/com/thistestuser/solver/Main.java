package com.thistestuser.solver;

import java.util.HashMap;
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
			if(SUDOKU_PROBLEMS.containsKey(args[1]))
			{
				System.out.println("Invalid problem specified");
				return;
			}
			solveProblem(args[1], SUDOKU_PROBLEMS.get(args[1]));
			System.out.println("Done");
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

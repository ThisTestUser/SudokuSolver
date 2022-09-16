package com.thistestuser.solver;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Main
{
	public static final Map<String, SudokuProblem> SUDOKU_PROBLEMS = new HashMap<>();

	static
	{
		//Most of these problems are from PuzzleMadness, with the exception of 4x4_diag and 9x9_hardest
		//All of the problems are from the hardest difficulty in PuzzleMadness
		//Most of the puzzles (that are from PuzzleMadness) are from the Sep 10 2022 archive
		//The remaining ones are from the Sep 2022 archive, with one (9x9_greaterthan_2) being from an unknown date earlier than June 2019
		
		//Initialize template problems
		SudokuTemplates.initProblems();
		
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

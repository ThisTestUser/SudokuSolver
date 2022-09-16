package com.thistestuser.solver;

public class SudokuProblem
{
	private final int[][] board;
	private int[][] solvedBoard;
	private final SudokuConstraints constraints;
	
	public SudokuProblem(int[][] board, SudokuConstraints constraints)
	{
		this.board = board;
		this.constraints = constraints;
	}
	
	public boolean solve()
	{
		solvedBoard = board.clone(); 
		boolean result = new SudokuSolver(constraints).solve(solvedBoard);
		if(!result)
			solvedBoard = null;
		return result;
	}
	
	public void printBoard()
	{
		int spaces = (int)Math.log10(constraints.getBoardSize()) + 1;
		for(int row = 0; row < constraints.getBoardSize(); row++)
		{
			for(int column = 0; column < constraints.getBoardSize(); column++)
			{
				int value = board[row][column];
				int valueLen = value <= 0 ? 0 : (int)Math.log10(value);
				System.out.print(value + String.format("%" + (spaces - valueLen) + "s", " "));
			}
			System.out.println();
		}
	}
	
	public void printSolvedBoard()
	{
		if(solvedBoard == null)
		{
			System.out.println("Board was not solved!");
			return;
		}
		for(int row = 0; row < constraints.getBoardSize(); row++)
		{
			for(int column = 0; column < constraints.getBoardSize(); column++)
				System.out.print(solvedBoard[row][column] + " ");
			System.out.println();
		}
	}
}

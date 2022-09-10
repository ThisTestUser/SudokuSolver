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
		for(int row = 0; row < constraints.getBoardSize(); row++)
		{
			for(int column = 0; column < constraints.getBoardSize(); column++)
				System.out.print(board[row][column] + " ");
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

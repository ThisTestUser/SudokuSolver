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
	
	public void printBoard(boolean displayAlpha)
	{
		printBoard(board, displayAlpha);
	}
	
	public void printSolvedBoard(boolean displayAlpha)
	{
		if(solvedBoard == null)
		{
			System.out.println("Board was not solved!");
			return;
		}
		printBoard(solvedBoard, displayAlpha);
	}
	
	private void printBoard(int[][] board, boolean displayAlpha)
	{
		int spaces = (int)Math.log10(constraints.getBoardSize()) + 1;
		if(displayAlpha && constraints.getBoardSize() < 36)
			spaces = 1;
		for(int row = 0; row < constraints.getBoardSize(); row++)
		{
			for(int column = 0; column < constraints.getBoardSize(); column++)
			{
				int value = board[row][column];
				boolean isAlpha = displayAlpha && value > 9 && value < 36;
				String printed = isAlpha ? String.valueOf((char)(value + 55)) : String.valueOf(value);
				int valueLen = isAlpha || value <= 0 ? 0 : (int)Math.log10(value);
				System.out.print(printed + String.format("%" + (spaces - valueLen) + "s", " "));
			}
			System.out.println();
		}
	}
}

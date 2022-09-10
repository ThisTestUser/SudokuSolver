package com.thistestuser.solver;

public class Cell
{
	private final int row;
	private final int column;
	
	public Cell(int row, int column)
	{
		this.row = row;
		this.column = column;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getColumn()
	{
		return column;
	}
	
	@Override
	public int hashCode()
	{
		return row * 31 + column;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof Cell))
			return false;
		return ((Cell)o).row == row && ((Cell)o).column == column;
	}
}

package com.blackflamegamez.game;

import java.util.ArrayList;

/**
 * @author Milan Topalovic
 *
 */
public abstract class GenericBoard 
{
	
	protected ArrayList<Cell> board;
	
	public GenericBoard()
	{
		board = new ArrayList<Cell>();
	}
	
	/**
	 * This method should only contain implementation of cell creation
	 * of some specific board
	 */
	public abstract void makeCells();
	
	/**
	 * Deriving graph from board should is done in this method implementation
	 */
	public abstract void determineNeighbours();
	
	public void addCell(Cell c)
	{
		board.add(c);
	}
	
	public void setBoard(ArrayList<Cell> board)
	{
		this.board = board;
	}
	
	public ArrayList<Cell> getBoard()
	{
		return board;
	}
	
	/* probna metoda */
	public void resetBoard()
	{
		for(Cell c : board)
			c.resetCell();
	}
	
}

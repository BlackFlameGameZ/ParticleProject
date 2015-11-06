package com.blackflamegamez.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blackflamegamez.game.geometry.Body2D;

/**
 * @author Milan Topalovic
 *
 */
public class Cell extends Body2D 
{

	private int row;
	private int col;
	private Hexagon hexagon;
	private ArrayList<Cell> neighbours;
	
	public Cell(float x , float y , float width , float height , int row , int col , Hexagon hexagon)
	{
		super(x , y , width , height);
		this.row      = row;
		this.col	  = col;
		this.hexagon  = hexagon;
		this.neighbours = new ArrayList<Cell>();
//		this.particle = null;
	}
	
	public boolean contains(float x , float y)
	{
		return hexagon.contains(x , y);
	}
	
	public void addNeighbour(Cell c)
	{
		if(neighbours.contains(c))
			return;
		neighbours.add(c);
	}
	
	public void setNeighbours(ArrayList<Cell> n)
	{
		this.neighbours = n;
	}
	
	public ArrayList<Cell> getNeighbours()
	{
		return neighbours;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public void setCol(int col)
	{
		this.col = col;
	}
	
	public void setRow(int row)
	{
		this.row = row;
	}
	
	public Hexagon getHexagon()
	{
		return hexagon;
	}
	
	public void debug(ShapeRenderer sr)
	{
		hexagon.debug(sr);
	}
}

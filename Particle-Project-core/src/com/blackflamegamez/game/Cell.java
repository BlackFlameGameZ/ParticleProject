package com.blackflamegamez.game;

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
	//private Virus   virus;
	
	public Cell(float x , float y , float width , float height , int row , int col , Hexagon hexagon)
	{
		super(x , y , width , height);
		this.row      = row;
		this.col	  = col;
		this.hexagon  = hexagon;
//		this.virus    = null; //TODO
	}
	
	public boolean contains(float x , float y)
	{
		return hexagon.contains(x , y);
	}
	
}

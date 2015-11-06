package com.blackflamegamez.game;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.blackflamegamez.game.enums.ParticleColor;

/**
 * @author Milan Topalovic
 *
 */
public class Player 
{
	
	private ArrayList<Cell> cells; //all cells on the grid
	private ParticleColor   color;
	private Cell            previousCell;
	private boolean         cellInstantiated;
	
	public Player(ParticleColor color , ArrayList<Cell> cells)
	{
		this.color = color;
		this.cellInstantiated = false;
		this.cells = cells;
	}

	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) 
	{
		for(Cell c : cells)
			if(c.contains(x, y))
			{
				previousCell = c;
				break;
			}
		return true;
	}
	
	public void touchUp(InputEvent event ,float x , float y , int pointer , int button)
	{		
	}
	
	public void touchDragged(InputEvent event , float x , float y , int pointer)
	{
	}
}

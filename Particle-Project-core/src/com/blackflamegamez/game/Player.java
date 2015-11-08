package com.blackflamegamez.game;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.blackflamegamez.game.enums.ParticleAction;
import com.blackflamegamez.game.enums.ParticleColor;
import com.blackflamegamez.game.gameactions.MoveResolution;

/**
 * @author Milan Topalovic
 *
 */
public class Player 
{
	
	private GameBoard       board;
	private ParticleColor   color;
	private Cell            previousCell;
	private boolean         cellInstantiated;
	private boolean         dragging;
	
	public Player(ParticleColor color , GameBoard board)
	{
		this.color = color;
		this.cellInstantiated = false;
		this.board = board;
		resetState();
	}

	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) 
	{
		previousCell = board.getCellForCoordinates(x , y);
		return true;
	}
	
	public MoveResolution touchUp(InputEvent event ,float x , float y , int pointer , int button)
	{		
		Cell nc = board.getCellForCoordinates(x , y);
		if(!cellInstantiated && nc != null)
			if(nc.getParticle() == null)
			{
				cellInstantiated = true;
				return MoveResolution.create(ParticleAction.CREATE_CELL).forPlayer(this).addToAttack(1).addToDefense(1).toCell(nc);
			}
		resetState();
		return MoveResolution.create(ParticleAction.INVALID_MOVE);
	}
	
	public void touchDragged(InputEvent event , float x , float y , int pointer)
	{
		dragging = true;
	}
	
	/**
	 * Resets values of helper fields for player
	 */
	public void resetState()
	{
		previousCell = null;
		dragging = false;
	}
	
	public ParticleColor getColor()
	{
		return color;
	}
	
	/* probna metoda */
	public void reset()
	{
		cellInstantiated = false;
	}
	
}

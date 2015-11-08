package com.blackflamegamez.game;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.blackflamegamez.game.enums.ParticleAction;
import com.blackflamegamez.game.enums.ParticleColor;
import com.blackflamegamez.game.enums.PlayerState;
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
	private boolean         dragging;
	private PlayerState     state;
	
	public Player(ParticleColor color , GameBoard board)
	{
		this.color = color;
		this.board = board;
		this.state = PlayerState.INSTANTIATING;
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
		if(nc != null && state == PlayerState.INSTANTIATING)
		{
			if(nc.getParticle() == null)
			{
				state = PlayerState.WAITING_FOR_COMMAND;
				resetState();
				return MoveResolution.create(ParticleAction.CREATE_CELL).forPlayer(this).addToAttack(1).addToDefense(1).toCell(nc);
			} else {
				resetState();
				return MoveResolution.create(ParticleAction.INVALID_MOVE);
			}
		}  else if (nc != null && state == PlayerState.WAITING_FOR_COMMAND) {
			resetState();
			return MoveResolution.create(ParticleAction.CREATE_DIALOG).forPlayer(this).toCell(nc);
		} else {
			resetState();
			return MoveResolution.create(ParticleAction.INVALID_MOVE);
		}
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
	
	public void setState(PlayerState s)
	{
		state = s;
	}
	
	/* probna metoda */
	public void reset()
	{
		state = PlayerState.INSTANTIATING;
	}
	
}

package com.blackflamegamez.game.gameactions;

import com.blackflamegamez.game.Cell;
import com.blackflamegamez.game.Player;
import com.blackflamegamez.game.enums.ParticleAction;
import com.blackflamegamez.game.enums.ParticleColor;

/**Move resolution using builder pattern model
 * @author Milan Topalovic
 * 
 */
public class MoveResolution
{
	
	private ParticleAction action;
	private Player         player;
	private Cell           targetCell;
	private Cell           startingCell;
	private int            defense;
	private int            attack;
	
	private MoveResolution(ParticleAction action)
	{
		this.action = action;
	}
	
	/**Creates new move action using {@link ParticleAction}
	 * @param action action to take
	 * @return new action
	 */
	public static MoveResolution create(ParticleAction action)
	{
		return new MoveResolution(action);
	}
	
	public MoveResolution addToDefense(int volume)
	{
		defense = volume;
		return this;
	}
	
	public MoveResolution addToAttack(int volume)
	{
		attack = volume;
		return this;
	}
	
	public MoveResolution forPlayer(Player p)
	{
		player = p;
		return this;
	}
	
	public MoveResolution toCell(Cell target)
	{
		targetCell = target;
		return this;
	}
	
	public MoveResolution fromCell(Cell start)
	{
		startingCell = start;
		return this;
	}
	
	public ParticleAction getAction()
	{
		return action;
	}
	
	public Cell getTargetCell()
	{
		return targetCell;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public int getDefense()
	{
		return defense;
	}
	
	public int getAttack()
	{
		return attack;
	}
	
	public Cell getStartCell()
	{
		return startingCell;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder(action.toString() + " ");
		switch(action)
		{
			case ATTACK:
				sb.append(startingCell.getRow() + " " + startingCell.getCol() + " " + targetCell.getRow() + " " + targetCell.getCol());
				break;
			case CREATE_CELL:
				sb.append(ParticleColor.getColorValue(player.getColor()) + " " + targetCell.getRow() + " " + targetCell.getCol() + " " + attack + " " + defense);
				break;
			case REMOVE_ATK_DEF:
				sb.append(targetCell.getRow() + " " + targetCell.getCol() + attack + " " + defense);
				break;
			case UPGRADE:
				sb.append(targetCell.getRow() + " " + targetCell.getCol() + attack + " " + defense);
				break;
			case INVALID_MOVE:
				break;
		}
		return sb.toString();
	}
}

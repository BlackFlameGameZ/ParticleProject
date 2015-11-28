package com.blackflamegamez.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blackflamegamez.game.enums.ParticleAction;
import com.blackflamegamez.game.gameactions.MoveResolution;

/**
 * @author Milan Topalovic
 *
 */
public class GameBoard extends GenericBoard {

	float x , y , rectWidth , vPadding , hPadding;
	
	public GameBoard(float x , float y , float rectWidth , float hPadding , float vPadding)
	{
		this.x = x;
		this.y = y;
		this.rectWidth = rectWidth;
		this.hPadding  = hPadding;
		this.vPadding  = vPadding;
		makeCells();
		determineNeighbours();
	}
	
	@Override
	public void makeCells() 
	{
		for(int i = -4 ; i <= 4 ; ++i)
			for(int j = 0 ; j < 9 - Math.abs(i) ; ++j)
				addCell(createCellForRowCol( i , j , x , y , rectWidth , hPadding , vPadding));
	}

	private Cell createCellForRowCol(int row , int col , float x , float y , float rectWidth , float hPadding , float vPadding)
	{
		int shiftX = 4 - Math.abs(row);
		x = x - shiftX * (rectWidth / 2 + hPadding / 2) + col * (rectWidth + hPadding);
		y = y - (4 + row) * (vPadding + rectWidth / 4 + rectWidth / 2);
		Hexagon h = new Hexagon(x , y , rectWidth);
		Cell c    = new Cell(x , y - rectWidth / 4 , rectWidth , rectWidth , 5 + row , col + 1 ,  h);
		System.out.println(y + " " + (y - rectWidth / 4));
		return c;
	}
	
	@Override
	public void determineNeighbours() 
	{
		for(Cell c : board)
		{
			ArrayList<Cell> n = ParticleUtil.getNeighboursForCell(c, board);
			c.setNeighbours(n);
		}
	}
	
	public void render(SpriteBatch batch , float delta)
	{
		for(Cell c : board)
			c.render(batch, delta);
	}
	
	public void debug(ShapeRenderer sr)
	{
		for(Cell c : board)
			c.getHexagon().debug(sr);
	}

	public Cell getCellForCoordinates(float x2, float y2) 
	{
		for(Cell c : board)
			if(c.contains(x2, y2))
				return c;
		return null;
	}
	
	public boolean resolveCommand(MoveResolution move)
	{
		if(move.getAction() == ParticleAction.INVALID_MOVE)
			return false;
		if(move.getAction() == ParticleAction.CREATE_CELL)
			return createCell(move);
		if(move.getAction() == ParticleAction.UPGRADE)
			return upgradeCell(move);
		if(move.getAction() == ParticleAction.SPLIT)
			return split(move);
		if(move.getAction() == ParticleAction.ATTACK)
			return attack(move);
		else
			return false;
	}
	
	private boolean createCell(MoveResolution move)
	{
		Player p = move.getPlayer();
		Cell targetCell = move.getTargetCell();
		int attack = move.getAttack();
		int defense = move.getDefense();
		targetCell.createParticle(p , attack , defense);
		return true;
	}
	
	private boolean upgradeCell(MoveResolution move)
	{
		Player p = move.getPlayer();
		Cell targetCell = move.getTargetCell();
		if(targetCell.getParticle() == null)
			return false;
		if(targetCell.getParticle().getOwner() != p)
			return false;
		int tatk = targetCell.getParticle().getAttackLvl();
		int tdef = targetCell.getParticle().getDefenseLvl();
		if(tatk + move.getAttack() > 5 || tdef + move.getDefense() > 5)
			return false;
		targetCell.upgradeParticle(move.getAttack(), move.getDefense());
		return true;
	}
	
	private boolean split(MoveResolution move)
	{
		Player p 		  = move.getPlayer();
		Cell targetCell   = move.getTargetCell();
		Cell startingCell = move.getStartCell();
		int  attack       = move.getAttack();
		int  defense      = move.getDefense();
		if(startingCell.getParticle() == null || !startingCell.hasNeighbour(targetCell))
			return false;
		if(targetCell.getParticle() != null)
			if(startingCell.getParticle().getCellOwner() != targetCell.getParticle().getCellOwner())
				return false;
		int ta = 0 , td = 0 , sd = startingCell.getParticle().getDefenseLvl();
		if(sd - defense < 1)
			return false;
		if(targetCell.getParticle() != null)
		{
			ta = targetCell.getParticle().getAttackLvl();
			td = targetCell.getParticle().getDefenseLvl();
			if(ta + attack > 5 || td + defense > 5 ) //we can't have more than 5 defense or attack units per cell and likewise we can't have less than one defense unit per cell
				return false;
			targetCell.upgradeParticle(attack, defense);
			startingCell.downgradeParticle(attack, defense);
			return true;
		} else {
			targetCell.createParticle(p, attack, defense);
			startingCell.downgradeParticle(attack, defense);
			return true;
		}
	}
	
	public boolean attack(MoveResolution move)
	{
		Player p = move.getPlayer();
		Cell   startingCell = move.getStartCell();
		Cell   targetCell   = move.getTargetCell();
		int    attack       = move.getAttack();
		int    defense  	= move.getDefense();
		if(startingCell.getParticle() == null || targetCell.getParticle() == null || !startingCell.hasNeighbour(targetCell))
			return false;
		if(startingCell.getParticle().getCellOwner() == targetCell.getParticle().getCellOwner()) //same cell owners so attack upgrade should fallow
		{
			int sa = startingCell.getParticle().getAttackLvl() , ta = targetCell.getParticle().getAttackLvl();
			if(sa == 0 || ta + 1 > 5)
				return false;
			startingCell.downgradeParticle(attack, defense);
			targetCell.upgradeParticle(attack, defense);
			return true;
		} else {
			//TODO: attacking opponents cell
			return true;
		}
	}
}

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
	
	public void resolveCommand(MoveResolution move)
	{
		if(move.getAction() == ParticleAction.INVALID_MOVE)
			return;
		if(move.getAction() == ParticleAction.CREATE_CELL)
			createCell(move);
	}
	
	private void createCell(MoveResolution move)
	{
		Player p = move.getPlayer();
		Cell targetCell = move.getTargetCell();
		int attack = move.getAttack();
		int defense = move.getDefense();
		targetCell.createParticle(p , attack , defense);
	}

}

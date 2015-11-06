package com.blackflamegamez.game;

import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;

import java.util.ArrayList;
import com.badlogic.gdx.math.Vector2;

public class ParticleUtil 
{
	/*
	 * We take most top and bottom point of hexagon and we move it's x cordinate by -padding and +padding
	 * and check if there is some other cell which contains that new point(meaning it's neighbour of cell from which we started)
	 * It does similar this to determine his left and right neighbour taking most left bottom point and most right bottom point
	 */
	public static ArrayList<Cell> getNeighboursForCell(Cell c , ArrayList<Cell> grid)
	{
		ArrayList<Cell> neighbours = new ArrayList<Cell>();
		float padding = 30f * hRatio;
		Vector2 bottom = c.getHexagon().getVertices().get(1);
		Vector2 top    = c.getHexagon().getVertices().get(4);
		Vector2 left   = c.getHexagon().getVertices().get(0);
		Vector2 right  = c.getHexagon().getVertices().get(2);
		for(Cell nc : grid)
		{
			if(nc.contains(bottom.x + padding, bottom.y))
				neighbours.add(nc);
			if(nc.contains(bottom.x - padding, bottom.y))
				neighbours.add(nc);
			if(nc.contains(top.x - padding, top.y))
				neighbours.add(nc);
			if(nc.contains(top.x + padding, top.y))	
				neighbours.add(nc);
			if(nc.contains(left.x - padding , left.y))
				neighbours.add(nc);
			if(nc.contains(right.x + padding, right.y))
				neighbours.add(nc);
		}
		return neighbours;
	}
}

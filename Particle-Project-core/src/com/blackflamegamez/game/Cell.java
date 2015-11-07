package com.blackflamegamez.game;

import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;
import static com.blackflamegamez.game.staticfields.GameStaticValues.ratioDifference;
import static com.blackflamegamez.game.staticfields.GameStaticValues.vRatio;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	private Particle particle;
	private float tmpx;
	private float tmpy;
	
	public Cell(float x , float y , float width , float height , int row , int col , Hexagon hexagon)
	{
		super(x , y , width , height);
		tmpx = x;
		tmpy = y;
		this.row      = row;
		this.col	  = col;
		this.hexagon  = hexagon;
		this.neighbours = new ArrayList<Cell>();
		this.particle = null;
	}
	
	public void createParticle(Player p, int attack, int defense) {
		if(particle != null)
			return;
		float x1 = x/hRatio;
		float y1 = (y + ratioDifference)/vRatio;
		x1 -= 30f;
		y1 -= 30f;
		particle = new Particle(p , x1, y1, 240f, defense, attack);
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
	
	public void render(SpriteBatch batch , float delta)
	{
		if(particle != null)
			particle.render(batch, delta);
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
	
	public Particle getParticle()
	{
		return particle;
	}
	
	public void debug(ShapeRenderer sr)
	{
		hexagon.debug(sr);
	}
}

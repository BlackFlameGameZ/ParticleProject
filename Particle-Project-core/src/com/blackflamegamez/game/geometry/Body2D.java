package com.blackflamegamez.game.geometry;


/**
 * @author Milan Topalovic
 * Purpose of this class is to contain coordinates of object's which are rendered on the screen 
 */
public class Body2D 
{

	protected float x;
	protected float y;
	protected float width;
	protected float height;
	
	public Body2D(float x , float y , float width , float height)
	{
		this.x = x;
		this.x = y;
		this.width = width;
		this.height = height;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getWidht()
	{
		return width;
	}
	
	public float getHeight()
	{
		return height;
	}
	
	public void setX(float x)
	{
		this.x = x;
	}
	
	public void setY(float y)
	{
		this.y = y;
	}
	
	public void setWidth(float width)
	{
		this.width = width;
	}
	
	public void setHeight(float height)
	{
		this.height = height;
	}
}

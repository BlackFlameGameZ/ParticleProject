package com.blackflamegamez.game.geometry;
import static com.blackflamegamez.game.staticfields.GameStaticValues.*;


/**Purpose of this class is to contain coordinates of object's which are rendered on the screen
 * @author Milan Topalovic
 */
public class Body2D 
{

	protected float x;
	protected float y;
	protected float width;
	protected float height;
	
	/**
	 * Translates coordinates given in parameter list
	 * @param x not translated bottom left x coordinate of body
	 * @param y not translated bottom left y coordinate of body
	 * @param width not scaled width of body
	 * @param height not scaled height of body
	 */
	public Body2D(float x , float y , float width , float height)
	{
		this.x = x * hRatio;
		this.y = y * vRatio - ratioDifference;
		this.width = width * hRatio;
		this.height = height * hRatio;
	}
	
	/**
	 * Sets body coordinates to given parameters. Coordinates given should be precalculated or in other words they 
	 * should correspond to translated coordinates
	 * @param nx translated bottom left x coordinate of body
	 * @param ny translated bottom left y coordinate of body
	 * @param nw scaled width of body
	 * @param nh scaled height of body
	 */
	public void setWorldCoordinates(float nx , float ny , float nw , float nh)
	{
		x = nx;
		y = ny;
		width = nw;
		height = nh;
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

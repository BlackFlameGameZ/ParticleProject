package com.blackflamegamez.game.geometry;
import static com.blackflamegamez.game.staticfields.GameStaticValues.*;

import com.blackflamegamez.game.uielements.GenericDialog;


/**Purpose of this class is to contain coordinates of object's which are rendered on the screen
 * @author Milan Topalovic
 * 
 */
public class Body2D 
{

	protected float x;
	protected float y;
	protected float width;
	protected float height;
	
	public Body2D(float x , float y , float width , float height)
	{
		this.x = x * hRatio;
		this.y = y * vRatio - ratioDifference;
		this.width = width * hRatio;
		this.height = height * hRatio;
	}
	
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

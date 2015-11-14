package com.blackflamegamez.game;

import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;
import static com.blackflamegamez.game.staticfields.GameStaticValues.vRatio;
import static com.blackflamegamez.game.staticfields.GameStaticValues.ratioDifference;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
public class Hexagon 
{
	private Array<Vector2> vertices;
	
	public Hexagon(float x1 , float y1 , float rectWidth)
	{
		setNormal(x1, y1, rectWidth);
	}
	
	public Hexagon(float x1 , float y1 , float x2 , float y2 , float rectWidth)
	{
		setRotated(x1, y1, x2, y2, rectWidth);
	}
	
	public void setNormal(float x1 , float y1 , float rectWidth)
	{
		float newWidth = rectWidth * hRatio;
		x1 *= hRatio;
		y1 = y1 * vRatio - ratioDifference;
		Float[] arr = new Float[]{x1 , y1 , x1 + newWidth / 2 , y1 - newWidth / 4 , x1 + newWidth , y1 , x1 + newWidth , y1 + newWidth / 2 , x1 + newWidth/2 , y1 + newWidth/2 + newWidth/4 , x1 , y1 + newWidth/2};
		calculateVertices(arr);
	}
	
	public void setRotated(float x1 , float y1 , float x2 , float y2 , float rectWidth)
	{
		float newWidth = rectWidth * hRatio;
		x1 *= hRatio;
		x2 *= hRatio;
		y1 *= vRatio;
		y1 -= ratioDifference;
		y2 *= vRatio;
		y2 -= ratioDifference;
		
		// DEBUG  System.out.println("(x1, y1) => (" + x1 + ", " + y1 + "\n(x2, y2) => (" + x2 + ", " + y2 + ")\nratioDifference => " + ratioDifference);
		
		Float[] arr = new Float[]{x1 , y1 , x2 , y2 , x2 + newWidth , y2 , x1 + 2 * (x2 - x1) + newWidth , y1 , x2 + newWidth , y1 + y1 - y2 , x2 , y1 + y1 - y2};
		calculateVertices(arr);
	}
	
	private void calculateVertices(Float[] arr)
	{
		vertices = new Array<Vector2>();
		for(int i = 0 ; i < arr.length ; i += 2)
			vertices.add(new Vector2(arr[i] , arr[i+1]));
	}
	
	public boolean contains(float x , float y)
	{
		return Intersector.isPointInPolygon(vertices, new Vector2(x , y));
	}

	public void debug(ShapeRenderer sr)
	{	
		for(int i = 0 ; i < vertices.size - 1 ; ++i)
			sr.line(vertices.get(i) , vertices.get(i + 1));	
		sr.line(vertices.get(vertices.size - 1) , vertices.get(0));
	}
	
	public Array<Vector2> getVertices()
	{
		return vertices;
	}
	
}

package com.blackflamegamez.game;

import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;
import static com.blackflamegamez.game.staticfields.GameStaticValues.ratioDifference;
import static com.blackflamegamez.game.staticfields.GameStaticValues.vRatio;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
public class Hexagon 
{

	private Array<Vector2> vertices;

	
	public Hexagon(float x1 , float y1 , float rectWidth)
	{
		float newWidth = rectWidth * hRatio;
		x1 *= hRatio;
		y1 = y1 * vRatio - 3 * newWidth / 4;
		Float[] arr = new Float[]{x1 , y1 , x1 + newWidth / 2 , y1 - newWidth / 4 , x1 + newWidth , y1 , x1 + newWidth , y1 + newWidth / 2 , x1 + newWidth/2 , y1 + newWidth/2 + newWidth/4 , x1 , y1 + newWidth/2};
		calculateVertices(arr);
	}
	
	public Hexagon(float x1 , float y1 , float x2 , float y2 , float rectWidth)
	{
		float newWidth = rectWidth * hRatio;
		x1 *= hRatio;
		x2 *= hRatio;
		y1 *= vRatio;
		y2 *= vRatio;
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
		for(int i = 0 ; i < vertices.size() - 1 ; ++i)
			sr.line(vertices.get(i) , vertices.get(i + 1));	
		sr.line(vertices.get(vertices.size() - 1) , vertices.get(0));
	}
	
}

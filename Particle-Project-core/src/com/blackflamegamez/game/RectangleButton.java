package com.blackflamegamez.game;

import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;
import static com.blackflamegamez.game.staticfields.GameStaticValues.ratioDifference;
import static com.blackflamegamez.game.staticfields.GameStaticValues.vRatio;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.blackflamegamez.game.geometry.Body2D;

public class RectangleButton
{
	private Texture normalTexture;
	private Texture pressedTexture;
	private Array<Vector2> 	vertices;
	private boolean 		isPressed;
	private boolean 		isDisabled;
	private float 			x;
	private float 			y;
	private float 			width;
	private float 			height;
	
	public RectangleButton(Texture normal, Texture pressed, float x, float y, float width, float height) 
	{
		normalTexture 	= normal;
		pressedTexture 	= pressed;
		isPressed 		= false;
		isDisabled 		= false;
		
		this.x 		= x * hRatio;
		this.y 		= (y * vRatio) - 2 * ratioDifference;
		this.width 	= width * hRatio;
		this.height = height * hRatio;
		System.out.println("Y: " + this.y);
		System.out.println("Ratio diff: " + ratioDifference);
		Float[] arr = new Float[]{this.x, this.y, this.x, this.y + this.height, this.x + this.width, this.y + this.height, this.x + this.width, this.y};
		calculateVertices(arr);
	}
	
	public void render(SpriteBatch batch)
	{
		if(isDisabled)
		{
			Color c = batch.getColor();
			batch.setColor(c.r, c.g, c.b, 0.5f);
			batch.draw(normalTexture, x, y, width, height);
			batch.setColor(c);
		}
		else
		{
			if(isPressed)
				batch.draw(pressedTexture, x, y, width, height);
			else
				batch.draw(normalTexture, x, y, width, height);
		}
	}
	
	public void setDisabled(boolean isDisabled) 
	{
		this.isDisabled = isDisabled;
	}
	
	public void setPressed(boolean isPressed) 
	{
		this.isPressed = isPressed;
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

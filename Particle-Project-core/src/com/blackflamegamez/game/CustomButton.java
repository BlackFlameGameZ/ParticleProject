package com.blackflamegamez.game;

import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;
import static com.blackflamegamez.game.staticfields.GameStaticValues.ratioDifference;
import static com.blackflamegamez.game.staticfields.GameStaticValues.vRatio;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CustomButton extends Hexagon
{
	private Texture normalTexture;
	private Texture pressedTexture;
	private boolean isPressed;
	private boolean isDisabled;
	private float x;
	private float y;
	
	public CustomButton(Texture normal, Texture pressed, float x, float y) 
	{
		super(x + 15, y + 85, x + 50, y + 156, 510);
		normalTexture = normal;
		pressedTexture = pressed;
		isDisabled = false;
		this.x = x;
		this.y = y;
	}
	
	public void render(SpriteBatch batch)
	{
		if(isDisabled)
		{
			Color c = batch.getColor();
			batch.setColor(c.r, c.g, c.b, 0.5f);
			batch.draw(normalTexture, x * hRatio, y * vRatio - ratioDifference, 610 * hRatio, 172 * hRatio);
			batch.setColor(c);
		}
		else
		{
			if(isPressed)
				batch.draw(pressedTexture, x * hRatio, y * vRatio - ratioDifference, 610 * hRatio, 172 * hRatio);
			else
				batch.draw(normalTexture, x * hRatio, y * vRatio - ratioDifference, 610 * hRatio, 172 * hRatio);
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
}

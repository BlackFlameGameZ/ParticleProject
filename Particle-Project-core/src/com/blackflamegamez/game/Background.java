package com.blackflamegamez.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import static com.blackflamegamez.game.staticfields.GameStaticValues.*;

/**
 * @author BlackFlame
 *
 * Backgroud class used to store background texture and base color
 */

public class Background 
{
	private Texture backgroundImage;
	private Color colorBase;
	
	public Background(Texture backgroundImage, Color colorBase) 
	{
		super();
		this.backgroundImage = backgroundImage;
		this.colorBase = colorBase;
	}

	public void render(SpriteBatch batch)
	{
		batch.draw(backgroundImage, 0, -ratioDifference, hRatio * 2560, hRatio * 1600);
	}
	
	public Color getColorBase() 
	{
		return colorBase;
	}
}

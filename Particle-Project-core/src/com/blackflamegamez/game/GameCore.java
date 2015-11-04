package com.blackflamegamez.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blackflamegamez.gameScreens.LoadingScreen;

public class GameCore extends Game 
{
	private SpriteBatch batch;
	
	public static float defaultWidth 	= 2560f;
	public static float defaultHeight 	= 1600f;
	
	public static float realWidth;
	public static float realHeight;
	public static float vRatio;
	public static float hRatio;
	public static float ratioDifference;
	
	@Override
	public void create() 
	{
		System.out.println("GameCore create()");
		calculateScreen();
		batch = new SpriteBatch();
		Assets.load();
		setScreen(new LoadingScreen(batch));
	}
	
	
	
	private void calculateScreen()
	{
		realWidth 	= Gdx.graphics.getWidth();
		realHeight 	= Gdx.graphics.getHeight();
		float tmpRatio = realWidth / realHeight;
		if(tmpRatio == Ratios._16_10)
		{
			hRatio = realWidth / defaultWidth;
			vRatio = hRatio;
			ratioDifference = 0;
		}
		else if(tmpRatio == Ratios._16_9)
		{
			hRatio = realWidth / defaultWidth;
			vRatio = hRatio;
			ratioDifference = (int)(realHeight / 16 * 10 - realHeight / 16 * 9);
		}
		else
		{
			hRatio = realWidth / defaultWidth;
			vRatio = realHeight / defaultHeight;
			ratioDifference = (int)(realHeight / 16 * 10 - realHeight / 16 * 9);
		}
		
		System.out.println("Ratio difference: " + ratioDifference);
		System.out.println("Real width: " + realWidth);
		System.out.println("Real height: " + realHeight);
	}
}

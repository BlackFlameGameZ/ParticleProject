package com.blackflamegamez.game.staticfields;

import com.badlogic.gdx.Gdx;
import com.blackflamegamez.game.GameCore;
import com.blackflamegamez.game.Ratios;

public class GameStaticValues 
{
	
	public static float defaultWidth = GameCore.defaultWidth;
	public static float defaultHeight= GameCore.defaultHeight;
	
	public static float realWidth;
	public static float realHeight;
	public static float vRatio;
	public static float hRatio;
	public static float ratioDifference;
	
	public static void calculate()
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

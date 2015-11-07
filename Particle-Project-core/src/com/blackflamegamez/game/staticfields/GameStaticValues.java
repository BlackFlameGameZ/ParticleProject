package com.blackflamegamez.game.staticfields;

import static com.blackflamegamez.game.staticfields.GameStaticValues.defaultHeight;

import com.badlogic.gdx.Gdx;
import com.blackflamegamez.game.Ratios;

public class GameStaticValues 
{
	
	public static float defaultWidth 	= 2560;
	public static float defaultHeight	= 1600;
	
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
		System.out.println("Tmp ratio: " + tmpRatio);
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
			ratioDifference = (int)(realHeight / 9 * 10 - realHeight) / 2;
		}
		else
		{
			hRatio = realWidth / defaultWidth;
			vRatio = hRatio;
			ratioDifference = (int)(realHeight - defaultHeight * hRatio) / 2;
			if(tmpRatio < Ratios._16_9)
			{
				ratioDifference = -ratioDifference;
				System.out.println("Vece !!!");
			}
		}
		
		System.out.println("Ratio difference: " + ratioDifference);
		System.out.println("Real width: " + realWidth);
		System.out.println("Real height: " + realHeight);
	}
	
	/**
	 * Some constants needed for GameBoard class
	 */
	public static float starting_x = 806f;
	public static float starting_y = defaultHeight - 261f;
	public static float rect_width = 180f;
	public static float h_padding  = 12f;
	public static float v_padding  = 11f;
}

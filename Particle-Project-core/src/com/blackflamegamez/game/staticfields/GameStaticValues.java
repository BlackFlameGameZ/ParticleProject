package com.blackflamegamez.game.staticfields;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.blackflamegamez.game.Assets;
import com.blackflamegamez.game.Background;
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
	
	public static Background[] backgroundArray;
	public static Background 	background;
	public static Skin 		skin;
	public static Color 	originalColor;
	
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
	
	public static void loadBackground()
	{
		backgroundArray = new Background[]
		{
			new Background(Assets.manager.get("images/backgrounds/u_1.jpg", Texture.class), new Color(187f/255f, 0f, 1f, 1f)), 
			new Background(Assets.manager.get("images/backgrounds/u_2.jpg", Texture.class), new Color(0f, 137f/255f, 1f, 1f)),
			new Background(Assets.manager.get("images/backgrounds/u_3.jpg", Texture.class), new Color(0f, 179f/255f, 230f/255f, 1f)),
			new Background(Assets.manager.get("images/backgrounds/u_4.jpg", Texture.class), new Color(0f, 47f/255f, 1f, 1f)),
			new Background(Assets.manager.get("images/backgrounds/u_5.jpg", Texture.class), new Color(1f, 47f/255f, 0f, 1f))
		};
		background 	= backgroundArray[4];
		skin 		= Assets.manager.get("files/uiskin.json", Skin.class);
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

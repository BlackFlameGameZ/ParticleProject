package com.blackflamegamez.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets 
{
	public static AssetManager manager = new AssetManager();
	
	public static void load()
	{
		manager.load("images/particle/base.png", Texture.class);
		//komentar
		for(int i = 1 ; i < 6 ; i++)
		{
			manager.load("images/particle/atk_" + i + ".png", Texture.class);
			manager.load("images/particle/def_" + i + ".png", Texture.class);
		}
		
		manager.load("images/splash_#[625,493].png", Texture.class);
		manager.load("images/board_grid.png", Texture.class);
		manager.load("images/board_1.png", Texture.class);
		manager.load("images/board_2.png", Texture.class);
		manager.load("files/uiskin.json", Skin.class);
	}
}

package com.blackflamegamez.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blackflamegamez.game.staticfields.GameStaticValues;

public class GameCore extends Game 
{
	private SpriteBatch batch;
	
	private GameManager gameManager;
	private static Texture background;
	
	@Override
	public void create() 
	{
		GameStaticValues.calculate();
		batch = new SpriteBatch();
		gameManager = new GameManager(this , batch);
		Assets.load();
		gameManager.setLoadingScreen();
	}
	
	public GameManager getGameManager()
	{
		return gameManager;
	}
	
	public static void setBackground(Texture background) 
	{
		GameCore.background = background;
	}
	
	public static Texture getBackground()
	{
		return background;
	}
	
	@Override
	public void dispose() 
	{
		Assets.manager.dispose();
	}
}

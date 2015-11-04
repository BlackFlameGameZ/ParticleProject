package com.blackflamegamez.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blackflamegamez.game.staticfields.GameStaticValues;

public class GameCore extends Game 
{
	private SpriteBatch batch;
	
	private GameManager gameManager;
	
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
}

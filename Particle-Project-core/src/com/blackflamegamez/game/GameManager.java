package com.blackflamegamez.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blackflamegamez.gameScreens.LoadingScreen;
import com.blackflamegamez.gameScreens.SplashScreen;


/**
 * @author Milan Topalovic
 *
 */
public class GameManager 
{
	private SpriteBatch batch;
	private Screen      loadingScreen;
	private Screen      splashScreen;
	private Screen      currentScreen;
	private GameCore    game;
	
	public GameManager(GameCore game , SpriteBatch batch)
	{
		this.game  = game;
		this.batch = batch;
		this.loadingScreen = null;
		this.splashScreen  = null;
		this.currentScreen = game.getScreen();
	}
	
	public SpriteBatch getSpriteBatch()
	{
		return batch;
	}
	
	public Screen getCurrentScreen()
	{
		return currentScreen;
	}
	
	public void setLoadingScreen()
	{
		if(loadingScreen == null)
			loadingScreen = new LoadingScreen(batch);
		currentScreen = loadingScreen;
		game.setScreen(currentScreen);
	}
	
	public void setSplashScreen()
	{
		if(splashScreen == null)
			splashScreen = new SplashScreen(batch);
		currentScreen = splashScreen;
		game.setScreen(currentScreen);
	}
}

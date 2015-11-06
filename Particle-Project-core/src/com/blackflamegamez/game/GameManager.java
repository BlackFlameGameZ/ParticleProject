package com.blackflamegamez.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blackflamegamez.gameScreens.LoadingScreen;
import com.blackflamegamez.gameScreens.MainMenuScreen;
import com.blackflamegamez.gameScreens.ParticleGameScreen;
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
	private Screen		mmScreen;
	private Screen		playScreen;
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
	
	public void setMainMenuScreen()
	{
		if(mmScreen == null)
			mmScreen = new MainMenuScreen(batch);
		currentScreen = mmScreen;
		game.setScreen(currentScreen);
	}
	
	public void setPlayScreen()
	{
		if(playScreen == null)
			playScreen = new ParticleGameScreen(batch);
		currentScreen = playScreen;
		game.setScreen(currentScreen);
	}
}

package com.blackflamegamez.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blackflamegamez.game.interfaces.PBluetooth;
import com.blackflamegamez.gameScreens.LoadingScreen;
import com.blackflamegamez.gameScreens.MainMenuScreen;
import com.blackflamegamez.gameScreens.OptionsScreen;
import com.blackflamegamez.gameScreens.ParticleGameScreen;
import com.blackflamegamez.gameScreens.PlayScreen;
import com.blackflamegamez.gameScreens.SplashScreen;


/**
 * @author Milan Topalovic
 *
 */
public class GameManager 
{
	private SpriteBatch batch;
	private PBluetooth  bluetoothI;
	private Screen      loadingScreen;
	private Screen      splashScreen;
	private Screen		mmScreen;
	private Screen		gameScreen;
	private Screen		playScreen;
	private Screen 		optionsScreen;
	private Screen      currentScreen;
	private GameCore    game;
	
	public GameManager(GameCore game , SpriteBatch batch , PBluetooth bluetoothI)
	{
		this.game  		= game;
		this.batch 		= batch;
		this.bluetoothI = bluetoothI;
		loadingScreen 	= null;
		splashScreen 	= null;
		mmScreen 		= null;
		gameScreen 		= null;
		playScreen		= null;
		optionsScreen	= null;
		
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
			mmScreen = new MainMenuScreen(batch , bluetoothI);
		currentScreen = mmScreen;
		game.setScreen(currentScreen);
	}
	
	public void setGameScreen()
	{
		if(gameScreen == null)
			gameScreen = new ParticleGameScreen(batch);
		currentScreen = gameScreen;
		game.setScreen(currentScreen);
	}
	
	public void setPlayScreen() 
	{
		if(playScreen == null)
			playScreen = new PlayScreen(batch);
		currentScreen = playScreen;
		game.setScreen(currentScreen);
	}
	
	public void setOptionsScreen() 
	{
		if(optionsScreen == null)
			optionsScreen = new OptionsScreen(batch);
		currentScreen = optionsScreen;
		game.setScreen(currentScreen);
	}
}

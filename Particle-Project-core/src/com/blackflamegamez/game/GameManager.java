package com.blackflamegamez.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blackflamegamez.game.interfaces.PBluetooth;
import com.blackflamegamez.gameScreens.Game;
import com.blackflamegamez.gameScreens.Loading;
import com.blackflamegamez.gameScreens.MainMenu;
import com.blackflamegamez.gameScreens.Play;
import com.blackflamegamez.gameScreens.Splash;


/**
 * @author Milan Topalovic
 *
 */
public class GameManager 
{
	private SpriteBatch batch;
	private GameCore    core;
	
	private Screen      loading;
	private Screen      splash;
	private Screen		main_menu;
	private Screen		play;
	private Screen		game;
	private Screen      currentScreen;
	
	public GameManager(GameCore core , SpriteBatch batch , PBluetooth bluetoothI)
	{
		this.core  		= core;
		this.batch 		= batch;
		
		loading 		= null;
		splash 			= null;
		main_menu 		= null;
		play 			= null;
		game			= null;
		currentScreen 	= core.getScreen();
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
		if(loading == null)
			loading = new Loading(batch);
		currentScreen = loading;
		core.setScreen(currentScreen);
	}
	
	public void setSplashScreen()
	{
		if(splash == null)
			splash = new Splash(batch);
		currentScreen = splash;
		core.setScreen(currentScreen);
	}
	
	public void setMainMenuScreen()
	{
		if(main_menu == null)
			main_menu = new MainMenu(batch);
		currentScreen = main_menu;
		core.setScreen(currentScreen);
	}
	
	public void setPlayScreen()
	{
		if(play == null)
			play = new Play(batch);
		currentScreen = play;
		core.setScreen(currentScreen);
	}
	
	public void setGameScreen()
	{
		if(game == null)
			game = new Game(batch, 2);
		currentScreen = game;
		core.setScreen(currentScreen);
	}
}

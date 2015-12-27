package com.blackflamegamez.gameScreens;

import static com.blackflamegamez.game.staticfields.GameStaticValues.background;
import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;
import static com.blackflamegamez.game.staticfields.GameStaticValues.ratioDifference;
import static com.blackflamegamez.game.staticfields.GameStaticValues.vRatio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.blackflamegamez.game.Assets;
import com.blackflamegamez.game.BoxType;
import com.blackflamegamez.game.GameCore;
import com.blackflamegamez.game.MainMenuButton;
import com.blackflamegamez.game.MessageBox;
import com.blackflamegamez.game.input.CustomInputListener;
import com.blackflamegamez.game.input.Touchable;
import com.blackflamegamez.game.interfaces.PBluetooth;

/**
 * @author BlackFlame
 *
 * MainMenu screen
 */

public class MainMenu extends ScreenAdapter implements Touchable
{
	private SpriteBatch 	batch;
	private Stage 			stage;
	
	float 					elapsedTime = 0f;
	boolean 				show = true;
	
	private MainMenuButton 	play;
	private MainMenuButton 	options;
	private MainMenuButton 	donate;
	private MainMenuButton 	quit;
	private MainMenuButton 	pressedButton;
	private boolean 		switchScreen;
	private boolean 		startingAnimation;
	
	public static MessageBox 		msgBox;
	
	public MainMenu(SpriteBatch batch) 
	{
		this.batch 	= batch;
		stage 		= new Stage();
		
		stage.addListener(new CustomInputListener(this));
		
		play 			= new MainMenuButton(Assets.manager.get("images/main_menu/play_text.png", Texture.class), -550, 862);
		options 		= new MainMenuButton(Assets.manager.get("images/main_menu/options_text.png", Texture.class), -550, 712);
		donate 			= new MainMenuButton(Assets.manager.get("images/main_menu/donate_text.png", Texture.class), -550, 562);
		quit 			= new MainMenuButton(Assets.manager.get("images/main_menu/quit_text.png", Texture.class), -550, 412);
		pressedButton 	= null;
		
		msgBox 			= new MessageBox(906, 300);
		switchScreen	= false;
		
		
		donate.setEnabled(false);
	}
	
	@Override
	public void show() 
	{
		Gdx.input.setInputProcessor(stage);
		System.out.println("Show menu");
		setClickable(false);
		quit.unhide(0);
		donate.unhide(10);
		options.unhide(20);
		play.unhide(30);
		startingAnimation = true;
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		elapsedTime += delta;
		batch.begin();
			background.render(batch);
			batch.draw(Assets.manager.get("images/main_menu/title.png", Texture.class), 114 * hRatio, 1070 * vRatio - ratioDifference, 700 * hRatio, 200 * hRatio);
			play.render(batch);
			options.render(batch);
			donate.render(batch);
			quit.render(batch);
			
			msgBox.render(batch);
			
		batch.end();
		
		if(startingAnimation && !play.isInAnimation())
			setClickable(true);
		
		if(switchScreen && !quit.isInAnimation())
		{
			switchScreen = false;
			((GameCore)Gdx.app.getApplicationListener()).getGameManager().setPlayScreen();
		}
	}
	
	private void setClickable(boolean clickable)
	{
		play.setClick_enabled(clickable);
		options.setClick_enabled(clickable);
		donate.setClick_enabled(clickable);
		quit.setClick_enabled(clickable);
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
	{
		if(play.contains(x, y))
		{
			play.setActivated(true);
			pressedButton = play;
		}
		else if(options.contains(x, y))
		{
			options.setActivated(true);
			options.setBlockedActivation(true);
			pressedButton = options;
		}
		else if(donate.contains(x, y))
		{
			donate.setActivated(true);
			pressedButton = donate;
		}
		else if(quit.contains(x, y))
		{
			quit.setActivated(true);
			pressedButton = quit;
		}
		else if(msgBox.getMode() == BoxType.OPTIONS && !msgBox.contains(x, y))
		{
			play.unhide(0);
			donate.unhide(10);
			quit.unhide(20);
			msgBox.switchTo(BoxType.WELCOME);
			options.setBlockedActivation(false);
		}
		return true;
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
	{
		if(pressedButton != null)
		{
			if(pressedButton.contains(x, y))
			{
				if(pressedButton.equals(play))
				{
					System.out.println("Play");
					msgBox.switchTo(BoxType.WELCOME);
					play.hide(0, false);
					options.hide(10, false);
					donate.hide(20, false);
					quit.hide(30, false);
					switchScreen = true;
					setClickable(false);
				}
				else if(pressedButton.equals(options))
				{
					System.out.println("Options");
					msgBox.switchTo(BoxType.OPTIONS);
					play.hide(0, false);
					donate.hide(10, false);
					quit.hide(20, false);
					setClickable(false);
				}
				else if(pressedButton.equals(donate))
					System.out.println("Donate");
				else if(pressedButton.equals(quit))
					System.exit(0);
			}
			pressedButton.setActivated(false);
			pressedButton = null;
		}
	}

	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		// TODO Auto-generated method stub
		
	}
}

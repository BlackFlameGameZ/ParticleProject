package com.blackflamegamez.gameScreens;

import static com.blackflamegamez.game.staticfields.GameStaticValues.background;
import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;
import static com.blackflamegamez.game.staticfields.GameStaticValues.ratioDifference;
import static com.blackflamegamez.game.staticfields.GameStaticValues.vRatio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.blackflamegamez.game.Assets;
import com.blackflamegamez.game.BoxType;
import com.blackflamegamez.game.GameCore;
import com.blackflamegamez.game.MainMenuButton;
import com.blackflamegamez.game.MessageBox;
import com.blackflamegamez.game.input.CustomInputListener;
import com.blackflamegamez.game.input.Touchable;

public class Play extends ScreenAdapter implements Touchable
{
	private SpriteBatch 	batch;				// batch to draw to
	private Stage 			stage;				// stage for listening of mouse events
	
	private MainMenuButton 	vsAI;				// button for playing vs AI
	private MainMenuButton 	hotseat;			// button for playing hot seat
	private MainMenuButton 	online;				// button for playin online
	private MainMenuButton 	back;				// button for goin back to menu
	private MainMenuButton 	pressedButton;		// tmp last pressed button
	
	private MessageBox 		msgBox;				// message box
	
	private boolean 		switchBack;			// 
	private boolean 		switchToGame;		// 
	private boolean 		titleAnimation;
	
	private float 			titleOpacity;
	private float 			opacityStep;
	
	
	public Play(SpriteBatch batch) 
	{
		this.batch 	= batch;
		stage 		= new Stage();
		
		stage.addListener(new CustomInputListener(this));
		
		vsAI 			= new MainMenuButton(Assets.manager.get("images/play/versus_ai_text.png", Texture.class), -550, 862);
		hotseat 		= new MainMenuButton(Assets.manager.get("images/play/hot_seat_text.png", Texture.class), -550, 712);
		online 			= new MainMenuButton(Assets.manager.get("images/play/online_text.png", Texture.class), -550, 562);
		back 			= new MainMenuButton(Assets.manager.get("images/play/back_text.png", Texture.class), -550, 412);
		pressedButton 	= null;
		
		msgBox 			= MainMenu.msgBox;
		switchBack		= false;
		switchToGame	= false;
		titleAnimation	= false;
		
		vsAI.setEnabled(false);
		online.setEnabled(false);
		
		titleOpacity	= 1f;
		opacityStep		= 0f;
	}
	
	@Override
	public void show() 
	{
		Gdx.input.setInputProcessor(stage);
		vsAI.unhide(0);
		hotseat.unhide(10);
		online.unhide(20);
		back.unhide(30);
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
			background.render(batch);
			batch.setColor(1f, 1f, 1f, titleOpacity);
			batch.draw(Assets.manager.get("images/main_menu/title.png", Texture.class), 114 * hRatio, 1070 * vRatio - ratioDifference, 700 * hRatio, 200 * hRatio);
			batch.setColor(Color.WHITE);
			vsAI.render(batch);
			hotseat.render(batch);
			online.render(batch);
			back.render(batch);
			
			msgBox.render(batch);
			
			if(titleAnimation)
				titleOpacity += opacityStep;
			
			if(titleOpacity <= 0)
			{
				titleAnimation 	= false;
				titleOpacity	= 0;
			}
			
		batch.end();
		
		if(switchBack && !vsAI.isInAnimation())
		{
			switchBack = false;
			((GameCore)Gdx.app.getApplicationListener()).getGameManager().setMainMenuScreen();
		}
		
		if(switchToGame && !back.isInAnimation() && !msgBox.isInAnimation() && !titleAnimation)
		{
			switchToGame = false;
			((GameCore)Gdx.app.getApplicationListener()).getGameManager().setGameScreen();
		}
	}
	
	private void hideTitle()
	{
		titleAnimation 	= true;
		opacityStep 	= -0.05f;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
	{
		if(vsAI.contains(x, y))
		{
			vsAI.setActivated(true);
			pressedButton = vsAI;
		}
		else if(hotseat.contains(x, y))
		{
			hotseat.setActivated(true);
			pressedButton = hotseat;
		}
		else if(online.contains(x, y))
		{
			online.setActivated(true);
			pressedButton = online;
		}
		else if(back.contains(x, y))
		{
			back.setActivated(true);
			pressedButton = back;
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
				if(pressedButton.equals(hotseat))
				{
					System.out.println("HotSeat");
					vsAI.hide(10, true);
					hotseat.hide(0, true);
					online.hide(20, true);
					back.hide(30, true);
					msgBox.hideAll(0);
					hideTitle();
					switchToGame = true;
				}
				else if(pressedButton.equals(back))
				{
					System.out.println("Back");
					vsAI.hide(30, false);
					hotseat.hide(20, false);
					online.hide(10, false);
					back.hide(0, false);
					switchBack = true;
				}
			}
			pressedButton.setActivated(false);
			pressedButton = null;
		}
	}
	
	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) 
	{
		
	}
}

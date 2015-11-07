package com.blackflamegamez.gameScreens;

import static com.blackflamegamez.game.staticfields.GameStaticValues.*;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.blackflamegamez.game.Assets;
import com.blackflamegamez.game.CustomButton;
import com.blackflamegamez.game.GameCore;
import com.blackflamegamez.game.input.CustomInputListener;
import com.blackflamegamez.game.input.Touchable;

public class PlayScreen extends ScreenAdapter implements Touchable
{
	private SpriteBatch 	batch;
	private Texture 		background;
	private CustomButton 	sPlayer;
	private CustomButton 	bluetooth;
	private CustomButton 	online;
	private CustomButton 	back;
	private CustomButton 	pressedButton;
	private ShapeRenderer 	sr;
	private Stage 			stage;
	
	public PlayScreen(SpriteBatch batch) 
	{
		this.batch 	= batch;
		background 	= GameCore.getBackground();
		sPlayer 	= new CustomButton(Assets.manager.get("images/play_screen/single_player_[617, 800].png", Texture.class), Assets.manager.get("images/play_screen/single_player_pressed_[617, 800].png", Texture.class), 617, 800);
		bluetooth 	= new CustomButton(Assets.manager.get("images/play_screen/bluetooth_[617, 999].png", Texture.class), Assets.manager.get("images/play_screen/bluetooth_pressed_[617, 999].png", Texture.class), 617, 601);
		online 		= new CustomButton(Assets.manager.get("images/play_screen/online_[1332, 800].png", Texture.class), Assets.manager.get("images/play_screen/online_pressed_[1332, 800].png", Texture.class), 1332, 800);
		back 		= new CustomButton(Assets.manager.get("images/play_screen/back_[1332, 999].png", Texture.class), Assets.manager.get("images/play_screen/back_pressed_[1332, 999].png", Texture.class), 1332, 601);
		sr 			= new ShapeRenderer();
		stage 		= new Stage();
		stage.addListener(new CustomInputListener(this));
		sPlayer.setDisabled(true);
		online.setDisabled(true);
	}
	
	@Override
	public void show() 
	{
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
			batch.draw(background, 0, 0 - ratioDifference, 2560 * hRatio, 1600 * hRatio);
			batch.draw(Assets.manager.get("images/play_screen/title_[1024, 1054].png", Texture.class), 1024 * hRatio, 1054 * vRatio - ratioDifference, 511 * hRatio, 170 * hRatio);
			batch.draw(Assets.manager.get("images/mm_screen/top_left.png", Texture.class), 0, 913 * vRatio - 2*ratioDifference, 872 * hRatio, 687 * hRatio);
			batch.draw(Assets.manager.get("images/mm_screen/top_right.png", Texture.class), 1688 * hRatio, 913 * vRatio - 2 *ratioDifference, 872 * hRatio, 687 * hRatio);
			batch.draw(Assets.manager.get("images/mm_screen/bottom_left.png", Texture.class), 0, 0, 872 * hRatio, 687 * hRatio);
			batch.draw(Assets.manager.get("images/mm_screen/bottom_right.png", Texture.class), 1688 * hRatio, 0, 872 * hRatio, 687 * hRatio);
			sPlayer.render(batch);
			bluetooth.render(batch);
			online.render(batch);
			back.render(batch);
		batch.end();
		/* DEBUGGING
		sr.setColor(Color.WHITE);
		sr.begin(ShapeType.Line);
			sPlayer.debug(sr);
			bluetooth.debug(sr);
			online.debug(sr);
			back.debug(sr);
		sr.end();*/
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
	{
		if(bluetooth.contains(x, y))
		{
			bluetooth.setPressed(true);
			pressedButton = bluetooth;
		}
		else if(back.contains(x, y))
		{
			back.setPressed(true);
			pressedButton = back;
		}
		return true;
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
	{
		if(pressedButton != null && pressedButton.contains(x, y))
		{
			if(pressedButton.equals(bluetooth))
				((GameCore)Gdx.app.getApplicationListener()).getGameManager().setGameScreen();
			if(pressedButton.equals(back))
				((GameCore)Gdx.app.getApplicationListener()).getGameManager().setMainMenuScreen();
		}
		
		pressedButton = null;
		sPlayer.setPressed(false);
		bluetooth.setPressed(false);
		online.setPressed(false);
		back.setPressed(false);
	}

	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		// TODO Auto-generated method stub
		
	}
}

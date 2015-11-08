package com.blackflamegamez.gameScreens;

import static com.blackflamegamez.game.staticfields.GameStaticValues.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.blackflamegamez.game.ActorImage;
import com.blackflamegamez.game.Assets;
import com.blackflamegamez.game.BackgroundScroll;
import com.blackflamegamez.game.CustomButton;
import com.blackflamegamez.game.GameCore;
import com.blackflamegamez.game.input.CustomInputListener;
import com.blackflamegamez.game.input.Touchable;

public class OptionsScreen extends ScreenAdapter implements Touchable
{
	private SpriteBatch 		batch;
	private BackgroundScroll 	backgrounds;
	private Stage 				stage;
	private CustomButton 		back;
	private CustomButton 		pressedButton;
	
	public OptionsScreen(SpriteBatch batch) 
	{
		this.batch 				= batch;
		stage 					= new Stage();
		HorizontalGroup list 	= new HorizontalGroup();
		
		for(int i = 1 ; i < 7 ; i++)
			list.addActor(new ActorImage(Assets.manager.get("images/backgrounds/bg_thumb_" + i + ".png", Texture.class)));
		
		backgrounds 	= new BackgroundScroll(list, skin);
		back 			= new CustomButton(Assets.manager.get("images/play_screen/back_[1332, 999].png", Texture.class), Assets.manager.get("images/play_screen/back_pressed_[1332, 999].png", Texture.class), 976, 254);
		pressedButton 	= null;
		
		stage.addListener(new CustomInputListener(this));
		stage.addActor(backgrounds);
	}
	
	@Override
	public void show() 
	{
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
			batch.draw(background, 0, 0 - ratioDifference, 2560 * hRatio, 1600 * hRatio);
			batch.draw(Assets.manager.get("images/options_screen/title.png", Texture.class), 980 * hRatio, 1288 * vRatio - ratioDifference, 600 * hRatio, 150 * hRatio);
			batch.draw(Assets.manager.get("images/options_screen/backgrounds.png", Texture.class), 137 * hRatio, 974 * vRatio - ratioDifference, 550 * hRatio, 120 * hRatio);
			batch.draw(Assets.manager.get("images/options_screen/music.png", Texture.class), 137 * hRatio, 668 * vRatio - ratioDifference, 550 * hRatio, 120 * hRatio);
			batch.draw(Assets.manager.get("images/options_screen/sound.png", Texture.class), 137 * hRatio, 513 * vRatio - ratioDifference, 550 * hRatio, 120 * hRatio);
			back.render(batch);
		batch.end();
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
	{
		if(back.contains(x, y))
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
			if(pressedButton.equals(back))
				((GameCore)Gdx.app.getApplicationListener()).getGameManager().setMainMenuScreen();
		}
	}

	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		// TODO Auto-generated method stub
		
	}
}

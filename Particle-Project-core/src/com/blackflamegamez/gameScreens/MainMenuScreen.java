package com.blackflamegamez.gameScreens;

import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;
import static com.blackflamegamez.game.staticfields.GameStaticValues.ratioDifference;
import static com.blackflamegamez.game.staticfields.GameStaticValues.vRatio;

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

public class MainMenuScreen extends ScreenAdapter implements Touchable
{
	private SpriteBatch 	batch;
	private Texture 		background;
	private CustomButton 	play;
	private CustomButton 	options;
	private CustomButton 	exit;
	private CustomButton 	pressedButton;
	private ShapeRenderer 	sr;
	private Stage 			stage;
	
	public MainMenuScreen(SpriteBatch batch) 
	{
		this.batch 	= batch;
		background 	= GameCore.getBackground();
		play 		= new CustomButton(Assets.manager.get("images/mm_screen/play_[975, 800].png", Texture.class), Assets.manager.get("images/mm_screen/play_pressed_[975, 800].png", Texture.class), 975, 800);
		options 	= new CustomButton(Assets.manager.get("images/mm_screen/options_[975, 601].png", Texture.class), Assets.manager.get("images/mm_screen/options_pressed_[975, 601].png", Texture.class), 975, 601);
		exit 		= new CustomButton(Assets.manager.get("images/mm_screen/exit_[975, 402].png", Texture.class), Assets.manager.get("images/mm_screen/exit_pressed_[975, 402].png", Texture.class), 975, 402);
		sr 			= new ShapeRenderer();
		stage 		= new Stage();
		stage.addListener(new CustomInputListener(this));
		options.setDisabled(true);
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
			batch.draw(Assets.manager.get("images/mm_screen/title_[870, 1027].png", Texture.class), 870 * hRatio, 1027 * vRatio - ratioDifference, 820 * hRatio, 300 * hRatio);
			batch.draw(Assets.manager.get("images/mm_screen/top_left.png", Texture.class), 0, 913 * vRatio - ratioDifference, 872 * hRatio, 687 * hRatio);
			batch.draw(Assets.manager.get("images/mm_screen/top_right.png", Texture.class), 1688 * hRatio, 1027 * vRatio - ratioDifference, 872 * hRatio, 687 * hRatio);
			batch.draw(Assets.manager.get("images/mm_screen/bottom_left.png", Texture.class), 0, 0, 872 * hRatio, 687 * hRatio);
			batch.draw(Assets.manager.get("images/mm_screen/bottom_right.png", Texture.class), 1688 * hRatio, 0, 872 * hRatio, 687 * hRatio);
			play.render(batch);
			options.render(batch);
			exit.render(batch);
		batch.end();
		/* DEBUGGING
		sr.setColor(Color.WHITE);
		sr.begin(ShapeType.Line);
			play.debug(sr);
			options.debug(sr);
			exit.debug(sr);
		sr.end();*/
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
	{
		if(play.contains(x, y))
		{
			play.setPressed(true);
			pressedButton = play;
		}
		else if(exit.contains(x, y))
		{
			exit.setPressed(true);
			pressedButton = exit;
		}
		return true;
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
	{
		if(pressedButton != null && pressedButton.contains(x, y))
		{
			if(pressedButton.equals(play))
				((GameCore)Gdx.app.getApplicationListener()).getGameManager().setPlayScreen();
		}
		
		pressedButton = null;
		play.setPressed(false);
		options.setPressed(false);
		exit.setPressed(false);
	}

	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		// TODO Auto-generated method stub
		
	}
}

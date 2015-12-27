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

public class Game extends ScreenAdapter implements Touchable
{
	private SpriteBatch 	batch;
	private Stage 			stage;

	
	public Game(SpriteBatch batch, int numOfPlayers) 
	{
		this.batch 	= batch;
		stage 		= new Stage();
		
		stage.addListener(new CustomInputListener(this));
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
			background.render(batch);
			batch.draw(Assets.manager.get("images/game/side_bar/background.png", Texture.class), 0, -ratioDifference, 440 * hRatio, 1600 * hRatio);
			
			batch.setColor(background.getColorBase());
			batch.draw(Assets.manager.get("images/game/side_bar/side_lines.png", Texture.class), 0, -ratioDifference, 440 * hRatio, 1600 * hRatio);
			batch.setColor(Color.WHITE);
		batch.end();
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
	{
		return true;
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
	{
		
	}

	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) {
		// TODO Auto-generated method stub
		
	}
}

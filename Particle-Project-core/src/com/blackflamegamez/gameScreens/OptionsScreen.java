package com.blackflamegamez.gameScreens;

import static com.blackflamegamez.game.staticfields.GameStaticValues.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.blackflamegamez.game.ActorImage;
import com.blackflamegamez.game.Assets;
import com.blackflamegamez.game.BackgroundScroll;
import com.blackflamegamez.game.input.CustomInputListener;

public class OptionsScreen extends ScreenAdapter
{
	private SpriteBatch 		batch;
	private BackgroundScroll 	backgrounds;
	private Stage 				stage;
	
	public OptionsScreen(SpriteBatch batch) 
	{
		this.batch = batch;
		stage = new Stage();
		HorizontalGroup list = new HorizontalGroup();
		for(int i = 1 ; i < 7 ; i++)
			list.addActor(new ActorImage(Assets.manager.get("images/backgrounds/bg_thumb_" + i + ".png", Texture.class)));
		backgrounds = new BackgroundScroll(list, skin);
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
		
		stage.act(delta);
		stage.draw();
	}
}

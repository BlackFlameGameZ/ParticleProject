package com.blackflamegamez.gameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blackflamegamez.game.AnimatedSprite;
import com.blackflamegamez.game.Assets;
import com.blackflamegamez.game.GameCore;
import static com.blackflamegamez.game.staticfields.GameStaticValues.*;

public class SplashScreen extends ScreenAdapter
{
	private SpriteBatch 	batch;
	private AnimatedSprite 	splashSprite;
	private float			elapsedTime;
	private float 			hr;
	private float 			vr;
	private float 			rd;

	public SplashScreen(SpriteBatch batch) 
	{
		this.batch 		= batch;
		splashSprite 	= new AnimatedSprite(Assets.manager.get("images/splash_#[625,493].png", Texture.class), 625, 493, false);
		hr				= GameCore.hRatio;
		vr				= GameCore.vRatio;
		rd				= GameCore.ratioDifference;
	}
	
	@Override
	public void show() 
	{
		elapsedTime = 0f;
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		elapsedTime += delta;
		batch.begin();
			batch.draw(splashSprite.getFrame(elapsedTime), 967 * hr, 620 * vr, 625 * hr, 493 * hr);
		batch.end();
	}
}

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
import com.blackflamegamez.game.AnimatedSprite;
import com.blackflamegamez.game.Assets;
import com.blackflamegamez.game.GameCore;

public class SplashScreen extends ScreenAdapter
{
	private SpriteBatch 	batch;
	private AnimatedSprite 	splashSprite;
	private float			elapsedTime;
	private int 			mode;
	private float 			alpha;
	private float 			alphaStep;
	private Color 			batchColor;

	public SplashScreen(SpriteBatch batch) 
	{
		float started = System.currentTimeMillis();
		this.batch 		= batch;
		splashSprite 	= new AnimatedSprite(Assets.manager.get("images/splash_#[625,493].png", Texture.class), 625, 493, false);
		System.out.println("Splash screen created [" + (System.currentTimeMillis() - started) + "ms] !!!");
	}
	
	@Override
	public void show() 
	{
		float started = System.currentTimeMillis();
		elapsedTime = 0f;
		alpha 		= 0;
		alphaStep	= 1f/60f;
		mode 		= 0;
		batchColor 	= batch.getColor();
		System.out.println("Splash screen showed [" + (System.currentTimeMillis() - started) + "ms] !!!");
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(mode == 1)
			elapsedTime += delta;
		else
			alpha += alphaStep;
		
		if(mode == 2 && alpha < 0)
			((GameCore)Gdx.app.getApplicationListener()).getGameManager().setMainMenuScreen();
		
		batch.begin();
			batch.setColor(batchColor.r, batchColor.g, batchColor.b, alpha);
			batch.draw(splashSprite.getFrame(elapsedTime), 967 * hRatio, 620 * vRatio - ratioDifference, 625 * hRatio, 493 * hRatio);
			batch.setColor(batchColor);
		batch.end();
		
		if(mode == 0 && alpha > 1)
		{
			alpha = 1;
			mode++;
		}
		
		if(mode == 1 && splashSprite.animationFinished(elapsedTime))
		{
			mode = 2;
			alphaStep = -alphaStep;
		}
		
		if(mode == 2 && alpha < 0)
			((GameCore)Gdx.app.getApplicationListener()).getGameManager().setMainMenuScreen();
	}
}

package com.blackflamegamez.gameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.blackflamegamez.game.AnimatedSprite;
import com.blackflamegamez.game.Assets;
import com.blackflamegamez.game.GameCore;

public class LoadingScreen extends ScreenAdapter
{
	private SpriteBatch 	batch;
	private AnimatedSprite 	loadingSprite;
	private Label 			label;
	private Skin			skin;
	private float			elapsedTime;
	private final float 	loadStep = 1f/81f;
	private float 			hr;
	private float 			vr;
	private float 			rd;
	private	Label 			resolutionLabel;

	public LoadingScreen(SpriteBatch batch) 
	{
		this.batch 		= batch;
		loadingSprite 	= new AnimatedSprite("images/loading_#[358,306].png", 358, 306, false);
		skin			= new Skin(Gdx.files.internal("files/uiskin.json"));
		label			= new Label("0%", skin, "loading");
		hr				= GameCore.hRatio;
		vr				= GameCore.vRatio;
		rd				= GameCore.ratioDifference;
		resolutionLabel = new Label((int)GameCore.realWidth + "x" + (int)GameCore.realHeight, skin, "loading");
		resolutionLabel.setPosition(20, 20);
		
		label.setSize(GameCore.realWidth, 30);
		label.setPosition(6 * hr, 608 * vr - rd);
		label.setAlignment(Align.center);
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
		Gdx.gl.glClear(Gdx.gl20.GL_COLOR_BUFFER_BIT);
		elapsedTime = 1f/60f * (int)(Assets.manager.getProgress()/loadStep);
		Assets.manager.update();
		batch.begin();
			batch.draw(loadingSprite.getFrame(elapsedTime), 1102 * hr, 688 * vr - rd, 358 * hr, 306 * hr);
			label.draw(batch, 1f);
			resolutionLabel.draw(batch, 1f);
		batch.end();
		
		label.setText((int)(Assets.manager.getProgress() * 100) + "%");
		if(Assets.manager.getProgress() == 1)
			((GameCore)Gdx.app.getApplicationListener()).setScreen(new SplashScreen(batch));
	}
}

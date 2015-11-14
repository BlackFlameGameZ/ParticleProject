package com.blackflamegamez.gameScreens;

import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;
import static com.blackflamegamez.game.staticfields.GameStaticValues.ratioDifference;
import static com.blackflamegamez.game.staticfields.GameStaticValues.vRatio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.blackflamegamez.game.AnimatedSprite;
import com.blackflamegamez.game.Assets;
import com.blackflamegamez.game.GameCore;
import com.blackflamegamez.game.staticfields.GameStaticValues;

public class LoadingScreen extends ScreenAdapter
{
	private SpriteBatch 	batch;
	private AnimatedSprite 	loadingSprite;
	private Label 			label;
	private float			elapsedTime;
	private final float 	loadStep = 1f/81f;
	private	Label 			resolutionLabel;

	public LoadingScreen(SpriteBatch batch) 
	{
		this.batch 		= batch;
		loadingSprite 	= new AnimatedSprite("images/loading_#[358,306].png", 358, 306, false);
		BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/loading_font.fnt"));
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		label			= new Label("0%", new LabelStyle(font, Color.WHITE));
		resolutionLabel = new Label((int)GameStaticValues.realWidth + "x" + (int)GameStaticValues.realHeight, new LabelStyle(font, Color.WHITE));
		resolutionLabel.setPosition(20 * hRatio, 0);
		
		resolutionLabel.setFontScale(hRatio);
		
		
		
		label.setSize(GameStaticValues.realWidth, 30);
		label.setPosition(6 * hRatio, 608 * vRatio - ratioDifference);
		label.setAlignment(Align.center);
		label.setFontScale(hRatio);
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
		elapsedTime = 1f/60f * (int)(Assets.manager.getProgress()/loadStep);
		Assets.manager.update();
		batch.begin();
			batch.draw(loadingSprite.getFrame(elapsedTime), 1102 * hRatio, 688 * vRatio - ratioDifference, 358 * hRatio, 306 * hRatio);
			label.draw(batch, 1f);
			resolutionLabel.draw(batch, 1f);
		batch.end();
		
		label.setText((int)(Assets.manager.getProgress() * 100) + "%");
		if(Assets.manager.getProgress() == 1)
		{
			Assets.setFilters();
			GameStaticValues.loadBackground();
			((GameCore)Gdx.app.getApplicationListener()).getGameManager().setSplashScreen();
		}
	}
}

package com.blackflamegamez.gameScreens;

import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;
import static com.blackflamegamez.game.staticfields.GameStaticValues.ratioDifference;
import static com.blackflamegamez.game.staticfields.GameStaticValues.vRatio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.blackflamegamez.game.Assets;
import com.blackflamegamez.game.GameCore;
import com.blackflamegamez.game.staticfields.GameStaticValues;

/**
 * @author BlackFlame
 *
 * Loading screen
 */

public class Loading extends ScreenAdapter
{
	private SpriteBatch 	batch;
	private Texture 		hex_border;
	private Texture			hex_fill;
	private Label 			label;
	private float			elapsedTime;
	private final float 	loadStep 		= 1f/81f;
	private	Label 			resolutionLabel;
	private Vector2[]		pointArray;
	private int 			framesElapsed 	= 0;
	private int				frameSwitch 	= 6;
	private int 			point_index 	= 0;
	private Vector2 		currentPoint;

	public Loading(SpriteBatch batch) 
	{
		this.batch 		= batch;
		BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/loading_font.fnt"));
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		label			= new Label("0", new LabelStyle(font, Color.WHITE));
		resolutionLabel = new Label((int)GameStaticValues.realWidth + "x" + (int)GameStaticValues.realHeight, new LabelStyle(font, Color.WHITE));
		resolutionLabel.setPosition(20 * hRatio, 0);
		
		resolutionLabel.setFontScale(hRatio);
		
		hex_border 	= new Texture("images/loading/border.png");
		hex_fill	= new Texture("images/loading/fill.png");
		
		hex_border	.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		hex_fill	.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		label.setSize(GameStaticValues.realWidth, 30);
		label.setPosition(0, 774 * vRatio - ratioDifference);
		label.setAlignment(Align.center);
		label.setFontScale(hRatio);
		
		pointArray = new Vector2[]
				{
					new Vector2(1190 * hRatio, 834 * vRatio - ratioDifference), 
					new Vector2(1285 * hRatio, 834 * vRatio - ratioDifference), 
					new Vector2(1332 * hRatio, 757 * vRatio - ratioDifference),
					new Vector2(1285 * hRatio, 680 * vRatio - ratioDifference),
					new Vector2(1190 * hRatio, 680 * vRatio - ratioDifference), 
					new Vector2(1144 * hRatio, 757 * vRatio - ratioDifference)
				};
		currentPoint = pointArray[0];
	}
	
	@Override
	public void show() 
	{
		elapsedTime 	= 0f;
		framesElapsed 	= 0;
		frameSwitch 	= 6;
		point_index 	= 0;
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		framesElapsed++;
		
		if(framesElapsed % frameSwitch == 0)
		{
			point_index++;
			point_index 	%= pointArray.length;
			currentPoint 	= pointArray[point_index];
		}
		
		elapsedTime = 1f/60f * (int)(Assets.manager.getProgress()/loadStep);
		Assets.manager.update();
		
		batch.begin();
			Color c = batch.getColor();
			batch.setColor(new Color(255f, 0f, 0f, 1f));
			label.draw(batch, 1f);
			resolutionLabel.draw(batch, 1f);
			for(Vector2 v : pointArray)
				batch.draw(hex_border, v.x, v.y, hRatio * 88, hRatio * 88);
			batch.draw(hex_fill, currentPoint.x, currentPoint.y, hRatio * 88, hRatio * 88);
		batch.end();
		batch.setColor(c);
		label.setText((int)(Assets.manager.getProgress() * 100) + "");
		if(Assets.manager.getProgress() == 1)
		{
			Assets.setFilters();
			GameStaticValues.loadBackground();
			((GameCore)Gdx.app.getApplicationListener()).getGameManager().setSplashScreen();
		}
	}
}

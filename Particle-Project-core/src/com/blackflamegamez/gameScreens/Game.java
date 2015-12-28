package com.blackflamegamez.gameScreens;

import static com.blackflamegamez.game.staticfields.GameStaticValues.background;
import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;
import static com.blackflamegamez.game.staticfields.GameStaticValues.ratioDifference;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.blackflamegamez.game.Assets;
import com.blackflamegamez.game.board.Board;
import com.blackflamegamez.game.input.CustomInputListener;
import com.blackflamegamez.game.input.Touchable;

public class Game extends ScreenAdapter implements Touchable
{
	private SpriteBatch 	batch;
	private Stage 			stage;
	private Board           board;
	
	private boolean 		inAnimation;
	
	private float 			sideBarOpacity;
	private float 			opacityStep;
	
	
	public Game(SpriteBatch batch, int numOfPlayers) 
	{
		this.batch 	= batch;
		stage 		= new Stage();
		
		stage.addListener(new CustomInputListener(this));
		sideBarOpacity 	= 0f;
		inAnimation		= false;
		showSideBar();
	}
	
	@Override
	public void show() 
	{
		Gdx.input.setInputProcessor(stage);
		board = new Board();
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
			// ============================================= background image rendering
			background.render(batch);
			
			// ============================================= side bar rendering
			batch.setColor(1f, 1f, 1f, sideBarOpacity);
			batch.draw(Assets.manager.get("images/game/side_bar/background.png", Texture.class), 0, -ratioDifference, 440 * hRatio, 1600 * hRatio);
			Color bc = background.getColorBase();
			batch.setColor(bc.r, bc.g, bc.b, sideBarOpacity);
			batch.draw(Assets.manager.get("images/game/side_bar/side_lines.png", Texture.class), 0, -ratioDifference, 440 * hRatio, 1600 * hRatio);
			batch.setColor(Color.WHITE);
			adjustSideBarOpacity();
			// ============================================= end of side bar rendering
			board.render(batch , delta);
		batch.end();
	}
	
	private void adjustSideBarOpacity()
	{
		if(inAnimation)
			sideBarOpacity += opacityStep;
		
		if(sideBarOpacity >= 1)
		{
			inAnimation 	= false;
			sideBarOpacity	= 1;
		}
	}
	
	private void showSideBar()
	{
		opacityStep = 0.05f;
		inAnimation	= true;
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

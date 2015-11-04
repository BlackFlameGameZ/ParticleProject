package com.blackflamegamez.gameScreens;

import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;
import static com.blackflamegamez.game.staticfields.GameStaticValues.ratioDifference;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blackflamegamez.game.Assets;

public class ParticleGameScreen extends ScreenAdapter
{
	private SpriteBatch batch;
	private Texture 	board;
	private Texture 	board_grid;
	
	public ParticleGameScreen(SpriteBatch batch) 
	{
		this.batch 	= batch;
		board		= Assets.manager.get("images/board_1.png", Texture.class);
		board_grid	= Assets.manager.get("images/board_grid.png", Texture.class);
		board.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		board_grid.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
			batch.draw(board, 0, 0 - ratioDifference, 2560 * hRatio, 1600 * hRatio);
			batch.draw(board_grid, 0, 0 - ratioDifference, 2560 * hRatio, 1600 * hRatio);
		batch.end();
	}
}

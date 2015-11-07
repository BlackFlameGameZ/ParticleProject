package com.blackflamegamez.gameScreens;

import static com.blackflamegamez.game.staticfields.GameStaticValues.defaultHeight;
import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;
import static com.blackflamegamez.game.staticfields.GameStaticValues.ratioDifference;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.blackflamegamez.game.Assets;
import com.blackflamegamez.game.Cell;
import com.blackflamegamez.game.GameCore;
import com.blackflamegamez.game.Hexagon;
import com.blackflamegamez.game.ParticleUtil;
import com.blackflamegamez.game.RectangleButton;
import com.blackflamegamez.game.input.CustomInputListener;
import com.blackflamegamez.game.input.Touchable;

/**
 * @author Milan Topalovic
 *
 */
public class ParticleGameScreen extends ScreenAdapter implements Touchable
{
	private SpriteBatch 		batch;
	private Texture 			board;
	private Texture 			board_grid;
	private ArrayList<Cell> 	cells;
	private Stage          		stage;
	private CustomInputListener listener;
	private ShapeRenderer       sr;
	private RectangleButton 	menu;
	private RectangleButton		pressedButton;
	
	public ParticleGameScreen(SpriteBatch batch) 
	{
		this.batch 	= batch;
		stage       = new Stage();
		listener    = new CustomInputListener(this);
		sr			= new ShapeRenderer();
		menu 		= new RectangleButton(Assets.manager.get("images/menu.png", Texture.class), Assets.manager.get("images/menu_pressed.png", Texture.class), 0, 1472, 318, 100);
		stage.addListener(listener);
		makeCells();
		determineNeighbours();
		loadAssets();
	}
	
	@Override
	public void show() 
	{
		pressedButton = null;
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
			batch.draw(board, 0, 0 - ratioDifference, 2560 * hRatio, 1600 * hRatio);
			batch.draw(board_grid, 0, 0 - ratioDifference, 2560 * hRatio, 1600 * hRatio);
			menu.render(batch);
		batch.end();
		sr.setColor(Color.WHITE);
		sr.begin(ShapeType.Line);
			for(Cell c : cells)
				c.debug(sr);
			menu.debug(sr);
		sr.end();
	}

	private void makeCells()
	{
		cells = new ArrayList<Cell>();
		float x = 806f , y = defaultHeight - 260f , rectWidth = 180f;
		for(int i = -4 ; i <= 4 ; ++i)
			for(int j = 0 ; j < 9 - Math.abs(i) ; ++j)
				cells.add(createCellForRowCol( i , j , x , y , rectWidth));
	}
	
	private Cell createCellForRowCol(int row , int col , float x , float y , float rectWidth)
	{
		float padding = 11f;
		float horizontal_padding = 12f;
		int shiftX = 4 - Math.abs(row);
		x = x - shiftX * (rectWidth / 2 + horizontal_padding / 2) + col * (rectWidth + horizontal_padding);
		y = y - (4 + row) * (padding + rectWidth/4 + rectWidth/2);
		Hexagon h = new Hexagon(x, y, rectWidth);
		Cell    c = new Cell(x, y - rectWidth/4, rectWidth, rectWidth, 5 + row, col + 1, h);
		return c;
	}
	
	private void determineNeighbours()
	{
		for(Cell c : cells)
		{
			ArrayList<Cell> n = ParticleUtil.getNeighboursForCell(c, cells);
			c.setNeighbours(n);
		}
	}
	
	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
	{
		if(menu.contains(x, y))
		{
			menu.setPressed(true);
			pressedButton = menu;
		}
		return true;
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
	{
		if(pressedButton != null && pressedButton.contains(x, y))
		{
			if(pressedButton.equals(menu))
				((GameCore)Gdx.app.getApplicationListener()).getGameManager().setMainMenuScreen();
		}
		menu.setPressed(false);
		pressedButton = null;
	}

	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) 
	{
		
	}
	
	private void loadAssets()
	{
		board		= GameCore.getBackground();
		board_grid	= Assets.manager.get("images/board_grid.png", Texture.class);
		board.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		board_grid.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
	
	@Override
	public void resume() 
	{
		super.resume();
	}
	
	public void hide()
	{
		
	}
}

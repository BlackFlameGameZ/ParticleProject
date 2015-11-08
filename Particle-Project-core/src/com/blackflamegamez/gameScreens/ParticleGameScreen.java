package com.blackflamegamez.gameScreens;

import static com.blackflamegamez.game.staticfields.GameStaticValues.background;
import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;
import static com.blackflamegamez.game.staticfields.GameStaticValues.h_padding;
import static com.blackflamegamez.game.staticfields.GameStaticValues.ratioDifference;
import static com.blackflamegamez.game.staticfields.GameStaticValues.rect_width;
import static com.blackflamegamez.game.staticfields.GameStaticValues.starting_x;
import static com.blackflamegamez.game.staticfields.GameStaticValues.starting_y;
import static com.blackflamegamez.game.staticfields.GameStaticValues.v_padding;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.blackflamegamez.game.Assets;
import com.blackflamegamez.game.GameBoard;
import com.blackflamegamez.game.GameCore;
import com.blackflamegamez.game.Player;
import com.blackflamegamez.game.RectangleButton;
import com.blackflamegamez.game.enums.ParticleColor;
import com.blackflamegamez.game.gameactions.MoveResolution;
import com.blackflamegamez.game.input.CustomInputListener;
import com.blackflamegamez.game.input.Touchable;

/**
 * @author Milan Topalovic
 *
 */
public class ParticleGameScreen extends ScreenAdapter implements Touchable
{
	private SpriteBatch 		batch;
	private Texture 			board_grid;
	private GameBoard           gameBoard;
	private Stage          		stage;
	private CustomInputListener listener;
	private ShapeRenderer       sr;
	private RectangleButton 	menu;
	private RectangleButton		pressedButton;
	private Player              player1 , player2;
	
	public ParticleGameScreen(SpriteBatch batch) 
	{
		this.batch 	= batch;
		stage       = new Stage();
		listener    = new CustomInputListener(this);
		sr			= new ShapeRenderer();
		gameBoard   = new GameBoard(starting_x , starting_y , rect_width ,h_padding , v_padding );
		player1     = new Player(ParticleColor.BLUE , gameBoard);
		menu 		= new RectangleButton(Assets.manager.get("images/menu.png", Texture.class), Assets.manager.get("images/menu_pressed.png", Texture.class), 0, 1472, 318, 100);
		stage.addListener(listener);
		board_grid	= Assets.manager.get("images/board_grid.png", Texture.class);
	}
	
	@Override
	public void show() 
	{
		reset();
		pressedButton = null;
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
			batch.draw(background, 0, 0 - ratioDifference, 2560 * hRatio, 1600 * hRatio);
			batch.draw(board_grid, 0, 0 - ratioDifference, 2560 * hRatio, 1600 * hRatio);
			menu.render(batch);
			gameBoard.render(batch, delta);
		batch.end();
		/* DEBUG */
		sr.setColor(Color.WHITE);
		sr.begin(ShapeType.Line);
			gameBoard.debug(sr);
		sr.end();
	}
	
	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
	{
		if(menu.contains(x, y))
		{
			menu.setPressed(true);
			pressedButton = menu;
		} 
		player1.touchDown(event, x, y, pointer, button);
		return true;
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
	{
		boolean flag = false;
		if(pressedButton != null && pressedButton.contains(x, y))
		{
			if(pressedButton.equals(menu))
			{
				((GameCore)Gdx.app.getApplicationListener()).getGameManager().setMainMenuScreen();
				flag = true;
			}
		}
		if(!flag)
		{
			MoveResolution mr = player1.touchUp(event, x, y, pointer, button);
			//here we try to execute command and if it's invalid then we can handle error properly 
			boolean validMove = gameBoard.resolveCommand(mr);
		}
		menu.setPressed(false);
		pressedButton = null;
	}

	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) 
	{
		player1.touchDragged(event, x, y, pointer);
	}
	
	@Override
	public void resume() 
	{
		super.resume();
	}
	
	public void hide()
	{
		
	}
	
	/* probna metoda */
	public void reset()
	{
		gameBoard.resetBoard();
		player1.reset();
	}
}

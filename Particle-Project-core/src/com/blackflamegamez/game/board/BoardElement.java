package com.blackflamegamez.game.board;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blackflamegamez.game.Assets;
import com.blackflamegamez.game.geometry.Body2D;
import com.blackflamegamez.game.staticfields.GameStaticValues;

/**
 * Container for cell in board which task is to render it self and it's children
 * and to serve as logical wrapper for all components that render themselves inside of this cell
 * 
 * @author Milan Topalovic
 */
public class BoardElement extends Body2D {
	
	
	private int row;
	private int col;
	
	public BoardElement(float x, float y, float width, float height , int row , int col) 
	{
		super(x, y, width, height);
		this.row = row;
		this.col = col;
	}
	
	public void render(SpriteBatch batch , float delta)
	{
		batch.setColor(Color.BLACK);
		batch.draw(Assets.manager.get("images/game/field.png" , Texture.class) , x , y , width , height);
		batch.setColor(GameStaticValues.background.getColorBase());
		batch.draw(Assets.manager.get("images/game/field_border.png" , Texture.class) , x , y , width , height);
		batch.setColor(Color.WHITE);
	}
	
	public int getCol()
	{
		return col;
	}
	
	public int getRow()
	{
		return row;
	}
}

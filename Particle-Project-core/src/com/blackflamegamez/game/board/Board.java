package com.blackflamegamez.game.board;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import static com.blackflamegamez.game.staticfields.GameStaticValues.*;
/** Board in game
 * @author Milan Topalovic
 *
 */
public class Board 
{

	private ArrayList<BoardElement> elements;
	
	public static final float hPadding 		 = 0f;   //horizontal padding between 2 adjacent elements 
	public static final float vPadding 		 = 160f; //padding between 2 adjacent rows
	public static final float startingOffset = 10f;  //offset for both x and y when game is started
	public static final int   rows 			 = 15;   //total num of rows
	
	public Board()
	{
		elements = new ArrayList<BoardElement>();
		generateBoard();
	}
	
	private void generateBoard()
	{
		float startingX = 440f , shiftX , x ,y;
		int cols;
		for(int i = 0 ; i < rows ; ++i)
		{
			cols = i % 2 == 0 ? 20 : 21;
			shiftX = cols == 20 ? 100f : 0f;
			y = defaultHeight - 200f - i * vPadding - startingOffset;
			for(int j = 0 ; j < cols ; ++j) 
			{
				x = startingX + shiftX + j * 200f + startingOffset;
				elements.add(new BoardElement(x , y , 200 , 200 , i , j));
			}
		}
	}
	
	public void render(SpriteBatch batch , float delta)
	{
		for(BoardElement el : elements)
			el.render(batch, delta);
	}
	
	public ArrayList<BoardElement> getElements()
	{
		return elements;
	}
}

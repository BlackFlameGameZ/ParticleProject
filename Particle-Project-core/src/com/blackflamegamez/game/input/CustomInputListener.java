package com.blackflamegamez.game.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class CustomInputListener extends InputListener 
{
	
	public Touchable screen;
	
	public CustomInputListener(Touchable t)
	{
		this.screen = t;
	}
	
	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer,
			int button) 
	{
		return screen.touchDown(event , x , y , pointer , button);
	}
	
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
	{
		screen.touchUp(event , x , y , pointer , button);
	}
	
	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) 
	{
		screen.touchDragged(event , x , y , pointer);
	}
}

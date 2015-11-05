package com.blackflamegamez.game.input;

import com.badlogic.gdx.scenes.scene2d.InputEvent;

public interface Touchable 
{
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button);
	
	public void touchUp(InputEvent event , float x, float y , int pointer , int button);
	
	public void touchDragged(InputEvent event , float x , float y , int pointer);
}

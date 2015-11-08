package com.blackflamegamez.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.blackflamegamez.game.input.Touchable;

import static com.blackflamegamez.game.staticfields.GameStaticValues.*;

public class BackgroundScroll extends ScrollPane implements Touchable
{
	public BackgroundScroll(Actor widget, Skin skin) 
	{
		super(widget, skin);
		setFlickScroll(true);
		setSmoothScrolling(true);
		setScrollingDisabled(false, true);
		setBounds(100, 100, 1680 * hRatio, 400 * hRatio);
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void touchDragged(InputEvent event, float x, float y, int pointer) 
	{
		// TODO Auto-generated method stub
		
	}
}

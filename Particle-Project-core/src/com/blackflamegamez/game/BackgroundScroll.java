package com.blackflamegamez.game;

import static com.blackflamegamez.game.staticfields.GameStaticValues.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.blackflamegamez.game.input.CustomInputListener;
import com.blackflamegamez.game.input.Touchable;

public class BackgroundScroll extends ScrollPane implements Touchable
{
	private HorizontalGroup group;
	
	public BackgroundScroll(HorizontalGroup gr, Skin skin) 
	{
		super(gr, skin);
		group = gr;
		setFlickScroll(true);
		setSmoothScrolling(true);
		setScrollingDisabled(false, true);
		setBounds(734 * hRatio, 834 * vRatio - ratioDifference, 1680 * hRatio, 400 * hRatio);
		((ActorImage)group.getChildren().get(1)).setSelected(true);
		group.addListener(new CustomInputListener(this));
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) 
	{
		ActorImage tmp = null;
		int index = 1;
		int imageIndex = index;
		for(Actor a : group.getChildren())
		{
			((ActorImage)a).setSelected(false);
			if(((ActorImage)a).contains(x, y))
			{
				tmp 		= (ActorImage)a;
				imageIndex 	= index;
			}
			index++;
		}
		
		if(tmp != null)
			tmp.setSelected(true);
		
		background = Assets.manager.get("images/backgrounds/bg_" + imageIndex + ".png", Texture.class);
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

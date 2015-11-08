package com.blackflamegamez.game;

import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class ActorImage extends Actor
{
	private Texture texture;
	private boolean isSelected;
	private Array<Vector2> vertices;
	
	public ActorImage(Texture t) 
	{
		texture 	= t;
		isSelected 	= false;
		setSize(560 * hRatio, 400 * hRatio);
		vertices = new Array<Vector2>();
		vertices.add(new Vector2(0, 0));
		vertices.add(new Vector2(0, 0));
		vertices.add(new Vector2(0, 0));
		vertices.add(new Vector2(0, 0));
	}
	
	private void updateRect()
	{
		float x = getX();
		float y = getY();
		vertices.get(0).set(x * hRatio, y * hRatio);
		vertices.get(1).set(x * hRatio, y * hRatio + 400 * hRatio);
		vertices.get(2).set(x * hRatio + 560 * hRatio, y * hRatio + 400 * hRatio);
		vertices.get(3).set(x * hRatio + 560 * hRatio, y * hRatio);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) 
	{
		updateRect();
		if(isSelected)
			batch.draw(Assets.manager.get("images/backgrounds/thumb_glow.png", Texture.class), getX(), getY(), texture.getWidth() * hRatio, texture.getHeight() * hRatio);
		batch.draw(texture, getX(), getY(), texture.getWidth() * hRatio, texture.getHeight() * hRatio);
	}
	
	public boolean contains(float x, float y)
	{
		return Intersector.isPointInPolygon(vertices, new Vector2(x, y));
	}
	
	public void setSelected(boolean isSelected) 
	{
		this.isSelected = isSelected;
	}
}

package com.blackflamegamez.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blackflamegamez.game.geometry.Body2D;

public class Particle extends Body2D 
{
	
	private int 		   deff;
	private int 		   attack;
	private AnimatedSprite sprite;
	private Color          particleColor;
	
	
	
	public Particle(float x , float y , float width , int deff , int attack)
	{
		super(x , y , width , width);
		this.deff = deff;
		this.attack = attack;
	}
	
	public void render(SpriteBatch batch , float delta)
	{
		
	}
}

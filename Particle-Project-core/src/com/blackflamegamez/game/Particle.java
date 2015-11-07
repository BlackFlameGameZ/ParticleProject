package com.blackflamegamez.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blackflamegamez.game.geometry.Body2D;

public class Particle extends Body2D 
{
	private AnimatedSprite 	sprite;
	private int 		   	defense;
	private int 		   	attack;
	private int 			atkLvl;
	private int 			defLvl;
	private Color          	shieldColor;
	private Color			bulletColor;
	private boolean			drawBase;
	
	
	
	public Particle(float x , float y , float width , int defLevel , int atkLevel)
	{
		super(x , y , width , width);
		atkLvl 	= atkLevel;
		defLvl 	= defLevel;
		attack 	= 0;
		defense = 0;
		shieldColor = Color.WHITE;
		bulletColor = Color.WHITE;
		drawBase = true;
		calculate();
	}
	
	public void render(SpriteBatch batch , float delta)
	{
		
	}
	
	private void calculate()
	{
		attack 	= (int)((1 + ((float)defLvl - 1) * 0.2) * (float)atkLvl * 10);
		defense = (int)((1 + ((float)atkLvl - 1) * 0.1) * (float)defLvl * 10);
	}
	
	public int getAttack() 
	{
		return attack;
	}
	
	public int getDefense() 
	{
		return defense;
	}
}

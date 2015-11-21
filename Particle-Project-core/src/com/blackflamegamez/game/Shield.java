package com.blackflamegamez.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.blackflamegamez.game.staticfields.GameStaticValues.*;

public class Shield 
{
	// Basic fields
	private int 				level;
	private int 				unitPower;
	private int 				health;
	private Color 				color;
	
	// Fields for animation
	private AnimatedSprite		texture;
	private boolean				isActive;

	// Reference to attacking particles (NEEDED FOR POWER UPDATES)
	private Attack 	attack;
	
	public Shield(Color shieldColor) 
	{
		attack 		= null;
		level 		= 1;
		unitPower 	= 10;
		health		= 10;
		color 		= shieldColor;
		texture		= new AnimatedSprite(Assets.manager.get("images/particle/def_1_#[180,180].png", Texture.class), 180, 180, true);
		deactivate();
	}
	
	public void render(SpriteBatch batch, float x, float y, float delta)
	{
		if(level > 0)
		{
			batch.setColor(color);
			batch.draw(texture.getFrame(delta), x, y, hRatio * 180, hRatio * 180);
		}
	}
	
	// THIS IS NEEEDED IF U WANT THIS CLASS TO WORK PROPERLY !!!
	public void setAttackParticles(Attack ap)
	{
		attack = ap;
	}
	
	public void destroy(float damage)
	{
		health -= damage;
		level = health / unitPower;
		if(health % unitPower > 0)
			level++;
		attack.update();
	}
	
	public int getLevel() 
	{
		return level;
	}
	
	public int getHealth() 
	{
		return health;
	}
	
	public void levelUp(int levels)
	{
		if(levels < 0)
			return;
		level = Math.min(5, level + levels);
		texture.changeSource(Assets.manager.get("images/particle/def_" + level + "_#[180,180].png", Texture.class));
	}
	
	public void levelDown(int levels)
	{
		if(levels < 0)
			return;
		level = Math.max(0, level - levels);
		if(level > 0)
			texture.changeSource(Assets.manager.get("images/particle/def_" + level + "_#[180,180].png", Texture.class));
	}
	
	public void update()
	{
		int tmp = health % unitPower;
		float procent = 1;
		if(tmp != 0)
			procent = (float)tmp / unitPower;
		unitPower = attack.getLevel() * 10;
		int lastShield = (int)(procent * unitPower);
		health = unitPower * (level - 1) + lastShield;
	}
	
	public void activate()
	{
		texture.forcePlay();
		texture.play();
	}
	
	public void deactivate()
	{
		texture.forceFinish();
	}
}

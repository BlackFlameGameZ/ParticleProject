package com.blackflamegamez.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blackflamegamez.game.enums.ParticleColor;
import com.blackflamegamez.game.geometry.Body2D;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class Particle extends Body2D 
{
	private int 		   	defense;
	private int 		   	attack;
	private int 			atkLvl;
	private int 			defLvl;
	private Color          	shieldColor;
	private Color			bulletColor;
	private boolean			drawBase;
	private Player          player;
	
	
	
	public Particle(Player p , float x , float y , float width , int defLevel , int atkLevel)
	{
		super(x , y , width , width);
		atkLvl 	= atkLevel;
		defLvl 	= defLevel;
		attack 	= 0;
		defense = 0;
		player   = p;
		shieldColor = ParticleColor.getColor(player.getColor());
		bulletColor = ParticleColor.getColor(player.getColor());
		drawBase = true;
		calculate();
	}
	
	public void render(SpriteBatch batch , float delta)
	{
		Color bc = batch.getColor();
		batch.setColor(Color.BLACK);
		batch.draw(Assets.manager.get("images/particle/base.png" , Texture.class) , x , y , width , height);
		batch.setColor(bulletColor);
		if(attack != 0)
			batch.draw(Assets.manager.get("images/particle/atk_" + atkLvl + ".png" , Texture.class) , x , y , width , height);
		batch.setColor(shieldColor);
		if(defense != 0)
			batch.draw(Assets.manager.get("images/particle/def_" + defLvl + ".png" , Texture.class) , x , y , width , height);
		batch.setColor(bc);
	}
	
	private void calculate()
	{
		attack 	= (int)((1 + ((float)defLvl - 1) * 0.2) * (float)atkLvl * 10);
		defense = (int)((1 + ((float)atkLvl - 1) * 0.1) * (float)defLvl * 10);
	}
	
	public void addAttack(int toAdd)
	{
		if(toAdd <= 0)
			return;
		atkLvl = min(5 , atkLvl + toAdd);
		calculate();
	}
	/**
	 * Removes some value from attack
	 * @param toRem value to subtract from, must be greater than zero
	 */
	public void remAttack(int toRem)
	{
		if(toRem <= 0)
			return;
		atkLvl = max(0 , atkLvl - toRem);
		calculate();
	}
	
	public void addDefense(int toAdd)
	{
		if(toAdd <= 0)
			return;
		defLvl = min(5 , defLvl + toAdd);
		calculate();
	}
	
	/**
	 * Removes some value from defense
	 * @param toRem value to subtract from , must be greater than zero
	 */
	public void remDefense(int toRem)
	{
		if(toRem <= 0)
			return;
		defLvl = max(0 , defLvl - toRem);
		calculate();
	}
	
	public int getAttackLvl()
	{
		return atkLvl;
	}
	
	public int getDefenseLvl()
	{
		return defLvl;
	}
	
	public int getAttack() 
	{
		return attack;
	}
	
	public int getDefense() 
	{
		return defense;
	}
	
	public Player getCellOwner()
	{
		return player;
	}
}

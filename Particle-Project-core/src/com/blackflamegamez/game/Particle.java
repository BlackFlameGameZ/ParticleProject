package com.blackflamegamez.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blackflamegamez.game.enums.ParticleColor;
import com.blackflamegamez.game.geometry.Body2D;

public class Particle extends Body2D 
{
	private Player          player;
	private Shield			shield;
	private Attack			attack;
	
	public Particle(Player p , float x , float y , float width , int defLevel , int atkLevel)
	{
		super(x , y , width , width);
		player   = p;
		shield = new Shield(ParticleColor.getColor(player.getShieldColor()));
		attack = new Attack(ParticleColor.getColor(player.getAttackColor()));
	}
	
	public void render(SpriteBatch batch , float delta)
	{
		batch.draw(Assets.manager.get("images/particle/base.png" , Texture.class) , x , y , width , height);
		
		shield.render(batch, x, y, delta);
		attack.render(batch, x, y, delta);
		
		batch.setColor(Color.WHITE);
	}
	
	private void calculate()
	{
		//attack 	= (int)((1 + ((float)defLvl - 1) * 0.2) * (float)atkLvl * 10);
		//(int)((1 + ((float)atkLvl - 1) * 0.1) * (float)defLvl * 10);
	}
	
	public void addAttack(int toAdd)
	{
		attack.levelUp(toAdd);
	}
	/**
	 * Removes some value from attack
	 * @param toRem value to subtract from, must be greater than zero
	 */
	public void remAttack(int toRem)
	{
		attack.levelDown(toRem);
	}
	
	public void addDefense(int toAdd)
	{
		shield.levelUp(toAdd);
	}
	
	/**
	 * Removes some value from defense
	 * @param toRem value to subtract from , must be greater than zero
	 */
	public void remDefense(int toRem)
	{
		shield.levelDown(toRem);
	}
	
	public int getAttackLvl()
	{
		return attack.getLevel();
	}
	
	public int getDefenseLvl()
	{
		return shield.getLevel();
	}
	/*
	public int getAttack() 
	{
		return attack;
	}*/
	
	public int getDefense() 
	{
		return shield.getHealth();
	}
	
	public Player getCellOwner()
	{
		return player;
	}
	
	public void activate()
	{
		System.out.println("Activate");
		shield.activate();
		attack.activate();
	}
	
	public void deactivate()
	{
		System.out.println("Deactivate");
		shield.deactivate();
		attack.deactivate();
	}
}

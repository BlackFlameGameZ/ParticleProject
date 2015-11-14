package com.blackflamegamez.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AttackParticles 
{
	private Shield shield;
	private int attackLevel;
	private float attackParticlePower;
	
	public AttackParticles() 
	{
		shield 				= null;
		attackLevel 		= 0;
		attackParticlePower = 0;
	}
	
	public void render(SpriteBatch batch, float x, float y)
	{
		
	}
	
	public void renderSkeleton(SpriteBatch batch, float x, float y)
	{
		
	}
	
	public int getAttackLevel() 
	{
		return attackLevel;
	}
	
	public void update()
	{
		attackParticlePower = shield.getShieldLevel() * 10;
	}
	
	// THIS IS NEEDED FOR THIS CLASS TO WORK PROPERLY
	public void setShield(Shield s)
	{
		shield = s;
	}
}

package com.blackflamegamez.game;

import static com.blackflamegamez.game.staticfields.GameStaticValues.*;

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

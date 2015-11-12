package com.blackflamegamez.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Shield 
{
	private AttackParticles 	attack;
	private int 				shieldLevel;
	private int 				shieldLayerPower;
	private int 				shieldDefPoints;
	private Color 				color;
	private ArrayList<Integer> 	layerPowerList;
	
	public Shield(Color shieldColor) 
	{
		attack 				= null;
		shieldLevel 		= 0;
		shieldLayerPower 	= 0;
		shieldDefPoints		= 0;
		color 				= shieldColor;
		layerPowerList 		= new ArrayList<Integer>();
	}
	
	public void render(SpriteBatch batch, float x, float y)
	{
		if(shieldLevel > 0)
		{
			batch.setColor(color);
		}
	}
	
	// THIS IS NEEEDED IF U WANT THIS CLASS TO WORK PROPERLY !!!
	public void setAttackParticles(AttackParticles ap)
	{
		attack = ap;
	}
	
	public void destroy(float damage)
	{
		shieldDefPoints -= damage;
		shieldLevel = shieldDefPoints / shieldLayerPower;
		if(shieldDefPoints % shieldLayerPower > 0)
			shieldLevel++;
		attack.update();
	}
	
	public int getShieldLevel() 
	{
		return shieldLevel;
	}
	
	public void update()
	{
		int tmp = shieldDefPoints % shieldLayerPower;
		float procent = 1;
		if(tmp != 0)
			procent = (float)tmp / shieldLayerPower;
		shieldLayerPower = attack.getAttackLevel() * 10;
		int lastShield = (int)(procent * shieldLayerPower);
		shieldDefPoints = shieldLayerPower * (shieldLevel - 1) + lastShield;
	}
	
	public ArrayList<Integer> shieldsPower()
	{
		layerPowerList.clear();
		int tmp = shieldDefPoints;
		while(tmp > shieldLayerPower)
		{
			layerPowerList.add(shieldLayerPower);
			tmp -= shieldLayerPower;
		}
		if(tmp > 0)
			layerPowerList.add(tmp);
		return layerPowerList;
	}
}

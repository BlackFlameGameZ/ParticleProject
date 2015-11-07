package com.blackflamegamez.game.enums;

import com.badlogic.gdx.graphics.Color;

/**
 * @author Milan Topalovic
 */
public enum ParticleColor 
{
	BLUE , GREEN , RED , YELLOW;
	
	public static Color getColor(ParticleColor c)
	{
		switch(c)
		{
			case BLUE:
				return Color.BLUE;
			case GREEN:
				return Color.GREEN;
			case RED:
				return Color.RED;
			case YELLOW:
				return Color.YELLOW;
			default:
				return Color.BLUE;
		}
	}
	
	
	public static int getColorValue(ParticleColor c)
	{
		ParticleColor colors[] = ParticleColor.values();
		for(int i = 0 ; i < colors.length ; ++i)
			if(c.equals(colors[i]))
				return i;
		return 0;
	}
	
	public static ParticleColor getParticleColorForValue(int value)
	{
		ParticleColor colors[] = ParticleColor.values();
		return colors[value];
	}
}

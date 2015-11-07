package com.blackflamegamez.game.enums;

import com.badlogic.gdx.graphics.Color;

/**
 * @author Milan Topalovic
 */
public enum ParticleColor 
{
	BLUE , GREEN , RED , YELLOW;
	
	public Color getColr(ParticleColor c)
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
}

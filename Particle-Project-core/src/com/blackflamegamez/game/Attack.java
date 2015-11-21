package com.blackflamegamez.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;

public class Attack 
{
	
	private int 	level;
	private int 	unitPower;
	private Color 	color;
	
	// Stuff for rendering
	private AnimatedSprite 	texture;
	private Label			unitLabel;
	
	// Reference to the Shield particles (NEEDED FOR POWER UPDATES)
	private Shield 	shield;
	
	public Attack(Color attackColor) 
	{
		shield 		= null;
		level 		= 1;
		unitPower 	= 10;
		color	 	= attackColor;
		texture 	= new AnimatedSprite(Assets.manager.get("images/particle/atk_1_#[80,80].png", Texture.class), 80, 80, true);
		deactivate();
		//unitLabel = new Label("", skin);
	}
	
	public void render(SpriteBatch batch, float x, float y, float delta)
	{
		if(level > 0)
		{
			batch.setColor(color);
			batch.draw(texture.getFrame(delta), x + 50 * hRatio, y + 50 * hRatio, 80 * hRatio, 80 * hRatio);
		}
	}
	
	public void renderSkeleton(SpriteBatch batch, float x, float y)
	{
		
	}
	
	public void levelUp(int levels)
	{
		System.out.println("Attack levelUp()");
		if(levels < 0)
			return;
		level = Math.min(5, level + levels);
		texture.changeSource(Assets.manager.get("images/particle/atk_" + level + "_#[80,80].png", Texture.class));
	}
	
	public void levelDown(int levels)
	{
		if(levels < 0)
			return;
		level = Math.max(0, level - levels);
		if(level > 0)
			texture.changeSource(Assets.manager.get("images/particle/atk_" + level + "_#[80,80].png", Texture.class));
	}
	
	public int getLevel() 
	{
		return level;
	}
	
	public int getUnitPower()
	{
		return unitPower;
	}
	
	public void update()
	{
		unitPower = shield.getLevel() * 10;
	}
	
	// THIS IS NEEDED FOR THIS CLASS TO WORK PROPERLY
	public void setShield(Shield s)
	{
		shield = s;
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

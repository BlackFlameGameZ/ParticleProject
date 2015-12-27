package com.blackflamegamez.game;

import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;
import static com.blackflamegamez.game.staticfields.GameStaticValues.ratioDifference;
import static com.blackflamegamez.game.staticfields.GameStaticValues.vRatio;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.blackflamegamez.game.staticfields.GameStaticValues;

/**
 * @author BlackFlame
 *
 * Button for MainMenu [ASPECT RATIO FIXED]
 */

public class MainMenuButton 
{
	private Texture text;				// Button text image
	private float 	x;					// Button x coordinate
	private float 	y;					// Button y coordinate
	private Polygon shape;				// Shape used for detecting mouse clicks
	private boolean activated;			// Bool used to se t button activated or deactivated
	private boolean blockedActivation;	// Tells us if we can change button state
	private boolean hidden;				// Bool which tells us if the button is hidden or not. This used for button hiding and unhiding animation
	private boolean inAnimation;		// Tells us if the button is in animation or is it static
	private float	animationStep;		// Step in pixels for animating a button
	private int 	frameDelay;			// Delay between click and animation start
	private float 	bottom_limit;
	private boolean click_enabled;
	private boolean enabled;
	
	public MainMenuButton(Texture textTex, float x, float y) 
	{
		text 			= textTex;
		x 				= x * hRatio;
		y 				= y * vRatio - ratioDifference;
		this.x 			= x;
		this.y 			= y;
		shape 			= new Polygon();
		activated 		= false;
		hidden			= false;
		inAnimation 	= false;
		frameDelay		= 0;
		animationStep 	= 0;
		bottom_limit	= -550;
		click_enabled	= true;
		enabled 		= true;
		calculateVertices();
	}
	
	public void render(SpriteBatch batch)
	{
		float alpha = 1f;
		if(!enabled)
			alpha = 0.5f;
		batch.setColor(1f, 1f, 1f, alpha);
		batch.draw(Assets.manager.get("images/main_menu/btn_back.png", Texture.class), x, y, hRatio * 735, hRatio * 110);
		batch.draw(text, x, y, hRatio * 735, hRatio * 110);
		Color c = GameStaticValues.background.getColorBase();
		batch.setColor(c.r, c.g, c.b, alpha);
		if(activated)
			batch.draw(Assets.manager.get("images/main_menu/btn_border_pressed.png", Texture.class), x, y, hRatio * 735, hRatio * 110);
		else
			batch.draw(Assets.manager.get("images/main_menu/btn_border.png", Texture.class), x, y, hRatio * 735, hRatio * 110);
		if(inAnimation)
		{
			if(frameDelay == 0)
			{
				x += animationStep;
				calculateVertices();
				if(x > 0)
				{
					x = 0;
					inAnimation = false;
				}
				else if(x < bottom_limit * hRatio)
				{
					x = bottom_limit * hRatio;
					inAnimation = false;
				}
			}
			else
				frameDelay--;
		}
		batch.setColor(Color.WHITE);
	}
	
	public boolean contains(float x, float y)
	{
		if(hidden || !click_enabled)
			return false;
		return shape.contains(x, y);
	}
	
	private void calculateVertices()
	{
		shape.setVertices(new float[]{x, y, x, y + hRatio * 110, x + 735 * hRatio, y + hRatio * 110, x + 625 * hRatio, y});
	}
	
	public void hide(int delay, boolean all)
	{
		if(all)
			bottom_limit = -735f;
		else
			bottom_limit = -550f;
		inAnimation 	= true;
		animationStep 	= -25f * hRatio;
		frameDelay		= delay;
		hidden 			= true;
	}
	
	public void unhide(int delay)
	{
		inAnimation 	= true;
		animationStep 	= 25f * hRatio;
		frameDelay		= delay;
		hidden 			= false;
	}
	
	public void setActivated(boolean activated) 
	{
		if(!blockedActivation)
			this.activated = activated;
	}
	
	public void setBlockedActivation(boolean block) 
	{
		blockedActivation = block;
		if(!blockedActivation)
			activated = false;
	}
	
	public void setClick_enabled(boolean click_enabled) 
	{
		this.click_enabled = click_enabled;
	}
	
	public void setEnabled(boolean enabled) 
	{
		this.enabled = enabled;
		if(!enabled)
			setClick_enabled(false);
	}
	
	public boolean isInAnimation() 
	{
		return inAnimation;
	}
}

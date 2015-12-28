package com.blackflamegamez.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;

import static com.blackflamegamez.game.staticfields.GameStaticValues.background;
import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;
import static com.blackflamegamez.game.staticfields.GameStaticValues.ratioDifference;
import static com.blackflamegamez.game.staticfields.GameStaticValues.vRatio;

/**
 * @author BlackFlame
 *
 * This class represents message box used for displaying some info, updates info and options.
 * 
 */

public class MessageBox 
{
	private float 		x;					// x coordinate of message box
	private float 		y;					// y coordinate of message box
	private float 		opacityStep;		// Opacity step for fading animation
	private float 		contentAlpha;			// Current value of alpha for text
	private float 		boxAlpha;			// Current value of alpha fo box
	
	private Texture 	backgroundImage;	// Background image of message box
	private Texture 	border;				// Border image of message box
	private BoxType 	mode;				// Box mode WELCOME - OPTIONS - PARTICLE_INFO
	private BoxType		switchTo;			// Box mode to switch to
	private BitmapFont	titleFont;			// Font used for drawing any title
	private BitmapFont  textFont;			// Font used for drawing text
	private Rectangle	shape;				// Used for mouse listening
	
	private boolean 	inAnimation;		// fading animation
	private boolean 	all;
	private boolean 	switching;			// this variable is for first half of fade animation FADE-OUT
	private boolean		doneSwitching;		// this variable is for second half of fade animation FADE-IN
	
	public MessageBox(float x, float y) 
	{
		this.x 			= x;
		this.y 			= y;
		backgroundImage = Assets.manager.get("images/main_menu/box_back.png", Texture.class);
		border 			= Assets.manager.get("images/main_menu/box_border.png", Texture.class);
		opacityStep 	= 0f;
		contentAlpha 	= 1f;
		boxAlpha		= 1f;
		mode			= BoxType.WELCOME;
		
		titleFont = Assets.manager.get("fonts/box_title.fnt", BitmapFont.class);
		titleFont.getData().setScale(hRatio);
		
		textFont = Assets.manager.get("fonts/box_text.fnt", BitmapFont.class);
		textFont.getData().setScale(hRatio);
		
		switching 		= false;
		doneSwitching 	= true;
		switchTo 		= BoxType.WELCOME;
		inAnimation 	= false;
		shape = new Rectangle(x * hRatio, y * hRatio, 1500 * hRatio, 1000 * hRatio);
	}
	
	public void render(SpriteBatch batch)
	{
		// rendering box behind
		batch.setColor(1f, 1f, 1f, boxAlpha);
		batch.draw(backgroundImage, x * hRatio, y * vRatio - ratioDifference, 1500 * hRatio, 1000 * hRatio);
		Color c = background.getColorBase();
		batch.setColor(c.r, c.g, c.b, boxAlpha);
		batch.draw(border, x * hRatio, y * vRatio - ratioDifference, 1500 * hRatio, 1000 * hRatio);
		batch.setColor(Color.WHITE);
		
		doAnimation();
		adjustAlpha();
		checkSwitching();
			
		switch(mode)
		{
			case WELCOME:
				renderWelcome(batch);
				break;
			case OPTIONS:
				renderOptions(batch);
				break;
			default:
				renderParticle(batch);
		}
	}
	
	private void doAnimation()
	{
		if(inAnimation)
		{
			if(all)
				boxAlpha += opacityStep;
			contentAlpha += opacityStep;
		}
	}
	
	private void adjustAlpha()
	{
		if(contentAlpha <= 0 || contentAlpha >= 1)
		{
			inAnimation = false;
			opacityStep = 0;
			contentAlpha = contentAlpha >= 1 ? 1 : 0;
		}
	}
	
	private void checkSwitching()
	{
		if(switching)
		{
			if(contentAlpha == 0)
			{
				mode = switchTo;
				unhideContent(0);
			}
			else if(switching && contentAlpha == 1)
				switching = false;
		}	
	}
	
	private void renderParticle(SpriteBatch batch)
	{
		
	}
	
	private void renderWelcome(SpriteBatch batch)
	{
		String title 	= "Welcome";
		String text 	= "Nano WARS is strategy based board game. Its name comes from nano particles "
						+ "which are waging the wars...";
		titleFont.setColor(1f, 1f, 1f, contentAlpha);
		textFont.setColor(1f, 1f, 1f, contentAlpha);
		titleFont.draw(batch, title, x * hRatio, 1230 * vRatio - ratioDifference, 1500 * hRatio, Align.center, false);
		textFont.draw(batch, text, (x + 50) * hRatio, 1130 * vRatio - ratioDifference, 1400 * hRatio, Align.center, true);
	}
	
	private void renderOptions(SpriteBatch batch)
	{
		String title 	= "Options";
		String text 	= "Some options here ...";
		titleFont.setColor(1f, 1f, 1f, contentAlpha);
		textFont.setColor(1f, 1f, 1f, contentAlpha);
		titleFont.draw(batch, title, x * hRatio, 1230 * vRatio - ratioDifference, 1500 * hRatio, Align.center, false);
		textFont.draw(batch, text, (x + 50) * hRatio, 1130 * vRatio - ratioDifference, 1400 * hRatio, Align.center, true);
	}
	
	public boolean contains(float x, float y)
	{
		return shape.contains(x, y);
	}
	
	public void switchTo(BoxType newMode)
	{
		if(mode != newMode)
		{
			switching = true;
			switchTo = newMode;
			hideContent(0);
		}
	}
	
	public void hideContent(int delay)
	{
		opacityStep = -0.05f;
		inAnimation = true;
	}
	
	public void hideAll(int delay)
	{
		opacityStep = -0.05f;
		inAnimation = true;
		all			= true;
	}
	
	public void unhideContent(int delay)
	{
		opacityStep = 0.05f;
		inAnimation = true;
	}
	
	public void unhideAll(int delay)
	{
		opacityStep = 0.05f;
		inAnimation = true;
		all			= false;
	}
	
	public boolean isInAnimation() 
	{
		return inAnimation;
	}
	
	public BoxType getMode() 
	{
		return mode;
	}
}

package com.blackflamegamez.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * 
 * @author BlackFlame
 * The class AnimatedSprite uses class Animation 
 * for returning frames from a SpriteSheet
 */
public class AnimatedSprite extends ScreenAdapter
{
	private Animation 		animation;
	private Texture			texture;
	private float 			width;
	private float 			height;
	private boolean			loop;
	
	/**
	 * Constructor, storing texture, texture region width and height
	 * @param texture 	- represents loaded Texture object
	 * @param width		- frame width
	 * @param height	- frame height
	 * @param loop		- <code>True</code> for looping and <code>False</code> for not looping animation
	 */
	public AnimatedSprite(Texture texture, float width, float height, boolean loop) 
	{
		this.texture 	= texture;
		this.width		= width;
		this.height		= height;
		this.loop		= loop;
		createAnimation();
	}
	
	/**
	 * Constructor, storing texture, texture region width and height
	 * @param texturePath 	- represents path to the Texture
	 * @param width			- frame width
	 * @param height		- frame height
	 * @param loop			- <code>True</code> for looping and <code>False</code> for not looping animation
	 */
	public AnimatedSprite(String texturePath, float width, float height, boolean loop) 
	{
		this.texture 	= new Texture(texturePath);
		this.width		= width;
		this.height		= height;
		this.loop		= loop;
		createAnimation();
	}
	
	private void createAnimation()
	{
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		int matrixWidth 	= texture.getWidth()/(int)width;
		int matrixHeight 	= texture.getHeight()/(int)height;
		
		TextureRegion[][] matrix 	= TextureRegion.split(texture, (int)width, (int)height);
		TextureRegion[] array		= new TextureRegion[matrixWidth * matrixHeight];
		
		int index = 0;
		for(int i = 0 ; i < matrixHeight ; i++)
			for(int j = 0 ; j < matrixWidth ; j++)
				array[index++] = matrix[i][j];
		
		animation = new Animation(1f/60f, array);
		if(loop)
			animation.setPlayMode(PlayMode.LOOP);
		else
			animation.setPlayMode(PlayMode.NORMAL);
	}
	
	/**
	 * 
	 * @param stateTime is used to determine which frame of the animation should be returned
	 * @return instance of TextureRegion class which represents one frame in animation
	 */
	public TextureRegion getFrame(float stateTime)
	{
		return animation.getKeyFrame(stateTime);
	}
	
	public boolean animationFinished(float deltaTime)
	{
		return animation.isAnimationFinished(deltaTime);
	}
}

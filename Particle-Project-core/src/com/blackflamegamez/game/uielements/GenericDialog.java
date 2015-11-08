package com.blackflamegamez.game.uielements;

import java.awt.geom.Rectangle2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.blackflamegamez.game.geometry.Body2D;
import com.sun.javafx.geom.Rectangle;

/**Generic class for rendering Dialog on game screen
 * 
 * @author Milan Topalovic
 */
public abstract class GenericDialog extends Body2D 
{

	private DialogDirection    direction;
	private boolean            useAnimation;
	private float              animationDuration;
	private float              elapsedTime;
	private boolean            isOpening;
	private boolean            isClosing;
	private boolean            canRender;
	private boolean            windowClosed;
	private NinePatch          backgroundBox;
	private float              minWidth;
	private float              minHeight;
	
	public GenericDialog(float screenX , float screenY , DialogDirection dir , float animationDuration , boolean useAnimation )
	{
		super(screenX , screenY , 0f , 0f);
		this.direction     = dir; 
		this.useAnimation  = useAnimation;
		this.backgroundBox = new NinePatch(new Texture(Gdx.files.internal("images/dialog_box.png")) , 10 , 10 , 10 , 10 );
		this.minWidth  = 20f;
		this.minHeight = 20f;
		this.canRender = !useAnimation;
		this.isOpening = true;
		this.isClosing = false;
		this.windowClosed      = false;
		this.animationDuration = animationDuration;
	}
	
	public void render(SpriteBatch sb , float delta)
	{
		elapsedTime += delta;
		drawBackground(sb , delta);
		if(canRender)
			drawDialogElements(sb , delta);
	}

	public void drawBackground(SpriteBatch sb , float delta)
	{
		if(useAnimation)
		{
			if(isOpening)
				drawOpening(sb);
			else if(isClosing)
				drawClosing(sb);
			else if(canRender) 
			{
				Rectangle2D.Float bound = calculateCoordinates(x , y , width , height);
				backgroundBox.draw(sb, bound.x , bound.y , bound.width , bound.height);
			}
		} else {
			Rectangle2D.Float bound = calculateCoordinates(x , y , width , height);
			backgroundBox.draw(sb, bound.x , bound.y , bound.width , bound.height);
		}
	}
	
	private void drawOpening(SpriteBatch sb)
	{
		if(elapsedTime <= animationDuration)
		{
			float factor = Interpolation.pow4.apply(elapsedTime / animationDuration);
			float tmpwidth = minWidth + factor * (width - minWidth);
			float tmpheight = minHeight + factor * (height - minHeight);
			Rectangle2D.Float bound = calculateCoordinates(x , y , tmpwidth , tmpheight);
			backgroundBox.draw(sb, bound.x , bound.y , bound.width , bound.height);
		} else {
			canRender = true;
			isOpening = false;
		}
	}
	
	private void drawClosing(SpriteBatch sb)
	{
		if(elapsedTime <= animationDuration)
		{
			float factor = Interpolation.pow4.apply(elapsedTime / animationDuration);
			float tmpwidth = width - factor * (width - minWidth);
			float tmpheight= height - factor * (height - minHeight);
			Rectangle2D.Float bound = calculateCoordinates(x , y , tmpwidth , tmpheight);
			backgroundBox.draw(sb, bound.x , bound.y , bound.width , bound.height);
		} else 
			windowClosed = true;
	}
	
	public void closeWindow()
	{
		elapsedTime = 0f;
		canRender = false;
		isClosing = true;
	}
	
	/**
	 * Computes position of dialog box depending on direction
	 *  
	 * @return {@link Rectangle2D.Float} dialog frame
	 */
	private Rectangle2D.Float getImagePoints(float x , float y , float w , float h)
	{
		Rectangle2D.Float tmp = new Rectangle2D.Float();
		switch(direction)
		{
		case DOWN_RIGHT:
			tmp.x = x;
			tmp.y = y - h;
			break;
		case DOWN_LEFT:
			tmp.x = x - w;
			tmp.y = y - h;
			break;
		case UP_RIGHT:
			tmp.x = x - w;
			tmp.y = y;
			break;
		case UP_LEFT:
			tmp.x = x;
			tmp.y = y;
			break;
		}
		tmp.width = w;
		tmp.height = h;
		return tmp;
	}
	
	public boolean getWindowClosed()
	{
		return windowClosed;
	}
	
	/**
	 * Sets coordinates of dialog
	 */
	protected Rectangle2D.Float calculateCoordinates(float x , float y , float width , float height)
	{
		Rectangle2D.Float bounds = getImagePoints(x , y , width , height);
		return bounds;
	}
	
	public boolean canClose()
	{
		return (!useAnimation | (!isOpening & !isClosing));
	}
	
	public void setMinWidth(float width)
	{
		minWidth = width;
	}
	
	public void setMinHeight(float height)
	{
		minHeight = height;
	}
	
	/**
	 * Used for drawing dialog elements after window opening is finished
	 * @param sb
	 * @param delta
	 */
	public abstract void drawDialogElements(SpriteBatch sb , float delta);
	
	/**Various directions of dialog opening and closing animation
	 * 
	 * @author Milan Topalovic
	 * 
	 */
	public static enum DialogDirection
	{
		UP_LEFT , UP_RIGHT , DOWN_LEFT , DOWN_RIGHT;
	}
	
	/**
	 * Calculates position of dialog which is beeing rendered depending on screen width and screen height
	 * @param x 
	 * @param y
	 * @param width
	 * @param height
	 * @return {@link DialogDirection}
	 */
	public static DialogDirection getDirectionForBounds(float x , float y , float width , float height)
	{
		return null;
	}
}

package com.blackflamegamez.game.uielements;

import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;
import static com.blackflamegamez.game.staticfields.GameStaticValues.ratioDifference;
import static com.blackflamegamez.game.staticfields.GameStaticValues.vRatio;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;

import javafx.geometry.Bounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;
import com.blackflamegamez.game.geometry.Body2D;

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
				backgroundBox.draw(sb, x, y - height, width, height);
		} else
			backgroundBox.draw(sb, x, y - height, width, height);
	}
	
	private void drawOpening(SpriteBatch sb)
	{
		if(elapsedTime <= animationDuration)
		{
			float factor = Interpolation.pow4.apply(elapsedTime / animationDuration);
			float tmpwidth = minWidth + factor * (width - minWidth);
			float tmpheight = minHeight + factor * (height - minHeight);
			backgroundBox.draw(sb, x, y - tmpheight, tmpwidth , tmpheight);
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
			backgroundBox.draw(sb, x, y - tmpheight , tmpwidth, tmpheight);
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
	private Rectangle2D.Float getImagePoints()
	{
		Rectangle2D.Float tmp = new Rectangle2D.Float();
		float newx = x , newy = y;
		switch(direction)
		{
		case DOWN_LEFT:
			newx = x - width;
			newy = y;
			break;
		case DOWN_RIGHT:
			newx = x;
			newy = y;
			break;
		case UP_LEFT:
			newx = x - width;
			newy = y + height;
			break;
		case UP_RIGHT:
			newx = x;
			newy = y + height;
			break;
		}
		tmp.setRect(newx, newy, width, height);
		return tmp;
	}
	
	public boolean getWindowClosed()
	{
		return windowClosed;
	}
	
	/**
	 * Sets coordinates of dialog
	 */
	protected void calculateCoordinates()
	{
		Rectangle2D.Float bounds = getImagePoints();
		setWorldCoordinates(bounds.x , bounds.y , bounds.width , bounds.height);
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

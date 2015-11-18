package com.blackflamegamez.game;

import static com.blackflamegamez.game.staticfields.GameStaticValues.hRatio;
import static com.blackflamegamez.game.staticfields.GameStaticValues.ratioDifference;
import static com.blackflamegamez.game.staticfields.GameStaticValues.vRatio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Align;
import com.blackflamegamez.game.enums.PlayerState;
import com.blackflamegamez.game.staticfields.GameStaticValues;

/**
 * @author Mario Burovic
 * @author Milan Topalovic
 */
public class UpgradeBox 
{
	private float 		x;
	private float 		y;
	private float 		hilightX;
	private float		hilightY;
	private boolean 	opened;
	private boolean 	animate;
	private Hexagon 	atkUpgrade;
	private Hexagon 	defUpgrade;
	private Hexagon 	attack;
	private Hexagon 	split;
	private Hexagon 	selectedHexagon;
	private float 		xOffset;
	private float   	xBaseStep;
	private Rectangle 	rect;
	private Player      player;
	
	private Label title;
	
	public UpgradeBox(float x, float y , Player player) 
	{
		this.x 			= x;
		this.y 			= y;
		this.player     = player;
		opened 			= false;
		xOffset 		= -550;
		atkUpgrade 		= new Hexagon(x + 580 + xOffset, y + 543, 100);
		defUpgrade 		= new Hexagon(x + 580 + xOffset, y + 400, 100);
		attack 			= new Hexagon(x + 580 + xOffset, y + 255, 100);
		split 			= new Hexagon(x + 580 + xOffset, y + 111, 100);
		selectedHexagon = null;
		xBaseStep		= 30;
		animate 		= false;
		rect			= new Rectangle(x + xOffset * hRatio, y * vRatio - ratioDifference, 630 * hRatio, 704 * hRatio);
		
		BitmapFont font = new BitmapFont(Gdx.files.internal("fonts/FB_Demi_60.fnt"));
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		title			= new Label("Particle Upgrades", new LabelStyle(font, Color.WHITE));
		title.setSize(580 * hRatio, 60 * hRatio);
		title.setAlignment(Align.center);
		title.setFontScale(hRatio);
	}
	
	public void render(SpriteBatch batch, float delta)
	{
		if(animate)
			xOffset += xBaseStep * 60 * delta;
		if(xOffset >= 0)
		{
			xOffset = 0;
			animate = false;
			updateHexs();
		}
		
		if(xOffset <= -550)
		{
			xOffset = -550;
			animate = false;
			updateHexs();
		}
		
		batch.draw(Assets.manager.get("images/left_upgrade_box.png", Texture.class)		, x + xOffset * hRatio, y * vRatio - ratioDifference, 682 * hRatio, 704 * hRatio);
		rect.setPosition(x + xOffset * hRatio, y * vRatio - ratioDifference);
		
		if(player.getState() != PlayerState.WAITING_FOR_COMMAND && player.getState() != PlayerState.INSTANTIATING)
		{
			float movedHilight = hilightX + xOffset * hRatio;
			batch.draw(Assets.manager.get("images/green_highlight.png", Texture.class)	, movedHilight, hilightY, 100 * hRatio, 100 * hRatio);
		}
		
		title.setPosition(xOffset * hRatio, (y + 600) * vRatio - ratioDifference);
		title.draw(batch, 1f);
		
		batch.draw(Assets.manager.get("images/atk_upgrade.png", Texture.class)			, x + 582 *hRatio + xOffset * hRatio, (y + 518) * vRatio - ratioDifference, 100 * hRatio, 100 * hRatio);
		batch.draw(Assets.manager.get("images/def_upgrade.png", Texture.class)			, x + 580 *hRatio + xOffset * hRatio, (y + 372) * vRatio - ratioDifference, 100 * hRatio, 100 * hRatio);
		batch.draw(Assets.manager.get("images/attack.png", Texture.class)				, x + 580 *hRatio + xOffset * hRatio, (y + 229) * vRatio - ratioDifference, 100 * hRatio, 100 * hRatio);
		batch.draw(Assets.manager.get("images/split.png", Texture.class)				, x + 580 *hRatio + xOffset * hRatio, (y + 86) * vRatio - ratioDifference, 100 * hRatio, 100 * hRatio);
	}
	
	public void debug(ShapeRenderer sr)
	{
		atkUpgrade.debug(sr);
		defUpgrade.debug(sr);
		attack.debug(sr);
		split.debug(sr);
	}
	
	public void open()
	{
		animate 	= true;
		xBaseStep 	= 30;
		opened 		= true;
	}
	
	public void close()
	{
		xBaseStep 	= -30;
		animate		= true;
		opened 		= false;
	}
	
	private void updateHexs()
	{
		atkUpgrade	.setNormal(x + 580 + xOffset, y + 543, 100);
		defUpgrade	.setNormal(x + 580 + xOffset, y + 400, 100);
		attack		.setNormal(x + 580 + xOffset, y + 255, 100);
		split		.setNormal(x + 580 + xOffset, y + 111, 100);
	}
	
	public void reset()
	{
		opened 			= false;
		xOffset 		= -550;
		updateHexs();
	}
	
	public boolean touchDown(float x, float y)
	{
		if(!animate)
		{
			if(atkUpgrade.contains(x, y))
			{
				selectedHexagon = atkUpgrade;
				return true;
			}
			else if(defUpgrade.contains(x, y))
			{
				selectedHexagon = defUpgrade;
				return true;
			}
			else if(attack.contains(x, y))
			{
				selectedHexagon = attack;
				return true;
			}
			else if(split.contains(x, y))
			{
				selectedHexagon = split;
				return true;
			}
		}
		return false;
	}
	
	public boolean touchUp(float x, float y)
	{
		boolean retVal = false;
		PlayerState currentState = player.getState();
		if(!animate && currentState != PlayerState.INSTANTIATING)
		{
			if(selectedHexagon != null)
			{
				if(selectedHexagon.equals(atkUpgrade) && atkUpgrade.contains(x, y))
				{
					System.out.println("ATK UPGRADE");
					hilightX = this.x + 582 *hRatio;
					hilightY = (this.y + 518) * vRatio - ratioDifference;
					if(currentState == PlayerState.UPGRADING_ATK)
						player.setState(PlayerState.WAITING_FOR_COMMAND);
					else
						player.setState(PlayerState.UPGRADING_ATK);
					retVal = true;
				}
				else if(selectedHexagon.equals(defUpgrade) && defUpgrade.contains(x, y))
				{
					System.out.println("DEF UPGRADE");
					hilightX = this.x + 580 *hRatio;
					hilightY = (this.y + 372) * vRatio - ratioDifference;
					if(currentState == PlayerState.UPGRADING_DEF)
						player.setState(PlayerState.WAITING_FOR_COMMAND);
					else
						player.setState(PlayerState.UPGRADING_DEF);
					retVal = true;
				}
				else if(selectedHexagon.equals(attack) && attack.contains(x, y))
				{
					System.out.println("ATTACK");
					hilightX = this.x + 580 *hRatio;
					hilightY = (this.y + 229) * vRatio - ratioDifference;
					if(currentState == PlayerState.ATTACKING)
						player.setState(PlayerState.WAITING_FOR_COMMAND);
					else
						player.setState(PlayerState.ATTACKING);
					retVal = true;
				}
				else if(selectedHexagon.equals(split) && split.contains(x, y))
				{
					System.out.println("SPLIT");
					hilightX = this.x + 580 *hRatio;
					hilightY = (this.y + 86) * vRatio - ratioDifference;
					if(currentState == PlayerState.SPLITTING)
						player.setState(PlayerState.WAITING_FOR_COMMAND);
					else
						player.setState(PlayerState.SPLITTING);
					retVal = true;
				}
			}
			selectedHexagon = null;
		}
		if(rect.contains(x, y))
			retVal = true;
		return retVal;
	}
	
	public boolean isOpened() 
	{
		return opened;
	}
}

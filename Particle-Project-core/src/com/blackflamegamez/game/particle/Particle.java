package com.blackflamegamez.game.particle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blackflamegamez.game.Player;
import com.blackflamegamez.game.geometry.Body2D;

public abstract class Particle extends Body2D 
{
	private Player          owner;
	private String 			name;
	private int 			maxLevel;
	private int				level;
	private int				health;
	private int 			damage;
	private int 			range;
	private boolean			isUpgradeable;
	private boolean 		isMovable;
	private boolean			isSplittable;
	private boolean 		isStable;
	
	
	
	public Particle(float x, float y, float width, float height, Player owner, String name, int maxLevel, int level, int health,
			int damage, int range, boolean isUpgradeable, boolean isMovable, boolean isSplittable,
			boolean isStable) {
		super(x, y, width, height);
		
		this.owner 			= owner;
		this.name 			= name;
		this.maxLevel		= maxLevel;
		this.level 			= level;
		this.health 		= health;
		this.damage 		= damage;
		this.range 			= range;
		this.isUpgradeable 	= isUpgradeable;
		this.isMovable 		= isMovable;
		this.isSplittable 	= isSplittable;
		this.isStable 		= isStable;
	}
	
	public Player getOwner() 
	{
		return owner;
	}

	public void setOwner(Player owner) 
	{
		this.owner = owner;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getLevel() 
	{
		return level;
	}

	public void setLevel(int level) 
	{
		this.level = level;
	}

	public int getHealth() 
	{
		return health;
	}

	public void setHealth(int health) 
	{
		this.health = health;
	}

	public int getDamage() 
	{
		return damage;
	}

	public void setDamage(int damage) 
	{
		this.damage = damage;
	}

	public int getRange() 
	{
		return range;
	}

	public void setRange(int range) 
	{
		this.range = range;
	}

	public boolean isMovable() 
	{
		return isMovable;
	}
	
	public boolean isSplittable() 
	{
		return isSplittable;
	}
	
	public boolean isStable() 
	{
		return isStable;
	}
	
	public boolean isUpgradeable() 
	{
		return isUpgradeable;
	}
	
	public void destroy()
	{
		
	}
	
	public abstract void render(SpriteBatch batch , float delta);
	public abstract boolean isInRange();
	
	public void upgrade(int levels)
	{
		levels = Math.min(level + levels, maxLevel);
	}
	
	public void downgrade(int levels)
	{
		levels = Math.max(level - levels, 0);
	}
	
	public void attack(Particle p)
	{
		p.takeDamage(p);
	}
	
	public void takeDamage(Particle p)
	{
		health -= p.getDamage();
		
		if(health <= 0)
			destroy();
	}
}

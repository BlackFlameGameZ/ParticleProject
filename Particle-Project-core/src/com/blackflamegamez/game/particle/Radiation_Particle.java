package com.blackflamegamez.game.particle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blackflamegamez.game.Player;

public class Radiation_Particle extends Particle
{

	public Radiation_Particle(float x, float y, float width, float height, Player owner, String name, int maxLevel,
			int level, int health, int damage, int range, boolean isUpgradeable, boolean isMovable,
			boolean isSplittable, boolean isStable) {
		super(x, y, width, height, owner, name, maxLevel, level, health, damage, range, isUpgradeable, isMovable, isSplittable,
				isStable);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(SpriteBatch batch, float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInRange() {
		// TODO Auto-generated method stub
		return false;
	}

}

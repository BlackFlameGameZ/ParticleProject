package com.blackflamegamez.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.blackflamegamez.game.GameCore;

public class DesktopLauncher 
{
	public static void main (String[] arg) 
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		System.out.println("Before LwjglApplication");
		config.width 	= 1280;//LwjglApplicationConfiguration.getDesktopDisplayMode().width;
		config.height 	= 800;//LwjglApplicationConfiguration.getDesktopDisplayMode().height;
		config.resizable= false;
		//config.fullscreen = true;
		new LwjglApplication(new GameCore(), config);
		System.out.println("After LwjglApplication");
	}
}

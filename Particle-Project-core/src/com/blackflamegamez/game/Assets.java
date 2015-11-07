package com.blackflamegamez.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets 
{
	public static AssetManager manager = new AssetManager();
	
	public static void load()
	{
		manager.load("images/particle/base.png", Texture.class);
		//komentar
		for(int i = 1 ; i < 6 ; i++)
		{
			manager.load("images/particle/atk_" + i + ".png", Texture.class);
			manager.load("images/particle/def_" + i + ".png", Texture.class);
		}
		
		manager.load("images/splash_#[625,493].png", Texture.class);
		manager.load("images/board_grid.png", Texture.class);
		for(int i = 1 ; i < 4 ; i++)
			manager.load("images/backgrounds/bg_" + i + ".png", Texture.class);
		manager.load("files/uiskin.json", Skin.class);
		manager.load("images/mm_screen/play_[975, 800].png", Texture.class);
		manager.load("images/mm_screen/play_pressed_[975, 800].png", Texture.class);
		manager.load("images/mm_screen/options_[975, 601].png", Texture.class);
		manager.load("images/mm_screen/options_pressed_[975, 601].png", Texture.class);
		manager.load("images/mm_screen/exit_[975, 402].png", Texture.class);
		manager.load("images/mm_screen/exit_pressed_[975, 402].png", Texture.class);
		manager.load("images/mm_screen/top_left.png", Texture.class);
		manager.load("images/mm_screen/top_right.png", Texture.class);
		manager.load("images/mm_screen/bottom_left.png", Texture.class);
		manager.load("images/mm_screen/bottom_right.png", Texture.class);
		manager.load("images/mm_screen/title_[870, 1027].png", Texture.class);
		manager.load("images/play_screen/title_[1024, 1054].png", Texture.class);
		manager.load("images/play_screen/back_[1332, 999].png", Texture.class);
		manager.load("images/play_screen/back_pressed_[1332, 999].png", Texture.class);
		manager.load("images/play_screen/bluetooth_[617, 999].png", Texture.class);
		manager.load("images/play_screen/bluetooth_pressed_[617, 999].png", Texture.class);
		manager.load("images/play_screen/online_[1332, 800].png", Texture.class);
		manager.load("images/play_screen/online_pressed_[1332, 800].png", Texture.class);
		manager.load("images/play_screen/single_player_[617, 800].png", Texture.class);
		manager.load("images/play_screen/single_player_pressed_[617, 800].png", Texture.class);
		manager.load("images/menu.png", Texture.class);
		manager.load("images/menu_pressed.png", Texture.class);
	}
	
	public static void setFilters()
	{
		manager.get("images/splash_#[625,493].png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/board_grid.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		for(int i = 1 ; i < 4 ; i++)
			manager.get("images/backgrounds/bg_" + i + ".png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/play_[975, 800].png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/play_pressed_[975, 800].png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/options_[975, 601].png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/options_pressed_[975, 601].png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/exit_[975, 402].png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/exit_pressed_[975, 402].png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/top_left.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/top_right.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/bottom_left.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/bottom_right.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/title_[870, 1027].png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/play_screen/title_[1024, 1054].png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/play_screen/back_[1332, 999].png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/play_screen/back_pressed_[1332, 999].png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/play_screen/bluetooth_[617, 999].png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/play_screen/bluetooth_pressed_[617, 999].png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/play_screen/online_[1332, 800].png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/play_screen/online_pressed_[1332, 800].png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/play_screen/single_player_[617, 800].png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/play_screen/single_player_pressed_[617, 800].png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/menu.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/menu_pressed.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
}

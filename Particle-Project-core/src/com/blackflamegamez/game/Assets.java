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
		for(int i = 1 ; i < 7 ; i++)
		{
			manager.load("images/backgrounds/bg_" + i + ".png", Texture.class);
			manager.load("images/backgrounds/bg_thumb_" + i + ".png", Texture.class);
		}
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
		manager.load("images/backgrounds/thumb_glow.png", Texture.class);
		manager.load("images/options_screen/backgrounds.png", Texture.class);
		manager.load("images/options_screen/title.png", Texture.class);
		manager.load("images/options_screen/music.png", Texture.class);
		manager.load("images/options_screen/sound.png", Texture.class);
		manager.load("images/left_upgrade_box.png", Texture.class);
		manager.load("images/atk_upgrade.png", Texture.class);
		manager.load("images/def_upgrade.png", Texture.class);
		manager.load("images/attack.png", Texture.class);
		manager.load("images/split.png", Texture.class);
		manager.load("images/green_highlight.png", Texture.class);
	}
	
	public static void setFilters()
	{
		manager.get("images/splash_#[625,493].png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/board_grid.png", Texture.class).setFilter(TextureFilter.Linear, TextureFilter.Linear);
		for(int i = 1 ; i < 7 ; i++)
		{
			manager.get("images/backgrounds/bg_" + i + ".png", Texture.class)					.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			manager.get("images/backgrounds/bg_thumb_" + i + ".png", Texture.class)				.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		manager.get("images/particle/base.png", Texture.class)									.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		//komentar
		for(int i = 1 ; i < 6 ; i++)
		{
			manager.get("images/particle/atk_" + i + ".png", Texture.class)						.setFilter(TextureFilter.Linear, TextureFilter.Linear);
			manager.get("images/particle/def_" + i + ".png", Texture.class)						.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
		manager.get("images/mm_screen/play_[975, 800].png", Texture.class)						.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/play_pressed_[975, 800].png", Texture.class)				.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/options_[975, 601].png", Texture.class)					.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/options_pressed_[975, 601].png", Texture.class)			.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/exit_[975, 402].png", Texture.class)						.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/exit_pressed_[975, 402].png", Texture.class)				.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/top_left.png", Texture.class)								.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/top_right.png", Texture.class)							.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/bottom_left.png", Texture.class)							.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/bottom_right.png", Texture.class)							.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/mm_screen/title_[870, 1027].png", Texture.class)					.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/play_screen/title_[1024, 1054].png", Texture.class)					.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/play_screen/back_[1332, 999].png", Texture.class)					.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/play_screen/back_pressed_[1332, 999].png", Texture.class)			.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/play_screen/bluetooth_[617, 999].png", Texture.class)				.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/play_screen/bluetooth_pressed_[617, 999].png", Texture.class)		.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/play_screen/online_[1332, 800].png", Texture.class)					.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/play_screen/online_pressed_[1332, 800].png", Texture.class)			.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/play_screen/single_player_[617, 800].png", Texture.class)			.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/play_screen/single_player_pressed_[617, 800].png", Texture.class)	.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/menu.png", Texture.class)											.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/menu_pressed.png", Texture.class)									.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/backgrounds/thumb_glow.png", Texture.class)							.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/options_screen/backgrounds.png", Texture.class)						.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/options_screen/title.png", Texture.class)							.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/options_screen/music.png", Texture.class)							.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/options_screen/sound.png", Texture.class)							.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/left_upgrade_box.png", Texture.class)								.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/atk_upgrade.png", Texture.class)									.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/def_upgrade.png", Texture.class)									.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/attack.png", Texture.class)											.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/split.png", Texture.class)											.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/green_highlight.png", Texture.class)											.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
}

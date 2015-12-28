package com.blackflamegamez.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * @author BlackFlame
 *
 * This class is used to load assets
 * 
 */
public class Assets 
{
	public static AssetManager manager = new AssetManager();
	
	public static void load()
	{
		for(int i = 1 ; i < 6 ; i++)
			manager.load("images/backgrounds/u_" + i + ".jpg", Texture.class);	// Background pictures
		
		manager.load("files/uiskin.json", Skin.class);							// UI skin file
		manager.load("fonts/box_title.fnt", BitmapFont.class);					// Font for box title
		manager.load("fonts/box_text.fnt", BitmapFont.class);					// Font for box text
		manager.load("images/splash_#[625,493].png", Texture.class);			// Splash screen sprite
		manager.load("images/main_menu/title.png", Texture.class);				// MainMenu title
		manager.load("images/main_menu/box_back.png", Texture.class);			// MainMenu massage box
		manager.load("images/main_menu/box_border.png", Texture.class);			// MainMenu massage box border
		manager.load("images/main_menu/btn_back.png", Texture.class);			// MainMenu button background
		manager.load("images/main_menu/btn_border.png", Texture.class);			// MainMenu button border
		manager.load("images/main_menu/btn_border_pressed.png", Texture.class);	// MainMenu button border pressed
		manager.load("images/main_menu/play_text.png", Texture.class);			// MainMenu button text image
		manager.load("images/main_menu/options_text.png", Texture.class);		// MainMenu button text image
		manager.load("images/main_menu/donate_text.png", Texture.class);		// MainMenu button text image
		manager.load("images/main_menu/quit_text.png", Texture.class);			// MainMenu button text image
		manager.load("images/play/versus_ai_text.png", Texture.class);			// MainMenu button text image
		manager.load("images/play/hot_seat_text.png", Texture.class);			// MainMenu button text image
		manager.load("images/play/online_text.png", Texture.class);				// MainMenu button text image
		manager.load("images/play/back_text.png", Texture.class);				// MainMenu button text image
		manager.load("images/game/side_bar/background.png", Texture.class);		// MainMenu button text image
		manager.load("images/game/side_bar/side_lines.png", Texture.class);		// MainMenu button text image
		manager.load("images/game/field.png" , Texture.class);
	}
	
	public static void setFilters()
	{
		for(int i = 1 ; i < 6 ; i++)
			manager.get("images/backgrounds/u_" + i + ".jpg", Texture.class) 	.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		manager.get("fonts/box_title.fnt", BitmapFont.class)					.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("fonts/box_text.fnt", BitmapFont.class)						.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/splash_#[625,493].png", Texture.class) 				.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/main_menu/title.png", Texture.class) 				.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/main_menu/box_back.png", Texture.class) 			.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/main_menu/box_border.png", Texture.class) 			.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/main_menu/btn_back.png", Texture.class) 			.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/main_menu/btn_border.png", Texture.class) 			.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/main_menu/btn_border_pressed.png", Texture.class) 	.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/main_menu/play_text.png", Texture.class)			.setFilter(TextureFilter.Linear, TextureFilter.Linear);			
		manager.get("images/main_menu/options_text.png", Texture.class)			.setFilter(TextureFilter.Linear, TextureFilter.Linear);		
		manager.get("images/main_menu/donate_text.png", Texture.class)			.setFilter(TextureFilter.Linear, TextureFilter.Linear);		
		manager.get("images/main_menu/quit_text.png", Texture.class)			.setFilter(TextureFilter.Linear, TextureFilter.Linear);	
		manager.get("images/play/versus_ai_text.png", Texture.class)			.setFilter(TextureFilter.Linear, TextureFilter.Linear);	
		manager.get("images/play/hot_seat_text.png", Texture.class)				.setFilter(TextureFilter.Linear, TextureFilter.Linear);	
		manager.get("images/play/online_text.png", Texture.class)				.setFilter(TextureFilter.Linear, TextureFilter.Linear);	
		manager.get("images/play/back_text.png", Texture.class)					.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/game/side_bar/background.png", Texture.class)		.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/game/side_bar/side_lines.png", Texture.class)		.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		manager.get("images/game/field.png" , Texture.class)                    .setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}
}

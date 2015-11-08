package com.blackflamegamez.game.uielements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MessageDialog extends GenericDialog {

	private String message;
	private GlyphLayout glyph;
	private BitmapFont  bmf;
	
	public MessageDialog(float screenX, float screenY, DialogDirection dir , String msg) {
		super(screenX, screenY , dir , 0.5f , true);
		message = msg;
		glyph = new GlyphLayout();
		bmf = new BitmapFont(Gdx.files.internal("fonts/armwrestler.fnt"));
		setText();
	}

	@Override
	public void drawDialogElements(SpriteBatch sb, float delta) 
	{
		bmf.draw(sb, glyph , x + 10f , y - 10f);
	}
	
	private void setText()
	{
		glyph.setText(bmf, message);
		setWorldCoordinates(x, y, glyph.width + 20f, glyph.height + 20f);
	}
}

package com.blackflamegamez.game.android;

import android.app.Activity;
import android.widget.Toast;

import com.blackflamegamez.game.interfaces.PBluetooth;

/**
 * @author Milan Topalovic
 *
 */
public class AndroidPBluetooth implements PBluetooth
{

	Activity activity;
	
	public AndroidPBluetooth(Activity a)
	{
		this.activity = a;
	}
	
	@Override
	public void debug() 
	{
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(activity , "Android " , Toast.LENGTH_LONG).show();
			}
		});
	}

}

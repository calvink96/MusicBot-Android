package com.kiwi9400.musicbot;

import android.content.Context;
import android.view.View;

public class PlayerView extends View {
	Player p;
	public PlayerView(Context c, Player pl){
		super(c);
		p = pl;
	}
}

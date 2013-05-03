package com.kiwi9400.musicbot;

import com.kiwi9400.musicbot.R;
import com.kiwi9400.musicbot.Dialogs.ChordDialogFragment;
import com.kiwi9400.musicbot.Dialogs.ScaleChordHelperInterface;
import com.kiwi9400.musicbot.Dialogs.HelpDialogFragment;
import com.kiwi9400.musicbot.Dialogs.ScaleDialogFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MusicBot extends FragmentActivity implements ScaleChordHelperInterface, playerDisabledListener {
	//acts as a manager - assigns reference numbers to Player instances
	Button scalebutton;
	Button chordsbutton;
	Button helpbutton;
	
	SeekBar volumeslider;
	
	ListView listview;
	int i = 0;
	PlayerAdapter players;
	int numdisabled = 0;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_bot);
        
        listview = (ListView)findViewById(R.id.player_list_view);
        
        players = new PlayerAdapter(this, R.layout.player_layout_view,R.id.playername);
        players.setNotifyOnChange(true);
        
        
        listview.setAdapter(players);
        
        scalebutton = (Button)findViewById(R.id.scalebutton);
        chordsbutton = (Button) findViewById(R.id.chordsbutton);
        helpbutton = (Button) findViewById(R.id.helpbutton);
        
        scalebutton.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		scaleClick();
        	}
        });
        chordsbutton.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		chordClick();
        	}
        });
        helpbutton.setOnClickListener(new OnClickListener(){
        	public void onClick(View v){
        		helpClick();
        	}
        });
    }
    public void scaleClick(){
    	ScaleDialogFragment d = new ScaleDialogFragment();
    	d.show(getSupportFragmentManager(), "ScaleDialog");
    }
    public void chordClick(){
    	ChordDialogFragment d = new ChordDialogFragment();
    	d.show(getSupportFragmentManager(), "ChordDialog");
    }
    public void helpClick(){
    	HelpDialogFragment d = new HelpDialogFragment();
    	d.show(getSupportFragmentManager(), "HelpDialog");
    }
    
    @Override
    public void manageSelections(int type, int temp, String choscal){
    	players.add(new Player(type, choscal, temp, i, getCacheDir()));
    	players.getItem(i-numdisabled).start();
    	i++;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.music_bot, menu);
        return true;
    }
    
    public void disablePlayer() {
		numdisabled++;
	}
    
}

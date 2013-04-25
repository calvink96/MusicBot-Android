package com.kiwi9400.musicbot;

import com.kiwi9400.musicbot.R;
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

public class MusicBot extends FragmentActivity implements ScaleChordHelperInterface {
	//acts as a manager - assigns reference numbers to Player instances
	Button scalebutton;
	Button chordsbutton;
	Button helpbutton;
	ListView listview;
	int i = 0;
	ArrayAdapter<Player> players;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_bot);
        
        Log.d("d", getCacheDir().toString());
        
        listview = (ListView)findViewById(R.id.player_list_view);
        
        players = new ArrayAdapter<Player>(this, R.layout.player_layout_view,R.id.playername);
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
    	Log.d("scalebutton", "blah");
    	ScaleDialogFragment d = new ScaleDialogFragment();
    	d.show(getSupportFragmentManager(), "ScaleDialog");
    }
    public void chordClick(){
    	Log.d("chordsbutton", "blahblah");
    }
    public void helpClick(){
    	HelpDialogFragment d = new HelpDialogFragment();
    	d.show(getSupportFragmentManager(), "HelpDialog");
    }
    
    @Override
    public void manageSelections(int type, int temp, String choscal){
    	Log.d("manage","managingselections");
    	players.add(new Player(type, choscal, temp, i, getCacheDir()));
    	players.getItem(i).start();
    	i++;
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.music_bot, menu);
        return true;
    }
    
}

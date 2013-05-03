package com.kiwi9400.musicbot;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class PlayerAdapter extends ArrayAdapter<Player> {
	ArrayList<Boolean> enabledisable = new ArrayList<Boolean>();
	
	public PlayerAdapter(Context context, int resid, int textViewResourceId) {
		super(context, resid, textViewResourceId);
		// TODO Auto-generated constructor stub
	}
	public void add(Player p){
		super.add(p);
	}
	public void disable(int i){
		getItem(i).stop();
		remove(getItem(i));
		((playerDisabledListener) getContext()).disablePlayer();
		notifyDataSetChanged();
	}
	public View getView(final int position, View convertView, ViewGroup parent){
		View v = View.inflate(getContext(), R.layout.player_layout_view, null);
		
		TextView tv = (TextView)v.findViewById(R.id.playername);
		tv.setText(getItem(position).toString());
		
		SeekBar volumeslider = (SeekBar)v.findViewById(R.id.volumeslider);
		volumeslider.setMax(100);
		volumeslider.setProgress(100);
		volumeslider.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				getItem(position).setVolume(seekBar.getProgress()/100, seekBar.getProgress()/100);
			}
		});
		
		Button pp = (Button)v.findViewById(R.id.playpause);
		pp.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View vie) {
				getItem(position).playpause();
			}
		});
		
		Button sto = (Button)v.findViewById(R.id.closeplayer);
		sto.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View vie){
				disable(position);
			}
		});
		
		return v;
		
	}
}

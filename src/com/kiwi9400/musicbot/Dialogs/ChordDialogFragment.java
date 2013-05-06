package com.kiwi9400.musicbot.Dialogs;

import com.kiwi9400.musicbot.ChordAdapter;
import com.kiwi9400.musicbot.ChordHelper;
import com.kiwi9400.musicbot.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

public class ChordDialogFragment extends DialogFragment {
	ScaleChordHelperInterface mbhelp;
	
	ListView lv;
	ChordAdapter c;
	TextView tv;
	
	int bartempo = 120;
	
	public ChordDialogFragment(){
		super();
		setStyle(STYLE_NO_TITLE, 0);
	}
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		mbhelp = (ScaleChordHelperInterface) activity; //ideally catch classcastexception, but I shouldn't do anything to cause this
		return;
	}
	
	public void passPlayer(){
		
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		LayoutInflater inf = getActivity().getLayoutInflater();
		
		View v = inf.inflate(R.layout.chord_dialog_layout, null);
		
		c = new ChordAdapter(getActivity(), R.layout.chord_dialog_layout);
		
		lv = (ListView)v.findViewById(R.id.chordslist);
		lv.setAdapter(c);
		c.setNotifyOnChange(true);
		
		Button add = (Button)v.findViewById(R.id.add_new_chord);
		add.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				c.add(new ChordHelper());
			}
			
		});
		
		tv = (TextView)v.findViewById(R.id.chord_tempo_disp);
		
		
		SeekBar sb = (SeekBar)v.findViewById(R.id.chord_tempo_seek);
		sb.setProgress(120);
		sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				bartempo = progress+1;
				tv.setText(""+(progress+1));
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});
		
		tv.setText(""+sb.getProgress());
		
		return new AlertDialog.Builder(getActivity())
		.setPositiveButton(R.string.ok,
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					passPlayer();
				}
			}
		)
		.setView(v)
	
		.create();
	}
}

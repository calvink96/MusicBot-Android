package com.kiwi9400.musicbot.Dialogs;

import com.kiwi9400.musicbot.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;

public class ScaleDialogFragment extends DialogFragment {
	ScaleChordHelperInterface mbhelp;
	int bartempo = 120;
	String sc = "C";
	boolean min = false;
	Spinner sp;
	CheckBox cb;
	SeekBar sb;
	
	public ScaleDialogFragment(){
		super();
		setStyle(STYLE_NO_TITLE, 0);
	}
	
	public void passPlayer(){
		String s = sc + (min?"m":"");
		mbhelp.manageSelections(0, bartempo, s);
	}
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		
		mbhelp = (ScaleChordHelperInterface) activity; //ideally catch classcastexception, but nothing I do should cause this
		return;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		LayoutInflater inf = getActivity().getLayoutInflater();
		
		View v = inf.inflate(R.layout.scale_dialog_layout, null);
		
		sp = (Spinner)v.findViewById(R.id.scale_dialog_spinner);
		
		sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				sc = (String)sp.getSelectedItem();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		
		cb = (CheckBox)v.findViewById(R.id.scale_dialog_minor_checkbox);
		cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				min = isChecked;
			}
		});
		sb = (SeekBar)v.findViewById(R.id.seekBar1);
		sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				bartempo = progress;
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});
		
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

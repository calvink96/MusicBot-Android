package com.kiwi9400.musicbot;

import com.kiwi9400.musicbot.ScaleChordUtils.NoteList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ChordAdapter extends ArrayAdapter<ChordHelper> {
	Spinner root;
	Spinner opts;
	
	public ChordAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
	}
	
	public View getView(final int position, View convertView, ViewGroup parent){
		View v = View.inflate(getContext(), R.layout.chord_list_item, null);
		
		
		root = (Spinner)v.findViewById(R.id.chord_select);
		root.setSelection(NoteList.getRootNoteFromRoot(getItem(position).root));
		root.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				getItem(position).root = (String)root.getSelectedItem();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		
		opts = (Spinner)v.findViewById(R.id.chord_options);
		String s = getItem(position).mods;
		int modifiers = 0; //following adapted from Chord.java
		if (s.contains("m7"))
			modifiers = 4;
		else if (s.contains("M7"))
			modifiers = 3;
		else if (s.contains("7"))
			modifiers = 2;
		else if (s.contains("m"))
			modifiers = 1;
		opts.setSelection(modifiers);
		opts.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				getItem(position).mods = (String)opts.getItemAtPosition(arg2);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		
		Button remove = (Button)v.findViewById(R.id.remove_chord);
		remove.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View vie){
				remove(getItem(position));
				notifyDataSetChanged();
			}
		});
		
		return v;
		
	}
}

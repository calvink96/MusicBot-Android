package com.kiwi9400.musicbot;

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
		
		getItem(position).root = "C";
		getItem(position).mods = "M";
		
		root = (Spinner)v.findViewById(R.id.chord_select);
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
		opts.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				getItem(position).mods = (String)opts.getSelectedItem();
				
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

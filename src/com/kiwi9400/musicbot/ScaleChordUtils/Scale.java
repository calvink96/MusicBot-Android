package com.kiwi9400.musicbot.ScaleChordUtils;


public class Scale extends NoteList {
	private int[] notes = new int[70];
	public Scale(String scal){
		super(scal);
		if (scal.contains("m"))
			modifiers = "m";
		else
			modifiers = "";
		for (int i=0;i<10;i+=1){ //set scale notes maj:WWHWWWH, min:WHWWHWW
			int as = 7*i;//notes shift
			int shift = rootnote+12*i;//sets octave
			notes[as] = 	shift;
			notes[as+1] = 	shift+2;
			notes[as+2] = 	modifiers=="m"?shift+3:shift+4;
			notes[as+3] =	shift+5;
			notes[as+4] =	shift+7;
			notes[as+5] =	modifiers=="m"?shift+8:shift+9;
			notes[as+6] =	modifiers=="m"?shift+10:shift+11;
		}
		for (int i=0;i<70;i+=1){
			if (notes[i]>127){
				notes[i] = rootnote+48; //nice reasonable note
			}
		}
	}
	public int[] getNotes(){
		return notes;
	}
}

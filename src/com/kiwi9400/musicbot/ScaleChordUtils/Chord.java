package com.kiwi9400.musicbot.ScaleChordUtils;


public class Chord extends NoteList {
	private int[] notes = new int[40];
	public Chord(String chor){
		super(chor);
		String modifiers;
		if (chor.contains("m7"))
			modifiers = "m7";
		else if (chor.contains("M7"))
			modifiers = "M7";
		else if (chor.contains("7"))
			modifiers = "7";
		else if (chor.contains("m"))
			modifiers = "m";
		else
			modifiers = "";
		for (int i=0;i<10;i+=1){
			int as = 4*i;
			int shift = 12*i+rootnote;
			notes[as] = shift;
			notes[as+1] = modifiers.contains("m")?shift+3:shift+4;
			notes[as+2] = shift+7;
			notes[as+3] = (!modifiers.contains("7"))?shift:(modifiers.contains("M")?shift+11:shift+10);
		}
		for (int i=0;i<40;i+=1){
			if (notes[i]>127){
				notes[i] = rootnote+36; //nice reasonable note
			}
		}
	}
	public int[] getNotes(){
		return notes;
	}
}

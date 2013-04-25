package com.kiwi9400.musicbot.ScaleChordUtils;



public class NoteList implements GetNotes {
	public int rootnote;
	public String root;
	public String modifiers;
	public int[] notes;
	public NoteList(String string){
		if (string.contains("#")||string.contains("b")){
			root = string.substring(0,2);
		}
		else{
			root = string.substring(0,1);
		}
		rootnote = getRootNoteFromRoot(root);
		modifiers = "";
	}
	public int getRootNoteFromRoot(String root){
		NoteEnum rootenum = NoteEnum.valueOf(root.replace("#", "S"));
		switch(rootenum){
		case C: 	return 0;
		case CS:
		case Db:	return 1;
		case D: 	return 2;
		case DS:
		case Eb:	return 3;
		case E: 	return 4;
		case F:	return 5;
		case FS:
		case Gb:	return 6;
		case G: 	return 7;
		case GS:
		case Ab:	return 8;
		case A: 	return 9;
		case AS:
		case Bb:	return 10;
		case B: 	return 11;
		}
		return -1;
	}
	public int[] getNotes(){
		return notes;
	}
	public enum NoteEnum {
		C,
		CS,
		Db,
		D,
		DS,
		Eb,
		E,
		F,
		FS,
		Gb,
		G,
		GS,
		Ab,
		A,
		AS,
		Bb,
		B
	}
}


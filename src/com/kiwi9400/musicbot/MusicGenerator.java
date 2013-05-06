package com.kiwi9400.musicbot;

import android.util.Log;

import com.kiwi9400.musicbot.ScaleChordUtils.Chord;
import com.kiwi9400.musicbot.ScaleChordUtils.GetNotes;
import com.kiwi9400.musicbot.ScaleChordUtils.Scale;
import com.leff.midi.*;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;
import com.leff.midi.event.meta.Tempo;
import com.leff.midi.event.meta.TimeSignature;

import java.util.ArrayList;
import java.util.Random;

public class MusicGenerator {
	
	//adapted from Contest1MusicBot
	
	private boolean noSolo = false;
	private String scal;
	private String chords;
	private int type;
	private int tempo;
	private int st = -1;
	private ArrayList<GetNotes> notelists = new ArrayList<GetNotes>();
	public MusicGenerator(int typ, String progscale, int temp){
		Log.d("generator",""+temp);
		type = typ;
		tempo = temp;
		if (type==0){
			scal = progscale;
			notelists.add(0, new Scale(scal));
		}
		if (type==1){
			chords = progscale;
			String[] cc = chords.split("\\s+");
			for (int i=0;i<cc.length;i++){
				notelists.add(i, new Chord(cc[i]));
			}
		}
	}
	MidiFile genFile(){
		MidiFile f = new MidiFile();
		MidiTrack tempostuff = new MidiTrack();
		TimeSignature time = new TimeSignature();
		time.setTimeSignature(4, 4, TimeSignature.DEFAULT_METER, TimeSignature.DEFAULT_DIVISION);
		Tempo temp = new Tempo();
		temp.setBpm(tempo);
		tempostuff.insertEvent(temp);
		tempostuff.insertEvent(time);
		MidiTrack tra = genSequence(16);
		f.addTrack(tempostuff);
		f.addTrack(tra);
		return f;
	}
	MidiTrack genSequence(int len){
		//len is length in ticks of the sequence for one chord or one scale
		//If there are n chords in notelists, it will generate n sequences of length len
		int[] notes;
		Random r = new Random();
		MidiTrack t = new MidiTrack();
		int c = 0;
		int d = 0;
		if (st==-1){
			st = notelists.get(0).getNotes().length/2;//a good starting note; root plus a few octaves, not too low
		}
		for (int k=0;k<notelists.size();k++){	//would've run into a bunch of issues without making it generate patterns for all chords at once
			notes = notelists.get(k).getNotes();
			if (!noSolo){
				while (c<((len)-4)){
					c = d;
					d+=r.nextInt(3)+1;
					t.insertEvent(new NoteOn(c*120, 1, notes[st], 120));
					t.insertEvent(new NoteOff(d*120, 1, notes[st], 120));
					st+= r.nextInt(5)-((st<5)?1:(st>notes.length-5)?3:2);//make it a little bit easier for the music to get out of the low end
					// had a tendency to get stuck playing low notes for a long time, also do the high notes
					if (st<0){ //we don't want any indexing errors 
						st = 0;
					}
					if (st>notes.length-1){
						st = notes.length-1;
					}
				}
				c = d;
				d = (k*len);
				t.insertEvent(new NoteOn(c*120, 1, notes[st], 110));
				t.insertEvent(new NoteOff(d*120, 1, notes[st], 110));
			}
			if (type==1){
				for (int i=0;i<len/4;i++){
					for (int j=0; j<4;j++){
						t.insertEvent(new NoteOn(((k*len)+4*i)*120, 1, notes[20+j], 100));
						t.insertEvent(new NoteOff(((k*len)+4*i+1)*120, 1, notes[20+j], 100));
					}
				}
				st-=st%4;//if it's a chord progression, make the next chord start on the root
				if (st<12){
					st=12;//prevent getting stuck in the low end
				}
			}
		}
		return t;
	}
}

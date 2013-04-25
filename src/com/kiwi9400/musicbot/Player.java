package com.kiwi9400.musicbot;

import java.io.File;
import java.io.FileInputStream;

import com.leff.midi.MidiFile;

import android.media.MediaPlayer;
import android.util.Log;

public class Player {
	MediaPlayer mp1 = new MediaPlayer();
	public int idnum; //number assigned by MusicBot, important for eliminating midi file confusion
	public MusicGenerator mGen;
	public int i = 0;
	public File cachedir;
	public infinitePlayListener PL = new infinitePlayListener();
	
	public class infinitePlayListener implements MediaPlayer.OnCompletionListener{
		@Override
		public void onCompletion(MediaPlayer mp) {
			System.out.println(mp1.isPlaying());
			mp1.release();
			mp1 = new MediaPlayer();
			MidiFile mf = genFile();
			File f = null;
			try{
				f = File.createTempFile( idnum+"__", ".mid", cachedir);
				mf.writeToFile(f);
				mp1.setDataSource((new FileInputStream(f)).getFD());
				mp1.prepare();
				mp1.start();
				mp1.setOnCompletionListener(PL);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public void start(){
		MidiFile mf = genFile();
		File f = null;
		try{
			f = File.createTempFile( idnum+"__", ".mid", cachedir);
			Log.d("d", f.toString());
			mf.writeToFile(f);
			mp1.setDataSource((new FileInputStream(f)).getFD());
			mp1.prepare();
			mp1.start();
			mp1.setOnCompletionListener(PL);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public MidiFile genFile(){
		return mGen.genFile();
	}
	public Player(int typ, String choscal, int temp, int num, File cd){
		idnum = num;
		mGen = new MusicGenerator(typ, choscal, temp);
		cachedir = cd;
	}
	public boolean isPlaying(){
		return mp1.isPlaying();
	}
	public void play(){
		try{
			mp1.start();
		}
		catch(Exception e){
			;
		}
	}
	public void pause(){
		try{
			mp1.pause();
		}
		catch(Exception e){
			;
		}
	}
	public void stop(){
		try{
			mp1.stop();
		}
		catch(Exception e){
			;
		}
	}
	@Override
	public String toString(){
		return "Player "+idnum;
	}
}

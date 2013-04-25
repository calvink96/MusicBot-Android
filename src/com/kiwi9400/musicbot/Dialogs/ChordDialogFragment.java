package com.kiwi9400.musicbot.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class ChordDialogFragment extends DialogFragment {
	ScaleChordHelperInterface mbhelp;
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		mbhelp = (ScaleChordHelperInterface) activity; //ideally catch classcastexception, but I shouldn't do anything to cause this
		return;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		return null;
	}
}

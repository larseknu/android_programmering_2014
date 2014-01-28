package com.capgemini.larseknu.beginningwithfragments;

import android.app.Activity;
import android.os.Bundle;

public class ButtonHandlingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.button_handling_activity);
	}
	
//	public void onClick(View view) {
//		FragmentManager fragmentManager = getFragmentManager();
//		ButtonHandlingFragment fragment = (ButtonHandlingFragment) fragmentManager.findFragmentById(R.id.buttonHandlingFragment);
//		
//		fragment.myButtonClickHandler(view);
//	}
}

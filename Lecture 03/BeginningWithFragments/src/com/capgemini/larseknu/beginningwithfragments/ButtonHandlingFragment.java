package com.capgemini.larseknu.beginningwithfragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ButtonHandlingFragment extends Fragment implements View.OnClickListener {
	private int _clickCount = 0;
	private TextView _textViewClickCount = null;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.button_handling_fragment, container, false);
		
		_textViewClickCount = (TextView) view.findViewById(R.id.textViewClickCount);
		
		Button pushMeButton = (Button) view.findViewById(R.id.buttonPushMe);
		pushMeButton.setOnClickListener(this);
		
		return view;
	}

	public void myButtonClickHandler(View view) {
		_textViewClickCount.setText("Click count: " + ++_clickCount);
	}
	
	@Override
	public void onClick(View view) {
		myButtonClickHandler(view);
	}
}

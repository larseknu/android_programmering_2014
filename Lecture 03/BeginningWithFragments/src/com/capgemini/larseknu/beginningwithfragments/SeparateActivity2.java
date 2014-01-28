package com.capgemini.larseknu.beginningwithfragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

public class SeparateActivity2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.separate_activity2);
		
		Intent startupIntent = getIntent();
		
		int movieDetailIndex = startupIntent.getIntExtra("movieDetailIndex", 0);
		
		FragmentManager fragmentManager = getFragmentManager();
		MovieDetailFragment movieDetailFragment = (MovieDetailFragment) fragmentManager.findFragmentById(R.id.movieDetailFragment);
		movieDetailFragment.setDesplayedDetail(movieDetailIndex);
	}
}


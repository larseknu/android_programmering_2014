package com.capgemini.larseknu.beginningwithfragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

public class SeparateActivity1 extends Activity implements MovieFragmentCoordinator {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.separate_activity1);
	}

	@Override
	public void onSelectedMovieChanged(int index) {
		FragmentManager fragmentManager = getFragmentManager();
		MovieDetailFragment movieDetailFragment = (MovieDetailFragment) fragmentManager.findFragmentById(R.id.movieDetailFragment);
		
		if(movieDetailFragment != null && movieDetailFragment.isVisible()) {
			movieDetailFragment.setDesplayedDetail(index);
		}
		else {
			Intent intent = new Intent(this, SeparateActivity2.class);
			intent.putExtra("movieDetailIndex", index);
			startActivity(intent);
		}
	}
}

package com.capgemini.larseknu.beginningwithfragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class FragmentedActivity extends Activity implements MovieFragmentCoordinator {

	@Override
	public void onCreate(Bundle savedInstanceBundle)
	{
		super.onCreate(savedInstanceBundle);
		
		setContentView(R.layout.fragmented_activity);
	}

	@Override
	public void onSelectedMovieChanged(int index) {
		FragmentManager fragmentManager = getFragmentManager();
		MovieDetailFragment movieDetailFragment = (MovieDetailFragment) fragmentManager.findFragmentById(R.id.movieDetailFragment);
		
		movieDetailFragment.setDesplayedDetail(index);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.separate_activities:
				startSeparateActivity();
				return true;
			case R.id.buttonHandlingActivity:
				startButtonHandlingActivity();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	private void startSeparateActivity() {
		Intent intent = new Intent(this, SeparateActivity1.class);
		startActivity(intent);
	}
	
	private void startButtonHandlingActivity() {
		Intent intent = new Intent(this, ButtonHandlingActivity.class);
		startActivity(intent);
	}
}

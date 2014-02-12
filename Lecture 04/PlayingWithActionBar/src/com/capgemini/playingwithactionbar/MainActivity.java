package com.capgemini.playingwithactionbar;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		
		return true;
	}
	
	public void onOption1Clicked(MenuItem menuItem) {
		Toast.makeText(this, "Option 1", Toast.LENGTH_LONG).show();
	}
	
	public void onOption2Clicked(MenuItem menuItem) {
		Toast.makeText(this, "Option 2", Toast.LENGTH_LONG).show();
	}
	
	public void onOption3Clicked(MenuItem menuItem) {
		Toast.makeText(this, "Option 3", Toast.LENGTH_LONG).show();
	}
	
	public void onExitClicked(MenuItem menuItem) {
		finish();
	}
}

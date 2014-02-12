package com.capgemini.playingwithactionbar;

import android.app.ActionBar;
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
		setActionBarLogo();
		Toast.makeText(this, "Option 1", Toast.LENGTH_LONG).show();
	}
	
	public void onOption2Clicked(MenuItem menuItem) {
		setActionBarTitleAndSubtitle();
		Toast.makeText(this, "Option 2", Toast.LENGTH_LONG).show();
	}
	
	public void onOption3Clicked(MenuItem menuItem) {
		hideTitleAndSubtitle();
		Toast.makeText(this, "Option 3", Toast.LENGTH_LONG).show();
	}
	
	public void onExitClicked(MenuItem menuItem) {
		finish();
	}
	
	public void setActionBarLogo() {
		ActionBar actionBar = getActionBar();
		actionBar.setLogo(R.drawable.capgemini_logo);
	}
	
	public void setActionBarTitleAndSubtitle() {
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Capgemini");
		actionBar.setSubtitle("People Matter. Results Count.");
	}
	
	public void hideTitleAndSubtitle() {
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
	}
}

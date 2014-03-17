package com.capgemini.playingwithsqlite;

import com.capgemini.playingwithsqlite.database.ShowDataSource;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;

public class ShowListActivity extends ListActivity {
	ShowDataSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		datasource = new ShowDataSource(this);
		datasource.open();
	}
	
	@Override
  	protected void onResume() {
		datasource.open();
    	super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}

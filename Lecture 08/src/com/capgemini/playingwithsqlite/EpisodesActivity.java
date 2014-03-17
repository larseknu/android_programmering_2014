package com.capgemini.playingwithsqlite;

import android.app.ListActivity;
import android.os.Bundle;

import com.capgemini.playingwithsqlite.database.ShowDataSource;

public class EpisodesActivity extends ListActivity {
	public static String SHOW_ID = "show id";
	public static String SHOW_TITLE = "show title";

	private ShowDataSource datasource;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_episodes);
		
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
}
